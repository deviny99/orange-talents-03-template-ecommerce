package br.com.zup.mercadolivre.produto.data.domain.builder;

import br.com.zup.mercadolivre.categoria.data.domain.Categoria;
import br.com.zup.mercadolivre.imagem.data.domain.Imagem;
import br.com.zup.mercadolivre.produto.data.domain.Caracteristica;
import br.com.zup.mercadolivre.produto.data.domain.Produto;
import br.com.zup.mercadolivre.usuario.data.domain.Usuario;

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
    private Set<Imagem> imagems;
    private Usuario user;

    @Override
    public ProdutoBuilder addNome(String nome) {
        this.nome = nome;
        return this;
    }

    @Override
    public ProdutoBuilder addUsuario(Usuario user) {
        this.user = user;
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
    public ProdutoBuilder addImagem(Imagem imagem) {
        this.imagems.add(imagem);
        return this;
    }

    @Override
    public ProdutoBuilder addImagems(Set<Imagem> imagems) {
        this.imagems.addAll(imagems);
        return this;
    }

    @Override
    public ProdutoBuilder addId(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public Produto build() {

        return new Produto(this.id,this.nome,this.valor,this.quantidade,
                this.caracteristicas,this.descricao,this.categoria, this.imagems,this.user);
    }
}
