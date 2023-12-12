package br.com.felipec91.domain.model.ticket.entity;

import br.com.felipec91.domain.model.concept.DomainEntityUUID;
import br.com.felipec91.domain.model.ticket.value_object.TicketMessage;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "ticket_communication")
public class TicketCommunication extends DomainEntityUUID {

    @ElementCollection
    @CollectionTable(name = "ticket_message", joinColumns = @JoinColumn(name = "ticket_communication_id"))
    private List<TicketMessage> messages;

    @OneToOne
    @MapsId
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    //    private Set<UserModel> subscribers;
    public TicketCommunication(){}

    public TicketCommunication(UUID id, List<TicketMessage> messages, Ticket ticket) {
        super(id);
        this.messages = messages;
        this.ticket = ticket;
    }

    public static TicketCommunication startCommunication(Ticket self) {
        return new TicketCommunication(null, new LinkedList<>(), self);
    }

    public List<TicketMessage> getMessage(TicketMessage anMessage) {
        if (Objects.isNull(messages))
            messages = new ArrayList<>();

        return Collections.unmodifiableList(messages);
    }

    public void addMessage(TicketMessage newMessage) {
        messages.add(newMessage);
    }
}
