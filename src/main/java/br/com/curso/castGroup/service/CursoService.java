package br.com.curso.castGroup.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import Request.CursoPost;
import br.com.curso.castGroup.entities.Categoria;
import br.com.curso.castGroup.entities.Curso;
import br.com.curso.castGroup.repository.CursoRepository;


@Service
public class CursoService {

	@Autowired
	CursoRepository repository;

	public List<Curso> GetAll() {
		return repository.findAll();
	}

	public List<Curso> GetByDescricao(String descricao) {
		return repository.getByDescricao(descricao);
	}

	public void cadastro(CursoPost cursoPost) {
		Curso curso = new Curso();
		br.com.curso.castGroup.entities.Categoria categoria = new Categoria(cursoPost.getCategoria());

		curso.setDescricao(cursoPost.getDescricao());
		curso.setDataInicio(LocalDate.parse(cursoPost.getDataInicio()));
		curso.setDataFim(LocalDate.parse(cursoPost.getDataFim()));
		curso.setQtdAlunos(Integer.parseInt(cursoPost.getQtdAlunos()));
		curso.setCategoria(categoria);
		
		if (curso.getDataInicio().isBefore(LocalDate.now())) {
			throw new RuntimeException("Data Invalida");
		}

		repository.save(curso);
	}

	public Curso atualizar(Curso curso) {
		return repository.save(curso);
	}

	public List<Curso> periodo(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicio,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataFim) {
		LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		return repository.getByDataInicio(dataInicio, dataFim);
	}

	public ResponseEntity<String> deletar(long idCurso) {
		Optional<Curso> item = repository.findById(idCurso);
		Curso curso = item.get();
		if (LocalDate.now().isBefore(curso.getDataFim())) {
			repository.deleteById(idCurso);
			return ResponseEntity.status(HttpStatus.OK).body("Curso excluido");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
					.body("Voce não pode excluir este curso pois ele já foi finalizado");
		}

	}
}