package io.github.luanleiteleao.domain.repository;

import io.github.luanleiteleao.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto,Integer> {
}
