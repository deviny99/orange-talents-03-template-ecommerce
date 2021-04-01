package br.com.zup.mercadolivre.produto.web.dto.request;

import br.com.zup.mercadolivre.produto.data.domain.Caracteristica;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class CaracteristicaToProdutoRequest {

    @NotBlank
    @JsonProperty("nome")
    private String nome;
    @NotBlank
    @JsonProperty("valor")
    private String valor;

    @Deprecated
    public CaracteristicaToProdutoRequest() { }

    public String getNome() {
        return nome;
    }

    public String getValor() {
        return valor;
    }

    public static Caracteristica map(CaracteristicaToProdutoRequest caracteristicaRequest){
        return new Caracteristica(caracteristicaRequest.getNome(),
                caracteristicaRequest.getValor());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CaracteristicaToProdutoRequest)) return false;
        CaracteristicaToProdutoRequest that = (CaracteristicaToProdutoRequest) o;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }
}
