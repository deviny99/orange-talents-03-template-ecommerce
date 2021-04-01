package br.com.zup.mercadolivre.pergunta.data.repository;

import br.com.zup.mercadolivre.pergunta.data.domain.Pergunta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PerguntaRepository extends JpaRepository<Pergunta,Long> {

    @Override
    Optional<Pergunta> findById(Long id);

    @Query("SELECT ask FROM Pergunta ask JOIN ask.produto p WHERE p.id = ?1")
    Page<Pergunta> findByProduto(Long id, PageRequest of);

    @Query("SELECT COUNT(ask) FROM Pergunta ask JOIN ask.produto p WHERE p.id = ?1")
    Integer totalPerguntasByProduto(Long id);
}
