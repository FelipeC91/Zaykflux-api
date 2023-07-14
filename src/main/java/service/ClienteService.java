package service;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.UriInfo;
import model.Cliente;
import repository.ClienteRepository;
import service.exception.ClienteAlreadyExistsException;

public class ClienteService {

    @Inject
    ClienteRepository clienteRepository;


    @Transactional
    public Cliente save(Cliente cliente, UriInfo uriInfo) {
            var clienteOptional = clienteRepository.findByRazaoSocialOrCpfCnpj(cliente.getRazaoSocial(), cliente.getCpfCnpj());

            if (clienteOptional.isPresent())
                throw new ClienteAlreadyExistsException("Cliente Já possui cadastro", uriInfo);

            clienteRepository.persist(cliente);

            return  clienteRepository.findByRazaoSocialOrCpfCnpj(cliente.getRazaoSocial(), cliente.getCpfCnpj())
                    .get();
    }


}
