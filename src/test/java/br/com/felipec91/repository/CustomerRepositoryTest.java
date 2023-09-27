package br.com.felipec91.repository;

import br.com.felipec91.domain.repository.CustomerRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
class CustomerRepositoryTest {

    @Inject
    CustomerRepository underTest;

    @Test
    void givenAnValidCustomerName_whenFindByTradingName_findIt() {
        var alreadySavedCustomerName = "Açai"; //already saved: AÇAILAND

        var resultList = underTest.findByTradingNameIgnoreCase(alreadySavedCustomerName);

        Assertions.assertNotNull(resultList.get(0));
        Assertions.assertTrue(resultList.get(0).getTradingName().contains(alreadySavedCustomerName.toUpperCase()));

    }

    @Test
    void givenAnValidCpfCnpj_whenFindByTradingNameOrCpfCnpjOptional_findIt() {
        var validCpfCnpj = "45181657000130"; //already saved: AÇAILAND
        var alreadySavedCustomerName = "Açai"; //already saved: AÇAILAND

        var customerOptional = underTest.findByTradingNameIgnoreCaseOrCpfCnpjOptional("", validCpfCnpj);

        Assertions.assertTrue(customerOptional.isPresent());
        Assertions.assertTrue(customerOptional.get().getTradingName().contains(alreadySavedCustomerName.toUpperCase()));
        Assertions.assertEquals(validCpfCnpj, customerOptional.get().getCpfCnpj());
    }
}