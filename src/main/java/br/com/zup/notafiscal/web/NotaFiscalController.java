package br.com.zup.notafiscal.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notas-fiscais")
public class NotaFiscalController {

    @GetMapping
    public void notasFicais(@RequestParam("idCompra") Long idCompra, @RequestParam("idUsuario") Long idUsuario){
        System.out.println();
        System.out.println();
        System.out.println("Emitindo nota fiscal da compra de ID: "+idCompra+"\nUsuario: "+idUsuario);
        System.out.println();
        System.out.println();
    }

}