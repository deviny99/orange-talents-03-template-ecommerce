package br.com.zup.mercadolivre.clientes;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "notaFiscalClient", url = "http://localhost:8080/mercado-livre")
public interface NotaFiscalClient {

    @RequestMapping(value = "/notas-fiscais", method = RequestMethod.GET)
    void notasFicais(@RequestParam("idCompra") Long idCompra, @RequestParam("idUsuario") Long idUsuario);

}
