package br.com.nilo.laboratorioexames.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Exame extends AbstractEntity {
	@NotEmpty(message = "Nome não pode estar em branco")
	private String nome;

	@NotEmpty(message = "Nome não pode estar em branco")
	private String tipo;

	@ManyToMany
	@JoinTable(name = "laboratorio_exame", joinColumns = @JoinColumn(name = "exame_id"), inverseJoinColumns = @JoinColumn(name = "laboratorio_id"))
	private List<Laboratorio> laboratorios;

	public Exame() {
	}

	public Exame(Long id, String nome, String tipo, String status) {
		super.id = id;
		this.nome = nome;
		this.tipo = tipo;
		super.status = status;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
