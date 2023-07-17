package model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "area_catalogo")
public class AreaCatalogo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;


    @OneToMany(cascade = CascadeType.ALL)
    private Set<ItemCatalogo> itemCatalogoSet = new HashSet<>();













    public AreaCatalogo() {
    }





    public AreaCatalogo(Long id, String nome, Set<ItemCatalogo> itemCatalogoSet) {
        this.id = id;
        this.nome = nome;
        this.itemCatalogoSet = itemCatalogoSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<ItemCatalogo> getItemCatalogoSet() {
        return itemCatalogoSet;
    }

    public void setItemCatalogoSet(Set<ItemCatalogo> itemCatalogoSet) {
        this.itemCatalogoSet = itemCatalogoSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AreaCatalogo that = (AreaCatalogo) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
