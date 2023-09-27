package br.com.felipec91.infrastructure.web.dto.customer;

import java.util.UUID;

public record CustomerBasicInfoOutputDTO(UUID id, String tradingName, Boolean active) {
}
