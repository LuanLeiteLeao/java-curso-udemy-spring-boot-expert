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

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class,args);
    }

}
