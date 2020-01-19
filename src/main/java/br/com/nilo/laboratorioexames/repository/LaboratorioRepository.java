package br.com.nilo.laboratorioexames.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.nilo.laboratorioexames.domain.Laboratorio;

public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long>{

}
