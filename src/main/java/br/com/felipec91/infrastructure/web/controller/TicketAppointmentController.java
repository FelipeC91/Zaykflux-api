package br.com.felipec91.infrastructure.web.controller;


import br.com.felipec91.application.use_case.CreateNewAppointmentUseCase;
import br.com.felipec91.domain.exception.TicketNotFoundException;
import br.com.felipec91.domain.repository.TicketRepository;
import br.com.felipec91.infrastructure.web.dto.appointment.AppointmentInputDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.util.UUID;

@Path("/tickets/{ticketId}/appointments")
public class TicketAppointmentController {

    @Inject
    CreateNewAppointmentUseCase createNewAppointmentUseCase;

    @Inject
    TicketRepository ticketRepository;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll(@PathParam("ticketId") UUID ticketId) {
        var ticket = ticketRepository.findByIdOptional(ticketId)
                                            .orElseThrow(() -> new TicketNotFoundException("Ticket não encontrado"));

        return Response
                .status(Response.Status.OK)
                .entity(ticket.getAppointments())
                .build();
    }

    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createNewAppointment(@PathParam("ticketId") UUID ticketId, @RequestBody AppointmentInputDTO appointmentInputDTO) {
        appointmentInputDTO.setTicketId(ticketId);

        var newAppointment = createNewAppointmentUseCase.execute(appointmentInputDTO);

        return Response
                .status(Response.Status.CREATED)
                .entity(newAppointment)
                .build();
    }
}
