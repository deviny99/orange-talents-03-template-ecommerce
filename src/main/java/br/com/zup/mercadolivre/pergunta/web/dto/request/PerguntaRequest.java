package br.com.zup.mercadolivre.pergunta.web.dto.request;

import br.com.zup.mercadolivre.global.web.validations.ExistsID;
import br.com.zup.mercadolivre.pergunta.data.domain.Pergunta;
import br.com.zup.mercadolivre.produto.data.domain.Produto;
import br.com.zup.mercadolivre.produto.data.domain.builder.ProdutoBuilderImpl;
import br.com.zup.mercadolivre.usuario.data.domain.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PerguntaRequest {

    @NotBlank
    @JsonProperty(value = "titulo",required = true, access = JsonProperty.Access.WRITE_ONLY)
    private String titulo;
    @NotNull
    @ExistsID(targetEntity = Produto.class, nameFieldID = "id", message = "Não existe cadastrado o ID do produto informado",requiredField = true)
    @JsonProperty(value = "produtoID",required = true, access = JsonProperty.Access.WRITE_ONLY)
    private Long produtoID;

    public Pergunta toModel(Long id, Usuario usuario){
        return new Pergunta(
                id,
                this.titulo,
                usuario,
                new ProdutoBuilderImpl()
                        .addId(this.produtoID)
                        .build()
        );
    }

}
