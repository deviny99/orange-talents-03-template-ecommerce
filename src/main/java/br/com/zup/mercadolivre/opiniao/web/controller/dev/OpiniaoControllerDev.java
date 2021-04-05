package br.com.zup.mercadolivre.opiniao.web.controller.dev;

import br.com.zup.dev.UserLogadoMock;
import br.com.zup.mercadolivre.opiniao.data.domain.Opiniao;
import br.com.zup.mercadolivre.opiniao.data.repository.OpiniaoRepository;
import br.com.zup.mercadolivre.opiniao.web.dto.request.OpiniaoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.Map;

@Profile("dev")
@RestController
@RequestMapping("/opinioes")
public class OpiniaoControllerDev {

    private final OpiniaoRepository opiniaoRepository;

    @Autowired
    public OpiniaoControllerDev(OpiniaoRepository opiniaoRepository){
        this.opiniaoRepository = opiniaoRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrarOpiniao(@RequestBody @Valid OpiniaoRequest opiniaoRequest ){

        Opiniao opiniao = this.opiniaoRepository.save(opiniaoRequest.toModel(null, UserLogadoMock.simularUsuarioLogado()));
        return ResponseEntity.ok(Map.of("id",opiniao.getId()));
    }

}
