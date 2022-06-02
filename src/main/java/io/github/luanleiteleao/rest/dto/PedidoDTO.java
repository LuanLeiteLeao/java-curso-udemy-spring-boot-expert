package io.github.luanleiteleao.rest.dto;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDTO {
    @NotNull(message="Informe o código do cliente")
    private Integer cliente;
    @NotNull(message="Campo total do pedido é obrigatório")
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
