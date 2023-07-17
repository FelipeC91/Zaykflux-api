package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull(message = "Campo CEP deve ser preenchido")
    private String cep;

    @NotNull(message = "Campo Bairro deve ser preenchido")
    private String bairro;

    @NotNull(message = "Campo Logradouro deve ser preenchido")
    private String logradouro;
    private String complemento;

    @NotNull(message = "Campo Numero deve ser preenchido")
    private Integer numero;
    @ManyToOne
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;





    public Endereco(){}

    public Endereco(Long id, String cep, String bairro, String logradouro, String complemento, Integer numero, Cidade cidade, Cliente cliente) {
        this.id = id;
        this.cep = cep;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.numero = numero;
        this.cidade = cidade;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
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
        Endereco endereco = (Endereco) o;
        return Objects.equals(id, endereco.id) && Objects.equals(cep, endereco.cep) && Objects.equals(bairro, endereco.bairro) && Objects.equals(logradouro, endereco.logradouro) && Objects.equals(numero, endereco.numero) && Objects.equals(cidade, endereco.cidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cep, bairro, logradouro, numero, cidade);
    }
}
