package io.github.luanleiteleao.service.imp;

import io.github.luanleiteleao.domain.entity.Cliente;
import io.github.luanleiteleao.domain.entity.ItemPedido;
import io.github.luanleiteleao.domain.entity.Pedido;
import io.github.luanleiteleao.domain.entity.Produto;
import io.github.luanleiteleao.domain.enums.StatusPedido;
import io.github.luanleiteleao.domain.repository.Clientes;
import io.github.luanleiteleao.domain.repository.ItemPedidos;
import io.github.luanleiteleao.domain.repository.Pedidos;
import io.github.luanleiteleao.domain.repository.Produtos;
import io.github.luanleiteleao.exception.PedidoNaoEncontradoException;
import io.github.luanleiteleao.exception.RegraNegocioException;
import io.github.luanleiteleao.rest.dto.AtualizacaoStatusPedidoDTO;
import io.github.luanleiteleao.rest.dto.PedidoDTO;
import io.github.luanleiteleao.rest.dto.itemPedidoDTO;
import io.github.luanleiteleao.service.PedidoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImp implements PedidoService {
    private Pedidos pedidosRepository;
    private Clientes clientesRepository;
    private Produtos produtosRepository;
    private ItemPedidos itemPedidosRepository;


    public PedidoServiceImp(Pedidos pedidosRepository,
                            Clientes clientesRepository,
                            Produtos produtosRepository,
                            ItemPedidos itemPedidosRepository) {

        this.pedidosRepository=pedidosRepository;
        this.clientesRepository=clientesRepository;
        this.produtosRepository=produtosRepository;
        this.itemPedidosRepository=itemPedidosRepository;
    }

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(()-> new RegraNegocioException("Código do Cliente iválido"));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.CANCELADO.REALIZADO);

        List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
        pedidosRepository.save(pedido);
        itemPedidosRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);

        return pedido;
    }
    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return pedidosRepository.findByIdFetchItens(id);
    }


    @Override
    @Transactional
    public void atualizarStatus(Integer id, StatusPedido statusPedido) {
        pedidosRepository
                .findById(id)
                .map(pedido -> {
                    pedido.setStatus(statusPedido);
                    return  pedidosRepository.save(pedido);
                }).orElseThrow(()-> new PedidoNaoEncontradoException());
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<itemPedidoDTO> items){
        if(items.isEmpty()){
            throw new RegraNegocioException("Não é possivel realizar um pedido sem items");
        }

        return items
                .stream()
                .map(dto->{
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(
                                    ()->new RegraNegocioException("Código do produto iválido: "+idProduto)
                            );
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }
}
