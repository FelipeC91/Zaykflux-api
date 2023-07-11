package model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;

    @ManyToMany
    @JoinTable(name = "grupo_permissao", joinColumns =  @JoinColumn(name = "grupo_id"), inverseJoinColumns = @JoinColumn(name = "permissao_id"))
    private Set<Permissao> permissoes = new HashSet<>();









    public Grupo(Long id, String nome, Set<Permissao> permissoes) {
        this.id = id;
        this.nome = nome;
        this.permissoes = permissoes;
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

    public Set<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(Set<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Grupo grupo = (Grupo) o;
        return Objects.equals(id, grupo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
