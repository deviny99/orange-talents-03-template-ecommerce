package br.com.zup.global.config.security.token.interfac;

import org.springframework.context.annotation.Profile;

@Profile({"prod","test"})
public interface AutenticacaoTokenFilter  {

    void autenticarUsuario(String token);

}
