package br.com.zup.mercadolivre.compra.data.domain;

import br.com.zup.mercadolivre.produto.data.domain.Produto;
import javax.persistence.*;

@Entity
@Table(name = "produtosQuantidade")
public class ProdutoQuantidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(referencedColumnName = "id")
    private Produto produto;
    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Deprecated
    public ProdutoQuantidade(){ }

    public ProdutoQuantidade(Long id, Produto produto, Integer quantidade) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Integer getQuantidade() {
        return this.quantidade;
    }

    public Produto getProduto() {
        return this.produto;
    }

}
