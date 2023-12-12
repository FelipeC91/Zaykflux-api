package br.com.felipec91.repository;

import br.com.felipec91.domain.model.service_desk.entity.ServiceDesk;
import br.com.felipec91.domain.repository.ServiceDeskRepository;
import br.com.felipec91.infrastructure.persistence.repositoryImpl.ServiceDeskPanacheRepositoryImpl;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

@QuarkusTest
class ServiceDeskPanacheRepositoryImplTest {

    @Inject
    ServiceDeskPanacheRepositoryImpl underTest;

    @Test
    void findByIdBatch() {
        var firstId = UUID.fromString("744daba3-a502-4cc0-b323-740c789a0b28");
        var secondId =UUID.fromString("3039802e-f12d-4f45-9fc0-a80f7b5aec43");
        var thirdId= UUID.fromString("b9458763-649c-41b4-962d-6f991542505a");

        var validServiceDeskUUIDBatch = List.of(
                                                firstId,
                                                secondId,
                                                thirdId
                                        );

        var serviceDeskList = underTest.findByIdBatch(validServiceDeskUUIDBatch);

        Assertions.assertNotNull(serviceDeskList);
        Assertions.assertInstanceOf(ServiceDesk.class, serviceDeskList.get(0));
        Assertions.assertEquals(firstId, serviceDeskList.get(0).getId());
        Assertions.assertEquals(secondId, serviceDeskList.get(1).getId());
        Assertions.assertEquals(thirdId, serviceDeskList.get(2).getId());
    }
}