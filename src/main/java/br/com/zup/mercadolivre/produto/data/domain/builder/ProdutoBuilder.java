package br.com.zup.mercadolivre.produto.data.domain.builder;

import br.com.zup.mercadolivre.categoria.data.domain.Categoria;
import br.com.zup.mercadolivre.imagem.data.domain.Imagem;
import br.com.zup.mercadolivre.produto.data.domain.Caracteristica;
import br.com.zup.mercadolivre.produto.data.domain.Produto;
import br.com.zup.mercadolivre.usuario.data.domain.Usuario;
import java.math.BigDecimal;
import java.util.Set;

public interface ProdutoBuilder {

    ProdutoBuilder addNome(String nome);
    ProdutoBuilder addUsuario(Usuario user);
    ProdutoBuilder addValor(BigDecimal valor);
    ProdutoBuilder addQuantidade(Integer qtd);
    ProdutoBuilder addDescricao(String desc);
    ProdutoBuilder addCategoria(Long categoriaID);
    ProdutoBuilder addCategoria(Categoria categoria);
    ProdutoBuilder addCaracteristicas(Set<Caracteristica> carascteristicas);
    ProdutoBuilder addCaracteristica(Caracteristica caracteristica);
    ProdutoBuilder addId(Long id);
    ProdutoBuilder addImagem(Imagem imagem);
    ProdutoBuilder addImagems(Set<Imagem> imagens);
    Produto build();
}
