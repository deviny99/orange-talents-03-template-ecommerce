package br.com.zup.mercadolivre.produto.data.domain;

import br.com.zup.mercadolivre.categoria.data.domain.Categoria;
import br.com.zup.mercadolivre.usuario.data.domain.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ProdutoTest {

    @BeforeEach
    private void setup(){

    }

    private List<Caracteristica> listaCaracteristicas(){
        List<Caracteristica> caracteristicas = new ArrayList<>();
        caracteristicas.add(new Caracteristica(1L,"Caracteristica 1","1"));
        caracteristicas.add(new Caracteristica(2L,"Caracteristica 2","2"));
        caracteristicas.add(new Caracteristica(3L,"Caracteristica 1","3"));
        caracteristicas.add(new Caracteristica(4L,"Caracteristica 2","4"));//
        caracteristicas.add(new Caracteristica(5L,"Caracteristica 5","5"));//
        caracteristicas.add(new Caracteristica(6L,"Caracteristica 6","6"));
        return caracteristicas;
    }


    @Test
    public void naoDeveAceitarMesmasCaracteristicas(){

        Produto produto = new Produto(null,"Iphone 11",new BigDecimal(12000.00),2,new HashSet<Caracteristica>(),
                "Produto caro", new Categoria(1L,"Celulares",null),new HashSet(), new Usuario(1l,"user","senha",null));

        for(Caracteristica caracteristica : this.listaCaracteristicas()){
            produto.getCaracteristicas().add(caracteristica);
        }

        Assertions.assertTrue(produto.getCaracteristicas().contains(new Caracteristica(1L,"Caracteristica 1","1")));
        Assertions.assertTrue(produto.getCaracteristicas().contains(new Caracteristica(2L,"Caracteristica 2","2")));
        Assertions.assertTrue(produto.getCaracteristicas().contains(new Caracteristica(5L,"Caracteristica 5","5")));
        Assertions.assertTrue(produto.getCaracteristicas().contains(new Caracteristica(6L,"Caracteristica 6","6")));
        Assertions.assertEquals(4,produto.getCaracteristicas().size());

    }

}
