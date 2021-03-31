package br.com.zup.pergunta.web.dto.response;

import br.com.zup.pergunta.data.domain.Pergunta;

public class PerguntaResponse {

    private String titulo;

    public PerguntaResponse(Pergunta pergunta) {
        this.titulo = pergunta.getTitulo();
    }

    public String getTitulo() {
        return titulo;
    }
}
