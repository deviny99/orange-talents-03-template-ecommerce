package br.com.zup.pergunta.web.controller;

import br.com.zup.pergunta.data.domain.Pergunta;
import br.com.zup.pergunta.data.repository.PerguntaRepository;
import br.com.zup.global.util.notification.Gmail;
import br.com.zup.pergunta.data.service.NotificacaoVendedor;
import br.com.zup.pergunta.web.dto.request.PerguntaRequest;
import br.com.zup.usuario.data.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.Map;

@Profile({"prod","test"})
@RestController
@RequestMapping("/perguntas")
public class PerguntaController {

    private final PerguntaRepository perguntaRepository;
    private final NotificacaoVendedor notificacaoVendedor;

    @Autowired
    public PerguntaController(PerguntaRepository perguntaRepository, NotificacaoVendedor notificacaoVendedor){
        this.perguntaRepository = perguntaRepository;
        this.notificacaoVendedor = notificacaoVendedor;
    }

    @PostMapping
    public ResponseEntity<?> cadastrarPergunta(@RequestBody @Valid PerguntaRequest perguntaRequest){
        Pergunta pergunta = this.perguntaRepository.save(perguntaRequest.toModel(null,usuarioAutenticado()));
        this.notificacaoVendedor.notificar(pergunta.getId(),new Gmail());
        return ResponseEntity.ok(Map.of("id",pergunta.getId()));
    }

    private Usuario usuarioAutenticado() {
        return (Usuario) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

}
