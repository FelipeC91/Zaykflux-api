package br.com.felipec91.application.use_case;

import br.com.felipec91.application.use_case.concept.UseCase;
import br.com.felipec91.domain.exception.TicketInvalidOperationException;
import br.com.felipec91.domain.exception.TicketNotFoundException;
import br.com.felipec91.domain.model.ticket.entity.Ticket;
import br.com.felipec91.domain.model.ticket.value_object.TicketMessage;
import br.com.felipec91.domain.model.ticket.value_object.TicketStatus;
import br.com.felipec91.domain.repository.TicketRepository;
import br.com.felipec91.infrastructure.web.dto.ticket.TicketMergeOperationDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.OffsetDateTime;

@ApplicationScoped
public class MergeTicketsUseCase implements UseCase<TicketMergeOperationDTO, Ticket> {
    @Inject
    TicketRepository ticketRepository;

    @Transactional
    @Override
    public Ticket execute(TicketMergeOperationDTO input) {
        var mergedTicketCandidate = ticketRepository.findByIdOptional(input.mergeWith())
                        .orElseThrow(() -> new TicketNotFoundException("ticket não encontrado"));

        var mainTicket = ticketRepository.findByIdOptional(input.main())
                                            .orElseThrow(() -> new TicketNotFoundException("ticket não encontrado"));

        if (!isTicketsOnValidState(mainTicket, mergedTicketCandidate))
            throw new TicketInvalidOperationException("Um dos tickets se encontra em estado inválido para esta operação");

        handleMergedTicket(mergedTicketCandidate, mainTicket.getNumber().toString());

        handleMainTicket(mainTicket, mergedTicketCandidate);

        return mainTicket;
    }

    private  boolean isTicketsOnValidState(Ticket mainTicket, Ticket mergedTicketCandidate) {
        return (mainTicket.getStatus() == TicketStatus.ABERTO && mergedTicketCandidate.getStatus() == TicketStatus.ABERTO)
                && mainTicket.getCustomer().equals(mergedTicketCandidate.getCustomer());
    }


    private void handleMergedTicket(Ticket mergedTicketCandidate, final String mainTicketNumber) {
        mergedTicketCandidate.changeToMergedStatus();
        mergedTicketCandidate.addMergeDescription(mainTicketNumber);

        ticketRepository.persistAndFlush(mergedTicketCandidate);
    }


    private void handleMainTicket(Ticket mainTicket, final Ticket mergedTicket) {
        mergedTicket.getAppointments()
                    .forEach(mainTicket::attachAppointment);


            var standardMergeCommunicationMessage = formatMergeMessage(mergedTicket.getNumber());

            //TODO obter usr a partir do token jwt
            var mergeCommunicationMessage = new TicketMessage(
                                                        OffsetDateTime.now(),
                                                        standardMergeCommunicationMessage,
                                                        null
                                            );

        mainTicket.getCommunication().addMessage(mergeCommunicationMessage);

        ticketRepository.persistAndFlush(mainTicket);
    }

    public String formatMergeMessage(Long mergedTicketNumber) {
        return String.format("O ticket %s foi agrupado a este ticket", mergedTicketNumber.toString());
    }

}

