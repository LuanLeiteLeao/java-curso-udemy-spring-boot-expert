package io.github.luanleiteleao;

import io.github.luanleiteleao.domain.entity.Cliente;
import io.github.luanleiteleao.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VendasApplication {
    @Bean
    public CommandLineRunner commandLineRunner(@Autowired Clientes clientes){
        return args -> {
            clientes.save(new Cliente("fulano"));
            clientes.save(new Cliente("Cliclano"));
            clientes.save(new Cliente("Beltrano"));
            clientes.save(new Cliente("Margarida"));
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class,args);
    }

}
