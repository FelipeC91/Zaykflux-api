package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import model.Contato;
import model.Endereco;

import java.util.Optional;

@ApplicationScoped
public class ContatoRepository implements PanacheRepository<Contato> {
    public Optional<Contato> findByEmailAndCliente(String email, Long clienteId) {
        return Optional.ofNullable( find("SELECT c FROM Contato c WHERE c.email = :email AND c.cliente.id = :id", Parameters.with("email", email).and("id", clienteId))
                            .firstResult() );
    }

}
