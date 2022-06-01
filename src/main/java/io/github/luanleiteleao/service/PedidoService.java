package io.github.luanleiteleao.service;

import io.github.luanleiteleao.domain.entity.Pedido;
import io.github.luanleiteleao.domain.enums.StatusPedido;
import io.github.luanleiteleao.rest.dto.AtualizacaoStatusPedidoDTO;
import io.github.luanleiteleao.rest.dto.PedidoDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public interface PedidoService {
    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);

    void atualizarStatus(Integer id, StatusPedido statusPedido);
}
