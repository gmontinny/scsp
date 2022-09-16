package br.gov.mt.controladoria.scsp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.gov.mt.controladoria.scsp.model.PessoaEndereco;
import br.gov.mt.controladoria.scsp.model.PessoaEnderecoId;

@Repository
public interface PessoaEnderecoRepository extends JpaRepository<PessoaEndereco, PessoaEnderecoId>{
	
	public Page<PessoaEndereco> findAll(Pageable pageable);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM PessoaEndereco pe WHERE pe.pessoa.idPessoa = :idPessoa AND pe.endereco.idEndereco = :idEndereco")
	public void deleteByPessoaAndEndereco(@Param("idPessoa") Integer idPessoa, @Param("idEndereco") Integer idEndereco);	
	
	@Transactional
	@Modifying
	@Query("UPDATE PessoaEndereco pe SET pe.pessoa.idPessoa = :idNPessoa, pe.endereco.idEndereco = :idNEndereco "
			+ " WHERE pe.pessoa.idPessoa = :idPessoa AND pe.endereco.idEndereco = :idEndereco ")
	public void updateByPessoaAndEndereco(@Param("idNPessoa") Integer idNPessoa, @Param("idNEndereco") Integer idNEndereco,
			@Param("idPessoa") Integer idPessoa, @Param("idEndereco") Integer idEndereco);
}
