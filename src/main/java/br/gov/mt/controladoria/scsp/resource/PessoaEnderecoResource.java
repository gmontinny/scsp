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
import br.gov.mt.controladoria.scsp.model.PessoaEndereco;
import br.gov.mt.controladoria.scsp.repository.PessoaEnderecoRepository;

@RestController
@RequestMapping("/pessoaEnderecos")
public class PessoaEnderecoResource {
	@Autowired
	private PessoaEnderecoRepository pessoaEnderecoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public Page<PessoaEndereco> listar(Pageable pageable){
		return pessoaEnderecoRepository.findAll(pageable);
	}
	
	@PostMapping
	public ResponseEntity<PessoaEndereco> criar(@RequestBody PessoaEndereco pessoaEndereco, HttpServletResponse response){
		PessoaEndereco pessoaEnderecoSalvar = pessoaEnderecoRepository.save(pessoaEndereco);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaEnderecoSalvar.getPessoa().getIdPessoa()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaEnderecoSalvar);
	}
	
	@DeleteMapping("/pessoa/{pessoaId}/endereco/{enderecoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer pessoaId, @PathVariable Integer enderecoId) {		
		this.pessoaEnderecoRepository.deleteByPessoaAndEndereco(pessoaId, enderecoId);
	}
	
	@PutMapping("/pessoa/{pessoaId}/endereco/{enderecoId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void atualizar(@PathVariable Integer pessoaId, @PathVariable Integer enderecoId, @RequestBody PessoaEndereco pessoaEndereco) {
		this.pessoaEnderecoRepository.updateByPessoaAndEndereco(pessoaEndereco.getPessoa().getIdPessoa(), pessoaEndereco.getEndereco().getIdEndereco(), 
				pessoaId, enderecoId);
	}
	
	
}
