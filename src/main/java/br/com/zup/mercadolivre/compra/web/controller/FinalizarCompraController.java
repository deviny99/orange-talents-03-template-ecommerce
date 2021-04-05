package br.com.zup.mercadolivre.compra.web.controller;

import br.com.zup.mercadolivre.compra.data.domain.Compra;
import br.com.zup.mercadolivre.compra.data.service.FinalizarCompraService;
import br.com.zup.mercadolivre.compra.web.dto.request.PagSeguroRequest;
import br.com.zup.mercadolivre.compra.web.dto.request.PaypalRequest;
import br.com.zup.mercadolivre.compra.web.dto.request.RetornoGatewayDePagamento;
import br.com.zup.mercadolivre.global.util.notification.Gmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/finalizar/compra")
public class FinalizarCompraController {

    private final FinalizarCompraService finalizarCompraService;

    @Autowired
    public FinalizarCompraController(FinalizarCompraService finalizarCompraService){
        this.finalizarCompraService = finalizarCompraService;
    }

    @PostMapping("paypal/{idCompra}/{idTransacao}")
    public ResponseEntity<?> finalizarOrdemDeCompraPaypal(
            @PathVariable("idCompra") Long idCompra,@PathVariable("idTransacao") Long idTransacao,
            @RequestBody PaypalRequest paypalRequest){

        Compra compra = finalizarCompra(idCompra,paypalRequest);
        return ResponseEntity.ok("");
    }

    @PostMapping("pagseguro/{idCompra}/{idTransacao}")
    public ResponseEntity<?> finalizarOrdemDeCompraPagSeguro(
            @PathVariable("idCompra") Long idCompra,@PathVariable("idTransacao") Long idTransacao,
            @RequestBody PagSeguroRequest pagSeguroRequest){

        Compra compra = finalizarCompra(idCompra,pagSeguroRequest);

        return ResponseEntity.ok("");
    }

    private Compra finalizarCompra(Long idCompra, RetornoGatewayDePagamento gatewayDePagamento){

       Compra compra = finalizarCompraService.finalizarTransacao(idCompra,gatewayDePagamento);
       this.finalizarCompraService.notificarComprador(compra,new Gmail(),gatewayDePagamento);
       this.finalizarCompraService.emitirNotaFiscal(compra);
       this.finalizarCompraService.atualizarSistemaRanking(compra);
       return compra;
    }

}