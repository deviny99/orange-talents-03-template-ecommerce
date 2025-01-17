package br.com.zup.mercadolivre.produto.web.controller.dev;

import br.com.zup.mercadolivre.global.util.storage.GoogleStorage;
import br.com.zup.mercadolivre.produto.data.repository.ProdutoRepository;
import br.com.zup.mercadolivre.produto.data.service.ProdutoImagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static br.com.zup.mercadolivre.global.web.exception.ControllerException.badRequest;

@Profile("dev")
@RestController
@RequestMapping("/produtos/imagem")
public class ImagemProdutoControllerDev {

    private final ProdutoImagemService produtoImagemService;
    private final ProdutoRepository produtoRepository;

    @Autowired
    public ImagemProdutoControllerDev(ProdutoImagemService produtoImagemService, ProdutoRepository produtoRepository){
        this.produtoImagemService = produtoImagemService;
        this.produtoRepository = produtoRepository;
    }


    @PostMapping("{produto}")
    @Transactional
    public ResponseEntity<?> cadastrarImagemProduto(@RequestParam("imagens") MultipartFile[] multipartFiles,
                                                    @PathVariable("produto") Long idProduto) throws IOException {
        List<String> uris = new ArrayList<>();

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
