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
@Table(name = "lotacao")
public class Lotacao {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lotacao_generator")
	@SequenceGenerator(name = "lotacao_generator", sequenceName = "lotacao_sequence", allocationSize = 1)
	@Column(name = "lot_id")
	private Integer idLotacao;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "pes_id")
	private Pessoa pessoa;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "unid_id")
	private Unidade unidade;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "lot_data_lotacao")
	private Date dataLotacao;
	
	@NotNull	
	@Temporal(TemporalType.DATE)
	@Column(name = "lot_data_remocao")
	private Date dataRemocao;
	
	@NotNull
	@Column(name = "lot_portaria")
	private String portariaLotacao;

	public Integer getIdLotacao() {
		return idLotacao;
	}

	public void setIdLotacao(Integer idLotacao) {
		this.idLotacao = idLotacao;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Date getDataLotacao() {
		return dataLotacao;
	}

	public void setDataLotacao(Date dataLotacao) {
		this.dataLotacao = dataLotacao;
	}

	public Date getDataRemocao() {
		return dataRemocao;
	}

	public void setDataRemocao(Date dataRemocao) {
		this.dataRemocao = dataRemocao;
	}

	public String getPortariaLotacao() {
		return portariaLotacao;
	}

	public void setPortariaLotacao(String portariaLotacao) {
		this.portariaLotacao = portariaLotacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idLotacao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lotacao other = (Lotacao) obj;
		return Objects.equals(idLotacao, other.idLotacao);
	}
	
	
	
	
}
