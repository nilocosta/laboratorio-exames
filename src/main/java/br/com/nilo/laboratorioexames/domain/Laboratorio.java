package br.com.nilo.laboratorioexames.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
public class Laboratorio extends AbstractEntity {	
	@NotEmpty(message = "Nome não pode estar em branco")
	private String nome;

	@NotEmpty(message = "Endereço não pode estar em branco")
	private String endereco;

	@ManyToMany(mappedBy = "laboratorios")
	@JsonInclude(Include.NON_NULL)
	private List<Exame> exames;

	public Laboratorio() {
	}

	public Laboratorio(Long id, String nome, String endereco, String status) {
		super.id = id;
		this.nome = nome;
		this.endereco = endereco;
		super.status = status;
	}
	
	public Laboratorio(Long id) {
		super.id = id;
	}
	
	public Laboratorio(String nome, String endereco, String status) {
		this.nome = nome;
		this.endereco = endereco;
		super.status = status;
	}
	
	public Laboratorio mergeLaboratorio(Laboratorio laboratorio) {
		laboratorio.nome = laboratorio.nome == null ? this.nome : laboratorio.nome;
		laboratorio.endereco = laboratorio.endereco == null ? this.endereco : laboratorio.endereco; 
		laboratorio.status = laboratorio.status == null ? this.status : laboratorio.status;
		
		return laboratorio;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<Exame> getExames() {
		return exames;
	}

	public void setExames(List<Exame> exames) {
		this.exames = exames;
	}

}
