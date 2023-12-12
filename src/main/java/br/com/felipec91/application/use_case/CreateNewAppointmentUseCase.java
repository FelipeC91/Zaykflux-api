package br.com.felipec91.application.use_case;

import br.com.felipec91.application.use_case.concept.UseCase;
import br.com.felipec91.domain.exception.AccontableAlreadyHaveAppointmentInThisPeriodException;
import br.com.felipec91.domain.exception.TicketNotFoundException;
import br.com.felipec91.domain.exception.UserNotFoundException;
import br.com.felipec91.domain.model.ticket.value_object.Appointment;
import br.com.felipec91.domain.repository.TicketRepository;
import br.com.felipec91.domain.repository.UserModelRepository;
import br.com.felipec91.infrastructure.web.dto.appointment.AppointmentInputDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class CreateNewAppointmentUseCase implements UseCase<AppointmentInputDTO, Appointment> {

    @Inject
    TicketRepository ticketRepository;

    @Inject
    UserModelRepository userModelRepository;

    @Transactional
    public Appointment execute(AppointmentInputDTO input) {
        var validTicket = ticketRepository.findByIdOptional(input.getTicketId())
                                        .orElseThrow(() -> new TicketNotFoundException("Ticket indicado não encontrado ou inexistente"));


        //TODO validar se autor da msg é o mesmo to token
        var accountable = userModelRepository.findAttendantByIdOptional(input.getAccountableId())
                                        .orElseThrow(() -> new UserNotFoundException("Usuário indicado não encontro"));


        var appointmentCandidate = Appointment.create(
                input.getAppointmentDate().toLocalDate(),
                input.getBeginningAt(),
                input.getEndingAt(),
                input.getDescription(),
                accountable
        );

        if (hasScheduledAppointmentsForAccountable(appointmentCandidate))
            throw new AccontableAlreadyHaveAppointmentInThisPeriodException("Usuário já tem apontamentos neste período");


        var newAppointmentIndex = validTicket.attachAppointment(appointmentCandidate);

        ticketRepository.persistAndFlush(validTicket);


        return validTicket.getAppointments().get(newAppointmentIndex);
    }


    private boolean hasScheduledAppointmentsForAccountable(Appointment appointment) {
        var possibleAppointment = ticketRepository.findByAppointment_AccountableAnd_DateAnd_BeginningAtLessThanEqualAnd_EndingAtGreaterThanEqual(appointment);

        return  possibleAppointment > 0;
//        return ticketRepository.findExistsByAppointment_AccountableAnd_DateAnd_BeginningAtLessThanEqualAnd_EndingAtGreaterThanEqual(appointment);
    }
}
