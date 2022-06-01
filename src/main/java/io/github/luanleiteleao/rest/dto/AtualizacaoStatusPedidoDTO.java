package io.github.luanleiteleao.rest.dto;

public class AtualizacaoStatusPedidoDTO {
    private String novoStatus;

    public AtualizacaoStatusPedidoDTO(String novoStatus) {
        this.novoStatus = novoStatus;
    }

    public AtualizacaoStatusPedidoDTO() {
    }

    public String getNovoStatus() {
        return novoStatus;
    }

    public void setNovoStatus(String novoStatus) {
        this.novoStatus = novoStatus;
    }
}
