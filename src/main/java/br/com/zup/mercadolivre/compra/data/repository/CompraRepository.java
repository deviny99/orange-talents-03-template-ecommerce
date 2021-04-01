package br.com.zup.mercadolivre.compra.data.repository;

import br.com.zup.mercadolivre.compra.data.domain.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CompraRepository extends JpaRepository<Compra,Long> {

    @Query("SELECT c FROM Compra c JOIN c.produtos p JOIN p.produto prod JOIN prod.caracteristicas LEFT JOIN prod.imagens WHERE c.id = ?1")
    Optional<Compra> findByIdFetch(Long id);
}
