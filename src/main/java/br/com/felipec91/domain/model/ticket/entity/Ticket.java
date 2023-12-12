package br.com.felipec91.domain.model.ticket.entity;

import br.com.felipec91.domain.exception.InvalidAppointmentException;
import br.com.felipec91.domain.exception.TicketInvalidOperationException;
import br.com.felipec91.domain.model.concept.AggregateRoot;
import br.com.felipec91.domain.model.customer.entity.Customer;
import br.com.felipec91.domain.model.service_desk.entity.ServiceDesk;
import br.com.felipec91.domain.model.ticket.value_object.Appointment;
import br.com.felipec91.domain.model.ticket.value_object.Priority;
import br.com.felipec91.domain.model.ticket.value_object.TicketStatus;
import br.com.felipec91.domain.model.user.entity.Attendant;
import br.com.felipec91.domain.model.user.entity.Tenant;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.util.*;

@Entity
public class Ticket extends AggregateRoot {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long number;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @CreationTimestamp
    @Column(name = "opened_at")
    private OffsetDateTime openedAt;

    @CreationTimestamp
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @CreationTimestamp
    @Column(name = "closed_at")
    private OffsetDateTime closedAt;

    @ManyToOne
    @JoinColumn(name = "parent_ticket_id")
    private Ticket parentTicket;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "requester_user_model_id")
    private Tenant requester;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_desk_id")
    private ServiceDesk serviceDesk;


    @ElementCollection
    @CollectionTable(name = "appointment", joinColumns = @JoinColumn(name = "ticket_id"))
    private List<Appointment> appointments;



    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private TicketCommunication communication;

    @ManyToOne
    @JoinColumn(name = "attendant_user_model_id")
    private Attendant accountable;

    private String serviceCatalogTree;
    public Ticket() {
    }

    private Ticket(UUID id) {
        super(id);
    }

    private Ticket(UUID id, Long number, TicketStatus status, String title, String description, Priority priority, OffsetDateTime openedAt, OffsetDateTime updatedAt, OffsetDateTime closedAt, Ticket parentTicket, Customer customer, Tenant requester, ServiceDesk serviceDesk, String serviceCatalogTree, Attendant accountable,  List<Appointment> appointments, TicketCommunication ticketCommunication) {
        super(id);
        this.number = number;
        this.status = status;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.openedAt = openedAt;
        this.updatedAt = updatedAt;
        this.closedAt = closedAt;
        this.parentTicket = parentTicket;
        this.customer = customer;
        this.requester = requester;
        this.serviceDesk = serviceDesk;
        this.serviceCatalogTree = serviceCatalogTree;
        this.accountable = accountable;
        this.appointments = appointments;
        this.communication = ticketCommunication;
    }

    public static Ticket create(Long number, String title, String description, Priority priority, Ticket parentTicket, Customer customer, Tenant requester, ServiceDesk serviceDesk, String serviceCatalogTree) {

        return new Ticket(
                null,
                number,
                TicketStatus.ABERTO,
                title,
                description,
                priority,
                OffsetDateTime.now(),
                null,
                null,
                parentTicket,
                customer,
                requester,
                serviceDesk,
                serviceCatalogTree,
                null,
                null,
                null);
    }


    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void setStatus(TicketStatus status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }


    public String getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }


    public OffsetDateTime getOpenedAt() {
        return openedAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public OffsetDateTime getClosedAt() {
        return closedAt;
    }

    public Ticket getParentTicket() {
        return parentTicket;
    }

    public Customer getCustomer() {
        return customer;
    }


    public Tenant getRequester() {
        return requester;
    }

    public ServiceDesk getServiceDesk() {
        return serviceDesk;
    }

    public Attendant getAccountable() {
        return accountable;
    }

    public List<Appointment> getAppointments() {
        if (Objects.isNull(this.appointments))
            this.appointments = new ArrayList<>();

        return Collections.unmodifiableList(this.appointments);
    }

    public int attachAppointment(Appointment appointmentCandidate) {
        if (this.status != TicketStatus.ABERTO)
            throw new IllegalStateException("Ticket não pode receber apontamentos pois não está ABERTO");

        if (hasAppointmentCollision(appointmentCandidate))
            throw new InvalidAppointmentException("Atendente já possui apontamento vinculado a este Ticket neste mesmo período");

        this.appointments.add(appointmentCandidate);

        return appointments.indexOf(appointmentCandidate);
    }

    private boolean hasAppointmentCollision(Appointment candidate) {
        if (Objects.isNull(candidate))
            throw new InvalidAppointmentException("Apontamento não pode ser nulo");

        for (var attached : this.getAppointments()) {
            if (!attached.getAccountable().equals(candidate.getAccountable()))
                continue;

            if (!attached.getAppointmentDate().equals(candidate.getAppointmentDate()))
                continue;

            if (hasAppointmentPeriodCollision(attached, candidate))
                return true;
        }
        return false;
    }
    public boolean isCanceled() {
        return this.getStatus() == TicketStatus.CANCELADO;
    }

    public boolean isMerged() {
        return this.getStatus() == TicketStatus.CANCELADO_E_AGRUPADO;
    }

    private boolean hasAppointmentPeriodCollision(Appointment alreadyAttached, Appointment candidate) {
        return (alreadyAttached.getBeginningAt().equals( candidate.getBeginningAt() ) || alreadyAttached.getBeginningAt().isBefore( candidate.getBeginningAt() ))
                &&
                (alreadyAttached.getEndingAt().equals( candidate.getBeginningAt() ) || alreadyAttached.getEndingAt().isAfter( candidate.getBeginningAt() ))
                ||
                (alreadyAttached.getBeginningAt().equals( candidate.getEndingAt()) || alreadyAttached.getBeginningAt().isBefore( candidate.getEndingAt() ))
                &&
                (alreadyAttached.getEndingAt().equals( candidate.getEndingAt()) || alreadyAttached.getEndingAt().isAfter( candidate.getBeginningAt() ));

    }

    public TicketCommunication getCommunication() {
        if (Objects.isNull(communication))
            communication = TicketCommunication.startCommunication(this);

        return communication;
    }

    public void changeToMergedStatus() {
        this.status = TicketStatus.CANCELADO_E_AGRUPADO;
    }

    public void assignAccountable(Attendant accountableCandidate) {
        this.accountable = accountableCandidate;
    }

    public void addMergeDescription(String mainTicketNumber) {
        if (!this.isMerged())
                throw new TicketInvalidOperationException("Ticket se encontra em estado inválido para esta operação");

        var mergeMessage = """
                                    ====================================================
                                        Este Ticket foi agrupado ao ticket #%s e a continuidade do seu tratamento se dará por meio deste último.
                                                            
                                    ====================================================
                                    
                               """.formatted(mainTicketNumber);

        this.description = mergeMessage.concat(this.description);

    }

}