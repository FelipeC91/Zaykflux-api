package controller;


import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import model.Contato;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import repository.ContatoRepository;
import service.ContatoService;

import java.util.List;

@Path("/v1/clientes/{id}/contatos")
public class ContatoController {

    @Inject
    ContatoService contatoService;

    @Inject
    ContatoRepository contatoRepository;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response criarContato(@PathParam("id") Long clienteId, @Valid @RequestBody Contato contato, @Context UriInfo uriInfo){

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

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Contato> listarContatos(@PathParam("id") Long clientId) {
        return contatoRepository.findAll()
                .list();
    }
}
