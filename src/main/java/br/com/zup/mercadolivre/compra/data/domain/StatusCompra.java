package br.com.zup.mercadolivre.compra.data.domain;

import java.time.LocalDateTime;

public enum StatusCompra {

    INICIADA("Ordem de Compra Aberta", null),
    AGUARDANDO("Aguardando Pagamento", null),
    CANCELADA("Compra Cancelada", null),
    ERRO_PAGAMENTO("Ocorreu um erro no Pagamento",null),
    APROVADA("Compra aprovada com Sucesso", LocalDateTime.now());

     StatusCompra(String value, LocalDateTime instanteCompraAprovada){
        this.value = value;
        this.instanteCompraAprovada = instanteCompraAprovada;
    }

    private String value;
    private LocalDateTime instanteCompraAprovada;

    public String getValue() {
        return value;
    }

    public LocalDateTime getInstanteCompraAprovada() {
        return instanteCompraAprovada;
    }
}
