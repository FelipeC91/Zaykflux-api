package br.com.felipec91.domain.model.service_desk.entity;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "service_catalog")
public class ServiceCatalog {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<CatalogArea> catalogAreas;




    public ServiceCatalog() {
    }

    public ServiceCatalog(Long id, String name) {
        this.id = id;
        this.name = name;
//        this.catalogAreaSet = catalogAreaSet;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceCatalog that = (ServiceCatalog) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
