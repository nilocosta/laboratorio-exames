package br.com.nilo.laboratorioexames.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.com.nilo.laboratorioexames.domain.Laboratorio;
import br.com.nilo.laboratorioexames.exception.IdCanNotBeNullException;
import br.com.nilo.laboratorioexames.exception.LaboratorioNotFoundException;
import br.com.nilo.laboratorioexames.repository.LaboratorioRepository;

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

		laboratorioRepository.findOne(Example.of(laboratorio)).orElseThrow(() -> new LaboratorioNotFoundException(
				String.format("O laboratório  com a id %s não existe", laboratorio.getId())));

		return laboratorio;
	}

	public Laboratorio save(Laboratorio laboratorio) {
		if (laboratorio.getId() == null) {
			laboratorio.setStatus("ativo");
		}
		
		if (!laboratorio.getStatus().equalsIgnoreCase("ativo")) {
			laboratorio.setStatus("inativo");
		}

		return laboratorioRepository.save(laboratorio);
	}

	public List<Laboratorio> save(List<Laboratorio> laboratorios) {
		laboratorios.forEach(laboratorio -> {			
			laboratorioRepository.save(laboratorio);
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
