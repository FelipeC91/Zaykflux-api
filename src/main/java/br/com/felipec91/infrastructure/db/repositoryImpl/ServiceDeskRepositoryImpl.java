package br.com.felipec91.infrastructure.db.repositoryImpl;

import br.com.felipec91.domain.model.service_desk.entity.ServiceDesk;
import br.com.felipec91.domain.repository.ServiceDeskRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ServiceDeskRepositoryImpl implements ServiceDeskRepository{

    public List<ServiceDesk> findByIdBatch(Collection<UUID> ids) {
        return find("""
                            SELECT sd FROM ServiceDesk AS sd
                                WHERE sd.id IN( ?1 )
                            """, ids
            ).list();
    }
}
