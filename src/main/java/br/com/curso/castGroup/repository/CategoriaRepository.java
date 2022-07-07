package br.com.curso.castGroup.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.curso.castGroup.entities.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>{

}
