package br.com.zup.mercadolivre.opiniao.data.repository;

import br.com.zup.mercadolivre.opiniao.data.domain.Opiniao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OpiniaoRepository extends JpaRepository<Opiniao,Long> {

    @Query("SELECT o FROM Opiniao o JOIN o.produto p WHERE p.id = ?1")
    Page<Opiniao> findByProduto(Long id, Pageable pageable);

    @Query("SELECT COUNT(o) FROM Opiniao o JOIN o.produto p WHERE p.id = ?1")
    Integer totalOpinioesByProduto(Long id);

    @Query("SELECT AVG(o.nota) FROM Opiniao o JOIN o.produto p WHERE p.id = ?1")
    Double mediaNotasByProduto(Long id);
}
