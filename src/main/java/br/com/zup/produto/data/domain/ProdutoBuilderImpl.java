package br.com.zup.produto.data.domain;

import br.com.zup.categoria.data.domain.Categoria;
import java.math.BigDecimal;
import java.util.Set;

public class ProdutoBuilderImpl implements  ProdutoBuilder {

    private Long id;
    private String nome;
    private BigDecimal valor;
    private Integer quantidade;
    private Set<Caracteristica> caracteristicas;
    private String descricao;
    private Categoria categoria;


    @Override
    public ProdutoBuilder addNome(String nome) {
        this.nome = nome;
        return this;
    }

    @Override
    public ProdutoBuilder addValor(BigDecimal valor) {
        this.valor = valor;
        return this;
    }

    @Override
    public ProdutoBuilder addQuantidade(Integer qtd) {
        this.quantidade = qtd;
        return this;
    }

    @Override
    public ProdutoBuilder addDescricao(String desc) {
        this.descricao = desc;
        return this;
    }

    @Override
    public ProdutoBuilder addCategoria(Long categoriaID) {
        this.categoria = new Categoria(categoriaID);
        return this;
    }

    @Override
    public ProdutoBuilder addCategoria(Categoria categoria) {
        this.categoria = categoria;
        return this;
    }

    @Override
    public ProdutoBuilder addCaracteristicas(Set<Caracteristica> caracteristicas) {
        this.caracteristicas = caracteristicas;
        return this;
    }

    @Override
    public ProdutoBuilder addCaracteristica(Caracteristica caracteristica) {
        this.caracteristicas.add(caracteristica);
        return this;
    }

    @Override
    public ProdutoBuilder addId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public Produto build() {
        return new Produto(this.id,this.nome,this.valor,this.quantidade,this.caracteristicas,this.descricao,this.categoria);
    }
}
