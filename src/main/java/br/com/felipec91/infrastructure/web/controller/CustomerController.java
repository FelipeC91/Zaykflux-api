package br.com.felipec91.infrastructure.web.controller;

import br.com.felipec91.domain.model.customer.entity.Customer;
import br.com.felipec91.domain.service.CustomerService;
import br.com.felipec91.infrastructure.persistence.repositoryImpl.CustomerPanacheRepositoryImpl;
import br.com.felipec91.infrastructure.web.dto.customer.bean_mapper.CustomerMapper;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.util.UUID;

@Path("/customers")
public class CustomerController {

    @Inject
    CustomerPanacheRepositoryImpl customerRepository;

    @Inject
    CustomerService customerService;

    @Inject
    CustomerMapper customerMapper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllClients() {
        var customersDTO = customerRepository.findAll()
                        .stream()
                        .map(customerMapper::toDTO);

        return Response
                .status(Response.Status.OK)
                .entity(customersDTO)
                .build();
    }


    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll(@QueryParam("name") String nomeFilter) {

        var customerList = customerRepository.findByTradingNameIgnoreCase(nomeFilter)
                                        .stream()
                                        .map(customerMapper::toDTO)
                                        .toList();

        if (!customerList.isEmpty()) {
            return Response
                    .status(Response.Status.OK)
                    .entity(customerList)
                    .build();

        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    @GET
    @Path("/{customerId}")
    public Response getClientGeneralInfo(@PathParam("customerId") UUID customerId) {
        var clientOptional = customerRepository.findByIdOptional(customerId);

        if (clientOptional.isPresent()) {
            return Response
                    .status(Response.Status.ACCEPTED)
                    .entity(clientOptional.get())
                    .build();

        } else {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .build();
        }
    }

    @POST
    @Path("/new")
    public Response create(@RequestBody @Valid Customer customer, @Context UriInfo uriInfo) {
        var newCustomer = customerService.save(customer);

        return Response
                .status(Response.Status.CREATED)
                .entity(newCustomer)
                .build();
    }

    @PUT
    @Path("/{customerId}/status")
    @Produces(MediaType.APPLICATION_JSON)
    public Response toggleStatus(@PathParam("customerId") UUID customerId, @RequestBody boolean updatedStatus) {
        var updatedCustomer = customerService.updateCustomerStatus(customerId, updatedStatus);

        return Response
                .status(Response.Status.ACCEPTED)
                .entity(updatedCustomer)
                .build();

    }
}
