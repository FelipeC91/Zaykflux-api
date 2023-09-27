package br.com.felipec91.resource;

import br.com.felipec91.domain.model.customer.value_object.ContactType;
import br.com.felipec91.domain.model.customer.value_object.Contact;
import br.com.felipec91.domain.repository.CustomerRepository;
import br.com.felipec91.domain.service.CustomerService;
import br.com.felipec91.infrastructure.web.dto.customer.OldAndNewContactInputDTO;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CustomerContactResourceTest {

    @Inject
    CustomerRepository customerRepository;

    @Inject
    CustomerService customerService;

    @Test
    @Order(1)
    public void givenNewContact_whenValid_saveIt(){
        var validContact = new Contact(
                                "Maria",
                                "teste@teste.com",
                                "11911112222",
                                ContactType.PROFISSIONAL
                        );

        var existentCustomerId = customerRepository.findByTradingNameIgnoreCase("AÇAILAND")
                                            .get(0)
                                            .getId();

        given()
                .contentType(ContentType.JSON)
                .pathParams("customerId" ,existentCustomerId)
                .body(validContact)
                .post("/customers/{customerId}/contacts")
                .then()
                    .log().all()
                    .statusCode(201)
                    .body("name", CoreMatchers.equalTo(validContact.getName()),
                            "email", CoreMatchers.equalTo(validContact.getEmail()),
                            "phone", CoreMatchers.equalTo(validContact.getPhone()),
                            "contactType", CoreMatchers.equalTo(validContact.getContactType().toString())
                    );
    }



    @Test
    @Order(2)
    public void givenAnContact_whenExists_editIt() {
        var existentCustomerId = customerRepository.findByTradingNameIgnoreCase("AÇAILAND")
                .get(0)
                .getId();

        var oldContact = new Contact(
                "Maria",
                "teste@teste.com",
                "11911112222",
                ContactType.PROFISSIONAL
        );

        var newContact = new Contact(
                "Jose",
                "teste@teste.com",
                "11911112222",
                ContactType.PESSOAL
        );

        given()
                .contentType(ContentType.JSON)
                .pathParams("customerId" ,existentCustomerId)
                .body(new OldAndNewContactInputDTO(oldContact, newContact))
                .put("/customers/{customerId}/contacts")
                .then()
                    .log().all()
                    .statusCode(202)
                    .body("name", CoreMatchers.equalTo(newContact.getName()),
                    "email", CoreMatchers.equalTo(newContact.getEmail()),
                            "phone", CoreMatchers.equalTo(newContact.getPhone()),
                            "contactType", CoreMatchers.equalTo(newContact.getContactType().toString())
        );
    }

    @Test
    @Order(3)
    public void givenAnContact_whenExists_deleteIt() {
        var anCustomer = customerRepository.findByTradingNameIgnoreCase("AÇAILAND").get(0);


        var existentContact = new Contact("Teste",
                                    "test@test.com",
                                    "11933334444",
                                    ContactType.PESSOAL
                                    );


        given()
                .contentType(ContentType.JSON)
                .pathParams("customerId" ,anCustomer.getId())
                .body(existentContact)
                .delete("/customers/{customerId}/contacts")
                .then()
                .log().all()
                .statusCode(202);

        var contactsAfter = customerRepository.findByTradingNameIgnoreCase("AÇAILAND").get(0).getContacts();

        Assertions.assertFalse(contactsAfter.contains(existentContact));

    }


}
