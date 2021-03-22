package br.com.zup.categoria.data.repository;

import br.com.zup.categoria.data.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> { }