package br.gov.mt.controladoria.scsp.model;

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "foto_pessoa")
public class FotoPessoa {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "foto_pessoa_generator")
	@SequenceGenerator(name = "foto_pessoa_generator", sequenceName = "foto_pessoa_sequence", allocationSize = 1)
	@Column(name = "fp_id")
	private Integer idFotoPessoa;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "pes_id")
	private Pessoa pessoa;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "fp_data")
	private Date dataFotoPessoa;
	
	@NotNull
	@Column(name = "fp_bucket")
	private String bucketFotoPessoa;
	
	@NotNull
	@Column(name = "fp_hash")
	private String hashFotoPessoa;

	public Integer getIdFotoPessoa() {
		return idFotoPessoa;
	}

	public void setIdFotoPessoa(Integer idFotoPessoa) {
		this.idFotoPessoa = idFotoPessoa;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Date getDataFotoPessoa() {
		return dataFotoPessoa;
	}

	public void setDataFotoPessoa(Date dataFotoPessoa) {
		this.dataFotoPessoa = dataFotoPessoa;
	}

	public String getBucketFotoPessoa() {
		return bucketFotoPessoa;
	}

	public void setBucketFotoPessoa(String bucketFotoPessoa) {
		this.bucketFotoPessoa = bucketFotoPessoa;
	}

	public String getHashFotoPessoa() {
		return hashFotoPessoa;
	}

	public void setHashFotoPessoa(String hashFotoPessoa) {
		this.hashFotoPessoa = hashFotoPessoa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idFotoPessoa);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FotoPessoa other = (FotoPessoa) obj;
		return Objects.equals(idFotoPessoa, other.idFotoPessoa);
	}
	
	
}
