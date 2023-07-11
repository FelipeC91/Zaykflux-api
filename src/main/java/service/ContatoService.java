package service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.UriInfo;
import model.Contato;
import repository.ClienteRepository;
import repository.ContatoRepository;
import service.exception.ClienteNonExistentExeception;
import service.exception.ContatoAlreadyLinkendWithCliente;

import java.util.Optional;

@ApplicationScoped
public class ContatoService {

    @Inject
    ContatoRepository contatoRepository;

    @Inject
    ClienteRepository clienteRepository;

    @Transactional
    public Contato save(Long clienteId, Contato contato, UriInfo uriInfo) {
        var clienteOptional = clienteRepository.findByIdOptional(clienteId);

        if (clienteOptional.isEmpty())
            throw new ClienteNonExistentExeception("Cliente inexistente", uriInfo);

       var contatoOptional = contatoRepository. findByEmailAndCliente(contato.getEmail(), clienteId);

        if (contatoOptional.isPresent())
            throw new ContatoAlreadyLinkendWithCliente("Email de contato já vinculado a outro contato deste este cliente", uriInfo);


        contato.setCliente(clienteOptional.get());

            contatoRepository.persistAndFlush(contato);



        return contatoRepository.findByEmailAndCliente(contato.getEmail(), clienteId )
                                    .get();

    }
}
