package br.gov.mt.controladoria.scsp.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "servidor_temporario")
public class ServidorTemporario {
	
	@EmbeddedId
	private ServidorTemporarioId Id;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "pes_id")
	private Pessoa pessoa;

	public ServidorTemporarioId getId() {
		return Id;
	}

	public void setId(ServidorTemporarioId id) {
		Id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	
	

}
