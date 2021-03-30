package br.com.zup.opiniao.data.repository;

import br.com.zup.opiniao.data.domain.Opiniao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpiniaoRepository extends JpaRepository<Opiniao,Long> {
}
