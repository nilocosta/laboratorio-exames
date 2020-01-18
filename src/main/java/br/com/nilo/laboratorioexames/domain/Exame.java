package br.com.nilo.laboratorioexames.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
public class Exame {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@JsonInclude(Include.NON_NULL)
	private String nome;

	@JsonInclude(Include.NON_NULL)
	private String tipo;

	@Column(columnDefinition = "integer default 1")
	private Integer status;

	@ManyToMany
	@JoinTable(name = "laboratorio_exame", joinColumns = @JoinColumn(name = "exame_id"), inverseJoinColumns = @JoinColumn(name = "laboratorio_id"))
	private List<Laboratorio> laboratorios;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Laboratorio> getLaboratorios() {
		return laboratorios;
	}

	public void setLaboratorios(List<Laboratorio> laboratorios) {
		this.laboratorios = laboratorios;
	}
}
