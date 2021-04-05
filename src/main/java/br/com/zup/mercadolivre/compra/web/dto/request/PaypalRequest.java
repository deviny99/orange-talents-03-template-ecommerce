package br.com.zup.mercadolivre.compra.web.dto.request;

import br.com.zup.global.StatusTransacao;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PaypalRequest implements RetornoGatewayDePagamento {

    @NotNull
    @Min(0)
    @Max(1)
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("url")
    private String url;


    public String getUrlValue() {
        return url;
    }

    @Deprecated
    PaypalRequest(){}

    public StatusTransacao getStatusTransacao() {
        return (status.equals(1)) ? StatusTransacao.APROVADO: StatusTransacao.NEGADO;
    }


}
