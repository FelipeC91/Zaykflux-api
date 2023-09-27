package br.com.felipec91.domain.repository;

import br.com.felipec91.domain.model.service_desk.entity.ServiceDesk;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface ServiceDeskRepository extends PanacheRepositoryBase<ServiceDesk, UUID> {

    List<ServiceDesk> findByIdBatch(Collection<UUID> ids);
}
