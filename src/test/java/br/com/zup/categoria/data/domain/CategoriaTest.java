package br.com.zup.categoria.data.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoriaTest {

    @Test
    void naoDeveReceberElaMesmaComoCategoriaMae(){

        Categoria categoria = new Categoria(1l,"Celulares",new Categoria(1l));

        Assertions.assertNull(categoria.getCategoriaSuper());
        Assertions.assertEquals(1L,categoria.getId());
    }

    @Test
    void deveReceberOutraCategoriaComoMae(){

        Categoria categoria = new Categoria(1l,"Celulares",new Categoria(2l));
        categoria.setId(1l);
        Assertions.assertNotNull(categoria.getCategoriaSuper());
        Assertions.assertEquals(1L,categoria.getId());
    }


}
