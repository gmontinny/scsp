package br.gov.mt.controladoria.scsp.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.gov.mt.controladoria.scsp.model.Cidade;
import br.gov.mt.controladoria.scsp.repository.CidadeRepository;

@Service
public class CidadeService {
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public Cidade atualizar(Integer codigo, Cidade cidade) {
		Cidade cidadeSalvar = bucarCidadePeloCodigo(codigo);
		BeanUtils.copyProperties(cidade, cidadeSalvar, "idCidade");
		return cidadeRepository.save(cidadeSalvar);
	}

	private Cidade bucarCidadePeloCodigo(Integer codigo) {
		Cidade cidadeSalvar = cidadeRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return cidadeSalvar;
	}
}
