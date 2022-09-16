package br.gov.mt.controladoria.scsp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UnidadeEnderecoId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @Column(name = "unid_id")
    private Integer unidadeId;

    @Column(name = "end_id")
    private Integer enderecoId;

	public Integer getUnidadeId() {
		return unidadeId;
	}

	public void setUnidadeId(Integer unidadeId) {
		this.unidadeId = unidadeId;
	}

	public Integer getEnderecoId() {
		return enderecoId;
	}

	public void setEnderecoId(Integer enderecoId) {
		this.enderecoId = enderecoId;
	}
    
    
	
}
