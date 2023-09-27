package br.com.felipec91.repository;

import br.com.felipec91.domain.repository.CityRepository;
import br.com.felipec91.infrastructure.db.repositoryImpl.CityRepositoryImp;
import io.quarkus.test.junit.QuarkusIntegrationTest;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CityRepositoryTest {

    @Inject
    CityRepository underTest;

    @Test
    public void givenCity_whenValid_shouldSaveIt() {
        var alreadySavedCityName = "Cotia";

        var result = underTest.findByName(alreadySavedCityName);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getName(), alreadySavedCityName);
    }
}
