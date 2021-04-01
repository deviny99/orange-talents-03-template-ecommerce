package br.com.zup.mercadolivre.pergunta.data.domain;

import br.com.zup.mercadolivre.produto.data.domain.Produto;
import br.com.zup.mercadolivre.usuario.data.domain.Usuario;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "perguntas")
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "titulo",nullable = false)
    private String titulo;
    @Column(name = "instanteCriacao",nullable = false)
    private LocalDateTime instanteCriacao = LocalDateTime.now();
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="produto_id", referencedColumnName="id",nullable=false)
    private Produto produto;

    @Deprecated
    public Pergunta(){}

    public Pergunta(Long id, String titulo, Usuario usuario, Produto produto) {
        this.id = id;
        this.titulo = titulo;
        this.usuario = usuario;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Produto getProduto() {
        return this.produto;
    }

}
