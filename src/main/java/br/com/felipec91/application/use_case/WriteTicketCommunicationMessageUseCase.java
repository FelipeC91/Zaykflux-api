package br.com.felipec91.application.use_case;

import br.com.felipec91.application.use_case.concept.UseCase;
import br.com.felipec91.domain.exception.TicketNotFoundException;
import br.com.felipec91.domain.exception.UserNotFoundException;
import br.com.felipec91.domain.model.ticket.value_object.TicketMessage;
import br.com.felipec91.domain.repository.TicketRepository;
import br.com.felipec91.infrastructure.db.repositoryImpl.UserModelRepositoryImpl;
import br.com.felipec91.infrastructure.web.dto.ticket_message.TicketMessageInputDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class WriteTicketCommunicationMessageUseCase implements UseCase<TicketMessageInputDTO, Void> {

    @Inject
    TicketRepository ticketRepository;

    @Inject
    UserModelRepositoryImpl userModelRepository;

    @Override
    public Void execute(TicketMessageInputDTO input) {
        var validTicket = ticketRepository.findByIdOptional(input.getTicketId())
                                        .orElseThrow(() -> new TicketNotFoundException("Ticket indicado não encontrado ou inexistente"));


        var ticketCommunication = validTicket.getCommunication();

        var author = userModelRepository.findByIdOptional(input.getAuthorId())
                                            .orElseThrow(() -> new UserNotFoundException("Usuário não encontro"));
        //TODO validar se autor da msg é o mesmo do token


        var newMessage = new TicketMessage(
                            input.getSendAt(),
                            input.getContent(),
                            author
                        );

        ticketCommunication.addMessage(newMessage);

        ticketRepository.persistAndFlush(validTicket);

        return null;
    }

}
