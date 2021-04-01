package br.com.zup.mercadolivre.imagem.web.dto.response;

import br.com.zup.mercadolivre.imagem.data.domain.Imagem;

public class ImagemResponse {

    private String uri;

    public ImagemResponse(Imagem imagem) {
        this.uri = imagem.getUri();
    }

    public String getUri() {
        return uri;
    }
}
