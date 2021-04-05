package br.com.zup.mercadolivre.clientes;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "paypalClient", url = "http://localhost:8080/mercado-livre/paypal")
public interface PaypalClient extends GatewayClient{

}
