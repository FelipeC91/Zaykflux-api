package controller;

import dto.ClienteBasicInfoDTO;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import model.Cliente;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import repository.ClienteRepository;
import service.ClienteService;

import java.util.List;

@Path("/v1/clientes")
public class ClienteController {

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    ClienteService clienteService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ClienteBasicInfoDTO> listarClientes() {
        return clienteRepository.findAllAndFilterToBasicInfo();
    }


    @GET
    @Path("/busca")
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

    @POST
    @Path("/novo")
    public Response criarCliente(@RequestBody @Valid Cliente cliente, @Context UriInfo uriInfo) {
        var clienteNovo = clienteService.save(cliente, uriInfo);

        var locationUrl = UriBuilder.fromUri(uriInfo.getRequestUri())
                            .path(clienteNovo.getId().toString())
                            .toTemplate();

        return Response
                .status(Response.Status.CREATED)
                .entity(clienteNovo)
                .build();
    }
}
