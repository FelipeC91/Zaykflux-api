package br.com.felipec91.infrastructure.persistence.repositoryImpl;

import br.com.felipec91.domain.model.customer.entity.Customer;
import br.com.felipec91.domain.model.service_desk.entity.ServiceDesk;
import br.com.felipec91.domain.model.ticket.entity.Ticket;
import br.com.felipec91.domain.model.ticket.value_object.Appointment;
import br.com.felipec91.domain.model.ticket.value_object.TicketStatus;
import br.com.felipec91.domain.model.user.entity.Tenant;
import br.com.felipec91.domain.repository.TicketRepository;
import br.com.felipec91.domain.repository.filter.TicketAdvancedSearchQueryFilter;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class TicketPanacheRepositoryImpl implements TicketRepository {

    @PersistenceContext
    EntityManager entityManager;

    private TicketAdvancedSearchQueryFilter filter;

    public List<Ticket> findByNumber(Long number) {

        return find("""
                                SELECT t FROM Ticket AS t
                                    WHERE t.number = :number
                            """,
                            Parameters.with("number", number)
                ).list();
    }

    public List<Ticket> findByTitleOrCustomerTradingName(String searchKey) {
        var searchKeyUpper = "%" + searchKey.toUpperCase() + "%";

        return find("""
                            SELECT t FROM Ticket AS t INNER JOIN Customer AS c ON t.customer.id = c.id
                                    WHERE UPPER(t.title) LIKE :title
                                        OR UPPER(t.customer.tradingName) LIKE :tradingName
                        """,
                    Parameters.with("title", searchKeyUpper)
                                .and("tradingName", searchKeyUpper)
                ).list();
    }

    public Long findByAppointment_AccountableAnd_DateAnd_BeginningAtLessThanEqualAnd_EndingAtGreaterThanEqual(Appointment anAppointment) {
        return find("""
                            SELECT t FROM Ticket AS t JOIN t.appointments AS a
                                    WHERE a.accountable.id = :accountableId
                                        AND a.appointmentDate = :appointmentDate
                                        AND :beginningAt BETWEEN a.beginningAt AND a.endingAt
                                        OR :endingAt BETWEEN a.beginningAt AND a.endingAt
                        """,
                        Parameters.with("accountableId", anAppointment.getAccountable().getId())
                                .and("appointmentDate", anAppointment.getAppointmentDate())
                                .and("beginningAt", anAppointment.getBeginningAt())
                                .and("endingAt", anAppointment.getEndingAt())
                ).count();
    }

//    public Boolean findExistsByAppointment_AccountableAnd_DateAnd_BeginningAtLessThanEqualAnd_EndingAtGreaterThanEqual(Appointment anAppointment) {
//        return entityManager.createQuery("""
//                            SELECT
//                                    CASE WHEN COUNT( a ) > 0
//                                        THEN true
//                                        ELSE false
//                                    END
//                        FROM  appointment AS a
//                            WHERE a.user_model_id = :accountableId
//                            AND a.appointment_date = :appointmentDate
//                            AND :beginningAt BETWEEN a.beginning_at AND a.ending_at
//                            OR :endingAt BETWEEN a.beginning_at AND a.ending_at
//                    """, Boolean.class)
//                    .setParameter("accountableId", anAppointment.getAccountable().getId())
//                    .setParameter("appointmentDate", anAppointment.getAppointmentDate())
//                    .setParameter("beginningAt", anAppointment.getBeginningAt())
//                    .setParameter("endingAt", anAppointment.getEndingAt())
//                    .getSingleResult();
//    }

    public List<Ticket> findByCriteriaFilter(TicketAdvancedSearchQueryFilter  filter) {
        this.filter = filter;

        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteriaQuery = criteriaBuilder.createQuery(Ticket.class);
        var criteriaRoot = criteriaQuery.from(Ticket.class);

        var predicatesArray = resolvePredicates(criteriaBuilder, criteriaRoot);

        return entityManager.createQuery(criteriaQuery.where(predicatesArray))
                .getResultList();
    }

    private Predicate[] resolvePredicates(final CriteriaBuilder builder, final Root<Ticket> fromTicket) {
        var predicates = new ArrayList<Predicate>();

        if (filter.hasAValidDateParameter()) {
            switch (filter.getDateContext()) {
                case ABERTURA -> {
                    predicates.add(builder.between(fromTicket.<OffsetDateTime>get("createdAt"), filter.getSince(), filter.getOrGenerateUntil()));
                }

                case FECHAMENTO -> {
                    predicates.add(builder.between(fromTicket.<OffsetDateTime>get("closedAt"), filter.getSince(), filter.getOrGenerateUntil()));
                }
            }
        }

        if (filter.hasANumber())
            predicates.add(builder.equal(fromTicket.get("number").as(Long.class), filter.getNumber()));

        if (filter.hasATitle())
            predicates.add(builder.like(fromTicket.get("title"), "%" + filter.getNumber() + "%"));

        if (filter.hasAStatus())
            predicates.add(builder.equal(fromTicket.<TicketStatus>get("status"), filter.getStatus()));

        if (filter.hasAnyCustomer()) {
            Join<Ticket, Customer> joinCustomer = fromTicket.join("customer");

            var numberOfNamesToCompare = filter.getCustomersTradingNameSet().size();
            var customerTradingNameComparations = new Predicate[numberOfNamesToCompare];

            var index = 0;

            for (var customerTradingName : filter.getCustomersTradingNameSet()) {
                var predicate = builder.equal(joinCustomer.<String>get("tradingName"), customerTradingName);

                customerTradingNameComparations[index] = predicate;

                index++;
            }

            predicates.add(builder.or(customerTradingNameComparations));
        }

        if (filter.hasRequester()) {
            Join<Ticket, Tenant> joinUserModel = fromTicket.join("requester");

            predicates.add(builder.like(joinUserModel.get("name"), "%" + filter.getRequesterName() + "%"));
        }

        if (filter.hasAnyServiceDesk()) {
            final Join<Ticket, ServiceDesk> joinServiceDesk = fromTicket.join("serviceDesk");

            var numberOfNamesToCompare = filter.getServiceDeskNames().size();
            var serviceDeskNameComparations = new Predicate[numberOfNamesToCompare];

            var index = 0;

            for (var serviceDeskName : filter.getServiceDeskNames()) {
                var predicate = builder.equal(joinServiceDesk.<String>get("name"), serviceDeskName);

                serviceDeskNameComparations[index] = predicate;

                index++;
            }

            predicates.add(builder.or(serviceDeskNameComparations));

        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }

}
