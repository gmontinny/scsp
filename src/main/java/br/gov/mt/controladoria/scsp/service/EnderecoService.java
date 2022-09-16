package br.gov.mt.controladoria.scsp.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.gov.mt.controladoria.scsp.model.Endereco;
import br.gov.mt.controladoria.scsp.repository.EnderecoRepository;

@Service
public class EnderecoService {
	@Autowired
	EnderecoRepository enderecoRepository;
	
	public Endereco atualizar(Integer codigo, Endereco endereco) {
		Endereco enderecoSalvar = buscarEnderecoPeloCodigo(codigo);
		BeanUtils.copyProperties(endereco,enderecoSalvar,"idEndereco");
		return enderecoRepository.save(enderecoSalvar);
	}

	private Endereco buscarEnderecoPeloCodigo(Integer codigo) {
		Endereco enderecoSalvar = enderecoRepository.findById(codigo)
				.orElseThrow(() -> new EmptyResultDataAccessException(1));
		return enderecoSalvar;
	}
}
