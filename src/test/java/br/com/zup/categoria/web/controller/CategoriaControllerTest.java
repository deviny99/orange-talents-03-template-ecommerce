package br.com.zup.categoria.web.controller;

import br.com.zup.categoria.data.domain.Categoria;
import br.com.zup.categoria.data.repository.CategoriaRepository;
import br.com.zup.categoria.web.dto.request.CategoriaRequest;
import br.com.zup.categoria.web.dto.response.CategoriaResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.ResponseEntity;

public class CategoriaControllerTest {

    private CategoriaController categoriaController;

    @Mock
    private CategoriaRepository categoriaRepository;
    @Captor
    private ArgumentCaptor<CategoriaResponse> categoriaResponseArgumentCaptor;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
        this.categoriaController = new CategoriaController(categoriaRepository);
    }

    private Categoria devolverCategoria(){
            return new Categoria(1l,"Eletrodomesticos",null);
    }

    private Categoria injetarCategoria(){
        return new Categoria("Eletrodomesticos",null);
    }

    @Test
    void deveSalvarCategoria(){

        Mockito.when(categoriaRepository.save(injetarCategoria())).thenReturn(devolverCategoria());
        ResponseEntity<?> responseEntity = this.categoriaController.cadastrarCategoria((new CategoriaRequest(null,"Eletrodomesticos",2L)));
        Mockito.verify(categoriaRepository).save(injetarCategoria());
        Assertions.assertEquals(200,responseEntity.getStatusCodeValue());
    }


    //argument captor -> capturar um objeto gerado dentro de um mock

}