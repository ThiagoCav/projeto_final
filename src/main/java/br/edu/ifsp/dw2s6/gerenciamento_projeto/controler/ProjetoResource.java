package br.edu.ifsp.dw2s6.gerenciamento_projeto.controler;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import br.edu.ifsp.dw2s6.gerenciamento_projeto.model.Projeto;
import br.edu.ifsp.dw2s6.gerenciamento_projeto.repository.ProjetoRespository;
import br.edu.ifsp.dw2s6.gerenciamento_projeto.service.ProjetoService;


@RestController
@RequestMapping("/projeto")
public class ProjetoResource {
	
	@Autowired
	private ProjetoRespository projetorRepository;
	
	@Autowired
	private ProjetoService projetoService;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PROJETO') and #oauth2.hasScope('read')")
	public List<Projeto> listar(){
		return projetorRepository.findAll();
	}
	
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PROJETO') and #oauth2.hasScope('read')")
	public ResponseEntity<Projeto> buscarPeloCodigo(@PathVariable Long codigo){
		Optional<Projeto> projeto = projetorRepository.findById(codigo);
		if(projeto.isPresent()) {
			return ResponseEntity.ok(projeto.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PROJETO') and #oauth2.hasScope('write')")
	public Projeto criar(@Valid @RequestBody Projeto projeto) {
		return projetorRepository.save(projeto);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_PROJETO') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		projetorRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PROJETO') and #oauth2.hasScope('write')")
	public ResponseEntity<Projeto> atualizar(@PathVariable Long codigo, @Valid @RequestBody Projeto projeto){
		Projeto projetoSalvo = projetoService.atualizar(codigo, projeto);
		return ResponseEntity.ok(projetoSalvo);
	}
	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PROJETO') and #oauth2.hasScope('read')")
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo){
		projetoService.atualizarPropriedadeAtivo(codigo, ativo);
	}
}
