package br.com.felipec91.infrastructure.web.dto.customer;

import br.com.felipec91.domain.model.customer.value_object.Contact;
import jakarta.validation.Valid;

public record OldAndNewContactInputDTO(@Valid Contact oldContact, @Valid  Contact newContact) {
}
