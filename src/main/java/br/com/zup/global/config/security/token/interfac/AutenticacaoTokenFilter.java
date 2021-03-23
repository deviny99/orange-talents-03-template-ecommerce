package br.com.zup.global.config.security.token.interfac;


public interface AutenticacaoTokenFilter  {

    void autenticarUsuario(String token);

}
