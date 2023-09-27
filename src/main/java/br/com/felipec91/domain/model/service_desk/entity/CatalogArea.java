package br.com.felipec91.domain.model.service_desk.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "catalog_area")
public class CatalogArea {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<CatalogItem> catalogItemSet;


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "service_catalog_id")
    private ServiceCatalog serviceCatalog;

    public CatalogArea() {
    }

    public CatalogArea(Long id, String nome, ServiceCatalog serviceCatalog) {
        this.id = id;
        this.name = nome;
        this.serviceCatalog = serviceCatalog;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return name;
    }

    public ServiceCatalog getServiceCatalog() {
        return serviceCatalog;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatalogArea that = (CatalogArea) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
