package model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private Boolean aberto;

    private String titulo;

    private String descricao;

    @Enumerated
    private Prioridade prioridade;

    @CreationTimestamp
    private OffsetDateTime dataCriacao;

    @CreationTimestamp
    private OffsetDateTime dataFechamento;

    @ManyToOne
    @JoinColumn(name = "ticket_pai_id")
    private Ticket ticketPai;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario solicitante;

//    private Set<Apontamento> apontamentos = new HashSet<>();
//    private Comunicacao comunicacao;











    public Ticket() {
    }

    public Ticket(Long id, Boolean aberto, String titulo, String descricao, Prioridade prioridade, OffsetDateTime dataCriacao, OffsetDateTime dataFechamento, Ticket ticketPai, Cliente cliente, Usuario solicitante) {
        this.id = id;
        this.aberto = aberto;
        this.titulo = titulo;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.dataCriacao = dataCriacao;
        this.dataFechamento = dataFechamento;
        this.ticketPai = ticketPai;
        this.cliente = cliente;
        this.solicitante = solicitante;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAberto() {
        return aberto;
    }

    public void setAberto(Boolean aberto) {
        this.aberto = aberto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public OffsetDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(OffsetDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public OffsetDateTime getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(OffsetDateTime dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Ticket getTicketPai() {
        return ticketPai;
    }

    public void setTicketPai(Ticket ticketPai) {
        this.ticketPai = ticketPai;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(Usuario solicitante) {
        this.solicitante = solicitante;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Objects.equals(id, ticket.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
