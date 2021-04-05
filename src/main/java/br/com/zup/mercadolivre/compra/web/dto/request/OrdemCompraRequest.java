package br.com.zup.mercadolivre.compra.web.dto.request;

import br.com.zup.mercadolivre.compra.data.domain.Compra;
import br.com.zup.mercadolivre.compra.data.domain.StatusCompra;
import br.com.zup.mercadolivre.usuario.data.domain.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.stream.Collectors;

public class OrdemCompraRequest {

    @NotNull
    @Size(min = 1, message = "A compra deve contem pelo menos 1 produto")
    @JsonProperty("produtos")
    private Set<@Valid ProdutoQuantidadeRequest> produtos;

    @Deprecated
    public OrdemCompraRequest(){}

    public Compra toModel(Usuario usuarioComprador, String gatewayDePagamento){

        return new Compra(
                this.produtos.stream().map(ProdutoQuantidadeRequest::toModel).collect(Collectors.toSet()),
                StatusCompra.INICIADA,
                usuarioComprador,
                gatewayDePagamento
        );
    }

    public Set<ProdutoQuantidadeRequest> getProdutos() {
        return produtos;
    }
}
