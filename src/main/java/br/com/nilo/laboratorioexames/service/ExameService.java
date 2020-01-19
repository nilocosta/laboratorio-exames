package br.com.nilo.laboratorioexames.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nilo.laboratorioexames.repository.ExameRepository;

@Service
public class ExameService {
	@Autowired
	ExameRepository exameRepository;
}
