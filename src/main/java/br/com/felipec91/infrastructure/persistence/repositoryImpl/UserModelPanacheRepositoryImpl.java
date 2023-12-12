package br.com.felipec91.infrastructure.persistence.repositoryImpl;

import br.com.felipec91.domain.model.user.entity.Attendant;
import br.com.felipec91.domain.model.user.entity.Tenant;
import br.com.felipec91.domain.repository.UserModelRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class UserModelPanacheRepositoryImpl implements UserModelRepository {


    public Optional<Attendant> findAttendantByIdOptional(UUID attendantId) {
        return  Optional.of( (Attendant) findById(attendantId) );
    }

    public Optional<Tenant> findTenantByIdOptional(UUID attendantId) {
        return  Optional.of( (Tenant) findById(attendantId) );
    }
}
