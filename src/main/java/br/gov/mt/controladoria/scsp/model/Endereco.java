package br.gov.mt.controladoria.scsp.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "endereco")
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco_generator")
	@SequenceGenerator(name = "endereco_generator", sequenceName = "endereco_sequence", allocationSize = 1)
	@Column(name = "end_id")
	private Integer idEndereco;
	
	@NotNull
	@Column(name = "end_tipo_logradouro")
	private String tipoLogradouroEndereco;
	
	@NotNull
	@Column(name = "end_logradouro")
	private String logradouroEndereco;
	
	@Column(name = "end_numero")
	private Integer numeroEndereco;
	
	@NotNull
	@Column(name = "end_bairro")
	private String bairroEndereco;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "cid_id")
	private Cidade cidade;

	public Integer getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Integer idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getTipoLogradouroEndereco() {
		return tipoLogradouroEndereco;
	}

	public void setTipoLogradouroEndereco(String tipoLogradouroEndereco) {
		this.tipoLogradouroEndereco = tipoLogradouroEndereco;
	}

	public String getLogradouroEndereco() {
		return logradouroEndereco;
	}

	public void setLogradouroEndereco(String logradouroEndereco) {
		this.logradouroEndereco = logradouroEndereco;
	}

	public Integer getNumeroEndereco() {
		return numeroEndereco;
	}

	public void setNumeroEndereco(Integer numeroEndereco) {
		this.numeroEndereco = numeroEndereco;
	}

	public String getBairroEndereco() {
		return bairroEndereco;
	}

	public void setBairroEndereco(String bairroEndereco) {
		this.bairroEndereco = bairroEndereco;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idEndereco);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		return Objects.equals(idEndereco, other.idEndereco);
	}
	
	
	
}
