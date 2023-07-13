package controller;

import dto.ClienteBasicInfoDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
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
    public Response getClienteGeneralInfo(@PathParam("id") Long clienteId) {
        var clienteOptional = clienteRepository.findByIdOptional(clienteId);

        if (clienteOptional.isPresent()) {
            return Response
                    .status(Response.Status.ACCEPTED)
                    .entity(clienteOptional.get())
                    .build();

        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }
}
