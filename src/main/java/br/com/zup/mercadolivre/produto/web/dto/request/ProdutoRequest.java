package br.com.zup.mercadolivre.produto.web.dto.request;

import br.com.zup.mercadolivre.categoria.data.domain.Categoria;
import br.com.zup.mercadolivre.global.web.validations.ExistsID;
import br.com.zup.mercadolivre.produto.data.domain.Caracteristica;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.lang.NonNull;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public class ProdutoRequest {

    @NotBlank
    @JsonProperty("nome")
    private String nome;
    @NotNull
    @Min(1)
    @JsonProperty("valor")
    private BigDecimal valor;
    @NotNull
    @Min(0)
    @Positive
    @JsonProperty("quantidade")
    private Integer quantidade;
    @Size(min = 3, message = "Um produto deve ter no minimo 3 caracteristicas")
    @JsonProperty("caracteristicas")
    private Set<CaracteristicaToProdutoRequest> caracteristicas;
    @Size(max = 1000)
    @JsonProperty("descricao")
    private String descricao;
    @NonNull
    @ExistsID(targetEntity = Categoria.class,nameFieldID = "id",message = "Não existe cadastrado o ID de categoria informado", requiredField = true)
    @JsonProperty("categoriaID")
    private Long categoriaID;

    @Deprecated
    public ProdutoRequest(){}

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Long getCategoriaID() {
        return categoriaID;
    }

    public Set<Caracteristica> mapList(){
        return this.caracteristicas.stream().map(CaracteristicaToProdutoRequest::map).collect(Collectors.toSet());
    }
}