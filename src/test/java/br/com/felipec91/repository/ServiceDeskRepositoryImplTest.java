package br.com.felipec91.repository;

import br.com.felipec91.domain.repository.ServiceDeskRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ServiceDeskRepositoryImplTest {

    @Inject
    ServiceDeskRepository underTest;

    @Test
    void findByIdBatch() {
        var firstId = UUID.fromString("d80cf336-d28c-4067-a56b-8fe9f7af99b5");
        var secondId = UUID.fromString("744daba3-a502-4cc0-b323-740c789a0b28");
        var thirdId =UUID.fromString("3039802e-f12d-4f45-9fc0-a80f7b5aec43");

        var validServiceDeskUUIDBatch = List.of(
                                                firstId,
                                                secondId,
                                                thirdId
                                        );

        var serviceDeskList = underTest.findByIdBatch(validServiceDeskUUIDBatch);

        Assertions.assertNotNull(serviceDeskList);
        Assertions.assertEquals(firstId, serviceDeskList.get(0).getId());
        Assertions.assertEquals(secondId, serviceDeskList.get(1).getId());
        Assertions.assertEquals(thirdId, serviceDeskList.get(2).getId());
    }
}