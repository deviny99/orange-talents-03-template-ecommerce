package br.com.zup.mercadolivre.produto.web.controller;

import br.com.zup.mercadolivre.produto.data.domain.Produto;
import br.com.zup.mercadolivre.produto.data.domain.builder.ProdutoBuilderImpl;
import br.com.zup.mercadolivre.produto.data.repository.ProdutoRepository;
import br.com.zup.mercadolivre.produto.web.dto.request.ProdutoRequest;
import br.com.zup.mercadolivre.usuario.data.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.URI;
import java.util.Map;

@Profile({"prod","test"})
@RestController
@RequestMapping("/produtos")
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
                .addId(null)
                .addNome(produtoRequest.getNome())
                .addCategoria(produtoRequest.getCategoriaID())
                .addDescricao(produtoRequest.getDescricao())
                .addQuantidade(produtoRequest.getQuantidade())
                .addCaracteristicas(produtoRequest.mapList())
                .addValor(produtoRequest.getValor())
                .addUsuario((Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .build();

        produto = this.produtoRepository.save(produto);
        return ResponseEntity.created(URI.create(String.format("/pessoa/%d", produto.getId())))
                .body(Map.of("id",produto.getId()));
    }


}