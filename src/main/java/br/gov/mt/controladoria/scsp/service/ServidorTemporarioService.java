package br.gov.mt.controladoria.scsp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.mt.controladoria.scsp.model.ServidorTemporario;
import br.gov.mt.controladoria.scsp.repository.ServidorTemporarioRepository;

@Service
public class ServidorTemporarioService {
	@Autowired
	ServidorTemporarioRepository servidorTemporarioRepository;
	
	public ServidorTemporario atualizar(Integer codigo, ServidorTemporario servidorTemporario) {		
		return servidorTemporarioRepository.save(servidorTemporario);
	}
	
	public void deletar(Integer codigo) {
		ServidorTemporario servidorTemporario = buscaServidorTemporario(codigo);
		servidorTemporarioRepository.delete(servidorTemporario);
	}

	private ServidorTemporario buscaServidorTemporario(Integer codigo) {
		
		ServidorTemporario servidorTemporarioSalvar = servidorTemporarioRepository.findTop1ByPessoaIdPessoaOrderByPessoaIdPessoaAsc(codigo).get(0);
				
		return servidorTemporarioSalvar;
	}
}
