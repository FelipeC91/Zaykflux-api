package br.com.felipec91.infrastructure.web.controller;

import br.com.felipec91.infrastructure.web.dto.ticket_message.TicketMessageInputDTO;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.util.UUID;

@Path("/ticket/{ticketId}/communication")
public class TicketCommunicationController {

    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response acceptNewMessage(@PathParam("ticketId") UUID ticketId,
                                     @RequestBody TicketMessageInputDTO ticketMessageInputDTO) {
        ticketMessageInputDTO.setTicketId(ticketId);

        return Response
                    .status(Response.Status.ACCEPTED)
                    .build();

    }
}
