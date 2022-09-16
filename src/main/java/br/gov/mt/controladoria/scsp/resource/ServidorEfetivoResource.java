package br.gov.mt.controladoria.scsp.resource;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.gov.mt.controladoria.scsp.event.RecursoCriadoEvent;
import br.gov.mt.controladoria.scsp.model.ServidorEfetivo;
import br.gov.mt.controladoria.scsp.repository.ServidorEfetivoRepository;
import br.gov.mt.controladoria.scsp.service.ServidorEfetivoService;
import br.gov.mt.controladoria.scsp.util.Functions;

@RestController
@RequestMapping("/servidorEfetivo")
public class ServidorEfetivoResource {
	
	@Autowired
	private ServidorEfetivoRepository servidorEfetivoRepository;
	
	@Autowired
	private ServidorEfetivoService serviceEfetivoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
	public Page<ServidorEfetivo> listar(Pageable pageable){
		return servidorEfetivoRepository.findAll(pageable);
	}
	
	@GetMapping("/{idUnidade}/unidade")
	public List<Map<String, Object>> listaServidorEfetivo(@PathVariable Integer idUnidade, 
	        @RequestParam(defaultValue = "0") Integer page,
	        @RequestParam(defaultValue = "3") Integer size){
		List<Map<String, Object>> result = new ArrayList<>();
		
		List<Object[]> obj = serviceEfetivoService.getServidorEfetivoAndLotacao(idUnidade, page, size);
		
		
		obj
		.stream()
		.filter(o -> o != null)
		.forEach(o -> {
			Map<String, Object> dados = new HashMap<>();
			Date createdDate = (Date) o[1];
			dados.put("nome", o[0]);
			dados.put("idade",  new Functions().age(createdDate, new Date()));
			dados.put("lotacao", o[2]);
			dados.put("foto", o[3]);
				
			result.add(dados);
		});
		
		
		
		return result;
	}
	
	@GetMapping("/{nome}/endereco")
	public List<Map<String, Object>> listaServidorEfetivo(@PathVariable String nome,
	        @RequestParam(defaultValue = "0") Integer page,
	        @RequestParam(defaultValue = "3") Integer size){
		
		List<Map<String, Object>> result = new ArrayList<>();
		List<Object[]> obj = serviceEfetivoService.getServidorEfetivoAndEnderecoEndUnidade(nome, page, size);
		
		obj
		.stream()
		.filter(o -> o != null)
		.forEach(o -> {
			Map<String, Object> dados = new HashMap<>();
			dados.put("unidade", o[0]);
			dados.put("bairro",  o[1]);
			dados.put("tipo", o[2]);
			dados.put("logradouro", o[3]);
				
			result.add(dados);
		});
		
		return result;
	}
	
	@PostMapping
	public ResponseEntity<ServidorEfetivo> criar(@RequestBody ServidorEfetivo servidorEfetivo, HttpServletResponse response){
		ServidorEfetivo servidorEfetivoSalvar = servidorEfetivoRepository.save(servidorEfetivo);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, servidorEfetivoSalvar.getPessoa().getIdPessoa()));
		return ResponseEntity.status(HttpStatus.CREATED).body(servidorEfetivoSalvar);
	}
	
	@PutMapping("/{matricula}")
	public ResponseEntity<ServidorEfetivo> atualizar(@PathVariable String matricula, @RequestBody ServidorEfetivo servidorEfetivo){
		ServidorEfetivo servidorEfetivoSalvar = serviceEfetivoService.atualizar(matricula, servidorEfetivo);
		return ResponseEntity.ok(servidorEfetivoSalvar);
	}
	
	
	@DeleteMapping("/{matricula}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable String matricula) {
		this.serviceEfetivoService.deletar(matricula);
	}
	
	
}
