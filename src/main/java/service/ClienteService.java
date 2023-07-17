package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.UriInfo;
import model.Cliente;
import repository.ClienteRepository;
import service.exception.ClienteAlreadyExistsException;


@ApplicationScoped
public class ClienteService {

    @Inject
    ClienteRepository clienteRepository;


    @Transactional
    public Cliente save(Cliente cliente) {
        var clienteOptional = clienteRepository.findByRazaoSocialOrCpfCnpj(cliente.getRazaoSocial(), cliente.getCpfCnpj());

        if (clienteOptional.isPresent())
            throw new ClienteAlreadyExistsException("Cliente Já possui cadastro");

        cliente.setAtivo(true);

        clienteRepository.persistAndFlush(cliente);

        return  clienteRepository.findByRazaoSocialOrCpfCnpj(cliente.getRazaoSocial(), cliente.getCpfCnpj())
                    .get();
    }


}
