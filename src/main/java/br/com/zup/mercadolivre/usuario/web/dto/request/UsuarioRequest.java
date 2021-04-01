package br.com.zup.mercadolivre.usuario.web.dto.request;

import br.com.zup.mercadolivre.global.web.validations.Unique;
import br.com.zup.mercadolivre.usuario.data.domain.NivelAcesso;
import br.com.zup.mercadolivre.usuario.data.domain.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class UsuarioRequest {

    @NotBlank
    @Email
    @Unique(targetEntity = Usuario.class, nameField = "email", message = "Um usuario já foi cadastrado com o mesmo email")
    @JsonProperty("email")
    private String email;
    @NotBlank
    @Size(min = 6)
    @JsonProperty("senha")
    private String senha;

    @Deprecated
    public UsuarioRequest() { }

    public Usuario toModel(Long id,List<NivelAcesso> niveisAcessos){
        return new Usuario(id,
                this.email,
                cripto(this.senha),
                niveisAcessos);
    }

    private String cripto(String senha){
        return new BCryptPasswordEncoder().encode(senha);
    }
}
