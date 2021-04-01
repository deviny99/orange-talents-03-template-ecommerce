package br.com.zup.mercadolivre.pergunta.data.service;

import br.com.zup.mercadolivre.global.util.notification.Notification;
import br.com.zup.mercadolivre.pergunta.data.domain.Pergunta;
import br.com.zup.mercadolivre.pergunta.data.repository.PerguntaRepository;
import br.com.zup.mercadolivre.global.util.notification.Email;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class NotificacaoVendedor {


    public static void notificar(Pergunta pergunta, Email email){

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder
                        .append("ID do Produto: " +pergunta.getProduto().getId())
                        .append("\nNome do Produto: "+pergunta.getProduto().getNome())
                        .append("\nPergunta: "+pergunta.getTitulo());

                Notification.notificar("Você tem uma nova Pergunta!",
                        pergunta.getProduto().getUsuario().getUsername(),
                        pergunta.getUsuario().getUsername(),
                        stringBuilder.toString(),
                        email
                );
    }
}