package br.com.felipec91.infrastructure.web.dto.customer;

import br.com.felipec91.domain.model.customer.value_object.Address;
import br.com.felipec91.domain.model.customer.value_object.Contact;

import java.util.Set;
import java.util.UUID;

public record CustomerInputDTO(
        String tradingName,
        String companyName,
        String cpfCnpj,
        Set<Contact> contacts,
        Set<Address> addresses,
        Set<UUID> allawedServiceDesks,
        Set<UUID> attendantGroups
) {
}
