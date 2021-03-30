package br.com.zup.pergunta.web.controller;

import br.com.zup.pergunta.data.domain.Pergunta;
import br.com.zup.pergunta.data.repository.PerguntaRepository;
import br.com.zup.pergunta.util.notification.Gmail;
import br.com.zup.pergunta.data.service.NotificacaoVendedor;
import br.com.zup.pergunta.web.dto.request.PerguntaRequest;
import br.com.zup.usuario.data.domain.NivelAcesso;
import br.com.zup.usuario.data.domain.Roles;
import br.com.zup.usuario.data.domain.Usuario;
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
import java.util.Map;

@Profile("dev")
@RestController
@RequestMapping("/perguntas")
public class PerguntaControllerDev {

        private final PerguntaRepository perguntaRepository;
        private final NotificacaoVendedor notificacaoVendedor;

        @Autowired
        public PerguntaControllerDev(PerguntaRepository perguntaRepository, NotificacaoVendedor notificacaoVendedor){
            this.perguntaRepository = perguntaRepository;
            this.notificacaoVendedor = notificacaoVendedor;
        }

        @PostMapping
        public ResponseEntity<?> cadastrarPergunta(@RequestBody @Valid PerguntaRequest perguntaRequest){
            Pergunta pergunta = this.perguntaRepository.save(perguntaRequest.toModel(null,this.simularUsuarioLogado()));
            this.notificacaoVendedor.notificar(pergunta.getId(),new Gmail());
            return ResponseEntity.ok(Map.of("id",pergunta.getId()));
        }

        private Usuario simularUsuarioLogado() {
            List<NivelAcesso> niveisAcesso = new ArrayList<>();
            niveisAcesso.add(new NivelAcesso(Roles.ROLE_USER));
            return new Usuario(1L,"email@email","123456",niveisAcesso);
        }

}
