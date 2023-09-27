package br.com.felipec91.infrastructure.web.dto.ticket;

import br.com.felipec91.domain.model.ticket.value_object.Priority;
import br.com.felipec91.domain.model.ticket.value_object.TicketStatus;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.UUID;
@JsonInclude(JsonInclude.Include.NON_NULL)
public record TicketProfileOutputDTO(UUID id, Long number, String title, String description, Priority priority, TicketStatus status, UUID requesterId, String requesterName, UUID customerId, String customerTradingName) {
}
