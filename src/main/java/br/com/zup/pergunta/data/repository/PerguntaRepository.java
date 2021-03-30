package br.com.zup.pergunta.data.repository;

import br.com.zup.pergunta.data.domain.Pergunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PerguntaRepository extends JpaRepository<Pergunta,Long> {

    @Override
    Optional<Pergunta> findById(Long id);
}
