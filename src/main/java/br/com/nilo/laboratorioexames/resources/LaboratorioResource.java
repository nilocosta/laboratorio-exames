package br.com.nilo.laboratorioexames.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.nilo.laboratorioexames.domain.Laboratorio;
import br.com.nilo.laboratorioexames.services.LaboratorioService;

@RestController
@RequestMapping("/laboratorios")
public class LaboratorioResource {
	@Autowired
	private LaboratorioService laboratorioService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<List<Laboratorio>> findAll() {
		return ResponseEntity.status(HttpStatus.OK).body(laboratorioService.findAll());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		Laboratorio laboratorio = laboratorioService.findById(new Laboratorio(id));
		return ResponseEntity.status(HttpStatus.OK).body(laboratorio);
	}

	@RequestMapping(value = "/status/{status}", method = RequestMethod.GET)
	public ResponseEntity<?> findById(@PathVariable("status") String status) {
	List<Laboratorio> laboratorios = laboratorioService.findByStatus(status);
		return ResponseEntity.status(HttpStatus.OK).body(laboratorios);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> create(@Valid @RequestBody Laboratorio laboratorio) {
		laboratorio.setId(null);
		laboratorioService.save(laboratorio);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(laboratorio.getId())
				.toUri();

		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/lote/",method = RequestMethod.POST)
	public ResponseEntity<Void> createAll(@Valid @RequestBody List<Laboratorio> laboratorios) {
		laboratorioService.createAll(laboratorios);

		return ResponseEntity.created(null).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Laboratorio laboratorio, @PathVariable("id") Long id) {
		laboratorioService.save(laboratorio);

		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/lote/", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateAll(@Valid @RequestBody List<Laboratorio> laboratorios) {
		laboratorioService.updateAll(laboratorios);

		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH)
	public ResponseEntity<Void> partialUpdate(@RequestBody Laboratorio laboratorio, @PathVariable("id") Long id) {
		laboratorio.setId(id);
		laboratorioService.save(laboratorio);

		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		laboratorioService.delete(new Laboratorio(id));
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/lote/", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteAll(@RequestBody List<Laboratorio> laboratorios) {
		laboratorioService.delete(laboratorios);
		return ResponseEntity.noContent().build();
	}
}
