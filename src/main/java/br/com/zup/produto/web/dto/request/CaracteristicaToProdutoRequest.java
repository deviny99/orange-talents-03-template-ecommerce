package br.com.zup.produto.web.dto.request;

import br.com.zup.produto.data.domain.Caracteristica;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class CaracteristicaToProdutoRequest {

    @NotBlank
    private String nome;
    @NotBlank
    private String valor;

    @Deprecated
    public CaracteristicaToProdutoRequest() { }

    public CaracteristicaToProdutoRequest(@NotBlank String nome, @NotBlank String valor) {
        this.nome = nome;
        this.valor = valor;
    }

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
