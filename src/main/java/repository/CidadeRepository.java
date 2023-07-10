package repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import model.Cidade;

public class CidadeRepository implements PanacheRepository<Cidade> {

    public Cidade findByNome(String nome) {
        return find("SELECT c Cidade WHERE c.nome = :nome", Parameters.with("nome", nome) )
                .firstResult();
    }
}
