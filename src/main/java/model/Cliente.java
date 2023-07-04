package model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Boolean ativo;

    private String nomeFantasia;

    private String razaoSocial;

    private String cpfCnpj;

    private String fotoUrl;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Contato> contatos;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> endereco;
//
//    private Usuario usuarioCliente;

//    @ManyToMany
//    private Set<MesaServico> mesaServico;
//


    public Cliente() {
    }

    public Cliente(Long id, Boolean ativo, String nomeFantasia, String razaoSocial, String cpfCnpj, String fotoUrl, List<Contato> contatos, List<Endereco> endereco) {
        this.id = id;
        this.ativo = ativo;
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cpfCnpj = cpfCnpj;
        this.fotoUrl = fotoUrl;
        this.contatos = contatos;
        this.endereco = endereco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public List<Endereco> getEndereco() {
        return endereco;
    }

    public void setEndereco(List<Endereco> endereco) {
        this.endereco = endereco;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) && Objects.equals(ativo, cliente.ativo) && Objects.equals(nomeFantasia, cliente.nomeFantasia) && Objects.equals(razaoSocial, cliente.razaoSocial) && Objects.equals(cpfCnpj, cliente.cpfCnpj) && Objects.equals(fotoUrl, cliente.fotoUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ativo, nomeFantasia, razaoSocial, cpfCnpj, fotoUrl);
    }
}
