package br.com.zup.gateway.data.domain;

import br.com.zup.global.StatusTransacao;
import javax.persistence.*;

@Entity
@Table(name = "transacoes")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private StatusTransacao statusTransacao;
    @Column(name = "idCompra", nullable = false)
    private Long idCompra;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private UrlNotification urlNotification;

    @Deprecated
    public Transacao(){}

    public Transacao(Long id, Long idCompra, UrlNotification urlNotification) {
        this.id = id;
        this.idCompra = idCompra;
        this.urlNotification = urlNotification;
    }

    public void aprovarTransacao(){
        this.statusTransacao = StatusTransacao.APROVADO;
    }

    public void negarTransacao(){
        this.statusTransacao = StatusTransacao.NEGADO;
    }

    public void iniciarTransacao(){
        this.statusTransacao = StatusTransacao.PROCESSAMENTO;
    }

    public StatusTransacao getStatusTransacao() {
        return statusTransacao;
    }

    public UrlNotification getUrlNotification() {
        return urlNotification;
    }

    public Long getIdCompra() {
        return idCompra;
    }

    public Long getId() {
        return id;
    }
}