package br.com.zup.mercadolivre.compra.data.domain;

import br.com.zup.global.StatusTransacao;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table
public class TransacaoCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private StatusTransacao statusTransacao;
    @Column(name = "instanteTransacao", nullable = false)
    private LocalDateTime instanteTransacao = LocalDateTime.now();

    @Deprecated
    TransacaoCompra(){}

    public TransacaoCompra(Long id, StatusTransacao statusTransacao) {
        this.id = id;
        this.statusTransacao = statusTransacao;
    }

    public boolean isAprovada(){
        return this.statusTransacao.equals(StatusTransacao.APROVADO);
    }

    public StatusTransacao getStatusTransacao() {
        return statusTransacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransacaoCompra)) return false;
        TransacaoCompra that = (TransacaoCompra) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}