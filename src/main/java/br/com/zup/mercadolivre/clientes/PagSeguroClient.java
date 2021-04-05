package br.com.zup.mercadolivre.clientes;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "pagSeguroClient", url = "http://localhost:8080/mercado-livre/pagseguro")
public interface PagSeguroClient extends GatewayClient{

}
