package br.com.felipec91.repository;

import br.com.felipec91.domain.model.ticket.value_object.Appointment;
import br.com.felipec91.infrastructure.db.repositoryImpl.TicketRepositoryImpl;
import br.com.felipec91.infrastructure.db.repositoryImpl.UserModelRepositoryImpl;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class TicketRepositoryTest {

    @Inject
    TicketRepositoryImpl underTest;

    @Inject
    UserModelRepositoryImpl userModelRepository;

    @Test
    void givenAnExistentTicketNumber_whenFindByNumber_thenFindTicket() {
        var existentTickerNumber = 1L;
        var existentTicketTitle = "LOJA AÇAILAND";

        var resultList = underTest.findByNumber(existentTickerNumber);

        assertNotNull(resultList);
        assertEquals(existentTicketTitle, resultList.get(0).getTitle());
    }

    @Test
    void givenAnSearchKeyWord_whenFindByTitleOrCustomerTradingName_thenFindTicket() {
        var validTitleSearchKey = "LOJA AÇAILAND";
        var validCustomerNameSearchKey = "JAND"; //...AIA

        var resultListTitleSearch = underTest.findByTitleOrCustomerTradingName(validTitleSearchKey);
        var resultListCustomerSearch = underTest.findByTitleOrCustomerTradingName(validCustomerNameSearchKey);

        Assertions.assertFalse(resultListTitleSearch.isEmpty());
        Assertions.assertEquals(validTitleSearchKey, resultListTitleSearch.get(0).getTitle());
        Assertions.assertFalse(resultListCustomerSearch.isEmpty());
        Assertions.assertTrue(resultListCustomerSearch.get(0).getCustomer().getTradingName().contains(validCustomerNameSearchKey));
    }

    @Test
    void findByAppointment_AccountableAnd_DateAnd_BeginningAtLessThanEqualAnd_EndingAtGreaterThanEqual() {
        var userModel = userModelRepository.findById(UUID.fromString("2665c568-abf6-458e-8801-658fa16ae552"));
        var existentAppointment = Appointment.create(
                        LocalDate.of(2023,12,19),
                        OffsetTime.of(LocalTime.of(13,30,0), ZoneOffset.of("-03:00")),
                        OffsetTime.of(LocalTime.of(14,30,0), ZoneOffset.of("-03:00")),
                "Realizamos nova visita à loja para nova avaliação. Quanto as duas câmera que não funcionam:\\n1. Uma delas (9) não foi instalada mas tem seu cabeamento passado até a beer\\ncave. Beer cave esta que, no momento da entrega da loja, não existia.  Ou seja: neste setor será necessária a instalação de nova câmera.\\n2. Nesta (2), localizada na entrada da loja, identificamos em testes que o cabo que a leva até o local está danificado em algum ponto da passagem.\\nSerá necessária nova visita para atuar nesta pendências.",
                        userModel
        );



        Long possibleAppointment = underTest.findByAppointment_AccountableAnd_DateAnd_BeginningAtLessThanEqualAnd_EndingAtGreaterThanEqual(existentAppointment);

        Assertions.assertEquals(1, possibleAppointment.intValue());
    }



    @Test
    void findByCriteriaFilter() {
    }
}