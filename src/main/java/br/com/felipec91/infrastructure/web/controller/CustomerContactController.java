package br.com.felipec91.infrastructure.web.controller;

import br.com.felipec91.domain.model.customer.value_object.Contact;
import br.com.felipec91.domain.service.CustomerService;
import br.com.felipec91.infrastructure.web.dto.customer.OldAndNewContactInputDTO;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.util.UUID;

@Path("/customers/{customerId}/contacts")
public class CustomerContactController {

    @Inject
    CustomerService customerService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@PathParam("customerId") UUID customerId,
                           @Valid @RequestBody Contact contact){

        var createdContact = customerService.registerNewContact(customerId, contact);

        return Response
                .status(Response.Status.CREATED)
                .entity(createdContact)
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll(@PathParam("customerId") UUID customerId) {

        return Response
                .status(Response.Status.OK)
                .entity(customerService.getAllContacts(customerId))
                .build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response editContact(@PathParam("customerId") UUID customerId,
                                @RequestBody OldAndNewContactInputDTO oldValueNewValueContactInputDTO) {

        var editedContact = customerService.updateContact(customerId, oldValueNewValueContactInputDTO);

        return Response
                .status(Response.Status.ACCEPTED)
                .entity(editedContact)
                .build();
    }
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteContact(@PathParam("customerId") UUID customerId,
                                  @Valid @RequestBody Contact contact){

        customerService.deleteContact(customerId, contact);

        return Response
                .status(Response.Status.ACCEPTED)
                .build();
    }
}
