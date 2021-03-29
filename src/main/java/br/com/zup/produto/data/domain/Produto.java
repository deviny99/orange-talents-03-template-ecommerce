package br.com.zup.produto.data.domain;

import br.com.zup.categoria.data.domain.Categoria;
import br.com.zup.imagem.data.domain.Imagem;
import br.com.zup.usuario.data.domain.Usuario;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Produtos")
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Min(1)
    @Column(name = "valor", nullable = false)
    private BigDecimal valor;
    @Min(0)
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;
    @Size(min = 3, message = "Um produto deve ter no minimo 3 caracteristicas")
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Caracteristica> caracteristicas;
    @Column(name = "descricao", length = 1000)
    private String descricao;
    @ManyToOne(fetch = FetchType.EAGER)
    private Categoria categoria;
    @Column(name = "instanteCadastro", nullable = false)
    private LocalDateTime instanteCadastro = LocalDateTime.now();
    @OneToMany(fetch = FetchType.LAZY)
    private Set<Imagem> imagens;
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;

    @Deprecated
    public Produto(){}

    public Produto(Long id, String nome, BigDecimal valor, Integer quantidade,
                   Set<Caracteristica> caracteristicas, String descricao, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoria = categoria;
        this.instanteCadastro = LocalDateTime.now();
    }

    public Produto(Long id, String nome, @Min(1) BigDecimal valor, @Min(0) Integer quantidade, @Size(min = 3, message = "Um produto deve ter no minimo 3 caracteristicas") Set<Caracteristica> caracteristicas, String descricao,
                   Categoria categoria, Set<Imagem> imagems,Usuario usuario) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
        this.caracteristicas = caracteristicas;
        this.descricao = descricao;
        this.categoria = categoria;
        this.instanteCadastro = LocalDateTime.now();
        this.imagens = imagems;
        this.usuario = usuario;
    }


    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Set<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public LocalDateTime getInstanteCadastro() {
        return instanteCadastro;
    }

    public Set<Imagem> getImagens() {
        return imagens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
