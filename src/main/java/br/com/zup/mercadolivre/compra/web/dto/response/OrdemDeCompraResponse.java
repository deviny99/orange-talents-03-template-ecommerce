package br.com.zup.mercadolivre.compra.web.dto.response;

import br.com.zup.mercadolivre.compra.data.domain.Compra;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class OrdemDeCompraResponse {

    private BigDecimal valorFinal;
    private String comprador;
    private String gatewayDePagamento;
    private LocalDateTime instanteCompra;
    private String statusCompra;
    private Set<ProdutoCompraResponse> produtos;

    @Deprecated
    public OrdemDeCompraResponse(){}

    public OrdemDeCompraResponse(Compra compra)
    {
        this.comprador = compra.getComprador().getUsername();
        this.gatewayDePagamento = compra.getGatewayDePagamento();
        this.produtos = compra.getProdutos().stream().map(ProdutoCompraResponse::new).collect(Collectors.toSet());
        this.statusCompra = compra.getStatusCompra().getValue();
        this.instanteCompra = compra.getInstanteCompra();
        this.valorFinal = compra.getValorTotal();
    }

    public String getGatewayDePagamento() {
        return gatewayDePagamento;
    }

    public LocalDateTime getInstanteCompra() {
        return instanteCompra;
    }

    public BigDecimal getValorFinal() {
        return valorFinal;
    }

    public String getComprador() {
        return comprador;
    }

    public Set<ProdutoCompraResponse> getProdutos() {
        return this.produtos;
    }

    public String getStatusCompra() {
        return statusCompra;
    }
}
