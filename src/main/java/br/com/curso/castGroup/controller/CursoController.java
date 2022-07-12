package br.com.curso.castGroup.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Request.CursoPost;
import br.com.curso.castGroup.entities.Curso;
import br.com.curso.castGroup.repository.CursoRepository;
import br.com.curso.castGroup.service.CursoService;

@RestController
@RequestMapping("/curso")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CursoController {

	@Autowired
	private CursoRepository repository;

	@Autowired
	private CursoService service;

	@GetMapping
	public ResponseEntity<?> GetAll() {
		service.GetAll();
		return ResponseEntity.ok(repository.findAll());
	}

	@GetMapping("/{idCurso}")
	public ResponseEntity<Curso> GetById(@PathVariable long idCurso) {
		return repository.findById(idCurso).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<?> getByDescricao(@PathVariable String descricao) {
		service.GetByDescricao(descricao);
		return ResponseEntity.status(HttpStatus.OK).body(service.GetByDescricao(descricao));
	}

	@GetMapping("/dataInicio/{dataInicio}/{dataFim}")
	public ResponseEntity<?> periodo(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicio,
			@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataFim) {

		return ResponseEntity.ok(service.periodo(dataInicio, dataFim));
	}

	@PostMapping
	public ResponseEntity<?> post(@RequestBody CursoPost curso) {

		try {
			service.cadastro(curso);
			return ResponseEntity.status(HttpStatus.OK).body("Curso cadastrado");
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Error: "+ e.getMessage());
		}

	}

	@PutMapping
	public ResponseEntity<?> put(@RequestBody Curso curso) {
		try {
			service.atualizar(curso);
			return ResponseEntity.status(HttpStatus.OK).body("Curso atualizado");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Curso não atualizado");
		}

	}

	@DeleteMapping("/{idCurso}")
	public ResponseEntity<String> delete(@PathVariable long idCurso) {
		return service.deletar(idCurso);

	}

}
