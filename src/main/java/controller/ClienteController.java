package controller;

import dto.ClienteBasicInfoDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import model.Cliente;
import repository.ClienteRepository;

import java.util.List;

@Path("/v1/clientes")
public class ClienteController {

    @Inject
    ClienteRepository clienteRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ClienteBasicInfoDTO> listarClientes() {
        return clienteRepository.findAllAndFilterToBasicInfo();
    }


    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public List<ClienteBasicInfoDTO> listarClientes(@QueryParam("nome") String nomeFilter) {
        return clienteRepository.findByNome(nomeFilter);
    }

    @GET
    @Path("/{id}")
    public Cliente getClienteGeneralInfo(@PathParam("id") Long clienteId) {
        return clienteRepository.findById(clienteId);
    }
}
