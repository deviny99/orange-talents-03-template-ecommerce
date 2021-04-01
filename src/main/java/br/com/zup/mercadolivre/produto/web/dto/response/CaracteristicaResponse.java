package br.com.zup.mercadolivre.produto.web.dto.response;

import br.com.zup.mercadolivre.produto.data.domain.Caracteristica;

public class CaracteristicaResponse {

    private String nome;
    private String valor;

    public CaracteristicaResponse(Caracteristica caracteristica) {
        this.nome = caracteristica.getNome();
        this.valor = caracteristica.getValor();
    }

    public String getNome() {
        return nome;
    }

    public String getValor() {
        return valor;
    }
}
