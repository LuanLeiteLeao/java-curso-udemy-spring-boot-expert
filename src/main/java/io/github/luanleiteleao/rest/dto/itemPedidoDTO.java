package io.github.luanleiteleao.rest.dto;

import io.github.luanleiteleao.domain.entity.ItemPedido;

import java.math.BigDecimal;
import java.util.List;

public class itemPedidoDTO {
    private Integer produto;
    private Integer quantidade;

    public Integer getProduto() {
        return produto;
    }

    public void setProduto(Integer produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
