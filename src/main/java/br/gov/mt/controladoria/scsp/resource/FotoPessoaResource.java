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
import br.gov.mt.controladoria.scsp.model.FotoPessoa;
import br.gov.mt.controladoria.scsp.repository.FotoPessoaRepository;
import br.gov.mt.controladoria.scsp.service.FotoPessoaService;

@RestController
@RequestMapping("/fotoPessoas")
public class FotoPessoaResource {
	@Autowired
	FotoPessoaRepository fotoPessoaRepository;
	
	@Autowired
	FotoPessoaService fotoPessoaService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping
	public ResponseEntity<FotoPessoa> criar(@RequestBody FotoPessoa fotoPessoa, HttpServletResponse response){
		FotoPessoa fotoPessoaSalvar = fotoPessoaRepository.save(fotoPessoa);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, fotoPessoaSalvar.getIdFotoPessoa()));
		return ResponseEntity.status(HttpStatus.CREATED).body(fotoPessoaSalvar);
	}
	
	@GetMapping
	public Page<FotoPessoa> listar(Pageable pageable){
		return fotoPessoaRepository.findAll(pageable);
	}
	
	
	@PutMapping("/{codigo}")
	public ResponseEntity<FotoPessoa> atualizar(@PathVariable Integer codigo, @RequestBody FotoPessoa fotoPessoa){
		FotoPessoa fotoPessoaSalvar = fotoPessoaService.atualizar(codigo, fotoPessoa);
		return ResponseEntity.ok(fotoPessoaSalvar);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer codigo) {
		this.fotoPessoaRepository.deleteById(codigo);
	}
}
