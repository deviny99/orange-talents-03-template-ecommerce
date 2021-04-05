package br.com.zup.mercadolivre.compra.data.service;

import br.com.zup.global.StatusTransacao;
import br.com.zup.mercadolivre.clientes.NotaFiscalClient;
import br.com.zup.mercadolivre.clientes.RankingClient;
import br.com.zup.mercadolivre.compra.data.domain.Compra;
import br.com.zup.mercadolivre.compra.data.domain.TransacaoCompra;
import br.com.zup.mercadolivre.compra.data.repository.CompraRepository;
import br.com.zup.mercadolivre.compra.web.dto.request.RetornoGatewayDePagamento;
import br.com.zup.mercadolivre.global.util.notification.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import static br.com.zup.mercadolivre.global.web.exception.ControllerException.notFound;

@Service
public class FinalizarCompraServiceImpl implements FinalizarCompraService{

    private final CompraRepository compraRepository;
    private final RankingClient rankingClient;
    private final NotaFiscalClient notaFiscalClient;

    @Autowired
    public FinalizarCompraServiceImpl(CompraRepository compraRepository, RankingClient rankingClient, NotaFiscalClient notaFiscalClient){
        this.rankingClient = rankingClient;
        this.compraRepository = compraRepository;
        this.notaFiscalClient = notaFiscalClient;
    }

    public void notificarComprador(Compra compra, Email email, RetornoGatewayDePagamento retornoGatewayDePagamento){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n\n\n");
        if (retornoGatewayDePagamento.getStatusTransacao().equals(StatusTransacao.NEGADO))
        {
            stringBuilder.append("Sua Compra foi Negada!").append("\n")
                    .append("De: ").append("Mercado Livre").append("\n")
                    .append("Para: ").append(compra.getComprador().getUsername()).append("\n")
                    .append("\n")
                    .append("Compra de ID: ").append(compra.getId()).append("\n")
                    .append("Tente novamente por: ").append(retornoGatewayDePagamento.getUrlValue());
        }else
        {

            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

            stringBuilder.append("Sua Compra foi Aprovada!").append("\n")
                    .append("De: ").append("Mercado Livre").append("\n")
                    .append("Para: ").append(compra.getComprador().getUsername()).append("\n")
                    .append("\n")
                    .append("Compra de ID: ").append(compra.getId()).append("\n")
                    .append("Instante da compra: ").append(compra.getInstanteCompra().format(dateFormat)).append("\n");

        }
        stringBuilder.append("\n\n\n");
        email.enviar(stringBuilder.toString());
    }

    @Override
    public Compra finalizarTransacao(Long idCompra, RetornoGatewayDePagamento retornoGatewayDePagamento) {
        Optional<Compra> compraOptional = this.compraRepository.findById(idCompra);
        if(!compraOptional.isPresent()) {
            notFound("Não existe essa ordem de compra");
        }
        Compra compra = compraOptional.get();
        compra.addTransacao(new TransacaoCompra(null, retornoGatewayDePagamento.getStatusTransacao()));
        return this.compraRepository.save(compra);
    }

    @Override
    public void emitirNotaFiscal(Compra compra) {
        if (compra.compraAprovadaComSucesso()){
            notaFiscalClient.notasFicais(compra.getId(),compra.getComprador().getId());
        }
    }

    @Override
    public void atualizarSistemaRanking(Compra compra) {
        if (compra.compraAprovadaComSucesso()){
                compra.getProdutos().forEach( produtoQuantidade -> {
                    rankingClient.atualizarRanking(compra.getId(),produtoQuantidade.getProduto().getUsuario().getId());
                });
        }
    }
}
