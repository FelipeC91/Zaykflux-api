package br.com.felipec91.domain.repository;

import br.com.felipec91.domain.model.customer.entity.Customer;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends PanacheRepositoryBase<Customer, UUID> {

    Optional<Customer> findByTradingNameIgnoreCaseOrCpfCnpjOptional(String tradingName, String cpfCnpj);

    List<Customer> findByTradingNameIgnoreCase(String nameFilter);
}
