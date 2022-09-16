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
import br.gov.mt.controladoria.scsp.model.Lotacao;
import br.gov.mt.controladoria.scsp.repository.LotacaoRepository;
import br.gov.mt.controladoria.scsp.service.LotacaoService;

@RestController
@RequestMapping("/lotacoes")
public class LotacaoResource {
	@Autowired
	LotacaoRepository lotacaoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private LotacaoService lotacaoService;
	
	@PostMapping
	public ResponseEntity<Lotacao> criar(@RequestBody Lotacao lotacao, HttpServletResponse response){
		Lotacao lotacaoSalvar = lotacaoRepository.save(lotacao);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, lotacaoSalvar.getIdLotacao()));
		return ResponseEntity.status(HttpStatus.CREATED).body(lotacaoSalvar);
	}
	
	@GetMapping
	public Page<Lotacao> listar(Pageable pageable){
		return lotacaoRepository.findAll(pageable);
	}
	
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Lotacao> atualizar(@PathVariable Integer codigo, @RequestBody Lotacao lotacao){
		Lotacao lotacaoSalvar = lotacaoService.atualizar(codigo, lotacao);
		return ResponseEntity.ok(lotacaoSalvar);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer codigo) {
		this.lotacaoRepository.deleteById(codigo);
	}
}
