package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import model.Endereco;
import repository.ClienteRepository;

@ApplicationScoped
public class EnderecoService {

    @Inject
    ClienteRepository clienteRepository;


    @Transactional
    public void save(Long clienteId, Endereco endereco) {
       var clienteOptional = clienteRepository.findByIdOptional(clienteId);


    }
}
