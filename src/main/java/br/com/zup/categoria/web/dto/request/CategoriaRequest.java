package br.com.zup.categoria.web.dto.request;

import br.com.zup.categoria.data.domain.Categoria;
import br.com.zup.global.web.validations.ExistsID;
import br.com.zup.global.web.validations.Unique;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;

public class CategoriaRequest {

    @NotBlank
    @Unique(targetEntity = Categoria.class,  nameField = "nome", message = "Já foi cadastrado uma categoria com esse nome")
    @JsonProperty("nome")
    private String nome;
    @ExistsID(targetEntity = Categoria.class, nameFieldID = "id", message = "Não contém uma categoria cadastrada com o ID informado")
    @JsonProperty("categoriaSuperID")
    private Long categoriaSuperID;

    @Deprecated
    public CategoriaRequest(){}

    public CategoriaRequest(@NotBlank @Unique(targetEntity = Categoria.class,  nameField = "nome", message = "Já foi cadastrado uma categoria com esse nome") String nome,
                            @ExistsID(targetEntity = Categoria.class, nameFieldID = "id", message = "Não contém uma categoria cadastrada com o ID informado") Long categoriaSuperID) {
        this.nome = nome;
        this.categoriaSuperID = categoriaSuperID;

    }

    public Categoria toModel(Long id)
    {
        return new Categoria(
                id,
                this.nome,
                (this.categoriaSuperID != null)
                ? new Categoria(this.categoriaSuperID)
                :null
        );
    }
}
