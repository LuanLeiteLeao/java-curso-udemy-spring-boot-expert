package io.github.luanleiteleao.service;

import io.github.luanleiteleao.model.Cliente;
import io.github.luanleiteleao.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    private ClienteRepository repository;

    public ClienteService(ClienteRepository repository){
        this.repository = repository;
    }

    public void salvarCliente(Cliente cliente ){
        validarCliente(cliente);
        this.repository.persistir(cliente);
    }

    private void validarCliente(Cliente cliente) {
//        aplicar validações
    }

}
