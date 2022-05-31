package io.github.luanleiteleao.rest.controller;

import io.github.luanleiteleao.domain.entity.Cliente;
import io.github.luanleiteleao.domain.repository.Clientes;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/clientes/")
public class ClienteController {

    private Clientes clientes;

    public ClienteController(Clientes clientes) {
        this.clientes = clientes;
    }

    @GetMapping("{id}")
   public Cliente getClienteById(@PathVariable Integer id){
        return  clientes
                .findById(id)
                .orElseThrow(()->
                    new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Cliente não encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody Cliente cliente){
        return clientes.save(cliente);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable Integer id){
        this.clientes.findById(id)
                .map(cliente -> {
                    clientes.delete(cliente);
                    return null;
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado"));

    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Cliente update(@PathVariable Integer id,@RequestBody Cliente cliente){
        return clientes.findById(id).
                map(clienteExistente->{
                    cliente.setId(clienteExistente.getId());
                    clientes.save(cliente);
                    return clienteExistente;
                }).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontrado"));
    }

    @GetMapping
    public List<Cliente> find(Cliente filtro){
        ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(
                ExampleMatcher.StringMatcher.CONTAINING
        );

        Example example = Example.of(filtro,matcher);
        return clientes.findAll(example);

    }
}