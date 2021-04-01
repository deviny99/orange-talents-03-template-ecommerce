package br.com.zup.mercadolivre.opiniao.web.controller;

import br.com.zup.mercadolivre.opiniao.data.domain.Opiniao;
import br.com.zup.mercadolivre.opiniao.data.repository.OpiniaoRepository;
import br.com.zup.mercadolivre.opiniao.web.dto.request.OpiniaoRequest;
import br.com.zup.mercadolivre.usuario.data.domain.NivelAcesso;
import br.com.zup.mercadolivre.usuario.data.domain.Roles;
import br.com.zup.mercadolivre.usuario.data.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
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

        Usuario userLogado = this.userLogado();
        Opiniao opiniao = this.opiniaoRepository.save(opiniaoRequest.toModel(null,userLogado));
        return ResponseEntity.ok(Map.of("id",opiniao.getId()));
    }

    private Usuario userLogado(){
        List<NivelAcesso> niveisAcesso = new ArrayList<>();
        niveisAcesso.add(new NivelAcesso(Roles.ROLE_USER));
        return new Usuario(1L,"email@email","123",niveisAcesso);
    }
}
