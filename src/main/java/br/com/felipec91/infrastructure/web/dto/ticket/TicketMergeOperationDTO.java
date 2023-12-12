package br.com.felipec91.infrastructure.web.dto.ticket;

import java.util.UUID;

public record TicketMergeOperationDTO(UUID main, UUID mergeWith) {
}
