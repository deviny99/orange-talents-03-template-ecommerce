package br.com.zup.produto.data.domain;

import br.com.zup.categoria.data.domain.Categoria;
import br.com.zup.imagem.data.domain.Imagem;
import br.com.zup.usuario.data.domain.Usuario;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
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
    private Set<Caracteristica> caracteristicas = new HashSet<>();
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

    public Set<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public String getNome() {
        return this.nome;
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
