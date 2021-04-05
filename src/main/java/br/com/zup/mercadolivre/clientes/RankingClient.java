package br.com.zup.mercadolivre.clientes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "ranking", url = "http://localhost:8080/mercado-livre")
public interface RankingClient {

    @RequestMapping(value = "/ranking", method = RequestMethod.GET)
    void atualizarRanking(@RequestParam("idCompra") Long idCompra, @RequestParam("idVendedor") Long idVendedor);

}
