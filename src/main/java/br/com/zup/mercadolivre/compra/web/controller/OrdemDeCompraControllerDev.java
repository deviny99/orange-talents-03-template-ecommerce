package br.com.zup.mercadolivre.compra.web.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Profile("dev")
@RestController
@RequestMapping("/order-de-compra/paypal")
public class OrdemDeCompraControllerDev {

    private final OrdemDeCompraService ordemDeCompraService;

    @Autowired
    public OrdemDeCompraControllerDev(OrdemDeCompraServiceImpl ordemDeCompraService){
        this.ordemDeCompraService = ordemDeCompraService;
    }

    @PostMapping
    public ResponseEntity<?> cadastrarOrdemDeCompra(@RequestBody @Valid OrdemCompraRequest ordemCompraRequest){

        Compra compra = this.ordemDeCompraService.abrirOrdemDeCompra(ordemCompraRequest.toModel(simularUsuarioLogado(), "Paypal"));
        this.ordemDeCompraService.abaterEstoque(compra.getId());
        this.ordemDeCompraService.notificarGateway(compra);
        this.ordemDeCompraService.notificarVendedores(compra,new Gmail());
        return ResponseEntity.ok(new OrdemDeCompraResponse(this.ordemDeCompraService.detalhesCompra(compra.getId())));
    }

    private Usuario simularUsuarioLogado() {
        List<NivelAcesso> niveisAcesso = new ArrayList<>();
        niveisAcesso.add(new NivelAcesso(Roles.ROLE_USER));
        return new Usuario(1L,"email@email","123456",niveisAcesso);
    }

}
