package br.com.felipec91.domain.service;

import br.com.felipec91.domain.exception.CustomerAlreadyExistsException;
import br.com.felipec91.domain.exception.CustomerNotFoundException;
import br.com.felipec91.domain.model.customer.entity.Customer;
import br.com.felipec91.domain.model.customer.value_object.Address;
import br.com.felipec91.domain.model.customer.value_object.Contact;
import br.com.felipec91.domain.model.group.entity.GroupModel;
import br.com.felipec91.domain.model.service_desk.entity.ServiceDesk;
import br.com.felipec91.domain.repository.CustomerRepository;
import br.com.felipec91.domain.repository.GroupModelRepository;
import br.com.felipec91.domain.repository.ServiceDeskRepository;
import br.com.felipec91.infrastructure.web.dto.customer.OldAndNewAddressInputDTO;
import br.com.felipec91.infrastructure.web.dto.customer.OldAndNewContactInputDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class CustomerService {

    @Inject
    CustomerRepository customerRepository;

    @Inject
    ServiceDeskRepository serviceDeskRepository;

    @Inject
    GroupModelRepository groupModelRepository;



    @Transactional
    public Customer save(Customer customerCandidate) {
        var clienteOptional = customerRepository.findByTradingNameIgnoreCaseOrCpfCnpjOptional(customerCandidate.getCompanyName(), customerCandidate.getCpfCnpj());

        if (clienteOptional.isPresent())
            throw new CustomerAlreadyExistsException("Cliente Já possui cadastro");


        var allowedServiceDesks = customerCandidate.getAllowedServiceDesks()
                                                .stream()
                                                .map(ServiceDesk::getId)
                                                .collect(Collectors.toSet());

        customerCandidate.clearAllAllowedServiceDesks();

        customerCandidate.defineAllowServiceDesks( serviceDeskRepository.findByIdBatch(allowedServiceDesks) );

        var attendantGroups = customerCandidate.getAttendantGroups()
                                                        .stream()
                                                        .map(GroupModel::getId)
                                                        .collect(Collectors.toSet());

        customerCandidate.clearAllAllowedServiceDesks();
        customerCandidate.defineAllAttendantGroups( groupModelRepository.findByIdBatch(attendantGroups) );


        customerCandidate.setActive(true);

        customerRepository.persistAndFlush(customerCandidate);

        return  customerCandidate;

    }

    @Transactional
    public Customer updateCustomerStatus(UUID customerId, boolean updatedStatus) {
        var customer = getCustomerIfExists(customerId);

        customer.setActive(updatedStatus);

        customerRepository.flush();

        return customer;

    }

    
    @Transactional
    public Contact registerNewContact(UUID customerId, Contact newContact) {
        var validCustomer = getCustomerIfExists(customerId);
        var collectionIndex = validCustomer.addNewContact(newContact);

        customerRepository.persistAndFlush(validCustomer);

        return validCustomer.getContacts().get(collectionIndex);
    }

    private Customer getCustomerIfExists(UUID customerId) {
        return customerRepository.findByIdOptional(customerId)
                .orElseThrow(() ->new CustomerNotFoundException("Usuário referenciado Não encontrado"));
    }


    @Transactional
    public void deleteContact(UUID customerId, Contact contact) {
        var validCustomer = customerRepository.findByIdOptional(customerId)
                                            .orElseThrow(() ->new CustomerNotFoundException("Usuário referenciado Não encontrado"));

        validCustomer.removeContact(contact);

        customerRepository.persistAndFlush(validCustomer);
    }

    @Transactional
    public Contact updateContact(UUID customerId, OldAndNewContactInputDTO oldValueNewValueContactInputDTO) {
        var validCustomer = getCustomerIfExists(customerId);

        validCustomer.removeContact(oldValueNewValueContactInputDTO.oldContact());

        var updatedContactIndex = validCustomer.addNewContact(oldValueNewValueContactInputDTO.newContact());

        customerRepository.persistAndFlush(validCustomer);

        return validCustomer.getContacts().get(updatedContactIndex);

    }

    public List<Contact> getAllContacts(UUID customerId) {
        var validCustomer = getCustomerIfExists(customerId);

        return validCustomer.getContacts();
    }



    @Transactional
    public Address registerNewAddresses(UUID customerId, Address newAddress) {
        var validCustomer = getCustomerIfExists(customerId);

        var collectionIndex = validCustomer.addNewAddress(newAddress);

        customerRepository.persistAndFlush(validCustomer);

        return validCustomer.getAddresses().get(collectionIndex);
    }


    @Transactional
    public void deleteAddress(UUID customerId, Address address) {
        var validCustomer = getCustomerIfExists(customerId);

        validCustomer.removeAddress(address);

        customerRepository.persistAndFlush(validCustomer);
    }

    @Transactional
    public Address updateAddress(UUID customerId, OldAndNewAddressInputDTO oldAndNewAddressInputDTO) {
        var validCustomer = getCustomerIfExists(customerId);

        validCustomer.removeAddress(oldAndNewAddressInputDTO.oldAddress());

        var updatedContactIndex = validCustomer.addNewAddress(oldAndNewAddressInputDTO.newAddress());

        customerRepository.persistAndFlush(validCustomer);

        return validCustomer.getAddresses().get(updatedContactIndex);

    }

    public List<Address> getAllAddresses(UUID customerId) {
        var validCustomer = getCustomerIfExists(customerId);

        return validCustomer.getAddresses();
    }

}
