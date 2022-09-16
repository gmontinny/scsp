package br.gov.mt.controladoria.scsp.resource;

import java.util.Optional;

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
import br.gov.mt.controladoria.scsp.model.Cidade;
import br.gov.mt.controladoria.scsp.repository.CidadeRepository;
import br.gov.mt.controladoria.scsp.service.CidadeService;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping
	public ResponseEntity<Cidade> criar(@RequestBody Cidade cidade, HttpServletResponse response){
		Cidade cidadeSalvar = cidadeRepository.save(cidade);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, cidadeSalvar.getIdCidade()));
		return ResponseEntity.status(HttpStatus.CREATED).body(cidadeSalvar);
	}
	
	@GetMapping
	public Page<Cidade> listar(Pageable pageable){
		return cidadeRepository.findAll(pageable);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Cidade> buscarPeloCodigo(@PathVariable Integer codigo){
		Optional<Cidade> cidade = cidadeRepository.findById(codigo);
		return cidade.isPresent() ? ResponseEntity.ok(cidade.get()) : ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Cidade> atualizar(@PathVariable Integer codigo, @RequestBody Cidade cidade){
		Cidade cidadeSalvar = cidadeService.atualizar(codigo, cidade);
		return ResponseEntity.ok(cidadeSalvar);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer codigo) {
		this.cidadeRepository.deleteById(codigo);
	}
	

	
}
