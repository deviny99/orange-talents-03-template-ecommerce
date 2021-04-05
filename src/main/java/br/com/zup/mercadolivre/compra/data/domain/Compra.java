package br.com.zup.mercadolivre.compra.data.domain;

import br.com.zup.global.StatusTransacao;
import br.com.zup.mercadolivre.produto.data.domain.Produto;
import br.com.zup.mercadolivre.usuario.data.domain.Usuario;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.util.Assert;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.*;

@Entity
@Table(name = "compras")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<ProdutoQuantidade> produtos;
    @Enumerated(EnumType.STRING)
    @Column(name = "statusCompra",nullable = false)
    private StatusCompra statusCompra;
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario comprador;
    @Column(name = "gatewayDePagamento", nullable = false)
    private String gatewayDePagamento;
    @Column(name = "instanteCompra", nullable = false)
    private LocalDateTime instanteCompra = LocalDateTime.now();
    @Column(name = "instanteCompraAprovada")
    private LocalDateTime instanteCompraAprovada;
    @Transient
    private BigDecimal valorTotal;
    @OneToMany( cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<TransacaoCompra> transacoes = new HashSet<>();


    @Deprecated
    public Compra(){}

    public Compra(Set<ProdutoQuantidade> produtos, StatusCompra statusCompra, Usuario comprador, String gatewayDePagamento) {
        this.addProdutos(produtos);
        this.comprador = comprador;
        this.gatewayDePagamento = gatewayDePagamento;
        this.valorTotal = new BigDecimal(0);
        this.statusCompra = statusCompra;
        this.instanteCompraAprovada = this.statusCompra.getInstanteCompraAprovada();
    }

    private void addProdutos(Set<ProdutoQuantidade> produtos){
        this.produtos = this.agruparProdutos(produtos);
    }

    public Set<ProdutoQuantidade> agruparProdutos(Set<ProdutoQuantidade> produtoQuantidades){
        Map<Produto, Integer> map = produtoQuantidades.stream().collect(groupingBy(ProdutoQuantidade::getProduto, Collectors.summingInt(ProdutoQuantidade::getQuantidade)));
        Set<ProdutoQuantidade> setProdutosQtd = new HashSet();
        for (Map.Entry<Produto, Integer> prodQtd : map.entrySet())
        {
            setProdutosQtd.add(new ProdutoQuantidade(null,prodQtd.getKey(),prodQtd.getValue()));
        }
        return setProdutosQtd;
    }

    public boolean compraAprovadaComSucesso(){
        Set<TransacaoCompra> transacoesComprasSucesso = this.transacoes.stream().filter(TransacaoCompra :: isAprovada)
                .collect(Collectors.toSet());
        return !transacoesComprasSucesso.isEmpty();
    }

    public void addTransacao(TransacaoCompra transacaoCompra){
        Assert.isTrue(!this.transacoes.contains(transacaoCompra),
                "Já existe uma transacao igual a essa processada");
        Assert.isTrue(!this.compraAprovadaComSucesso(),"Essa Compra já foi concluida com sucesso!");
        if(transacaoCompra.equals(StatusTransacao.APROVADO)){
            aprovarCompra();
        }

        this.transacoes.add(transacaoCompra);
    }


    private void aprovarCompra(){
        this.statusCompra = StatusCompra.APROVADA;
        this.instanteCompraAprovada = this.statusCompra.getInstanteCompraAprovada();
    }

    private void calculaValorTotal(){

        AtomicReference<BigDecimal> valorTotal = new AtomicReference<>(new BigDecimal(0));
        this.produtos.stream().forEach(it -> {
            if (it.getProduto().getValor() != null)
            valorTotal.set(
                    new BigDecimal(
                            valorTotal.get().doubleValue()
                                    +(it.getProduto().getValor().doubleValue() * it.getQuantidade())
                    ));
        });

        this.valorTotal = valorTotal.get();
    }

    public void setStatusCompra(StatusCompra statusCompra) {
        this.statusCompra = statusCompra;
    }

    public Long getId() {
        return id;
    }

    public StatusCompra getStatusCompra() {
        return statusCompra;
    }

    public LocalDateTime getInstanteCompra() {
        return instanteCompra;
    }

    public String getGatewayDePagamento() {
        return gatewayDePagamento;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public Set<ProdutoQuantidade> getProdutos() {
        return produtos;
    }

    public BigDecimal getValorTotal(){
        calculaValorTotal();
        return this.valorTotal;
    }
}
