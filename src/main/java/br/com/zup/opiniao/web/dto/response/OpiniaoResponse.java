package br.com.zup.opiniao.web.dto.response;

import br.com.zup.opiniao.data.domain.Opiniao;

public class OpiniaoResponse {

    private String titulo;
    private String descricao;
    private Integer nota;

    public OpiniaoResponse(Opiniao opiniao) {
        this.titulo = opiniao.getTitulo();
        this.descricao = opiniao.getDescricao();
        this.nota = opiniao.getNota();
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getNota() {
        return nota;
    }

}
