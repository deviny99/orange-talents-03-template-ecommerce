package br.com.zup.mercadolivre.pergunta.web.dto.response;

import br.com.zup.mercadolivre.pergunta.data.domain.Pergunta;

public class PerguntaResponse {

    private String titulo;

    public PerguntaResponse(Pergunta pergunta) {
        this.titulo = pergunta.getTitulo();
    }

    public String getTitulo() {
        return titulo;
    }
}
