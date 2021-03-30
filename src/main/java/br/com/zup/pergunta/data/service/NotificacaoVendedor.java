package br.com.zup.pergunta.data.service;

import br.com.zup.pergunta.data.domain.Pergunta;
import br.com.zup.pergunta.data.repository.PerguntaRepository;
import br.com.zup.pergunta.util.notification.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class NotificacaoVendedor {

    private PerguntaRepository perguntaRepository;

    @Autowired
    public NotificacaoVendedor(PerguntaRepository perguntaRepository) {
        this.perguntaRepository = perguntaRepository;
    }

    public void notificar(Long idPergunta, Email email){

        if(idPergunta != null){
            Optional<Pergunta> optional = this.perguntaRepository.findById(idPergunta);

            if(optional.isPresent())
            {
                Pergunta pergunta = optional.get();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("\n\nVocê tem uma nova pergunta!")
                        .append("\nde: "+pergunta.getUsuario().getUsername())
                        .append("\npara: "+pergunta.getProduto().getUsuario().getUsername())
                        .append("\n\nID do Produto: " +pergunta.getProduto().getId())
                        .append("\nNome do Produto: "+pergunta.getProduto().getNome())
                        .append("\nPergunta: "+pergunta.getTitulo())
                        .append("\n\n");

                email.enviar(stringBuilder.toString());
            }
        }
    }
}
