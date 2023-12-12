package br.com.felipec91.domain.model.ticket.value_object;

import br.com.felipec91.domain.model.user.entity.UserModel;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.Objects;

@Embeddable
@Table(name = "ticket_message")
public class TicketMessage {

    @Column(name = "send_at")
    private OffsetDateTime sendAt;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "author_user_model")
    private UserModel author;

    public TicketMessage(){}

    public TicketMessage(OffsetDateTime sendAt, String content, UserModel author) {
        this.sendAt = sendAt;
        this.content = content;
        this.author = author;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketMessage that = (TicketMessage) o;
        return Objects.equals(sendAt, that.sendAt) && Objects.equals(content, that.content) && Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sendAt, content, author);
    }
}
