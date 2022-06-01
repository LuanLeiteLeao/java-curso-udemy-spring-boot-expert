package io.github.luanleiteleao.rest.dto;

import java.math.BigDecimal;
import java.util.List;

public class PedidoDTO {
    private Integer cliente;
    private BigDecimal total;
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
