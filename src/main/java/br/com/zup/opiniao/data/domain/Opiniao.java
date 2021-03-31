package br.com.zup.opiniao.data.domain;

import br.com.zup.produto.data.domain.Produto;
import br.com.zup.usuario.data.domain.Usuario;
import javax.persistence.*;

@Entity
@Table(name = "opinioes")
public class Opiniao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nota",nullable = false)
    private Integer nota;
    @Column(name = "titulo",nullable = false)
    private String titulo;
    @Column(name = "descricao",nullable = false)
    private String descricao;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name="produto_id", referencedColumnName="id",nullable=false)
    private Produto produto;

    @Deprecated
    public Opiniao(){ }

    public Opiniao(Long id, Integer nota, String titulo, String descricao, Usuario usuario, Produto produto) {
        this.id = id;
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.usuario = usuario;
        this.produto = produto;
    }

    public Long getId() {
        return id;
    }

    public Integer getNota() {
        return nota;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getTitulo() {
        return titulo;
    }
}
