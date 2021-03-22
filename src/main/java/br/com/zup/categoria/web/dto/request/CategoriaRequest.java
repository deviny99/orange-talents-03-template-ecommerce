package br.com.zup.categoria.web.dto.request;

import br.com.zup.categoria.data.domain.Categoria;
import br.com.zup.global.web.validations.ExistsID;
import br.com.zup.global.web.validations.Unique;
import javax.validation.constraints.NotBlank;

public class CategoriaRequest {

    private Long id;
    @NotBlank
    @Unique(targetEntity = Categoria.class,  nameField = "nome", message = "Já foi cadastrado uma categoria com esse nome")
    private String nome;
    @ExistsID(targetEntity = Categoria.class, nameFieldID = "id", message = "Não contém uma categoria cadastrada com o ID informado")
    private Long categoriaSuperID;

    @Deprecated
    public CategoriaRequest(){}

    public CategoriaRequest(Long id, @NotBlank @Unique(targetEntity = Categoria.class,  nameField = "nome", message = "Já foi cadastrado uma categoria com esse nome") String nome,
                            @ExistsID(targetEntity = Categoria.class, nameFieldID = "id", message = "Não contém uma categoria cadastrada com o ID informado") Long categoriaSuperID) {
        this.id = id;
        this.nome = nome;
        System.out.println(categoriaSuperID);
        this.categoriaSuperID = categoriaSuperID;
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }

    public Long getCategoriaSuperID() {
        return categoriaSuperID;
    }

    public Categoria toModel()
    {
        return new Categoria(this.id,this.nome,(this.categoriaSuperID != null)? new Categoria(this.categoriaSuperID):null);
    }
}
