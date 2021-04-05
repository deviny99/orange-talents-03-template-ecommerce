package br.com.zup.mercadolivre.compra.web.dto.request;

import br.com.zup.global.StatusTransacao;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;

public class PagSeguroRequest implements RetornoGatewayDePagamento {

    @NotBlank
    @JsonProperty("status")
    private String status;
    @JsonProperty("url")
    private String url;



    public String getUrlValue() {
        return url;
    }
    @Deprecated
    public PagSeguroRequest(){}

    public StatusTransacao getStatusTransacao() {

        return (status.equalsIgnoreCase("SUCESSO")) ? StatusTransacao.APROVADO: StatusTransacao.NEGADO;
    }

}
