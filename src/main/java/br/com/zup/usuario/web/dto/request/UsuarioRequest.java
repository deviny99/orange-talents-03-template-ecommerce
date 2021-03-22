package br.com.zup.usuario.web.dto.request;

import br.com.zup.global.web.validations.Unique;
import br.com.zup.usuario.data.domain.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UsuarioRequest {

    private Long id;
    @NotBlank
    @Email
    @Unique(targetEntity = Usuario.class, nameField = "email", message = "Um usuario já foi cadastrado com o mesmo email")
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
