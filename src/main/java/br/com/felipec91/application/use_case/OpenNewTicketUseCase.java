package br.com.felipec91.application.use_case;

import br.com.felipec91.application.use_case.concept.UseCase;
import br.com.felipec91.domain.exception.CustomerNotFoundException;
import br.com.felipec91.domain.exception.ServiceDeskNotAllawedException;
import br.com.felipec91.domain.exception.ServiceDeskNotFoundException;
import br.com.felipec91.domain.exception.UserNotFoundException;
import br.com.felipec91.domain.model.ticket.entity.Ticket;
import br.com.felipec91.domain.repository.CustomerRepository;
import br.com.felipec91.domain.repository.ServiceDeskRepository;
import br.com.felipec91.domain.repository.TicketRepository;
import br.com.felipec91.domain.repository.UserModelRepository;
import br.com.felipec91.infrastructure.web.dto.ticket.TicketInputDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.Objects;

@ApplicationScoped
public class OpenNewTicketUseCase implements UseCase<TicketInputDTO, Ticket> {

    @Inject
    CustomerRepository customerRepository;

    @Inject
    UserModelRepository userModelRepository;

    @Inject
    ServiceDeskRepository serviceDeskRepository;

    @Inject
    TicketRepository ticketRepository;

    @Transactional
    public Ticket execute(TicketInputDTO input) {

        var existentCustomer =  customerRepository.findByIdOptional(input.customerId())
                                            .orElseThrow(() -> new CustomerNotFoundException("Cliente fornecido inexistente"));


        var requester = userModelRepository.findTenantByIdOptional(input.requesterId())
                                        .orElseThrow(() -> new UserNotFoundException("Usuário fornecido Inexistente"));

        var serviceDesk = serviceDeskRepository.findByIdOptional(input.serviceDeskId())
                                        .orElseThrow(() -> new ServiceDeskNotFoundException("Mesa de serviço indicada inexistente"));


        if (!existentCustomer.isServiceDeskAllowed(serviceDesk))
            throw new ServiceDeskNotAllawedException("Mesa de Serviço não suportada por este Cliente Ou inexistente");


        var parentTicketCandidate = !Objects.isNull(input.parentTicketId()) ? ticketRepository.findById(input.parentTicketId()) : null ;

        var ticketNumber = generateTicketNumber();

        var ticket = Ticket.create(
                ticketNumber,
                input.title(),
                input.description(),
                input.priority(),
                parentTicketCandidate,
                existentCustomer,
                requester,
                serviceDesk,
                input.serviceCatalogTree()
        );

        ticketRepository.persistAndFlush(ticket);

        return ticket;
    }

    private long generateTicketNumber() {
        return ticketRepository.count() + 1;
    }
}
