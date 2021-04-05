package br.com.zup.gateway.web.controller;

import br.com.zup.gateway.data.domain.Transacao;
import br.com.zup.gateway.data.repository.TransacaoRepository;
import br.com.zup.gateway.web.dto.UrlRequest;
import br.com.zup.global.StatusTransacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import static br.com.zup.mercadolivre.global.web.exception.ControllerException.badRequest;

@RestController
@RequestMapping("/pagseguro/transacao")
public class TransacaoPagSeguroController {

    private final TransacaoRepository transacaoRepository;

    @Autowired
    public TransacaoPagSeguroController(TransacaoRepository transacaoRepository){
        this.transacaoRepository = transacaoRepository;
    }

    @Transactional
    @PostMapping("/{idCompra}")
    public ResponseEntity<?> cadastrarTransacao(@RequestBody UrlRequest urlRequest, @PathVariable("idCompra") Long idCompra){

        if (this.transacaoRepository.findByIdCompraAndStatusTransacao(idCompra, StatusTransacao.APROVADO).isPresent()){
            throw badRequest("Uma transação para essa compra já foi aprovada.");
        }
        Transacao transacao = new Transacao(null,idCompra,urlRequest.toModel());
        transacao.iniciarTransacao();
        transacao = this.transacaoRepository.save(transacao);
        return ResponseEntity.ok("");
    }

    @Transactional
    @PostMapping("/aprovar/{id}")
    public ResponseEntity<?> aprovarTransacao(@PathVariable("id") Long idTransacao){

        Optional<Transacao> transacaoOptional = this.transacaoRepository.findById(idTransacao);
        if(transacaoOptional.isPresent()){
            Transacao transacao = transacaoOptional.get();
            if(transacao.getStatusTransacao().equals(StatusTransacao.APROVADO)){
                throw badRequest("Não pode aprovar a transação para essa compra, pois a transação já foi aprovada.");
            }
            transacao.aprovarTransacao();
            Map<String,String> body = new HashMap<>();
            body.put("status", "SUCESSO");
            this.transacaoRepository.save(transacao);
            notificarClient(transacao.getUrlNotification().getUrl(),
                    transacao.getUrlNotification().getMethod().getMethod(),
                    transacao.getIdCompra(),
                    transacao.getId(), body);
        }else
        {
            throw badRequest("Não existe uma Transação com o id inserido");
        }
        return ResponseEntity.ok("");
    }

    @Transactional
    @PostMapping("/negar/{id}")
    public ResponseEntity<?> negarTransacao(@PathVariable("id") Long idTransacao){

        Optional<Transacao> transacaoOptional = this.transacaoRepository.findById(idTransacao);
        if(transacaoOptional.isPresent()){
            Transacao transacao = transacaoOptional.get();
            if(transacao.getStatusTransacao().equals(StatusTransacao.APROVADO)){
                throw badRequest("Não pode negar a transação para essa compra, pois a transação já foi aprovada.");
            }
            transacao.negarTransacao();
            Map<String,String> body = new HashMap<>();
            body.put("status", "ERRO");
            body.put("url","http://localhost:8080/pagseguro/transacao/"+transacao.getIdCompra());
            this.transacaoRepository.save(transacao);
            notificarClient(transacao.getUrlNotification().getUrl(),
                    transacao.getUrlNotification().getMethod().getMethod(),
                    transacao.getIdCompra(),
                    transacao.getId(), body);
        }else{
            throw badRequest("Não existe uma Transação com o id inserido");
        }

        return ResponseEntity.ok("");
    }



    public HttpEntity<?> notificarClient(String url, HttpMethod method, Long idCompra, Long idTransacao,Map<String,String> body){

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","application/json");

        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> params = new HashMap<>();
        params.put("idCompra",idCompra.toString());
        params.put("idTransacao",idTransacao.toString());

        HttpEntity entity = new HttpEntity(body,headers);

        return restTemplate.exchange(url,method, entity,String.class,params);
    }

}