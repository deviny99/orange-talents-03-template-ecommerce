package br.com.zup.gateway.data.repository;

import br.com.zup.gateway.data.domain.Transacao;
import br.com.zup.global.StatusTransacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransacaoRepository extends JpaRepository<Transacao,Long> {

    Optional<Transacao> findByIdCompraAndStatusTransacao(Long aLong, StatusTransacao statusTransacao);
}
