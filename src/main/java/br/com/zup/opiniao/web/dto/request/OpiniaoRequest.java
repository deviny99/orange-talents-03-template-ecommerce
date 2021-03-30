package br.com.zup.opiniao.web.dto.request;

import br.com.zup.global.web.validations.ExistsID;
import br.com.zup.opiniao.data.domain.Opiniao;
import br.com.zup.produto.data.domain.Produto;
import br.com.zup.produto.data.domain.builder.ProdutoBuilderImpl;
import br.com.zup.usuario.data.domain.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.*;

public class OpiniaoRequest {

    @NotBlank
    @JsonProperty("titulo")
    private String titulo;
    @NotBlank
    @Size(max = 500)
    @JsonProperty("descricao")
    private String descricao;
    @NotNull
    @Min(1)
    @Max(5)
    @JsonProperty("nota")
    private Integer nota;
    @NotNull
    @ExistsID(targetEntity = Produto.class,nameFieldID = "id", message = "Não existe cadastrado o ID do produto informado")
    @JsonProperty("produtoID")
    private Long produtoID;

    public Opiniao toModel(Long id,Usuario usuario){
        return new Opiniao(id,
                    this.nota,
                    this.titulo,
                    this.descricao,
                    usuario,
                    new ProdutoBuilderImpl()
                            .addId(this.produtoID)
                            .build()
        );
    }

}
