package br.com.felipec91.infrastructure.db.repositoryImpl;

import br.com.felipec91.domain.model.group.entity.GroupModel;
import br.com.felipec91.domain.repository.GroupModelRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class GroupModelRepositoryImpl implements GroupModelRepository {

    public List<GroupModel> findByIdBatch(Collection<UUID> ids) {
        return find(" SELECT g FROM GroupModel AS g WHERE g.id IN( ?1 )", ids)
                .list();
    }
}
