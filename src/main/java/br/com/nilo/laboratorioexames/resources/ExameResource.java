package br.com.nilo.laboratorioexames.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.nilo.laboratorioexames.domain.Exame;
import br.com.nilo.laboratorioexames.services.ExameService;

@RestController
@RequestMapping("/exames")
public class ExameResource {
	@Autowired
	private ExameService exameService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Exame>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(exameService.findAll());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		Exame exame = exameService.findById(new Exame(id));
		return ResponseEntity.status(HttpStatus.OK).body(exame);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<?> findByFilter(@RequestParam(name = "nome", required = false) String nome,
			@RequestParam(name = "status", required = false) String status) {
		List<Exame> exames = new ArrayList<>();
		if (nome != null) {
			exames = exameService.findByNome(nome);
		}

		if (status != null) {
			exames = exameService.findByStatus(status);
		}

		return ResponseEntity.status(HttpStatus.OK).body(exames);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> create(@Valid @RequestBody Exame exame) {
		exame.setId(null);
		exameService.save(exame);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(exame.getId()).toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/lote/", method = RequestMethod.POST)
	public ResponseEntity<Void> createAll(@Valid @RequestBody List<Exame> exames) {
		exameService.createAll(exames);

		return ResponseEntity.created(null).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Exame exame, @PathVariable("id") Long id) {
		exame.setId(id);
		exameService.save(exame);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/lote/", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateAll(@Valid @RequestBody List<Exame> exames) {
		exameService.updateAll(exames);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		exameService.delete(new Exame(id));
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/lote/", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteAll(@RequestBody List<Exame> exames) {
		exameService.delete(exames);
		return ResponseEntity.noContent().build();
	}
}
