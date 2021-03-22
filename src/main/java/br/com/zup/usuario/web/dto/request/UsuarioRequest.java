package br.com.zup.usuario.web.dto.request;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioRequest {

    private Long id;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Size(min = 6)
    private String senha;

    @Deprecated
    public UsuarioRequest() {
    }

    public UsuarioRequest(Long id, @NotBlank @Email String email, @NotBlank @Size(min = 6) String senha) {
        this.id = id;
        this.email = email;
        this.senha = cripto(senha);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return cripto(senha);
    }

    private String cripto(String senha){
        return new BCryptPasswordEncoder().encode(senha);
    }
}
