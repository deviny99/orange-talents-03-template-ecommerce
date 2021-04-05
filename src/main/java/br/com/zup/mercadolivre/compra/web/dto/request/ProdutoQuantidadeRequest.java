package br.com.zup.mercadolivre.compra.web.dto.request;

import br.com.zup.mercadolivre.compra.data.domain.ProdutoQuantidade;
import br.com.zup.mercadolivre.compra.web.dto.request.validation.ContemEstoque;
import br.com.zup.mercadolivre.global.web.validations.ExistsID;
import br.com.zup.mercadolivre.produto.data.domain.Produto;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@ContemEstoque(nameFieldID = "produtoID", nameFieldQuantidade = "quantidade")
public class ProdutoQuantidadeRequest {

    @NotNull
    @ExistsID(targetEntity = Produto.class,nameFieldID = "id",message = "Não existe cadastrado o ID de categoria informado",requiredField = true)
    @JsonProperty("produtoID")
    private Long produtoID;
    @NotNull
    @Positive
    @JsonProperty("quantidade")
    private Integer quantidade;

    @Deprecated
    public  ProdutoQuantidadeRequest(){}

    public ProdutoQuantidade toModel(){
        return new ProdutoQuantidade(
                null,
                new Produto(this.produtoID),
                this.quantidade
        );
    }
}
