package io.github.luanleiteleao;

import io.github.luanleiteleao.domain.entity.Cliente;
import io.github.luanleiteleao.domain.entity.Produto;
import io.github.luanleiteleao.domain.repository.Clientes;
import io.github.luanleiteleao.domain.repository.Produtos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class VendasApplication {
    @Bean
    public CommandLineRunner commandLineRunner(@Autowired Clientes clientes, @Autowired Produtos produtos){
        return args -> {
            clientes.save(new Cliente("fulano"));
            clientes.save(new Cliente("Cliclano"));
            clientes.save(new Cliente("Beltrano"));
            clientes.save(new Cliente("Margarida"));

            produtos.save(new Produto("carne",  BigDecimal.valueOf(5.4)));
            produtos.save(new Produto("verdura",  BigDecimal.valueOf(6.4)));
            produtos.save(new Produto("lasanha",  BigDecimal.valueOf(69.4)));
        };
    }
    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class,args);
    }

}
