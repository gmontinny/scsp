package br.gov.mt.controladoria.scsp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.mt.controladoria.scsp.model.ServidorTemporario;
import br.gov.mt.controladoria.scsp.model.ServidorTemporarioId;

@Repository
public interface ServidorTemporarioRepository extends JpaRepository<ServidorTemporario, ServidorTemporarioId>{
	public Page<ServidorTemporario> findAll(Pageable pageable);
	public List<ServidorTemporario> findTop1ByPessoaIdPessoaOrderByPessoaIdPessoaAsc(Integer codigo);
}
