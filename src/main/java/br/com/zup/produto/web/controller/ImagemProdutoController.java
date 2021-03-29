package br.com.zup.produto.web.controller;

import br.com.zup.global.config.storage.GoogleStorage;
import br.com.zup.produto.data.repository.ProdutoRepository;
import br.com.zup.produto.data.service.ProdutoImagemService;
import br.com.zup.usuario.data.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static br.com.zup.global.web.exception.ControllerException.badRequest;

@RestController
@RequestMapping("/produtos/imagem")
public class ImagemProdutoController {

    private final ProdutoImagemService produtoImagemService;
    private final ProdutoRepository produtoRepository;

    @Autowired
    public ImagemProdutoController(ProdutoImagemService produtoImagemService, ProdutoRepository produtoRepository){
        this.produtoImagemService = produtoImagemService;
        this.produtoRepository = produtoRepository;
    }

    @PostMapping("{produto}")
    public ResponseEntity<?> cadastrarImagemProduto(@RequestParam("imagens") MultipartFile[] multipartFiles,
                                                    @PathVariable("produto") Long idProduto) throws IOException {
        List<String> uris = new ArrayList<>();
        Long idUserLogado = ((Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();

        if(!this.produtoRepository.findByProdutoAndUser(idProduto,idUserLogado).isPresent()){
            throw badRequest("Não foi possivel adicionar imagem ao produto pois o produto pertence a outro usuario");
        }

            if (multipartFiles.length > 0 && idProduto != null) {
                for (MultipartFile multipartFile : multipartFiles) {
                    uris.add(this.produtoImagemService.salvarImagemCloud(idProduto, multipartFile.getInputStream(), new GoogleStorage()));
                }
            } else {
                throw badRequest("");
            }

        return ResponseEntity.ok(Map.of("URIs",uris));
    }

}
