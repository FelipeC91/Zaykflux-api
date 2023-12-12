package br.com.felipec91.domain.repository;

import br.com.felipec91.domain.model.group.entity.GroupModel;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface GroupModelRepository extends PanacheRepositoryBase<GroupModel, UUID> {

    List<GroupModel> findByIdBatch(Collection<UUID> ids);
}
