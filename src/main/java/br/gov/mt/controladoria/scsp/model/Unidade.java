package br.gov.mt.controladoria.scsp.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "unidade")
public class Unidade {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unidade_generator")
	@SequenceGenerator(name = "unidade_generator", sequenceName = "unidade_sequence", allocationSize = 1)
	@Column(name = "unid_id")
	private Integer idUnidade;
	
	@NotNull
	@Column(name = "unid_nome")
	private String nomeUnidade;
	
	@NotNull
	@Column(name = "unid_sigla")
	private String siglaUnidade;

	public Integer getIdUnidade() {
		return idUnidade;
	}

	public void setIdUnidade(Integer idUnidade) {
		this.idUnidade = idUnidade;
	}

	public String getNomeUnidade() {
		return nomeUnidade;
	}

	public void setNomeUnidade(String nomeUnidade) {
		this.nomeUnidade = nomeUnidade;
	}

	public String getSiglaUnidade() {
		return siglaUnidade;
	}

	public void setSiglaUnidade(String siglaUnidade) {
		this.siglaUnidade = siglaUnidade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idUnidade);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Unidade other = (Unidade) obj;
		return Objects.equals(idUnidade, other.idUnidade);
	}
	
	
}
