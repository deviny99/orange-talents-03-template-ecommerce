package br.com.zup.mercadolivre.imagem.data.repository;

import br.com.zup.mercadolivre.imagem.data.domain.Imagem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagemRepository extends CrudRepository<Imagem,Long> {
}
