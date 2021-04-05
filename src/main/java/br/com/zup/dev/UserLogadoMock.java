package br.com.zup.dev;

import br.com.zup.mercadolivre.usuario.data.domain.NivelAcesso;
import br.com.zup.mercadolivre.usuario.data.domain.Roles;
import br.com.zup.mercadolivre.usuario.data.domain.Usuario;
import org.springframework.context.annotation.Profile;
import java.util.ArrayList;
import java.util.List;

@Profile("dev")
public class UserLogadoMock {

    public static Usuario simularUsuarioLogado() {
        List<NivelAcesso> niveisAcesso = new ArrayList<>();
        niveisAcesso.add(new NivelAcesso(Roles.ROLE_USER));
        return new Usuario(1L,"email@email","123456",niveisAcesso);
    }
}
