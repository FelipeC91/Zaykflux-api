package br.com.felipec91.infrastructure.web.controller;

import br.com.felipec91.domain.model.customer.value_object.Address;
import br.com.felipec91.domain.service.CustomerService;
import br.com.felipec91.infrastructure.web.dto.customer.OldAndNewAddressInputDTO;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.util.UUID;

@Path("/customers/{customerId}/addresses")
public class CustomerAddressController {
    @Inject
    CustomerService customerService;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@PathParam("customerId") UUID customerId,
                           @Valid @RequestBody Address address){

        var createdAddress = customerService.registerNewAddresses(customerId, address);

        return Response
                .status(Response.Status.CREATED)
                .entity(createdAddress )
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll(@PathParam("customerId") UUID customerId) {

        return Response
                .status(Response.Status.OK)
                .entity(customerService.getAllAddresses(customerId))
                .build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response editAddress(@PathParam("customerId") UUID customerId,
                                @RequestBody OldAndNewAddressInputDTO oldAndNewAddressInputDTO) {

        var editedAddress = customerService.updateAddress(customerId,oldAndNewAddressInputDTO);

        return Response
                .status(Response.Status.ACCEPTED)
                .entity(editedAddress)
                .build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAddress(@PathParam("customerId") UUID customerId,
                                  @Valid @RequestBody Address address){

        customerService.deleteAddress(customerId, address);

        return Response
                .status(Response.Status.ACCEPTED)
                .build();
    }
}
