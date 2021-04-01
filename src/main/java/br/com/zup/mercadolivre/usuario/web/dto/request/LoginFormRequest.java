package br.com.zup.mercadolivre.usuario.web.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginFormRequest {

    @NotBlank
    @Email
    @JsonProperty("email")
    private String email;
    @NotBlank
    @JsonProperty("senha")
    private String senha;

    @Deprecated
    public LoginFormRequest(){ }

    public UsernamePasswordAuthenticationToken convertToUserAuthentication() {
        return new UsernamePasswordAuthenticationToken(this.email,this.senha);
    }
}
