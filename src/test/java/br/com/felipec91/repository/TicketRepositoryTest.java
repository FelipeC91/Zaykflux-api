package br.com.felipec91.repository;

import br.com.felipec91.domain.exception.TicketNotFoundException;
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
    void givenAnExistentTicketNumber_whenFindByNumber_thenShouldFindTicket() {
        var existentTickerNumber = 1L;
        var existentTicketTitle = "LOJA AÇAILAND";

        var resultList = underTest.findByNumber(existentTickerNumber);

        assertNotNull(resultList);
        assertEquals(existentTicketTitle, resultList.get(0).getTitle());
    }

    @Test
    void givenAnSearchKeyWord_whenFindByTitleOrCustomerTradingName_thenShouldFindTicket() {
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
    void findByAppointment_AccountableAnd_DateAnd_BeginningAtLessThanEqualAnd_EndingAtGreaterThanEqual_thenShouldFindOne() {
        var existentAppointment = underTest.findById(UUID.fromString("5249812b-e8f9-480d-a8db-2ded686dee6b")).getAppointments().get(0);

        Long possibleAppointment = underTest.findByAppointment_AccountableAnd_DateAnd_BeginningAtLessThanEqualAnd_EndingAtGreaterThanEqual(existentAppointment);

        Assertions.assertEquals(1, possibleAppointment.intValue());
    }



    @Test
    void findByCriteriaFilter() {
    }
}