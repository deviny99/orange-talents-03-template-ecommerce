package br.com.zup.ranking.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ranking")
public class RankingVendedoresController {

    @GetMapping
    public void atualizarRanking(@RequestParam("idCompra") Long idCompra, @RequestParam("idVendedor") Long idVendedor){
        System.out.println();
        System.out.println();
        System.out.println("Atualizando Ranking de Vendedores...");
        System.out.println("Vendedor: "+idVendedor);
        System.out.println();
        System.out.println();
    }

}