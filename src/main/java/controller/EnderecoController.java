package controller;


import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Endereco;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import service.EnderecoService;

@Path("/clientes/{id}/enderecos")
public class EnderecoController {

    @Inject
    EnderecoService enderecoService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response criarEndereco(@PathParam("id") Long clienteId, @RequestBody Endereco endereco) {

        enderecoService.save(clienteId, endereco);

        return Response
                .status(Response.Status.CREATED)
                .build();

    }
}
