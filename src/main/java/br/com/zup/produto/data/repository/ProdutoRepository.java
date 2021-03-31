package br.com.zup.produto.data.repository;

import br.com.zup.produto.data.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto,Long> {

    @Query("SELECT p FROM Produto p LEFT JOIN FETCH p.caracteristicas c LEFT JOIN FETCH p.imagens i WHERE p.id = ?1")
    Optional<Produto> findByIdFetch(Long id);

    @Query("SELECT p FROM Produto p JOIN p.usuario u " +
            "WHERE p.id = ?1 AND u.id = ?2")
    Optional<Produto> findByProdutoAndUser(Long produto,Long user);

}
