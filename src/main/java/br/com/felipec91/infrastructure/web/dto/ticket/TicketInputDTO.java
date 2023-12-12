package br.com.felipec91.infrastructure.web.dto.ticket;

import br.com.felipec91.domain.model.ticket.value_object.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record TicketInputDTO(

        @NotNull
        UUID customerId,

        @NotNull
        UUID requesterId,

        @NotBlank
        String title,

        @NotBlank
        String description,
        Priority priority,
        UUID parentTicketId,

        @NotNull
        UUID serviceDeskId,

        @NotNull
        String serviceCatalogTree
) {}
