package br.com.zup.imagem.data.repository;

import br.com.zup.imagem.data.domain.Imagem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagemRepository extends CrudRepository<Imagem,Long> {
}
