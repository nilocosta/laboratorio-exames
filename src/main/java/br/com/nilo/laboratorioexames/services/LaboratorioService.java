package br.com.nilo.laboratorioexames.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import br.com.nilo.laboratorioexames.domain.Laboratorio;
import br.com.nilo.laboratorioexames.repository.LaboratorioRepository;
import br.com.nilo.laboratorioexames.services.exceptions.IdCanNotBeNullException;
import br.com.nilo.laboratorioexames.services.exceptions.LaboratorioNotFoundException;

@Service
public class LaboratorioService {
	@Autowired
	LaboratorioRepository laboratorioRepository;

	public List<Laboratorio> findAll() {
		return laboratorioRepository.findAll();
	}

	public Laboratorio findById(Laboratorio laboratorio) {
		if (laboratorio.getId() == null) {
			throw new IdCanNotBeNullException("O campo id não pode estar vazio.");
		}

		return laboratorioRepository.findOne(Example.of(new Laboratorio(laboratorio.getId())))
				.orElseThrow(() -> new LaboratorioNotFoundException(
						String.format("O laboratório com a id %s não existe", laboratorio.getId())));
	}

	public List<Laboratorio> findByStatus(String status) {
		Laboratorio laboratorio = new Laboratorio();
		laboratorio.setStatus(status);

		ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAny().withMatcher("status",
				ExampleMatcher.GenericPropertyMatchers.exact().ignoreCase());

		Example<Laboratorio> example = Example.of(laboratorio, customExampleMatcher);

		return laboratorioRepository.findAll(example);
	}

	public Laboratorio save(Laboratorio laboratorio) {
		if (laboratorio.getId() == null) {
			laboratorio.setStatus("ativo");
		} else {
			findById(laboratorio).mergeLaboratorio(laboratorio);
		}

		if (laboratorio.getStatus() == null || !laboratorio.getStatus().equalsIgnoreCase("ativo")) {
			laboratorio.setStatus("inativo");
		}

		return laboratorioRepository.save(laboratorio);
	}

	public void createAll(List<Laboratorio> laboratorios) {
		laboratorios.forEach(laboratorio -> {
			if (laboratorio.getId() != null) {
				throw new DataIntegrityViolationException("Para a inserção em lote, IDs não devem ser declarados no corpo da requisição");
			}
		});

		save(laboratorios);
	}

	public void updateAll(List<Laboratorio> laboratorios) {
		laboratorios.forEach(laboratorio -> {
			findById(laboratorio);
		});

		save(laboratorios);
	}

	public List<Laboratorio> save(List<Laboratorio> laboratorios) {
		laboratorios.forEach(laboratorio -> {
			save(laboratorio);
		});

		return laboratorios;
	}

	public void delete(Laboratorio laboratorio) {
		findById(laboratorio);
		laboratorioRepository.delete(findById(laboratorio));
	}

	public void delete(List<Laboratorio> laboratorios) {
		laboratorios.forEach(laboratorio -> {
			findById(laboratorio);
		});

		laboratorios.forEach(laboratorio -> {
			delete(laboratorio);
		});
	}
}
