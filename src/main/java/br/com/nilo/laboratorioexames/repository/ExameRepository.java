package br.com.nilo.laboratorioexames.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nilo.laboratorioexames.domain.Exame;

public interface ExameRepository extends JpaRepository<Exame, Long> {

}
