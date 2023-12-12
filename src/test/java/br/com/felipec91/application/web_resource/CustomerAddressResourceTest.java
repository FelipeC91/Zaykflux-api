package br.com.felipec91.application.web_resource;

import br.com.felipec91.domain.model.customer.value_object.Address;
import br.com.felipec91.domain.repository.CityRepository;
import br.com.felipec91.domain.repository.CustomerRepository;
import br.com.felipec91.infrastructure.web.dto.customer.OldAndNewAddressInputDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CustomerAddressResourceTest {

    @Inject
    CustomerRepository customerRepository;

    @Inject
    CityRepository cityRepository;


    @Test
    @Order(1)
    public void givenNewContact_whenValid_saveIt(){
        var city = cityRepository.findByName("São Paulo");

        var validAddress = new Address("01000000",
                "Vl. Teste",
                "R. Teste",
                null,
                123,
                city
        );

        var existentCustomerId = customerRepository.findByTradingNameIgnoreCase("AÇAILAND")
                .get(0)
                .getId();

        given()
                .contentType(ContentType.JSON)
                .pathParams("customerId" ,existentCustomerId)
                .body(validAddress)
                .post("/customers/{customerId}/addresses")
                .then()
                .log().all()
                .statusCode(201)
                .body("zipCode", CoreMatchers.equalTo(validAddress.getZipCode()),
                        "neighborhood", CoreMatchers.equalTo(validAddress.getNeighborhood()),
                        "street", CoreMatchers.equalTo(validAddress.getStreet()),
                        "number", CoreMatchers.equalTo(validAddress.getNumber()),
                        "city.name", CoreMatchers.equalTo(validAddress.getCity().getName())
                );
    }



    @Test
    @Order(2)
    public void givenAAddress_whenExists_thenShouldEditIt() {
        var existentCustomerId = customerRepository.findByTradingNameIgnoreCase("AÇAILAND")
                .get(0)
                .getId();



        var city = cityRepository.findByName("São Paulo");

        var oldAddress = new Address("03306010",
                "Cidade Mãe do Céu",
                "Rua Domingos Agostim",
                null,
                91,
                city
        );


        var newAddress = new Address("01000000",
                "Vl. Teste2",
                "R. Teste2",
                "teste",
                123,
                city
        );

        given()
                .contentType(ContentType.JSON)
                .pathParams("customerId" ,existentCustomerId)
                .body(new OldAndNewAddressInputDTO(oldAddress, newAddress))
                .put("/customers/{customerId}/addresses")
                .then()
                .log().all()
                .statusCode(202)
                .body("zipCode", CoreMatchers.equalTo(newAddress.getZipCode()),
                    "neighborhood", CoreMatchers.equalTo(newAddress.getNeighborhood()),
                        "complement", CoreMatchers.equalTo(newAddress.getComplement()),
                        "number", CoreMatchers.equalTo(newAddress.getNumber()),
                        "city.name", CoreMatchers.equalTo(newAddress.getCity().getName())
                );
    }

    @Test
    @Order(3)
    public void givenAAddress_whenExists_thenShouldDeleteIt() {
        var existentCustomerId = customerRepository.findByTradingNameIgnoreCase("AÇAILAND")
                .get(0)
                .getId();



        var city = cityRepository.findByName("São Paulo");

        var existentAddress = new Address("03306010",
                "Cidade Mãe do Céu",
                "ua Domingos Agostim",
                null,
                91,
                city
        );


        given()
                .contentType(ContentType.JSON)
                .pathParams("customerId" ,existentCustomerId)
                .body(existentAddress)
                .delete("/customers/{customerId}/addresses")
                .then()
                .log().all()
                .statusCode(202);

    }
}
