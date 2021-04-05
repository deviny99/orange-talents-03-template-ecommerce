package br.com.zup.gateway.data.domain;

import org.springframework.http.HttpMethod;

public enum MethodType {

    POST(HttpMethod.POST),
    PUT(HttpMethod.PUT),
    GET(HttpMethod.GET),
    DELETE(HttpMethod.DELETE);

    MethodType(HttpMethod method){
        this.method = method;
    }

    private HttpMethod method;

    public HttpMethod getMethod() {
        return method;
    }
}
