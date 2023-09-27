package br.com.felipec91.infrastructure.db.repositoryImpl;

import br.com.felipec91.domain.model.city.entity.City;
import br.com.felipec91.domain.repository.CityRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CityRepositoryImp implements CityRepository {

    public City findByName(String name) {
        return find("""
                            SELECT c FROM City AS c
                                WHERE UPPER( c.name ) = UPPER( :name )
                            """, Parameters.with("name", name)
            ).firstResult();
    }
}
