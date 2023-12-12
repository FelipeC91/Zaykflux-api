package br.com.felipec91.repository;

import br.com.felipec91.domain.model.user.entity.Attendant;
import br.com.felipec91.domain.model.user.entity.Tenant;
import br.com.felipec91.infrastructure.persistence.repositoryImpl.UserModelPanacheRepositoryImpl;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

@QuarkusTest
public class UserModelPanacheRepositoryTest {

    @Inject
    UserModelPanacheRepositoryImpl underTest;

    @Test
    public void givenAnValidUUID_whenFindTenantByID_thenShouldFindIt() {
        var validTenantId = UUID.fromString("a257fdad-672f-49bb-b6cd-1effe7b678a4");

        var tenantOptional = underTest.findTenantByIdOptional(validTenantId);

        Assertions.assertTrue(tenantOptional.isPresent());
        Assertions.assertInstanceOf(Tenant.class, tenantOptional.get());
        Assertions.assertEquals("Heitor", tenantOptional.get().getName());
    }

    @Test
    public void givenAnValidUUID_whenFindAttendantByID_thenShouldFindIt() {
        var validTenantId = UUID.fromString("2665c568-abf6-458e-8801-658fa16ae552");

        var attendantOptional = underTest.findAttendantByIdOptional(validTenantId);

        Assertions.assertTrue(attendantOptional.isPresent());
        Assertions.assertInstanceOf(Attendant.class, attendantOptional.get());
        Assertions.assertEquals("Felipe", attendantOptional.get().getName());
    }


}
