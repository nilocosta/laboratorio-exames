package br.com.nilo.laboratorioexames.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import br.com.nilo.laboratorioexames.domain.Exame;
import br.com.nilo.laboratorioexames.repository.ExameRepository;
import br.com.nilo.laboratorioexames.services.exceptions.ExameNotFoundException;
import br.com.nilo.laboratorioexames.services.exceptions.IdCanNotBeNullException;

@Service
public class ExameService {
	@Autowired
	ExameRepository exameRepository;

	public List<Exame> findAll() {
		return exameRepository.findAll();
	}

	public List<Exame> findByStatus(String status) {
		Exame exame = new Exame();
		exame.setStatus(status);

		ExampleMatcher customExampleMatcher = ExampleMatcher.matchingAny().withMatcher("status",
				ExampleMatcher.GenericPropertyMatchers.exact().ignoreCase());

		Example<Exame> example = Example.of(exame, customExampleMatcher);

		return exameRepository.findAll(example);
	}

	public Exame findById(Exame exame) {
		if (exame.getId() == null) {
			throw new IdCanNotBeNullException("O campo id não pode estar vazio.");
		}

		return exameRepository.findOne(Example.of(new Exame(exame.getId())))
				.orElseThrow(() -> new ExameNotFoundException(
						String.format("O exame com a id %s não existe", exame.getId())));
	}

	public Exame save(Exame exame) {
		if (exame.getId() == null) {
			exame.setStatus("ativo");
		} else {
			findById(exame).mergeExame(exame);
		}

		if (exame.getStatus() == null || !exame.getStatus().equalsIgnoreCase("ativo")) {
			exame.setStatus("inativo");
		}

		return exameRepository.save(exame);
	}

	public void createAll(List<Exame> exames) {
		exames.forEach(exame -> {
			if (exame.getId() != null) {
				throw new DataIntegrityViolationException(
						"Para a inserção em lote, IDs não devem ser declarados no corpo da requisição");
			}
		});

		save(exames);
	}

	public void updateAll(List<Exame> exames) {
		exames.forEach(exame -> {
			findById(exame);
		});

		save(exames);
	}

	public List<Exame> save(List<Exame> exames) {
		exames.forEach(exame -> {
			save(exame);
		});

		return exames;
	}

	public void delete(Exame exame) {
		findById(exame);
		exameRepository.delete(findById(exame));
	}

	public void delete(List<Exame> exames) {
		exames.forEach(exame -> {
			findById(exame);
		});

		exames.forEach(exame -> {
			delete(exame);
		});
	}
}
