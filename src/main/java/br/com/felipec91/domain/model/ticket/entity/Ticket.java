package br.com.felipec91.domain.model.ticket.entity;

import br.com.felipec91.domain.model.customer.entity.Customer;
import br.com.felipec91.domain.model.concept.AggregateRoot;
import br.com.felipec91.domain.model.service_desk.entity.ServiceDesk;
import br.com.felipec91.domain.model.ticket.value_object.Priority;
import br.com.felipec91.domain.model.ticket.value_object.TicketStatus;
import br.com.felipec91.domain.model.ticket.value_object.Appointment;
import br.com.felipec91.domain.model.user.entity.UserModel;
import br.com.felipec91.domain.exception.InvalidAppointmentException;
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
    @JoinColumn(name = "user_model_id")
    private UserModel requester;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "service_desk_id")
    private ServiceDesk serviceDesk;


    @ElementCollection
    @CollectionTable(name = "appointment", joinColumns = @JoinColumn(name = "ticket_id"))
    private List<Appointment> appointments;

    private String serviceCatalogTree;


    @OneToOne
    @PrimaryKeyJoinColumn
    private TicketCommunication communication;

    public Ticket() {
    }

    private Ticket(UUID id) {
        super(id);
    }

    private Ticket(UUID id, Long number, TicketStatus status, String title, String description, Priority priority, OffsetDateTime openedAt, OffsetDateTime updatedAt, OffsetDateTime closedAt, Ticket parentTicket, Customer customer, UserModel requester, ServiceDesk serviceDesk, String serviceCatalogTree, List<Appointment> appointments, TicketCommunication ticketCommunication) {
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
        this.appointments = appointments;
        this.communication = ticketCommunication;
    }

    public static Ticket create(Long number, String title, String description, Priority priority, Ticket parentTicket, Customer customer, UserModel requester, ServiceDesk serviceDesk, String serviceCatalogTree) {

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


    public UserModel getRequester() {
        return requester;
    }

    public ServiceDesk getServiceDesk() {
        return serviceDesk;
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
        for (var attached : this.appointments) {
            if (!attached.getAccountable().equals(candidate.getAccountable()))
                continue;

            if (!attached.getAppointmentDate().equals(candidate.getAppointmentDate()))
                continue;

            if (hasAppointmentPeriodCollision(attached, candidate))
                return true;
        }
        return false;
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
            communication = TicketCommunication.create(this);

        return communication;
    }


}