package service;

import jakarta.inject.Inject;
import model.Cliente;
import repository.ClienteRepository;

import java.util.Optional;

public class ClienteService {

    @Inject
    ClienteRepository clienteRepository;


}
