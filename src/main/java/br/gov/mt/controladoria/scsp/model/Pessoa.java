package br.gov.mt.controladoria.scsp.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pessoa")
public class Pessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pessoa_generator")
	@SequenceGenerator(name = "pessoa_generator", sequenceName = "pessoa_sequence", allocationSize = 1)
	@Column(name = "pes_id")
	private Integer idPessoa;
	
	@NotNull
	@Column(name = "pes_nome")
	private String nomePessoa;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "pes_data_nascimento")
	private Date dataNascimentoPessoa;
	
	@NotNull
	@Column(name = "pes_sexo")
	private String sexoPessoa;
	
	@NotNull
	@Column(name = "pes_mae")
	private String maePessoa;
	
	@NotNull
	@Column(name = "pes_pai")
	private String paiPessoa;

	public Integer getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Integer idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNomePessoa() {
		return nomePessoa;
	}

	public void setNomePessoa(String nomePessoa) {
		this.nomePessoa = nomePessoa;
	}

	public Date getDataNascimentoPessoa() {
		return dataNascimentoPessoa;
	}

	public void setDataNascimentoPessoa(Date dataNascimentoPessoa) {
		this.dataNascimentoPessoa = dataNascimentoPessoa;
	}

	public String getSexoPessoa() {
		return sexoPessoa;
	}

	public void setSexoPessoa(String sexoPessoa) {
		this.sexoPessoa = sexoPessoa;
	}

	public String getMaePessoa() {
		return maePessoa;
	}

	public void setMaePessoa(String maePessoa) {
		this.maePessoa = maePessoa;
	}

	public String getPaiPessoa() {
		return paiPessoa;
	}

	public void setPaiPessoa(String paiPessoa) {
		this.paiPessoa = paiPessoa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPessoa);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(idPessoa, other.idPessoa);
	}
	
	
}
