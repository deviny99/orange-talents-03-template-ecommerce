package br.com.zup.mercadolivre.compra.data.service;

import br.com.zup.mercadolivre.compra.data.domain.Compra;
import br.com.zup.mercadolivre.compra.data.domain.StatusCompra;
import br.com.zup.mercadolivre.compra.data.repository.CompraRepository;
import br.com.zup.mercadolivre.global.util.notification.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.*;
import java.util.Optional;

@Service
public class OrdemDeCompraServiceImpl implements OrdemDeCompraService {

    private final CompraRepository compraRepository;

    @Autowired
    public OrdemDeCompraServiceImpl(CompraRepository compraRepository){
        this.compraRepository = compraRepository;
    }

    @Override
    @Transactional
    public Compra abrirOrdemDeCompra(Compra compra){
        return this.compraRepository.save(compra);
    }

    @Override
    @Transactional
    public void abaterEstoque(Long idCompra){
        Optional<Compra> optionalCompra = this.compraRepository.findById(idCompra);
        if (optionalCompra.isPresent()) {
            Compra compra = optionalCompra.get();
            compra.getProdutos().stream().forEach(it ->
            {
                it.getProduto().abaterQuantidade(it.getQuantidade());
            });
            this.compraRepository.save(compra);
        }
    }

    @Transactional
    @Override
    public void notificarGateway(Compra compra){
        System.out.println();
        System.out.println("notificanto o gateway...");
        System.out.println();


        this.alterarStatusCompra(compra, StatusCompra.AGUARDANDO);
    }

    @Override
    @Transactional(readOnly = true)
    public void notificarVendedores(Compra compra, Email email){
        Optional<Compra> optionalCompra = this.compraRepository.findById(compra.getId());
        if (optionalCompra.isPresent()){
            NotificacaoOrderCompraVendedores.notificar(optionalCompra.get(), email);
        }
    }

    @Override
    @Transactional
    public Compra alterarStatusCompra(Compra compra, StatusCompra statusCompra){
        Optional<Compra> optionalCompra = this.compraRepository.findById(compra.getId());
        if (optionalCompra.isPresent()){
            compra = optionalCompra.get();
            compra.setStatusCompra(statusCompra);
        }
        return this.compraRepository.save(compra);
    }
    @Override
    @Transactional(readOnly = true)
    public Compra detalhesCompra(Long idCompra){
        return this.compraRepository.findById(idCompra).get();
    }
}
