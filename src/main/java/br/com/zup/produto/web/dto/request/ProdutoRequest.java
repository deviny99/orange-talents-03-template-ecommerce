package br.com.zup.produto.web.dto.request;

import br.com.zup.categoria.data.domain.Categoria;
import br.com.zup.global.web.validations.ExistsID;
import br.com.zup.produto.data.domain.Caracteristica;
import org.springframework.lang.NonNull;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public class ProdutoRequest {

    private Long id;
    @NotBlank
    private String nome;
    @NotNull
    @Min(1)
    private BigDecimal valor;
    @NotNull
    @Min(0)
    private Integer quantidade;
    @Size(min = 3, message = "Um produto deve ter no minimo 3 caracteristicas")
    private Set<CaracteristicaToProdutoRequest> caracteristicas;
    @Size(max = 1000)
    private String descricao;
    @NonNull
    @ExistsID(targetEntity = Categoria.class,nameFieldID = "id",message = "Não existe cadastrado o ID de categoria informado")
    private Long categoriaID;

    @Deprecated
    public ProdutoRequest(){}

    public ProdutoRequest(Long id, @NotBlank String nome, @NotNull @Size(min = 1) BigDecimal valor, @NotNull @Size(min = 0) Integer quantidade,
                          @Size(min = 3, message = "Um produto deve ter no minimo 3 caracteristicas") Set<CaracteristicaToProdutoRequest> caracteristicas,
                          @Size(max = 1000) String descricao, @NonNull Long categoriaID) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoriaID = categoriaID;
    }

    public Long getId() {
        return id;
    }

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

    public Set<CaracteristicaToProdutoRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public Set<Caracteristica> mapList(){
        return this.caracteristicas.stream().map(CaracteristicaToProdutoRequest::map).collect(Collectors.toSet());
    }
}