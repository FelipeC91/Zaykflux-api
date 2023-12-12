package br.com.felipec91.domain.model.service_desk.entity;

import br.com.felipec91.domain.model.concept.AggregateRoot;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "service_desk")
public class ServiceDesk extends AggregateRoot {

    private String name;

    private String description;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "service_desk_service_catalog",
            joinColumns = @JoinColumn(name = "service_desk_id"),
            inverseJoinColumns = @JoinColumn(name = "service_catalog_id")
    )
    private Set<ServiceCatalog> serviceCatalogSet = new HashSet<>();
    public ServiceDesk() {
    }

    public ServiceDesk(UUID id, String name, String description, Set<ServiceCatalog> serviceCatalogList) {
        super(id);
        this.name = name;
        this.description = description;
        this.serviceCatalogSet = serviceCatalogList;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

//    public Set<ServiceCatalog> getServiceCatalogSet() {
//        return serviceCatalogSet;
//    }
//
//    public void setServiceCatalogSet(Set<ServiceCatalog> serviceCatalogSet) {
//        this.serviceCatalogSet = serviceCatalogSet;
//    }

}
