package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import model.Endereco;

import java.util.Optional;

@ApplicationScoped
public class EnderecoRepository implements PanacheRepository<Endereco> {


    public Endereco findIfExistsInCliente(Endereco endereco, Long clienteId) {
        return find("SELECT e FROM Endereco e WHERE e.logradouro = ?1 AND e.numero = ?2 AND e.cliente.id = ?3", endereco.getLogradouro(), endereco.getNumero(), clienteId )
                .firstResult();
    }

  public void update(Endereco endereco) {
        update("UPDATE Endereco e SET e.cep = ?1, e.barirro = ?2," +
                                            "e.logradouro = ?3, e.complemento = ?4, e.numero = ?5, e.cidade = ?6 WHERE e.id = ?7",
                endereco.getCep(), endereco.getBairro(), endereco.getLogradouro(), endereco.getComplemento(),endereco.getNumero(), endereco.getCidade(), endereco.getId());
  }


}
