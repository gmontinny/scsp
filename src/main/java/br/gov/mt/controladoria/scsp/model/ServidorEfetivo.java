package br.gov.mt.controladoria.scsp.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "servidor_efetivo")
public class ServidorEfetivo {

	@EmbeddedId
	private ServidorEfetivoId id;
	
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "pes_id")
	private Pessoa pessoa;


	public ServidorEfetivoId getId() {
		return id;
	}


	public void setId(ServidorEfetivoId id) {
		this.id = id;
	}


	public Pessoa getPessoa() {
		return pessoa;
	}


	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}


	
	
	
	
}
