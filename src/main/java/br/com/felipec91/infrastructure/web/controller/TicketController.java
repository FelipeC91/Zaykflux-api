package br.com.felipec91.infrastructure.web.controller;

import br.com.felipec91.application.use_case.MergeTicketsUseCase;
import br.com.felipec91.application.use_case.OpenNewTicketUseCase;
import br.com.felipec91.domain.repository.TicketRepository;
import br.com.felipec91.domain.service.TicketService;
import br.com.felipec91.infrastructure.web.dto.ticket.TicketInputDTO;
import br.com.felipec91.infrastructure.web.dto.ticket.TicketMergeOperationDTO;
import br.com.felipec91.infrastructure.web.dto.ticket.bean_mapper.TicketMapper;
import br.com.felipec91.infrastructure.web.filter.FilterParser;
import io.vertx.core.net.impl.URIDecoder;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.Explode;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.links.LinkParameter;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.Objects;
import java.util.UUID;

@Path("/tickets")
@Tag(name = "Tickets", description = "allows customers/tenants and attendants to open or handle tickets")
public class TicketController {

    @Inject
    TicketRepository ticketRepository;

    @Inject
    TicketMapper ticketMapper;

    @Inject
    TicketService ticketService;

    @Inject
    OpenNewTicketUseCase openNewTicketUseCase;

    @Inject
    MergeTicketsUseCase mergeTicketsUseCase;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "List all opened tickets")

    public Response listAll(@Parameter(allowEmptyValue = false,
                                        name = "viewParams",
                                        description = "Query parameter should be encoded and in json format",
                                        example = "{\ngroupBy: ServiceDesk, \ngroupByValue: Remoto, \ncolumns: [\n\"number\", \n\"title\",\n\"customerName\",\n\"requesterName\",\n\"openedAt\",\n\"updatedAt\" \n]}",
                                        in = ParameterIn.QUERY

                            )
                            @QueryParam("viewParams") String viewParamsJson) {
        var ticketViewParam = FilterParser.parseJsonStringToDTO( URIDecoder.decodeURIComponent(viewParamsJson) );

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
    public Response search(@QueryParam("searchKey") String searchKey,
                           @QueryParam("viewParam") String viewParamJson) {
        var ticketCriteria = FilterParser.parseJsonStringToDTO(  URIDecoder.decodeURIComponent(viewParamJson) );

        var ticketList =  ticketService.simpleSearch(searchKey)
                                       .stream()
                                       .takeWhile(Objects::isNull)
                                       .map( (tkt) -> ticketMapper.toTicketDTO(tkt, ticketCriteria));

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

    @PUT
    @Path("/{ticketId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response acceptMerge(@PathParam("ticketId") UUID mainTicketId,
                                @QueryParam("mergeWith") UUID mergeWithTicket) {
        var ticketMergeOperationDTO = new TicketMergeOperationDTO(mainTicketId, mergeWithTicket);

        var mainTicket = mergeTicketsUseCase.execute(ticketMergeOperationDTO);


        return Response.status(Response.Status.ACCEPTED)
                .entity(mainTicket)
                .build();
    }





}