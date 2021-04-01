package br.com.zup.mercadolivre.global.util.notification;

public interface Email {

     default void enviar(String conteudo){
         System.out.println(conteudo);
    }
}
