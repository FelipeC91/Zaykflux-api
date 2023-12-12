package br.com.felipec91.application.web_resource;

import br.com.felipec91.domain.model.ticket.value_object.Priority;
import br.com.felipec91.domain.repository.TicketRepository;
import br.com.felipec91.infrastructure.web.dto.ticket.TicketInputDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class TicketResourceTest {

    @Inject
    TicketRepository ticketRepository;


    @Test
    @Order(1)
    public void givenTicketCandidate_whenValid_thenCreate() {
        var ticketValidInputDTO = new TicketInputDTO(
                UUID.fromString("4f653c10-cab3-40d8-8518-c160dbeb74c4"),
                UUID.fromString("a257fdad-672f-49bb-b6cd-1effe7b678a4"),
                "UPGRADE DE HARDWARE",
                "Máquinas apresentam extrema lentidão. Necessio que avliem a possiblidade de upgrade.",
                Priority.BAIXA,
                null,
                UUID.fromString("55f55ba5-9c67-48d1-b8e8-dd60705c98dd"),
                "Suporte a infraestrutura / Suporte a PDV / Upgrade de hardware"
        );

        Long expectedTicketNumber =  ticketRepository.count() + 1;

        given()
                .contentType(ContentType.JSON)
                .body(ticketValidInputDTO)
                .when().post("/tickets/new")
                .then()
                .log().all()
                .statusCode(201)
                .body("id", CoreMatchers.notNullValue(),
                        "number", CoreMatchers.equalTo(expectedTicketNumber.intValue()),
                        "title", CoreMatchers.equalTo("UPGRADE DE HARDWARE"),
                        "description", CoreMatchers.notNullValue()
                );


    }

}
