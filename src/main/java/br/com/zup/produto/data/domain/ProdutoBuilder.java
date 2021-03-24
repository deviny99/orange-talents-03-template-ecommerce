package br.com.zup.produto.data.domain;

import br.com.zup.categoria.data.domain.Categoria;

import java.math.BigDecimal;
import java.util.Set;

public interface ProdutoBuilder {

    ProdutoBuilder addNome(String nome);
    ProdutoBuilder addValor(BigDecimal valor);
    ProdutoBuilder addQuantidade(Integer qtd);
    ProdutoBuilder addDescricao(String desc);
    ProdutoBuilder addCategoria(Long categoriaID);
    ProdutoBuilder addCategoria(Categoria categoria);
    ProdutoBuilder addCaracteristicas(Set<Caracteristica> caracteristicas);
    ProdutoBuilder addCaracteristica(Caracteristica caracteristica);
    ProdutoBuilder addId(Long id);
    Produto build();
}
