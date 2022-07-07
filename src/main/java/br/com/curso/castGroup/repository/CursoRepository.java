package br.com.curso.castGroup.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.curso.castGroup.entities.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

	
	public List<Curso> getByDescricao(String descricao);
	
	@Query("select c from Curso c where "
			+ " :dataInicio <= c.dataInicio"
			+ " and :dataFim >= c.dataFim")
	public List<Curso> getByDataInicio(@Param("dataInicio")LocalDate dataInicio,@Param("dataFim") LocalDate dataFim);
	

}
