package io.github.luanleiteleao.rest.controller;

import io.github.luanleiteleao.domain.entity.ItemPedido;
import io.github.luanleiteleao.domain.entity.Pedido;
import io.github.luanleiteleao.domain.enums.StatusPedido;
import io.github.luanleiteleao.rest.dto.AtualizacaoStatusPedidoDTO;
import io.github.luanleiteleao.rest.dto.InformacoesItemPedidoDTO;
import io.github.luanleiteleao.rest.dto.InformacoesPedidoDTO;
import io.github.luanleiteleao.rest.dto.PedidoDTO;
import io.github.luanleiteleao.service.PedidoService;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save(@RequestBody @Valid PedidoDTO dto){
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id){
        return service
                .obterPedidoCompleto(id)
                .map(pedido ->convert(pedido))
                .orElseThrow(()->
                new ResponseStatusException(NOT_FOUND,"Pedido não encontrado")
                );
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateStatus(@PathVariable Integer id, @RequestBody AtualizacaoStatusPedidoDTO dto){
        String novoStatus = dto.getNovoStatus();
        service.atualizarStatus(id, StatusPedido.valueOf(novoStatus));
    }

    private InformacoesPedidoDTO convert(Pedido pedido){
        InformacoesPedidoDTO informacoesPedidoDTO = new InformacoesPedidoDTO();

        informacoesPedidoDTO.setCodigo(pedido.getId());
        informacoesPedidoDTO.setDataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        informacoesPedidoDTO.setCpf(pedido.getCliente().getCpf());
        informacoesPedidoDTO.setNomeCliente(pedido.getCliente().getNome());
        informacoesPedidoDTO.setTotal(pedido.getTotal());
        informacoesPedidoDTO.setStatus(pedido.getStatus().name());
        informacoesPedidoDTO.setItems(converter(pedido.getItens()));

        return informacoesPedidoDTO;
    }

    private List<InformacoesItemPedidoDTO> converter(List<ItemPedido> itens){
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }

        return  itens.stream().map(
                itemPedido -> new InformacoesItemPedidoDTO(
                        itemPedido.getProduto().getDescricao(),
                        itemPedido.getProduto().getPreco(),
                        itemPedido.getQuantidade()
                ))
                .collect(Collectors.toList());
    }
}
