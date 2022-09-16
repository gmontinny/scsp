package br.gov.mt.controladoria.scsp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PessoaEnderecoId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @Column(name = "pes_id")
    private Integer pessoaId;

    @Column(name = "end_id")
    private Integer enderecoId;

	public Integer getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(Integer pessoaId) {
		this.pessoaId = pessoaId;
	}

	public Integer getEnderecoId() {
		return enderecoId;
	}

	public void setEnderecoId(Integer enderecoId) {
		this.enderecoId = enderecoId;
	}

    
    

}
