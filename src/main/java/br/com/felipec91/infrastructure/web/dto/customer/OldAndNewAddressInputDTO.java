package br.com.felipec91.infrastructure.web.dto.customer;

import br.com.felipec91.domain.model.customer.value_object.Address;
import jakarta.validation.Valid;

public record OldAndNewAddressInputDTO(@Valid Address oldAddress,@Valid Address newAddress) {
}
