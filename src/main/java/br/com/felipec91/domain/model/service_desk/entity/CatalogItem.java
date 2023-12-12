package br.com.felipec91.domain.model.service_desk.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "catalog_item")
public class CatalogItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "catalog_area_id")
    private CatalogArea catalogArea;

    public CatalogItem() {
    }

    public CatalogItem(Long id, String name, CatalogArea catalogArea) {
        this.id = id;
        this.name = name;
        this.catalogArea = catalogArea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatalogItem that = (CatalogItem) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
