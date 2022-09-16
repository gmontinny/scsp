package br.gov.mt.controladoria.scsp.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.gov.mt.controladoria.scsp.model.ServidorEfetivo;
import br.gov.mt.controladoria.scsp.model.ServidorEfetivoId;

@Repository
public interface ServidorEfetivoRepository extends JpaRepository<ServidorEfetivo, ServidorEfetivoId> {
	public Page<ServidorEfetivo> findAll(Pageable pageable);
	public Optional<ServidorEfetivo> findByIdSeMatriculaServidorEfetivo(String seMatriculaServidorEfetivo);
	
	@Query(" SELECT p.nomePessoa, p.dataNascimentoPessoa, u.nomeUnidade, f.hashFotoPessoa "
			+ " FROM ServidorEfetivo se, Pessoa p, Unidade u, Lotacao l, FotoPessoa f "
			+ " WHERE p.idPessoa = se.pessoa.idPessoa "
			+ " AND l.pessoa.idPessoa = p.idPessoa "
			+ " AND u.idUnidade = l.unidade.idUnidade "
			+ " AND f.pessoa.idPessoa = p.idPessoa "
			+ " AND u.idUnidade = :codigo ")
	public Page<Object[]> findByServidorEfetivoAndLotacao( @Param("codigo") Integer codigo, Pageable pageable);
	
	@Query(" SELECT u.nomeUnidade, e.bairroEndereco, e.tipoLogradouroEndereco, e.logradouroEndereco  "
			+ " FROM ServidorEfetivo se, Pessoa p, Unidade u, Lotacao l, UnidadeEndereco ue, Endereco e "
			+ " WHERE p.idPessoa = se.pessoa.idPessoa "
			+ " AND l.pessoa.idPessoa = p.idPessoa "
			+ " AND u.idUnidade = l.unidade.idUnidade "
			+ " AND u.idUnidade = ue.unidade.idUnidade "
			+ " AND ue.endereco.idEndereco = e.idEndereco "
			+ " AND lower(p.nomePessoa) LIKE lower(concat('%',:nome,'%'))")
	public Page<Object[]> findByServidorEfetivoAndEnderecoEndUnidadeIgnoreCase( @Param("nome") String nome, Pageable pageable);
}
