package br.com.zup.mercadolivre.compra.web.controller;

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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.MalformedURLException;

@Profile("prod")
@RestController
@RequestMapping("/order-de-compra/paypal")
public class OrdemDeCompraPaypalController {

    private final OrdemDeCompraService ordemDeCompraService;
    private final PaypalClient gatewayClient;

    @Autowired
    public OrdemDeCompraPaypalController(OrdemDeCompraServiceImpl ordemDeCompraService, PaypalClient gatewayClient){
        this.ordemDeCompraService = ordemDeCompraService;
        this.gatewayClient = gatewayClient;
    }

    @PostMapping
    public ResponseEntity<?> cadastrarOrdemDeCompra(@RequestBody @Valid OrdemCompraRequest ordemCompraRequest) throws MalformedURLException {

        Compra compra = this.ordemDeCompraService.abrirOrdemDeCompra(ordemCompraRequest.toModel(userLogado(), "Paypal"));
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

    private Usuario userLogado() {
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}