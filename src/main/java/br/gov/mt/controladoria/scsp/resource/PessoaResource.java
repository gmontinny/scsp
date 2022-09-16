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
import br.gov.mt.controladoria.scsp.model.Pessoa;
import br.gov.mt.controladoria.scsp.repository.PessoaRepository;
import br.gov.mt.controladoria.scsp.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {
	@Autowired
	PessoaRepository pessoaRepository;
	
	@Autowired
	PessoaService pessoaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping
	public ResponseEntity<Pessoa> criar(@RequestBody Pessoa pessoa, HttpServletResponse response){
		Pessoa pessoaSalvar = pessoaRepository.save(pessoa);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalvar.getIdPessoa()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalvar);
	}
	
	@GetMapping
	public Page<Pessoa> listar(Pageable pageable){
		return pessoaRepository.findAll(pageable);
	}
	
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Integer codigo, @RequestBody Pessoa pessoa){
		Pessoa pessoaSalvar = pessoaService.atualizar(codigo, pessoa);
		return ResponseEntity.ok(pessoaSalvar);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer codigo) {
		this.pessoaRepository.deleteById(codigo);
	}
	
}
