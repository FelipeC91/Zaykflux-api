package br.com.felipec91.infrastructure.web.dto.ticket.bean_mapper;

import br.com.felipec91.domain.model.ticket.entity.Ticket;
import br.com.felipec91.infrastructure.web.dto.ticket.TicketProfileOutputDTO;
import br.com.felipec91.infrastructure.web.dto.ticket.TicketSearchOutputDTO;
import br.com.felipec91.infrastructure.web.filter.TicketViewParamFilter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "cdi", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TicketMapper {

//    @Mapping(target = "id", ignore = true)
//    @Mapping(target = "number", ignore = true)
//    Ticket toModel(TicketInputDTO ticketInputDTO);

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "customerTradingName", source = "customer.tradingName")
    @Mapping(target = "requesterId", source = "requester.id")
    @Mapping(target = "requesterName", source = "requester.name")
    TicketProfileOutputDTO toTicketProfileOutputDTO(Ticket ticket);

    default TicketSearchOutputDTO toTicketDTO(Ticket ticket, TicketViewParamFilter criteriaFilter) {

        var ticketDTO = new TicketSearchOutputDTO();

        ticketDTO.setId( ticket.getId() );

        if (criteriaFilter.columns().contains("number"))
            ticketDTO.setNumber( ticket.getNumber() );

        if (criteriaFilter.columns().contains("title"))
            ticketDTO.setTitle( ticket.getTitle() );

        if (criteriaFilter.columns().contains("customerName"))
            ticketDTO.setCustomerTradingName( ticket.getCustomer().getTradingName() );

        if (criteriaFilter.columns().contains("priority"))
            ticketDTO.setPriority( ticket.getPriority() );

        if (criteriaFilter.columns().contains("status"))
            ticketDTO.setStatus( ticket.getStatus() );

        if (criteriaFilter.columns().contains("requesterName"))
            ticketDTO.setRequesterName( ticket.getRequester().getName() );

        if (criteriaFilter.columns().contains("requesterEmail"))
            ticketDTO.setRequesterEmail( ticket.getRequester().getEmail() );

        if (criteriaFilter.columns().contains("openedAt"))
            ticketDTO.setOpenedAt( ticket.getOpenedAt() );

        if (criteriaFilter.columns().contains("updatedAt"))
            ticketDTO.setUpdatedAt( ticket.getUpdatedAt() );

        if (criteriaFilter.columns().contains("serviceDesk"))
            ticketDTO.setServiceDesk( ticket.getServiceDesk().getName() );

        return ticketDTO;

    }

}
