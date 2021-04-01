package br.com.zup.mercadolivre.compra.data.service;

import br.com.zup.mercadolivre.compra.data.domain.Compra;
import br.com.zup.mercadolivre.compra.data.domain.StatusCompra;
import br.com.zup.mercadolivre.global.util.notification.Email;

public interface OrdemDeCompraService {

    Compra abrirOrdemDeCompra(Compra compra);
    void abaterEstoque(Long idCompra);
    void notificarGateway(Compra compra);
    void notificarVendedores(Compra compra, Email email);
    Compra alterarStatusCompra(Compra compra, StatusCompra statusCompra);
    Compra detalhesCompra(Long idCompra);
}
