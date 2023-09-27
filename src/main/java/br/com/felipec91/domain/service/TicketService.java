package br.com.felipec91.domain.service;

import br.com.felipec91.domain.model.ticket.entity.Ticket;
import br.com.felipec91.domain.repository.TicketRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class TicketService {

    @Inject
    TicketRepository ticketRepository;

    public List<Ticket>  simpleSearch(String searchKey) {
        var validSearchKey = isValidSearchKeySize(searchKey);

        if (isAnNumericSearchKey(validSearchKey))
            return  ticketRepository.findByNumber( Long.getLong(validSearchKey) );

        else
            return ticketRepository.findByTitleOrCustomerTradingName(searchKey);

    }

    private String isValidSearchKeySize(String searchKey) {
        return Objects.nonNull(searchKey) && searchKey.length() > 11 ? searchKey.substring(0, 10) : searchKey;
    }

    private boolean isAnNumericSearchKey(String searchKey) {
        return  searchKey.matches("\\d+");
    }

}
