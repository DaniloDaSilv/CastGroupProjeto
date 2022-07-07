package br.com.curso.castGroup.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.curso.castGroup.entities.Curso;
import br.com.curso.castGroup.repository.CursoRepository;

@RestController
@RequestMapping("/curso")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CursoController {

	@Autowired
	private CursoRepository repository;
	
	@GetMapping
	public ResponseEntity<List<Curso>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Curso>> getByDescricao(@PathVariable String descricao) {
		return ResponseEntity.ok(repository.getByDescricao(descricao));
	}
	
	@PostMapping
	public ResponseEntity<Curso> post(@RequestBody Curso curso) {
		
		//Logica de curso no mesmo periodo
		
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(curso));
	}
	
	@PutMapping
	public ResponseEntity<Curso> put(@RequestBody Curso curso) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(curso));
	}
}
