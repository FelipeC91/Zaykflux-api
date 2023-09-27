package br.com.felipec91.domain.model.customer.value_object;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.Objects;

@Embeddable
public class Contact {

    @NotBlank(message = "Campo Nome deve ser preenchido")
    private  String name;

    @Email(message = "E-mail em formato inválido")
    private String email;

    @Length(max = 11, min = 11, message = "Telefone deve possuir formato e numero total de dígitos válido")
    @NotBlank(message = "Campo Número deve ser preenchido")
    private String phone;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "contact_type")
    private ContactType contactType;


    public Contact() {
    }

    public Contact(String name, String email, String phone, ContactType contactType) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.contactType = contactType;
    }


    public String getName() {
        return name;
    }


    public String getEmail() {
        return email;
    }


    public String getPhone() {
        return phone;
    }


    public ContactType getContactType() {
        return contactType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(name, contact.name) && Objects.equals(email, contact.email) && Objects.equals(phone, contact.phone) && contactType == contact.contactType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, phone, contactType);
    }
}