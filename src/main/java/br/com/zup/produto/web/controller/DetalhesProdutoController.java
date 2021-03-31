package br.com.zup.produto.web.controller;

import br.com.zup.opiniao.data.domain.Opiniao;
import br.com.zup.opiniao.data.repository.OpiniaoRepository;
import br.com.zup.pergunta.data.domain.Pergunta;
import br.com.zup.pergunta.data.repository.PerguntaRepository;
import br.com.zup.produto.data.domain.Produto;
import br.com.zup.produto.data.repository.ProdutoRepository;
import br.com.zup.produto.web.dto.response.DetalhesProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import static br.com.zup.global.web.exception.ControllerException.notFound;

@RestController
@RequestMapping("/produtos/detalhes")
public class DetalhesProdutoController {

    private final OpiniaoRepository opiniaoRepository;
    private final PerguntaRepository perguntaRepository;
    private final ProdutoRepository produtoRepository;

    @Autowired
    public DetalhesProdutoController(OpiniaoRepository opiniaoRepository, PerguntaRepository perguntaRepository,
                                     ProdutoRepository produtoRepository){

        this.produtoRepository = produtoRepository;
        this.perguntaRepository = perguntaRepository;
        this.opiniaoRepository = opiniaoRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalhesProduto(@PathVariable("id") Long id){

        Optional<Produto> optionalProduto = this.produtoRepository.findByIdFetch(id);
        if (!optionalProduto.isPresent()){
            throw notFound("Não tem nenhum produto cadastrado referente ao ID informado");
        }

        Page<Opiniao> opinioes = this.opiniaoRepository.findByProduto(id, PageRequest.of(0,20));
        Page<Pergunta> perguntas = this.perguntaRepository.findByProduto(id, PageRequest.of(0,20));
        Integer totalOpinioes = this.opiniaoRepository.totalOpinioesByProduto(id);
        Double mediaNotas = this.opiniaoRepository.mediaNotasByProduto(id);
        Integer totalPerguntas = this.perguntaRepository.totalPerguntasByProduto(id);

        return ResponseEntity.ok(

                new DetalhesProduto(
                        optionalProduto.get(),
                        opinioes.toSet(),
                        perguntas.toSet(),
                        totalOpinioes,
                        totalPerguntas,
                        mediaNotas
        ));
    }

}