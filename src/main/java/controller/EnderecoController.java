package controller;


import io.quarkus.panache.common.Parameters;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import model.Endereco;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import repository.EnderecoRepository;
import service.EnderecoService;

import java.util.List;

@Path("/v1/clientes/{id}/enderecos")
public class EnderecoController {

    @Inject
    EnderecoRepository enderecoRepository;

    @Inject
    EnderecoService enderecoService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response criarEndereco(@PathParam("id") Long clienteId, @RequestBody Endereco endereco, @Context UriInfo uriInfo) {

        var enderecoCriado = enderecoService.save(clienteId, endereco, uriInfo);

        var locationUri = UriBuilder
                                .fromUri(uriInfo.getRequestUri() + "/" + enderecoCriado.getId())
                                .build();

        return Response
                .status(Response.Status.CREATED)
                .header("Location", locationUri.getPath())
                .entity(enderecoCriado)
                .build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Endereco> listar(@PathParam("id") Long clienteId) {
        return enderecoRepository.list( "SELECT e FROM Endereco AS e Where e.cliente.id = :id", Parameters.with("id", clienteId));
    }

    @DELETE
    @Path("/{enderecoId}")
    public Response delete(@PathParam("enderecoId") Long enderecoId) {

        enderecoRepository.deleteById(enderecoId);

        return Response
                .status(Response.Status.OK)
                .build();
    }

    @PUT
    @Path("/{enderecoId}")
    public Response update(@RequestBody Endereco endereco) {

        enderecoRepository.update(endereco);

        var enderecoAtualizado = enderecoRepository.findById(endereco.getId());

        return Response
                .status(Response.Status.OK)
                .entity(enderecoAtualizado)
                .build();
    }


}
