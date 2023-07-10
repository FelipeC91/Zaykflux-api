package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.UriInfo;
import model.Endereco;
import repository.CidadeRepository;
import repository.ClienteRepository;
import repository.EnderecoRepository;
import service.exception.ClienteNonExistentExeception;

@ApplicationScoped
public class EnderecoService {

    @Inject
    ClienteRepository clienteRepository;

    @Inject
    EnderecoRepository enderecoRepository;

    @Inject
    CidadeRepository cidadeRepository;


    @Transactional
    public Endereco save(Long clienteId, Endereco endereco, UriInfo uriInfo) {
       var clienteOptional = clienteRepository.findByIdOptional(clienteId);

        if (clienteOptional.isEmpty())
            throw new ClienteNonExistentExeception("Cliente inexistente", uriInfo);


        System.out.println(endereco.toString());
        endereco.setCliente(clienteOptional.get());

        enderecoRepository.persistAndFlush(endereco);

        return enderecoRepository.findIfExistsInCliente(endereco, clienteId);
    }


}
