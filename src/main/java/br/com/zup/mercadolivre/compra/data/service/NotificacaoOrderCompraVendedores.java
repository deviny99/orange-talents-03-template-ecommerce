package br.com.zup.mercadolivre.compra.data.service;

import br.com.zup.mercadolivre.compra.data.domain.Compra;
import br.com.zup.mercadolivre.compra.data.domain.ProdutoQuantidade;
import br.com.zup.mercadolivre.global.util.notification.Email;
import br.com.zup.mercadolivre.global.util.notification.Notification;

public class NotificacaoOrderCompraVendedores {

    public static void notificar(Compra compra, Email email){
        if(compra != null){

                StringBuilder stringBuilder = new StringBuilder();

                for (ProdutoQuantidade produto : compra.getProdutos()){
                    stringBuilder
                            .append("ID do Produto: " +produto.getProduto().getId())
                            .append("\nNome do Produto: "+produto.getProduto().getNome())
                            .append("\nQuantidade: "+produto.getQuantidade())
                            .append("\nValor Total: "+produto.getProduto().getValor().doubleValue()*produto.getQuantidade());
                    send(produto.getProduto().getUsuario().getUsername(), compra.getComprador().getUsername(),stringBuilder.toString(), email);
                }
        }
    }


    private static void send(String destinatario, String remetente,String conteudo, Email email){

        Notification.notificar("Abriram uma Ordem de compra para seu produto!",
                destinatario,
                remetente,
                conteudo,
                email
                );
    }
}
