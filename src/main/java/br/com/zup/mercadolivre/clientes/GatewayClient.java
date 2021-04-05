package br.com.zup.mercadolivre.clientes;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.Map;

public interface GatewayClient {

    @RequestMapping(value = "/transacao/{idCompra}", method = RequestMethod.POST)
    ResponseEntity<?> realizarTransacao(@RequestBody Map map, @PathVariable("idCompra") Long idCompra);

}
