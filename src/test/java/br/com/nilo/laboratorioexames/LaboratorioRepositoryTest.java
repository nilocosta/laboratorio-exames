package br.com.nilo.laboratorioexames;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import javax.validation.Valid;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import br.com.nilo.laboratorioexames.domain.Laboratorio;
import br.com.nilo.laboratorioexames.repository.LaboratorioRepository;

@DataJpaTest
public class LaboratorioRepositoryTest {
	@Autowired
	private LaboratorioRepository laboratorioRepository;
	
	private Laboratorio persistirNovoLaboratorioDeTeste() {
		return laboratorioRepository.save(new Laboratorio("LabTeste", "Rua dos Testes", 1));
	}
	
	@Test
	public void criarDevePersistirLaboratorio() {
		Laboratorio laboratorio = persistirNovoLaboratorioDeTeste();
		
		assertThat(laboratorio.getId()).isNotNull();
		assertThat(laboratorio.getNome()).isEqualTo("LabTeste");
		assertThat(laboratorio.getEndereco()).isEqualTo("Rua dos Testes");
		assertThat(laboratorio.getStatus()).isEqualTo("ativo");
	}
	
	@Test
	public void alterarDeveMudarEPersistirLaboratorio() {
		Laboratorio insert = persistirNovoLaboratorioDeTeste();
		
		Laboratorio laboratorio = laboratorioRepository.getOne(insert.getId());
		
		laboratorio.setEndereco("Rua dos Alterados");
		laboratorio.setNome("LabAlterado");
		laboratorio.setStatus(0);
		
		assertThat(laboratorio.getId()).isEqualTo(insert.getId());
		assertThat(laboratorio.getNome()).isEqualTo("LabAlterado");
		assertThat(laboratorio.getEndereco()).isEqualTo("Rua dos Alterados");
		assertThat(laboratorio.getStatus()).isEqualTo("inativo");
	}
	
	@Test
	public void deletarDeveRemoverLaboratorio() {
		Laboratorio laboratorio = persistirNovoLaboratorioDeTeste();
		
		laboratorioRepository.delete(laboratorio);		
		
		assertThrows(JpaObjectRetrievalFailureException.class, () -> {
			laboratorioRepository.getOne(laboratorio.getId());
		});
	}	
}
