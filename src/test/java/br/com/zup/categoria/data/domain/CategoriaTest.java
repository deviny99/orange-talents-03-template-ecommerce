package br.com.zup.categoria.data.domain;

import br.com.zup.categoria.web.dto.request.CategoriaRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoriaTest {

    @Test
    void naoDeveReceberElaMesmaComoCategoriaMae(){

        CategoriaRequest categoriaRequest = new CategoriaRequest(1L,"Celulares",1L);
        Categoria categoria = categoriaRequest.toModel();
        Assertions.assertNull(categoria.getCategoriaSuper());
        Assertions.assertEquals(1L,categoria.getId());

    }

    @Test
    void deveReceberOutraCategoriaComoMae(){

        CategoriaRequest categoriaRequest = new CategoriaRequest(1L,"Celulares",2L);
        Categoria categoria = categoriaRequest.toModel();
        Assertions.assertNotNull(categoria.getCategoriaSuper());
        Assertions.assertEquals(1L,categoria.getId());
    }


}
