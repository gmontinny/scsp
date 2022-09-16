package br.gov.mt.controladoria.scsp.model;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pessoa_endereco")
public class PessoaEndereco {
	
	@EmbeddedId
	PessoaEnderecoId id;
		
	@NotNull
	@ManyToOne
	@MapsId("pessoaId")
	@JoinColumn(name = "pes_id")
	private Pessoa pessoa;
	
	@NotNull
	@ManyToOne
	@MapsId("enderecoId")
	@JoinColumn(name = "end_id")
	private Endereco endereco;

	public PessoaEnderecoId getId() {
		return id;
	}

	public void setId(PessoaEnderecoId id) {
		this.id = id;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		return Objects.hash(endereco, pessoa);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PessoaEndereco other = (PessoaEndereco) obj;
		return Objects.equals(endereco, other.endereco) && Objects.equals(pessoa, other.pessoa);
	}
	
	
	
}
