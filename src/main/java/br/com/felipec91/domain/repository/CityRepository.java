package br.com.felipec91.domain.repository;

import br.com.felipec91.domain.model.city.entity.City;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import java.util.UUID;

public interface CityRepository extends PanacheRepositoryBase<City, UUID> {

    City findByName(String name);
}
