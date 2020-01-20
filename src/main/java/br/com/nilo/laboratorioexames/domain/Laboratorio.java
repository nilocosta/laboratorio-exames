package br.com.nilo.laboratorioexames.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Laboratorio extends AbstractEntity {
	@NotEmpty(message = "Nome não pode estar em branco")
	private String nome;

	@NotEmpty(message = "Endereço não pode estar em branco")
	private String endereco;

	@ManyToMany(mappedBy = "laboratorios")
	@JsonInclude(Include.NON_EMPTY)
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

	@JsonIgnore
	public List<Exame> getExames() {
		return exames;
	}

	public void setExames(List<Exame> exames) {
		this.exames = exames;
	}

	public void addExame(Exame exame) {
		this.exames.add(exame);
		exame.getLaboratorios().add(this);
	}

	public void removeExame(Exame exame) {
		this.exames.remove(exame);
		exame.getLaboratorios().remove(this);
	}
}
