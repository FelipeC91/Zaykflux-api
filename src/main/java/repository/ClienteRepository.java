package repository;

import dto.ClienteBasicInfoDTO;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import model.Cliente;

import java.util.List;

@ApplicationScoped
public class ClienteRepository implements PanacheRepository<Cliente> {


    public List<ClienteBasicInfoDTO> findAllAndFilterToBasicInfo() {
        return findAll()
                .stream()
                .map(cliente -> new ClienteBasicInfoDTO(cliente.getId(), cliente.getNomeFantasia(), cliente.getAtivo()) )
                .toList();

    }

    public List<ClienteBasicInfoDTO> findByNome(String nomeFilter) {
        var formattedFilter = ('%' + nomeFilter + "%").toUpperCase();

        return find("SELECT c FROM Cliente AS c WHERE UPPER(c.nomeFantasia) LIKE :nomeFantasia",
                                                Parameters.with("nomeFantasia", formattedFilter) )
                .stream()
                .map(cliente -> new ClienteBasicInfoDTO(cliente.getId(), cliente.getNomeFantasia(), cliente.getAtivo()) )
                .toList();
    }


}
