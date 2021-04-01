package br.com.zup.mercadolivre.produto.data.domain;

import br.com.zup.mercadolivre.categoria.data.domain.Categoria;
import br.com.zup.mercadolivre.imagem.data.domain.Imagem;
import br.com.zup.mercadolivre.usuario.data.domain.Usuario;
import io.swagger.models.auth.In;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;
    @OneToMany(fetch = FetchType.LAZY)
    private Set<Imagem> imagens;

    @Deprecated
    public Produto(){}

    /**Construtor utilizado para fazer referencia ao Objeto Produto.
     * Para utilizar esse construtor o Objeto pertencente ao ID inserido
     * já deve ter sido persistido.
     * @param id ID do Produto*/
    public Produto(Long id)
    {
        this.id = id;
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

    public void abaterQuantidade(Integer quantidadeAbatida){
        if(quantidadeAbatida <= this.quantidade){
            this.quantidade -= quantidadeAbatida;
        }
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Integer getQuantidade() {
        return quantidade;
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
