package model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "mesa_servico")
public class MesaServico {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "mesa_servico", cascade = CascadeType.ALL)
    private Set<AreaCatalogo> areaCatalogoSet = new HashSet<>();








    public MesaServico() {
    }

    public MesaServico(Long id, String nome, Set<AreaCatalogo> areaCatalogoSet) {
        this.id = id;
        this.nome = nome;
        this.areaCatalogoSet = areaCatalogoSet;
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

    public Set<AreaCatalogo> getAreaCatalogoSet() {
        return areaCatalogoSet;
    }

    public void setAreaCatalogoSet(Set<AreaCatalogo> areaCatalogoSet) {
        this.areaCatalogoSet = areaCatalogoSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MesaServico that = (MesaServico) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
