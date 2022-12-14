package br.gov.mt.controladoria.scsp.resource;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.mt.controladoria.scsp.event.RecursoCriadoEvent;
import br.gov.mt.controladoria.scsp.model.UnidadeEndereco;
import br.gov.mt.controladoria.scsp.repository.UnidadeEnderecoRepository;

@RestController
@RequestMapping("/unidadeEnderecos")
public class UnidadeEnderecoResource {
	
	@Autowired
	private UnidadeEnderecoRepository unidadeEnderecoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public Page<UnidadeEndereco> listar(Pageable pageable){
		return unidadeEnderecoRepository.findAll(pageable);
	}
	
	@PostMapping
	public ResponseEntity<UnidadeEndereco> criar(@RequestBody UnidadeEndereco unidadeEndereco, HttpServletResponse response){
		UnidadeEndereco unidadeEnderecoSalvar = unidadeEnderecoRepository.save(unidadeEndereco);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, unidadeEnderecoSalvar.getUnidade().getIdUnidade()));
		return ResponseEntity.status(HttpStatus.CREATED).body(unidadeEnderecoSalvar);
	}
	
	@DeleteMapping("/unidade/{unidadeId}/endereco/{enderecoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer unidadeId, @PathVariable Integer enderecoId) {		
		this.unidadeEnderecoRepository.deleteByUnidadeAndEndereco(unidadeId, enderecoId);
	}
	
	@PutMapping("/unidade/{unidadeId}/endereco/{enderecoId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void atualizar(@PathVariable Integer unidadeId, @PathVariable Integer enderecoId, @RequestBody UnidadeEndereco unidadeEndereco) {
		this.unidadeEnderecoRepository.updateByUnidadeAndEndereco(unidadeEndereco.getUnidade().getIdUnidade(), unidadeEndereco.getEndereco().getIdEndereco(), 
				unidadeId, enderecoId);
	}
}
