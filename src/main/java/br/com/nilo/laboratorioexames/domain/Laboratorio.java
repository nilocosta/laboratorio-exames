package br.com.nilo.laboratorioexames.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
public class Laboratorio extends AbstractEntity {
	private String nome;

	private String endereco;

	private Integer status;

	@ManyToMany(mappedBy = "laboratorios")
	@JsonInclude(Include.NON_NULL)
	private List<Exame> exames;

	public Laboratorio() {
	}

	public Laboratorio(String nome, String endereco, Integer status) {
		super();
		this.nome = nome;
		this.endereco = endereco;
		this.status = status;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public List<Exame> getExames() {
		return exames;
	}

	public void setExames(List<Exame> exames) {
		this.exames = exames;
	}

}
