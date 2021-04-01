package br.com.zup.mercadolivre.categoria.web.controller;

import br.com.zup.mercadolivre.categoria.data.domain.Categoria;
import br.com.zup.mercadolivre.categoria.data.repository.CategoriaRepository;
import br.com.zup.mercadolivre.categoria.web.dto.request.CategoriaRequest;
import br.com.zup.mercadolivre.categoria.web.dto.response.CategoriaResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaController(CategoriaRepository categoriaRepository)
    {
        this.categoriaRepository = categoriaRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrarCategoria(@RequestBody @Valid CategoriaRequest categoriaRequest)
    {
        Categoria categoria = this.categoriaRepository.save(categoriaRequest.toModel(null));
        return ResponseEntity.ok(new CategoriaResponse(categoria));
    }

}