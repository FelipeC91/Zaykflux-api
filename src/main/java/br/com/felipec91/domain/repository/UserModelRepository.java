package br.com.felipec91.domain.repository;

import br.com.felipec91.domain.model.user.entity.Attendant;
import br.com.felipec91.domain.model.user.entity.Tenant;
import br.com.felipec91.domain.model.user.entity.UserModel;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import java.util.Optional;
import java.util.UUID;

public interface UserModelRepository extends PanacheRepositoryBase<UserModel, UUID> {

    Optional<Attendant> findAttendantByIdOptional(UUID attendantId);

    Optional<Tenant> findTenantByIdOptional(UUID attendantId);
}
