package br.com.felipec91.infrastructure.persistence.repositoryImpl;

import br.com.felipec91.domain.model.group.entity.GroupModel;
import br.com.felipec91.domain.repository.GroupModelRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class GroupModelPanacheRepositoryImpl implements GroupModelRepository {

    public List<GroupModel> findByIdBatch(Collection<UUID> ids) {
        return find(" SELECT g FROM GroupModel AS g WHERE g.id IN( ?1 )", ids)
                .list();
    }
}
