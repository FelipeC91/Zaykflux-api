package br.com.felipec91.infrastructure.web.controller;

import br.com.felipec91.application.use_case.OpenNewTicketUseCase;
import br.com.felipec91.domain.repository.TicketRepository;
import br.com.felipec91.infrastructure.web.dto.ticket.TicketInputDTO;
import br.com.felipec91.infrastructure.web.dto.ticket.bean_mapper.TicketMapper;
import br.com.felipec91.domain.service.TicketService;
import br.com.felipec91.infrastructure.web.filter.FilterParser;
import io.vertx.core.net.impl.URIDecoder;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import java.util.Objects;

@Path("/tickets")
public class TicketController {

    @Inject
    TicketRepository ticketRepository;

    @Inject
    TicketMapper ticketMapper;

    @Inject
    TicketService ticketService;

    @Inject
    OpenNewTicketUseCase openNewTicketUseCase;



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll(@QueryParam("viewParams") String viewParamJson) {
        var ticketViewParam = FilterParser.parseJsonStringToDTO( URIDecoder.decodeURIComponent(viewParamJson) );

        var ticketDTOs = ticketRepository.findAll()
                                        .stream()
                                        .map( (tkt) -> ticketMapper.toTicketDTO(tkt, ticketViewParam) )
                                        .toList();

        return Response
                .status(200)
                .entity(ticketDTOs)
                .build();
    }

    @GET
    @Path("/advanced-search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response advancedSearch(@QueryParam("filter") String ticketAdvancedSearchFilterString) {
        var decodedFilter =  URIDecoder.decodeURIComponent(ticketAdvancedSearchFilterString);
        var ticketAdvancedSearchFilter = FilterParser.parseJsonStringToFilter( decodedFilter);
            // TODO PROCESS SEARCH
        return Response
                .status(Response.Status.OK)
                .entity(ticketAdvancedSearchFilter)
                .build();
    }

    @GET
    @Path("/search")
    @Produces(MediaType.APPLICATION_JSON)
    public Response search(@QueryParam("searchKey") String searchKey, @QueryParam("viewParam") String viewParamJson) {
        var ticketCriteria = FilterParser.parseJsonStringToDTO(  URIDecoder.decodeURIComponent(viewParamJson) );

        var ticketList =  ticketService.simpleSearch(searchKey)
                                       .stream()
                                       .takeWhile(Objects::isNull)
                                       .map( (tkt) -> ticketMapper.toTicketDTO(tkt, ticketCriteria) );

        return Response
                .status(Response.Status.OK)
                .entity(ticketList)
                .build();
    }


    @POST
    @Path("/new")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response acceptNewTicket(@RequestBody @Valid TicketInputDTO ticketInputDTO ) {
        var newTicket = openNewTicketUseCase.execute(ticketInputDTO);

        return Response.status(Response.Status.CREATED)
                .entity(ticketMapper.toTicketProfileOutputDTO(newTicket))
                .build();
    }





}