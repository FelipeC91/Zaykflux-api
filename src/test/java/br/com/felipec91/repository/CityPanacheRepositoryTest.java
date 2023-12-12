package br.com.felipec91.repository;

import br.com.felipec91.infrastructure.persistence.repositoryImpl.CityPanacheRepositoryImp;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CityPanacheRepositoryTest {

    @Inject
    CityPanacheRepositoryImp underTest;

    @Test
    public void givenCity_whenValid_shouldSaveIt() {
        var alreadySavedCityName = "Carapicuípa";

        var result = underTest.findByName(alreadySavedCityName);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getName(), alreadySavedCityName);
    }
}
