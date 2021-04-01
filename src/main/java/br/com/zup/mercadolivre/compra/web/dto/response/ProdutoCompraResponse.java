package br.com.zup.mercadolivre.compra.web.dto.response;

import br.com.zup.mercadolivre.compra.data.domain.ProdutoQuantidade;
import java.math.BigDecimal;

public class ProdutoCompraResponse {

    private Long id;
    private String nome;
    private BigDecimal valorUnitario;
    private Integer quantidade;
    private BigDecimal valorTotal = new BigDecimal(0);

    @Deprecated
    public ProdutoCompraResponse(){}

    public ProdutoCompraResponse(ProdutoQuantidade produtoQuantidade){
        this.id = produtoQuantidade.getProduto().getId();
        this.quantidade = produtoQuantidade.getQuantidade();
        this.nome = produtoQuantidade.getProduto().getNome();
        this.valorUnitario = produtoQuantidade.getProduto().getValor();
        this.valorTotal = new BigDecimal(this.valorUnitario.doubleValue()*this.quantidade);
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
