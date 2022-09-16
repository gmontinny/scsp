package br.gov.mt.controladoria.scsp.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "unidade_endereco")
public class UnidadeEndereco {
	
	@EmbeddedId
	private UnidadeEnderecoId id;
	
	@NotNull
	@ManyToOne
	@MapsId("unidadeId")
	@JoinColumn(name = "unid_id")
	private Unidade unidade;
	
	@NotNull
	@ManyToOne
	@MapsId("enderecoId")
	@JoinColumn(name = "end_id")
	private Endereco endereco;

	public UnidadeEnderecoId getId() {
		return id;
	}

	public void setId(UnidadeEnderecoId id) {
		this.id = id;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	
	

}
