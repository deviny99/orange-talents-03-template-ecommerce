package br.com.zup.mercadolivre.compra.web.dto.request;

import br.com.zup.global.StatusTransacao;

public interface RetornoGatewayDePagamento {

     StatusTransacao getStatusTransacao();
     String getUrlValue();
}
