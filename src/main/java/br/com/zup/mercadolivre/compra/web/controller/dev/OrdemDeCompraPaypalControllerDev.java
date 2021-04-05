package br.com.zup.mercadolivre.compra.web.controller.dev;

import br.com.zup.dev.UserLogadoMock;
import br.com.zup.mercadolivre.clientes.PaypalClient;
import br.com.zup.mercadolivre.compra.data.domain.Compra;
import br.com.zup.mercadolivre.compra.data.service.OrdemDeCompraService;
import br.com.zup.mercadolivre.compra.data.service.OrdemDeCompraServiceImpl;
import br.com.zup.mercadolivre.compra.web.dto.request.OrdemCompraRequest;
import br.com.zup.mercadolivre.compra.web.dto.response.OrdemDeCompraResponse;
import br.com.zup.mercadolivre.global.util.notification.Gmail;
import br.com.zup.mercadolivre.usuario.data.domain.NivelAcesso;
import br.com.zup.mercadolivre.usuario.data.domain.Roles;
import br.com.zup.mercadolivre.usuario.data.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@Profile("dev")
@RestController
@RequestMapping("/order-de-compra/paypal")
public class OrdemDeCompraPaypalControllerDev {

    private final OrdemDeCompraService ordemDeCompraService;
    private final PaypalClient gatewayClient;

    @Autowired
    public OrdemDeCompraPaypalControllerDev(OrdemDeCompraServiceImpl ordemDeCompraService, PaypalClient gatewayClient){
        this.ordemDeCompraService = ordemDeCompraService;
        this.gatewayClient = gatewayClient;
    }

    @PostMapping
    public ResponseEntity<?> cadastrarOrdemDeCompra(@RequestBody @Valid OrdemCompraRequest ordemCompraRequest) throws MalformedURLException {

        Compra compra = this.ordemDeCompraService.abrirOrdemDeCompra(ordemCompraRequest.toModel(UserLogadoMock.simularUsuarioLogado(), "Paypal"));
        this.ordemDeCompraService.abaterEstoque(compra.getId());
        this.ordemDeCompraService.notificarGateway(compra, gatewayClient, "PAYPAL");
        this.ordemDeCompraService.notificarVendedores(compra,new Gmail());

        return ResponseEntity.ok(new OrdemDeCompraResponse(this.ordemDeCompraService.detalhesCompra(compra.getId())));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cadastrarOrdemDeCompra(@PathVariable("id") Long id){

        //cancelar Ordem de compra
        //devolver a quantidade de produtos no estoque

        return ResponseEntity.ok("");
    }

}
