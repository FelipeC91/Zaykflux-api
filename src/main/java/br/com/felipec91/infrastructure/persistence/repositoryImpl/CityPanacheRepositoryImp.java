package br.com.felipec91.infrastructure.persistence.repositoryImpl;

import br.com.felipec91.domain.model.city.entity.City;
import br.com.felipec91.domain.repository.CityRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CityPanacheRepositoryImp implements CityRepository {

    public City findByName(String name) {
        return find("""
                            SELECT c FROM City AS c
                                WHERE UPPER( c.name ) = UPPER( :name )
                            """, Parameters.with("name", name)
            ).firstResult();
    }
}
