package br.com.felipec91.infrastructure.web.filter;

import br.com.felipec91.infrastructure.persistence.repositoryImpl.filter.TicketAdvancedSearchQueryFilterImpl;
import br.com.felipec91.infrastructure.web.exception.UnhandledJsonStringToObjectMappingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FilterParser {

    public static TicketViewParamFilter parseJsonStringToDTO(String ticketViewParamFilter) {
        try {
            return new ObjectMapper().readValue(ticketViewParamFilter, TicketViewParamFilter.class);

        } catch (JsonProcessingException e) {
            throw new UnhandledJsonStringToObjectMappingException("Impossivel processar formato de filtro fornecido");
        }
    }

    public static TicketAdvancedSearchQueryFilterImpl parseJsonStringToFilter(String TicketAdvancedSearchFilter) {
        try {
            return new ObjectMapper().readValue(TicketAdvancedSearchFilter, TicketAdvancedSearchQueryFilterImpl.class);

        } catch (JsonProcessingException e) {
            throw new UnhandledJsonStringToObjectMappingException("Impossivel processar formato de filtro fornecido",e);
        }
    }
}
