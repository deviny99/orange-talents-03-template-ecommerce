package br.com.zup.produto.data.repository;

import br.com.zup.produto.data.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {

    @Query("SELECT p FROM Produto p JOIN p.caracteristicas c WHERE p.id = ?1")
    Optional<Produto> findById(Long id);

}
