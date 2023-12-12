package br.com.felipec91.application.use_case;

import br.com.felipec91.domain.model.customer.entity.Customer;
import br.com.felipec91.domain.model.group.entity.GroupModel;
import br.com.felipec91.domain.model.service_desk.entity.ServiceDesk;
import br.com.felipec91.domain.model.ticket.entity.Ticket;
import br.com.felipec91.domain.model.ticket.value_object.Priority;
import br.com.felipec91.domain.model.user.entity.Tenant;
import br.com.felipec91.domain.repository.CustomerRepository;
import br.com.felipec91.domain.repository.ServiceDeskRepository;
import br.com.felipec91.domain.repository.TicketRepository;
import br.com.felipec91.domain.repository.UserModelRepository;
import br.com.felipec91.infrastructure.web.dto.ticket.TicketInputDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//@QuarkusTest
@ExtendWith(MockitoExtension.class)
 class OpenNewTicketUseCaseTest {


    @Mock
    CustomerRepository stubCustomerRepository;

    @Mock
    ServiceDeskRepository stubServiceDeskRepository;

    @Mock
    UserModelRepository stubTenantRepository;

    @Mock
    TicketRepository stubTicketRepository;

    @InjectMocks
    OpenNewTicketUseCase underTest;

    ServiceDesk aServiceDesk;

    Tenant aTenant;

    Customer aCustomer;

    @BeforeEach
    void setUp() {
        aServiceDesk =  new ServiceDesk(
                                UUID.randomUUID(),
                                "Presencial",
                                null,
                                null
                        );
        var serviceDeskList = List.of(aServiceDesk);

        var groupsList = List.of(
                                                    new GroupModel(
                                                            UUID.randomUUID(),
                                                            "Técnico",
                                                            null
                                                    )
                                            );

        aCustomer = Customer.create(
                            UUID.randomUUID(),
                            "AÇAILAND",
                            "SIMPLES E NATURAL AÇAI LTDA",
                            "12456789101112",
                            null,
                            null,
                            null,
                            serviceDeskList,
                            groupsList
                        );
        Mockito.when(stubCustomerRepository.findByIdOptional(aCustomer.getId()))
                .thenReturn(Optional.of(aCustomer));

        Mockito.when(stubServiceDeskRepository.findByIdOptional(aServiceDesk.getId()))
                .thenReturn(Optional.of(aServiceDesk));

        aTenant = Tenant.create(
                        UUID.randomUUID(),
                        "testName",
                        "test@test.com"
                    );

        Mockito.when(stubTenantRepository.findTenantByIdOptional(aTenant.getId()))
                .thenReturn(Optional.of(aTenant));

        Mockito.doNothing().when(stubTicketRepository)
                .persistAndFlush(Mockito.isA(Ticket.class));

        Mockito.when(stubTicketRepository.count())
                .thenReturn(1L);
    }

    @Test
    void givenATicketCandidate_whenValid_thenShouldExecuteUseCase() {
        var ticketTitle = "Problema reportado...";

        var input = new TicketInputDTO(
                            aCustomer.getId(),
                            aTenant.getId(),
                            ticketTitle,
                            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                            Priority.ALTA,
                            null,
                            aServiceDesk.getId(),
                            null
                        );

        var newTicketExpected = underTest.execute(input);

        Mockito.verify(stubCustomerRepository,Mockito.times(1)).findByIdOptional(aCustomer.getId());
        Mockito.verify(stubTenantRepository,Mockito.times(1)).findTenantByIdOptional(aTenant.getId());
        Mockito.verify(stubServiceDeskRepository,Mockito.times(1)).findByIdOptional(aServiceDesk.getId());
        Mockito.verify(stubTicketRepository,Mockito.times(1)).count();
        Mockito.verify(stubTicketRepository,Mockito.times(1)).persistAndFlush(Mockito.isA(Ticket.class));

        Assertions.assertNotNull(newTicketExpected);
        Assertions.assertEquals(ticketTitle, newTicketExpected.getTitle());
        Assertions.assertEquals(2L, newTicketExpected.getNumber());
    }


}