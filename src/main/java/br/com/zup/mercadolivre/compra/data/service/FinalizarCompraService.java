package br.com.zup.mercadolivre.compra.data.service;

import br.com.zup.mercadolivre.compra.data.domain.Compra;
import br.com.zup.mercadolivre.compra.web.dto.request.RetornoGatewayDePagamento;
import br.com.zup.mercadolivre.global.util.notification.Email;

public interface FinalizarCompraService {

    Compra finalizarTransacao(Long idCompra, RetornoGatewayDePagamento retornoGatewayDePagamento);
    void emitirNotaFiscal(Compra compra);
    void atualizarSistemaRanking(Compra compra);
    void notificarComprador(Compra compra, Email email, RetornoGatewayDePagamento retornoGatewayDePagamento);

}
