package br.com.felipec91.domain.model.customer.entity;

import br.com.felipec91.domain.model.customer.value_object.Address;
import br.com.felipec91.domain.model.customer.value_object.Contact;
import br.com.felipec91.domain.model.concept.AggregateRoot;
import br.com.felipec91.domain.model.service_desk.entity.ServiceDesk;
import br.com.felipec91.domain.model.group.entity.GroupModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.*;

@Entity
public class Customer extends AggregateRoot {
    private Boolean active;

    @NotNull(message = "Campo Nome Fantasia deve ser preenchido")
    @Column(name = "trading_name")
    private String tradingName; //nome fantasia

    @NotNull(message = "Campo Razão Social deve ser preenchido")
    @Column(name = "company_name")
    private String companyName; //razao social

    @NotNull(message = "Campo CPF/CNPJ deve ser preenchido")
    @Pattern(regexp = "\\d{11,14}", message = "Formato inválido de CPF/CNPJ")
    @Column(name = "cpf_cnpj")
    private String cpfCnpj;

    @Column(name = "photo_url")
    private String photoUrl;

//    @JsonManagedReference
    @ElementCollection
    @CollectionTable(name = "contact", joinColumns = @JoinColumn(name = "customer_id"))
    private List<Contact> contacts;


    @ElementCollection
    @CollectionTable(name = "address", joinColumns = @JoinColumn(name = "customer_id"))
    private List<Address> addresses;


    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "customer_service_desk",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "service_desk_id")
    )
    private List<ServiceDesk> allowedServiceDesks;


    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "customer_group_model",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "group_model_id")
    )
    private List<GroupModel> attendantGroups;






    public Customer() {
    }

    private Customer(UUID id, Boolean active, String tradingName, String companyName, String cpfCnpj, String photoUrl, List<Contact> contacts, List<Address> addresses, List<ServiceDesk> allowedServiceDesks, List<GroupModel> attendantGroups) {
        super(id);
        this.active = active;
        this.tradingName = tradingName;
        this.companyName = companyName;
        this.cpfCnpj = cpfCnpj;
        this.photoUrl = photoUrl;
        this.contacts = contacts;
        this.addresses = addresses;
        this.allowedServiceDesks = allowedServiceDesks;
        this.attendantGroups = attendantGroups;
    }

    public static Customer create(UUID id,  String tradingName, String companyName, String cpfCnpj, String photoUrl, List<Contact> contacts, List<Address> addresses, List<ServiceDesk> allowedServiceDesks, List<GroupModel> attendantGroups) {
        return new Customer(id,
                            true,
                            tradingName,
                            companyName,
                            cpfCnpj,
                            photoUrl,
                            contacts,
                            addresses,
                            allowedServiceDesks,
                            attendantGroups
                );
    }

    public Boolean getActive() {
        return active;
    }

    public String getTradingName() {
        return tradingName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public List<Contact> getContacts() {
        if ( Objects.isNull(this.contacts) )
            this.contacts = new ArrayList<>();

        return Collections.unmodifiableList(contacts);
    }

    public void removeContact(Contact contact) {
        this.contacts.remove(contact);
    }

    public int addNewContact(Contact anContact) {
        if ( !this.contacts.contains(anContact) )
            this.contacts.add(anContact);

        return this.contacts.indexOf(anContact);
    }

    public List<Address> getAddresses() {
        if ( Objects.isNull(this.addresses) )
            this.addresses = new ArrayList<>();

        return Collections.unmodifiableList(addresses);
    }

    public int addNewAddress(Address anAddress) {
        if ( !this.addresses.contains(anAddress) )
            addresses.add(anAddress);

        return this.addresses.indexOf(anAddress);
    }

    public void removeAddress(Address address) {
        this.addresses.remove(address);
    }


    public List<ServiceDesk> getAllowedServiceDesks() {
        if ( Objects.isNull(this.allowedServiceDesks) )
            this.allowedServiceDesks = new ArrayList<>();

        return Collections.unmodifiableList(allowedServiceDesks);
    }

    public void clearAllAllowedServiceDesks() {
        this.allowedServiceDesks.clear();
    }

    public void defineAllowServiceDesks(List<ServiceDesk> allowedServiceDesks) {
        this.allowedServiceDesks.addAll(allowedServiceDesks);
    }

    public List<GroupModel> getAttendantGroups() {
        if ( Objects.isNull(this.attendantGroups) )
            this.attendantGroups = new ArrayList<>();

        return Collections.unmodifiableList(attendantGroups);
    }
    public void defineAllAttendantGroups(List<GroupModel> attGroups) {
        this.attendantGroups.addAll(attGroups);
    }

    public void setActive(boolean updatedStatus) {
        this.active = updatedStatus;
    }

    public boolean isAllowedServiceDesk(ServiceDesk serviceDeskCandidate) {
        return this.getAllowedServiceDesks().contains(serviceDeskCandidate);
    }

}
