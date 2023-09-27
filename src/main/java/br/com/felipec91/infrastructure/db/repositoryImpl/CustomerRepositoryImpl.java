package br.com.felipec91.infrastructure.db.repositoryImpl;

import br.com.felipec91.domain.model.customer.entity.Customer;
import br.com.felipec91.domain.repository.CustomerRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class CustomerRepositoryImpl implements CustomerRepository {


public List<Customer> findByTradingNameIgnoreCase(String nameFilter) {
        var formattedFilter = ('%' + nameFilter + "%").toUpperCase();

        return find("""
                            SELECT c FROM Customer AS c
                                WHERE UPPER(c.tradingName) LIKE :tradingName
                            """, Parameters.with("tradingName", formattedFilter)
                ).list();
    }


    public Optional<Customer> findByTradingNameIgnoreCaseOrCpfCnpjOptional(String tradingName, String cpfCnpj) {
        var formattedTradingName = ("%" + tradingName + "%").toUpperCase();

        return find("""
                        SELECT c FROM Customer AS c
                            WHERE UPPER(c.tradingName) LIKE :tradingName
                                OR c.cpfCnpj = :cpfCnpj
                         """, Parameters.with("tradingName", formattedTradingName).and("cpfCnpj", cpfCnpj)
            ).firstResultOptional();
    }



}