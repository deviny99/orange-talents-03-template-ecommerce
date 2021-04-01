package br.com.zup.mercadolivre.categoria.data.repository;

import br.com.zup.mercadolivre.categoria.data.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> { }