package br.com.felipec91.infrastructure.web.dto.ticket_message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@AllArgsConstructor
public class TicketMessageInputDTO {
    private OffsetDateTime sendAt;

    private String content;

    private UUID authorId;

    @Setter
    private UUID ticketId;

}
