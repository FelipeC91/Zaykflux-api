package br.com.felipec91.application.use_case;

import br.com.felipec91.domain.model.customer.entity.Customer;
import br.com.felipec91.domain.model.service_desk.entity.ServiceDesk;
import br.com.felipec91.domain.model.ticket.entity.Ticket;
import br.com.felipec91.domain.model.ticket.value_object.Appointment;
import br.com.felipec91.domain.model.ticket.value_object.Priority;
import br.com.felipec91.domain.model.user.entity.Attendant;
import br.com.felipec91.domain.model.user.entity.Tenant;
import br.com.felipec91.domain.repository.TicketRepository;
import br.com.felipec91.domain.repository.UserModelRepository;
import br.com.felipec91.infrastructure.web.dto.appointment.AppointmentInputDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.util.Optional;
import java.util.UUID;

//@QuarkusTest
@ExtendWith(MockitoExtension.class)
class CreateNewAppointmentUseCaseTest {

    @Mock
    TicketRepository ticketRepositoryStub;

    @Mock
    UserModelRepository userModelRepositoryStub;

    @InjectMocks
    CreateNewAppointmentUseCase underTest;

    @Spy
    Ticket ticketSpy;

    @Captor
    ArgumentCaptor<Appointment> appointmentArgumentCaptor;

     final UUID FAKE_VALID_TICKET_ID = UUID.randomUUID();

    final UUID FAKE_VALID_ATTENDANT_ID = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        var aTicket = Ticket.create(
                1L,
                "Título do apontamento",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry",
                Priority.BAIXA,
                null,
                Mockito.mock(Customer.class),
                Mockito.mock(Tenant.class),
                Mockito.mock(ServiceDesk.class),
                null
        );

        this.ticketSpy = Mockito.spy(aTicket);

        Mockito.when(ticketRepositoryStub.findByIdOptional(FAKE_VALID_TICKET_ID))
                .thenReturn(Optional.of(ticketSpy));

        Mockito.when(userModelRepositoryStub.findAttendantByIdOptional(FAKE_VALID_ATTENDANT_ID))
                .thenReturn(Optional.of(Mockito.mock(Attendant.class)));

        Mockito.when(ticketRepositoryStub.findByAppointment_AccountableAnd_DateAnd_BeginningAtLessThanEqualAnd_EndingAtGreaterThanEqual( Mockito.isA(Appointment.class)))
                .thenReturn(0L);
    }

    @Test
    void givenAppointment_whenIsValid_thenExecuteUseCase() {
        var beginningAt = OffsetTime.of(LocalTime.of(12,10), ZoneOffset.ofHours(-3));
        var endingAt = OffsetTime.of(LocalTime.of(14,10), ZoneOffset.ofHours(-3));

        var input = setUpAppointmentInputDTO(beginningAt, endingAt);

        var expectedAppointment = underTest.execute(input);

        Mockito.verify(userModelRepositoryStub,Mockito.times(1)).findAttendantByIdOptional(Mockito.isA(UUID.class));
        Mockito.verify(ticketRepositoryStub,Mockito.times(1)).findByIdOptional(Mockito.isA(UUID.class));
        Mockito.verify(ticketRepositoryStub,Mockito.times(1)).findByAppointment_AccountableAnd_DateAnd_BeginningAtLessThanEqualAnd_EndingAtGreaterThanEqual(Mockito.isA(Appointment.class));
        Mockito.verify(ticketRepositoryStub, Mockito.times(1)).persistAndFlush(ticketSpy);

        Mockito.verify(ticketSpy).attachAppointment(appointmentArgumentCaptor.capture());
        Assertions.assertEquals(appointmentArgumentCaptor.getValue(), expectedAppointment);

        Assertions.assertEquals(1, ticketSpy.getAppointments().size());
        Assertions.assertEquals(expectedAppointment, ticketSpy.getAppointments().get(0));
        Assertions.assertEquals(beginningAt, expectedAppointment.getBeginningAt());
        Assertions.assertEquals(endingAt, expectedAppointment.getEndingAt());
    }

    private AppointmentInputDTO setUpAppointmentInputDTO(OffsetTime beginningAt, OffsetTime endingAt) {

        return new AppointmentInputDTO(
                OffsetDateTime.now(),
                beginningAt,
                endingAt,
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry",
                FAKE_VALID_ATTENDANT_ID,
                FAKE_VALID_TICKET_ID
        );
    }
}