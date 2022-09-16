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
import br.gov.mt.controladoria.scsp.model.Unidade;
import br.gov.mt.controladoria.scsp.repository.UnidadeRepository;
import br.gov.mt.controladoria.scsp.service.UnidadeService;

@RestController
@RequestMapping("/unidades")
public class UnidadeResource {
	@Autowired
	UnidadeRepository unidadeRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private UnidadeService unidadeService;
	
	@GetMapping
	public Page<Unidade>  listar(Pageable pageable){
		return unidadeRepository.findAll(pageable);
	}
	
	@PostMapping
	public ResponseEntity<Unidade> criar(@RequestBody Unidade unidade,  HttpServletResponse response){
		Unidade unidadeSalvar = unidadeRepository.save(unidade);
		publisher.publishEvent(new RecursoCriadoEvent(this, response,unidadeSalvar.getIdUnidade()));
		return ResponseEntity.status(HttpStatus.CREATED).body(unidadeSalvar);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Unidade> atualizar(@PathVariable Integer codigo, @RequestBody Unidade unidade){
		Unidade unidadeSalvar = unidadeService.atualizar(codigo, unidade);
		return ResponseEntity.ok(unidadeSalvar);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer codigo) {
		this.unidadeRepository.deleteById(codigo);
	}
	
}
