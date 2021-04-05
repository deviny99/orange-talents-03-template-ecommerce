package br.com.zup.gateway.web.dto;

import br.com.zup.gateway.data.domain.MethodType;
import br.com.zup.gateway.data.domain.UrlNotification;
import com.fasterxml.jackson.annotation.JsonProperty;

public class UrlRequest {

    @JsonProperty("url")
    private String url;
    //valida metodo
    @JsonProperty("method")
    private String method;

    public UrlNotification toModel(){
        return new UrlNotification(null,url,verificarMetodo());
    }

    private MethodType verificarMetodo(){
        MethodType methodType = null;
        switch (this.method.toUpperCase()){
            case "POST":
                methodType = MethodType.POST;
                break;
            case "PUT":
                methodType = MethodType.PUT;
                break;
            case "GET":
                methodType = MethodType.GET;
                break;
            case "DELETE":
                methodType = MethodType.DELETE;
                break;
        }
        return methodType;
    }

    @Override
    public String toString() {
        return "TransacaoRequest{" +
                "url='" + url + '\'' +
                ", method='" + method + '\'' +
                '}';
    }
}
