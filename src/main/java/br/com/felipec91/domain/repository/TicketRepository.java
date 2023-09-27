package br.com.felipec91.domain.repository;

import br.com.felipec91.domain.model.ticket.entity.Ticket;
import br.com.felipec91.domain.model.ticket.value_object.Appointment;
import br.com.felipec91.domain.repository.filter.TicketAdvancedSearchQueryFilter;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TicketRepository extends PanacheRepositoryBase<Ticket, UUID> {

    List<Ticket> findByNumber(Long number);

    List<Ticket> findByTitleOrCustomerTradingName(String searchKey);

    List<Ticket> findByCriteriaFilter(TicketAdvancedSearchQueryFilter filter);

    Long findByAppointment_AccountableAnd_DateAnd_BeginningAtLessThanEqualAnd_EndingAtGreaterThanEqual(Appointment appointment);


}
