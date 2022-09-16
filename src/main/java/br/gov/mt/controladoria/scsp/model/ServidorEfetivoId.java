package br.gov.mt.controladoria.scsp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class ServidorEfetivoId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@NotNull
	@Column(name = "se_matricula")
	private String seMatriculaServidorEfetivo;


	public String getSeMatriculaServidorEfetivo() {
		return seMatriculaServidorEfetivo;
	}


	public void setSeMatriculaServidorEfetivo(String seMatriculaServidorEfetivo) {
		this.seMatriculaServidorEfetivo = seMatriculaServidorEfetivo;
	}
	
	@Override
	public boolean equals(Object obj) {
	    return super.equals(obj);
	}
	
}
