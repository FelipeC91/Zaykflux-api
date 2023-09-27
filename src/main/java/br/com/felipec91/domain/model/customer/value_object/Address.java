package br.com.felipec91.domain.model.customer.value_object;


import br.com.felipec91.domain.model.city.entity.City;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.Objects;

@Embeddable
public class Address {

    @Length(max = 8, min = 8)
    @NotBlank(message = "Campo CEP deve ser preenchido")

    @Column(name = "zip_code")
    private String zipCode;

    @NotBlank(message = "Campo Bairro deve ser preenchido")
    private String neighborhood;

    @NotBlank(message = "Campo Logradouro deve ser preenchido")
    private String street;
    private String complement;

    @NotNull(message = "Campo Numero deve ser preenchido")
    private Integer number;

    @NotNull

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    public Address(){}

    public Address(String zipCode, String neighborhood, String street, String complement, Integer number, City city) {
        this.zipCode = zipCode;
        this.neighborhood = neighborhood;
        this.street = street;
        this.complement = complement;
        this.number = number;
        this.city = city;
    }




    public String getZipCode() {
        return zipCode;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public String getStreet() {
        return street;
    }


    public String getComplement() {
        return complement;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public City getCity() {
        return city;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(zipCode, address.zipCode) && Objects.equals(neighborhood, address.neighborhood) && Objects.equals(street, address.street) && Objects.equals(complement, address.complement) && Objects.equals(number, address.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(zipCode, neighborhood, street, complement, number);
    }


}