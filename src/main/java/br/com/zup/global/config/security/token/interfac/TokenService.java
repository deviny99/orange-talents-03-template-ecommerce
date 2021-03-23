package br.com.zup.global.config.security.token.interfac;

import org.springframework.security.core.Authentication;

public interface TokenService {

     String gerarToken(Authentication userAutenticado);
     boolean isTokenValido(String token);
     Long retornarIdDoUsuarioPresenteNoCorpoDoToken(String token);
     String recuperarETratarToken(String token);
}
