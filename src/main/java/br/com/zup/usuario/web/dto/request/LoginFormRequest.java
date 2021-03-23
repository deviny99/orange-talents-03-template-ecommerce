package br.com.zup.usuario.web.dto.request;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginFormRequest {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String senha;

    @Deprecated
    public LoginFormRequest(){ }

    public LoginFormRequest(@NotBlank @Email String email, @NotBlank String senha) {
        this.email = email;
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public String getEmail() {
        return email;
    }

    public UsernamePasswordAuthenticationToken convertToUserAuthentication() {
        return new UsernamePasswordAuthenticationToken(this.email,this.senha);
    }
}
