package br.com.zup.usuario.web.dto.response;

public class JwtResponse {

    private String type;
    private String token;

    @Deprecated
    public JwtResponse(){}

    public JwtResponse(String type, String token) {
        this.type = type;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }

}
