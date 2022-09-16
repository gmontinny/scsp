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
import br.gov.mt.controladoria.scsp.model.Endereco;
import br.gov.mt.controladoria.scsp.repository.EnderecoRepository;
import br.gov.mt.controladoria.scsp.service.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoResource {
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private EnderecoService enderecoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	
	@PostMapping
	public ResponseEntity<Endereco> criar(@RequestBody Endereco endereco, HttpServletResponse response){
		Endereco enderecoSalvar = enderecoRepository.save(endereco);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, enderecoSalvar.getIdEndereco()));
		return ResponseEntity.status(HttpStatus.CREATED).body(enderecoSalvar);
	}
	
	@GetMapping
	public Page<Endereco> listar(Pageable pageable){
		return enderecoRepository.findAll(pageable);
	}
	
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Endereco> atualizar(@PathVariable Integer codigo, @RequestBody Endereco endereco){
		Endereco enderecoSalvar = enderecoService.atualizar(codigo, endereco);
		return ResponseEntity.ok(enderecoSalvar);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer codigo) {
		this.enderecoRepository.deleteById(codigo);
	}
}
