package br.com.zup.opiniao.web.controller;

import br.com.zup.opiniao.data.domain.Opiniao;
import br.com.zup.opiniao.data.repository.OpiniaoRepository;
import br.com.zup.opiniao.web.dto.request.OpiniaoRequest;
import br.com.zup.usuario.data.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.Map;

@Profile({"prod","test"})
@RestController
@RequestMapping("/opinioes")
public class OpiniaoController {

    private final OpiniaoRepository opiniaoRepository;

    @Autowired
    public OpiniaoController(OpiniaoRepository opiniaoRepository){
        this.opiniaoRepository = opiniaoRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrarOpiniao(@RequestBody @Valid OpiniaoRequest opiniaoRequest ){

        Usuario userLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Opiniao opiniao = this.opiniaoRepository.save(opiniaoRequest.toModel(null,userLogado));
        return ResponseEntity.ok(Map.of("id",opiniao.getId()));
    }

}