package br.com.felipec91.resource;

import br.com.felipec91.domain.model.customer.entity.Customer;
import br.com.felipec91.domain.model.group.entity.GroupModel;
import br.com.felipec91.domain.model.service_desk.entity.ServiceDesk;
import br.com.felipec91.domain.repository.ServiceDeskRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static io.restassured.RestAssured.given;

@QuarkusTest()
public class CustomerResourceTest {

    @Inject
    ServiceDeskRepository serviceDeskRepository;


    @Test
    public void TestCustomerGetEndPoint() {

        given()
                .when().get("/customers")
                .then()
                .statusCode(200)
                .body("size()", CoreMatchers.not(0));

    }

    @Test
    public void whenAnyValidWordIsSearched_thenFindIt() {
        var searchedName = "ant"; // some valid name: "SANTA RITA";

                 given()
                 .queryParam("name", searchedName)
                 .get("/customers/search")
                 .then()
                 .statusCode(200)
                 .body("size()", CoreMatchers.not(0), "[0].tradingName", CoreMatchers.containsStringIgnoringCase(searchedName));

    }

    @Test
    public void givenNotMachedWord_whenIsSearched_thenNotFindIt() {
        var searchedName = "dghldfojhmrptgojm"; // some valid name: "SANTA RITA";


                given()
                        .queryParam("name", searchedName)
                        .get("/customers/search")
                        .then()
                        .statusCode(404);

    }

    @Test
    public void givenCustomerCandidate_whenValid_thenCreateIt() {
        var sds = List.<ServiceDesk>of(
            new ServiceDesk( UUID.fromString("642b0cfc-6729-4c82-80c1-c0ebb6f28fb4"), null, null,null)
        );

        var groups = List.<GroupModel>of(
                new GroupModel(UUID.fromString("dbe99d91-b02e-48d6-a212-948599292aba"), null, null),
                new GroupModel(UUID.fromString("b9fb7589-07a7-4f43-a885-e07892002b1b"), null, null),
                new GroupModel(UUID.fromString("19fec72e-550d-4e85-bdf2-fa5d3a96dd11"), null, null),
                new GroupModel(UUID.fromString("f4265039-f95e-455e-be16-3d3a0f7dcdc8"), null, null)
        );
        var customerCandidate = Customer.create(null, "TEST 123", "\"POSTO TESTE2 LTDA", "14516114586511",null,null, null,sds,groups);

        given()
                .contentType(ContentType.JSON)
                .body(customerCandidate)
                .when()
                .post("/customers/new")
                .then()
                .log().all()
                .statusCode(201)
                .body( "tradingName", CoreMatchers.endsWith("123"), "cpfCnpj", CoreMatchers.containsString("14516114586511") );


    }
}
