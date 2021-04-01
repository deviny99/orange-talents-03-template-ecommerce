package br.com.zup.mercadolivre.produto.web.dto.response;

import br.com.zup.mercadolivre.categoria.web.dto.response.CategoriaResponse;
import br.com.zup.mercadolivre.imagem.web.dto.response.ImagemResponse;
import br.com.zup.mercadolivre.opiniao.data.domain.Opiniao;
import br.com.zup.mercadolivre.opiniao.web.dto.response.OpiniaoResponse;
import br.com.zup.mercadolivre.pergunta.data.domain.Pergunta;
import br.com.zup.mercadolivre.pergunta.web.dto.response.PerguntaResponse;
import br.com.zup.mercadolivre.produto.data.domain.Produto;
import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

public class DetalhesProduto {

    private String nome;
    private BigDecimal valor;
    private String descricao;
    private CategoriaResponse categoria;
    private Set<CaracteristicaResponse> caracteristicas;
    private Double mediaNotas;
    private Set<OpiniaoResponse> opinioes;
    private Set<PerguntaResponse> perguntas ;
    private Set<ImagemResponse> imagens ;
    private Integer totalOpinioes;
    private Integer totalPerguntas;
    private Integer quantidade;

    public DetalhesProduto(Produto produto, Set<Opiniao> opinioes, Set<Pergunta> perguntas,
                           Integer totalOpinioes, Integer totalPerguntas, Double mediaNotas) {

        this.opinioes = opinioes.stream().map(OpiniaoResponse::new).collect(Collectors.toSet());
        this.perguntas = perguntas.stream().map(PerguntaResponse::new).collect(Collectors.toSet());
        this.nome = produto.getNome();
        this.descricao = produto.getDescricao();
        this.categoria = new CategoriaResponse(produto.getCategoria());
        this.caracteristicas = produto.getCaracteristicas().stream().map(CaracteristicaResponse::new).collect(Collectors.toSet());
        this.mediaNotas = mediaNotas;
        this.totalOpinioes = totalOpinioes;
        this.imagens = produto.getImagens().stream().map(ImagemResponse::new).collect(Collectors.toSet());
        this.totalPerguntas = totalPerguntas;
        this.valor = produto.getValor();
        this.quantidade = produto.getQuantidade();

    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public CategoriaResponse getCategoria() {
        return categoria;
    }

    public Set<CaracteristicaResponse> getCaracteristicas() {
        return caracteristicas;
    }

    public Double getMediaNotas() {
        return mediaNotas;
    }


    public Set<OpiniaoResponse> getOpinioes() {
        return opinioes;
    }

    public Set<PerguntaResponse> getPerguntas() {
        return perguntas;
    }

    public Set<ImagemResponse> getImagens() {
        return imagens;
    }

    public Integer getTotalOpinioes() {
        return totalOpinioes;
    }

    public Integer getTotalPerguntas() {
        return totalPerguntas;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
