package br.com.nilo.laboratorioexames.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import io.swagger.annotations.ApiModelProperty;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Exame extends AbstractEntity {
	@NotEmpty(message = "Nome não pode estar em branco")
	private String nome;

	@NotEmpty(message = "Nome não pode estar em branco")
	private String tipo;

	@ManyToMany
	@JoinTable(name = "laboratorio_exame", joinColumns = @JoinColumn(name = "exame_id"), inverseJoinColumns = @JoinColumn(name = "laboratorio_id"))
	@JsonInclude(Include.NON_EMPTY)
	@ApiModelProperty(hidden = true)
	private List<Laboratorio> laboratorios;

	public Exame() {
	}

	public Exame(Long id) {
		super.id = id;
	}

	public Exame(Long id, String nome, String tipo, String status) {
		super.id = id;
		this.nome = nome;
		this.tipo = tipo;
		super.status = status;
	}

	public Exame mergeExame(Exame exame) {
		this.nome = exame.nome != null && !exame.nome.equals(this.nome) ? exame.nome : this.nome;
		this.tipo = exame.tipo != null && !exame.tipo.equals(this.tipo) ? exame.tipo : this.tipo;
		this.status = exame.status != null && !exame.status.equals(this.status) ? exame.status : this.status;

		return this;
	}

	public void addLaboratorio(Laboratorio laboratorio) {
		this.laboratorios.add(laboratorio);
		laboratorio.getExames().add(this);
	}

	public void removeLaboratorio(Laboratorio laboratorio) {
		this.laboratorios.remove(laboratorio);
		laboratorio.getExames().remove(this);
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
