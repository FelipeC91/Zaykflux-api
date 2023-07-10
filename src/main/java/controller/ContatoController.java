package controller;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import model.Contato;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import service.ContatoService;

@Path("/v1/clientes/{id}/contatos")
public class ContatoController {

    @Inject
    ContatoService contatoService;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response criarContato(@PathParam("id") Long clienteId, @RequestBody Contato contato, @Context UriInfo uriInfo){

        var contatoCriado = contatoService.save(clienteId, contato, uriInfo);

        var locationUri = UriBuilder
                .fromUri(uriInfo.getRequestUri() + "/" + contatoCriado.getId())
                .build();

        return Response
                .status(Response.Status.CREATED)
                .header("Location", locationUri.getPath())
                .entity(contatoCriado)
                .build();


    }
}
