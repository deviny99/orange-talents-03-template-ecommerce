package br.com.zup.produto.web.controller;

import br.com.zup.produto.data.domain.Produto;
import br.com.zup.produto.data.domain.ProdutoBuilderImpl;
import br.com.zup.produto.data.repository.ProdutoRepository;
import br.com.zup.produto.web.dto.request.ProdutoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoController(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrarProduto(@RequestBody @Valid ProdutoRequest produtoRequest){

        Produto produto = new ProdutoBuilderImpl()
                .addId(produtoRequest.getId())
                .addNome(produtoRequest.getNome())
                .addCategoria(produtoRequest.getCategoriaID())
                .addDescricao(produtoRequest.getDescricao())
                .addQuantidade(produtoRequest.getQuantidade())
                .addCaracteristicas(produtoRequest.mapList())
                .addValor(produtoRequest.getValor())
                .build();

        produto = this.produtoRepository.save(produto);
        return ResponseEntity.created(URI.create(String.format("/pessoa/%d", produto.getId())))
                .body(Map.of("id",produto.getId()));
    }

}