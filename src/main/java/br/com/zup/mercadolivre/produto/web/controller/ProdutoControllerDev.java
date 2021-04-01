package br.com.zup.mercadolivre.produto.web.controller;


import br.com.zup.mercadolivre.produto.data.domain.Produto;
import br.com.zup.mercadolivre.produto.data.domain.builder.ProdutoBuilderImpl;
import br.com.zup.mercadolivre.produto.data.repository.ProdutoRepository;
import br.com.zup.mercadolivre.produto.web.dto.request.ProdutoRequest;
import br.com.zup.mercadolivre.usuario.data.domain.NivelAcesso;
import br.com.zup.mercadolivre.usuario.data.domain.Roles;
import br.com.zup.mercadolivre.usuario.data.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Profile("dev")
@RestController
@RequestMapping("/produtos")
public class ProdutoControllerDev {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoControllerDev(ProdutoRepository produtoRepository){
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
                .addUsuario(this.simularUsuarioLogado())
                .build();

        produto = this.produtoRepository.save(produto);


        return ResponseEntity.created(URI.create(String.format("/pessoa/%d", produto.getId())))
                .body(Map.of("id",produto.getId()));
    }


    private Usuario simularUsuarioLogado() {
        List<NivelAcesso> niveisAcesso = new ArrayList<>();
        niveisAcesso.add(new NivelAcesso(Roles.ROLE_USER));
        return new Usuario(1L,"email@email","123456",niveisAcesso);
    }

}