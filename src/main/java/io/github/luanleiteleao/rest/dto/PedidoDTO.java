package io.github.luanleiteleao.rest.dto;

import io.github.luanleiteleao.validation.NotEmptyList;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDTO {
    @NotNull(message="{campo.codigo-cliente.obrigatorio}")
    private Integer cliente;
    @NotNull(message="{campo.total-pedido.obrigatorio}")
    private BigDecimal total;
    @NotEmptyList(message = "{campo.items-pedido.obrigatorio}")
    protected List<itemPedidoDTO> items;

    public Integer getCliente() {
        return cliente;
    }

    public void setCliente(Integer cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<itemPedidoDTO> getItems() {
        return items;
    }

    public void setItems(List<itemPedidoDTO> items) {
        this.items = items;
    }
}
