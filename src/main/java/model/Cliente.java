package model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.*;

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Boolean ativo;

    @NotNull(message = "Campo Nome Fantasia deve ser preenchido")
    @Column(name = "nome_fantasia")
    private String nomeFantasia;

    @NotNull(message = "Campo Razão Social deve ser preenchido")

    @Column(name = "razao_social")
    private String razaoSocial;

    @NotNull(message = "Campo CPF/CNPJ deve ser preenchido")
    @Pattern(regexp = "\\d{11,14}", message = "Formato inválido de CPF/CNPJ")
    @Column(name = "cpf_cnpj")
    private String cpfCnpj;

    @Column(name = "foto_url")
    private String fotoUrl;

    @JsonManagedReference
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Contato> contatos = new ArrayList<>();


    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "cliente_usuario",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private Set<Usuario> usuarios = new HashSet<>();


    @ManyToMany
    @JoinTable(
            name = "cliente_mesa_servico",
            joinColumns = @JoinColumn(name = "cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "mesa_servico_id")
    )
    private Set<MesaServico> mesaServicoSet = new HashSet<>();



    public Cliente() {
    }

    public Cliente(Long id, Boolean ativo, @NotNull(message = "Campo Nome Fantasia deve ser preenchido") String nomeFantasia, @NotNull(message = "Campo Razão Social deve ser preenchido") String razaoSocial, @NotNull(message = "Campo CPF/CNPJ deve ser preenchido") String cpfCnpj, String fotoUrl, List<Contato> contatos, List<Endereco> enderecos, Set<Usuario> usuarios, Set<MesaServico> mesaServicoSet) {
        this.id = id;
        this.ativo = ativo;
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cpfCnpj = cpfCnpj;
        this.fotoUrl = fotoUrl;
        this.contatos = contatos;
        this.enderecos = enderecos;
        this.usuarios = usuarios;
        this.mesaServicoSet = mesaServicoSet;
    }

    public Set<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
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
        return enderecos;
    }

    public void setEndereco(List<Endereco> endereco) {
        this.enderecos = endereco;
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
