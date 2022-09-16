package br.gov.mt.controladoria.scsp.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.gov.mt.controladoria.scsp.model.Pessoa;
import br.gov.mt.controladoria.scsp.repository.PessoaRepository;

@Service
public class PessoaService {
	@Autowired
	PessoaRepository pessoaRepository;
	
	public Pessoa atualizar(Integer codigo, Pessoa pessoa) {
		Pessoa pessoaSalvar = buscaPessoaPeloCodigo(codigo);
		BeanUtils.copyProperties(pessoa, pessoaSalvar, "idPessoa");
		return pessoaRepository.save(pessoaSalvar);
	}

	private Pessoa buscaPessoaPeloCodigo(Integer codigo) {
		Pessoa pessoaSalvar = pessoaRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return pessoaSalvar;
	}
}
