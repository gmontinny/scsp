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
import br.gov.mt.controladoria.scsp.model.ServidorTemporario;
import br.gov.mt.controladoria.scsp.repository.ServidorTemporarioRepository;
import br.gov.mt.controladoria.scsp.service.ServidorTemporarioService;

@RestController
@RequestMapping("/servidorTemporario")
public class ServidorTemporarioResource {
	@Autowired
	ServidorTemporarioRepository servidorTemporarioRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private ServidorTemporarioService servidorTemporarioService;
	
	@GetMapping
	public Page<ServidorTemporario>  listar(Pageable pageable){
		return servidorTemporarioRepository.findAll(pageable);
	}
	
	@PostMapping
	public ResponseEntity<ServidorTemporario> criar(@RequestBody ServidorTemporario servidorTemporario, HttpServletResponse response){
		ServidorTemporario servidorTemporarioSalvar = servidorTemporarioRepository.save(servidorTemporario);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, servidorTemporario.getPessoa().getIdPessoa()));
		return ResponseEntity.status(HttpStatus.CREATED).body(servidorTemporarioSalvar);
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<ServidorTemporario> atualizar(@PathVariable Integer codigo, @RequestBody ServidorTemporario servidorTemporario){
		ServidorTemporario servidorTemporarioSalvar = servidorTemporarioService.atualizar(codigo, servidorTemporario);
		return ResponseEntity.ok(servidorTemporarioSalvar);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer codigo) {
		this.servidorTemporarioService.deletar(codigo);
	}
}
