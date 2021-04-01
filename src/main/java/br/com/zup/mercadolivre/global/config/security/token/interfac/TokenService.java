package br.com.zup.mercadolivre.global.config.security.token.interfac;

import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;

@Profile({"prod","test"})
public interface TokenService {

     String gerarToken(Authentication userAutenticado);
     boolean isTokenValido(String token);
     Long retornarIdDoUsuarioPresenteNoCorpoDoToken(String token);
     String recuperarETratarToken(String token);
}
