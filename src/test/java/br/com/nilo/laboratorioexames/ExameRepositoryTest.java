package br.com.nilo.laboratorioexames;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import br.com.nilo.laboratorioexames.domain.Exame;
import br.com.nilo.laboratorioexames.repository.ExameRepository;

@DataJpaTest
public class ExameRepositoryTest {
	@Autowired
	private ExameRepository exameRepository;
	
	private Exame persistirNovoExameDeTeste() {
		return exameRepository.save(new Exame(null, "Exame Exemplar", "analise clinica", "ativo"));
	}
	
	@Test
	public void criarDevePersistirExame() {
		Exame exame = persistirNovoExameDeTeste();
		
		assertThat(exame.getId()).isNotNull();
		assertThat(exame.getNome()).isEqualTo("Exame Exemplar");
		assertThat(exame.getTipo()).isEqualTo("analise clinica");
		assertThat(exame.getStatus()).isEqualTo("ativo");
	}
	
	@Test
	public void alterarDeveMudarEPersistirExame() {
		Exame insert = persistirNovoExameDeTeste();
		
		Exame exame = exameRepository.getOne(insert.getId());
		
		exame.setNome("Exame Rotina");
		exame.setTipo("imagem");
		exame.setStatus("inativo");
		
		assertThat(exame.getId()).isEqualTo(insert.getId());
		assertThat(exame.getNome()).isEqualTo("Exame Rotina");
		assertThat(exame.getTipo()).isEqualTo("imagem");
		assertThat(exame.getStatus()).isEqualTo("inativo");
	}
	
	@Test
	public void deletarDeveRemoverExame() {
		Exame exame = persistirNovoExameDeTeste();
		
		exameRepository.delete(exame);		
		
		assertThrows(JpaObjectRetrievalFailureException.class, () -> {
			exameRepository.getOne(exame.getId());
		});
	}
}
