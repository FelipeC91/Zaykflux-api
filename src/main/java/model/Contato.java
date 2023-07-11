package model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull(message = "Campo Nome deve ser preenchido")
    private String nome;

    @NotNull(message = "Campo E-mail deve ser preenchido")
    @Email(message = "E-mail em formato inválido")
    private String email;

    private String telefone;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_contato")
    private TipoContato tipoContato;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;














    public Contato() {}

    public Contato(Long id, String nome, String email, String telefone, TipoContato tipoContato, Cliente cliente) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.tipoContato = tipoContato;
        this.cliente = cliente;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public TipoContato getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(TipoContato tipoContato) {
        this.tipoContato = tipoContato;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contato contato = (Contato) o;
        return Objects.equals(id, contato.id) && Objects.equals(nome, contato.nome) && Objects.equals(email, contato.email) && Objects.equals(telefone, contato.telefone) && tipoContato == contato.tipoContato;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, telefone, tipoContato);
    }
}
