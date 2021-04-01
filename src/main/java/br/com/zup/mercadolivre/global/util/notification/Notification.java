package br.com.zup.mercadolivre.global.util.notification;

public class Notification {

    public static void notificar(String assunto,String emailDestinatario, String emailRemetente,String conteudo, Email email){

                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("\n\n")
                        .append(assunto)
                        .append("\nde: "+emailRemetente)
                        .append("\npara: "+emailDestinatario)
                        .append("\n\n")
                        .append(conteudo)
                        .append("\n\n");

                email.enviar(stringBuilder.toString());
    }
}
