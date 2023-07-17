package model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;
    private String email;
    private Boolean ativo;

    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;

    @JsonIgnore
    private String senha;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "usuario_grupo",
            joinColumns = {@JoinColumn(name = "usuario_id")},
            inverseJoinColumns = {@JoinColumn(name = "grupo_id")}
    )
    private Set<Grupo>  grupos = new HashSet<>();










    public Usuario(Long id, String nome, String email, Boolean ativo, LocalDate dataCadastro, Set<Grupo> grupos) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.ativo = ativo;
        this.dataCadastro = dataCadastro;
        this.grupos = grupos;
    }


    public Usuario() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

//    public Set<Grupo> getGrupos() {
//        return grupos;
//    }
//
//    public void setGrupos(Set<Grupo> grupos) {
//        this.grupos = grupos;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
