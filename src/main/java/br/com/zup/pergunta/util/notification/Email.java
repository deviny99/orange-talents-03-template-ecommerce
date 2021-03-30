package br.com.zup.pergunta.util.notification;

public interface Email {

     default void enviar(String conteudo){
         System.out.println(conteudo);
    }
}
