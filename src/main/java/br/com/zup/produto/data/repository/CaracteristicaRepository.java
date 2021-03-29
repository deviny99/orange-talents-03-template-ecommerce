package br.com.zup.produto.data.repository;

import br.com.zup.produto.data.domain.Caracteristica;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaracteristicaRepository extends CrudRepository<Caracteristica,Long> {
}
