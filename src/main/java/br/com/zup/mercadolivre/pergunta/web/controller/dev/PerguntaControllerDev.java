package br.com.zup.mercadolivre.pergunta.web.controller.dev;

import br.com.zup.dev.UserLogadoMock;
import br.com.zup.mercadolivre.pergunta.data.domain.Pergunta;
import br.com.zup.mercadolivre.pergunta.data.repository.PerguntaRepository;
import br.com.zup.mercadolivre.global.util.notification.Gmail;
import br.com.zup.mercadolivre.pergunta.data.service.NotificacaoVendedor;
import br.com.zup.mercadolivre.pergunta.web.dto.request.PerguntaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.Map;

@Profile("dev")
@RestController
@RequestMapping("/perguntas")
public class PerguntaControllerDev {

        private final PerguntaRepository perguntaRepository;

        @Autowired
        public PerguntaControllerDev(PerguntaRepository perguntaRepository){
            this.perguntaRepository = perguntaRepository;
        }

        @PostMapping
        public ResponseEntity<?> cadastrarPergunta(@RequestBody @Valid PerguntaRequest perguntaRequest){
            Pergunta pergunta = this.perguntaRepository.save(perguntaRequest.toModel(null, UserLogadoMock.simularUsuarioLogado()));
            pergunta = (this.perguntaRepository.findById(pergunta.getId())).get();
            NotificacaoVendedor.notificar(pergunta,new Gmail());
            return ResponseEntity.ok(Map.of("id",pergunta.getId()));
        }

}
