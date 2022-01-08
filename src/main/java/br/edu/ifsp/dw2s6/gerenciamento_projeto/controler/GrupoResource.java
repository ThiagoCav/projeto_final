package br.edu.ifsp.dw2s6.gerenciamento_projeto.controler;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import br.edu.ifsp.dw2s6.gerenciamento_projeto.model.Grupo;
import br.edu.ifsp.dw2s6.gerenciamento_projeto.repository.GrupoRepository;
import br.edu.ifsp.dw2s6.gerenciamento_projeto.service.GrupoService;

@RestController
@RequestMapping("/grupo")
public class GrupoResource {
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private GrupoService grupoService;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_GRUPO') and #oauth2.hasScope('read')")
	public List<Grupo> listar(){
		return grupoRepository.findAll();
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_GRUPO') and #oauth2.hasScope('read')")
	public ResponseEntity<Grupo> buscarPeloCodigo(@PathVariable Long codigo){
		Optional<Grupo> grupo = grupoRepository.findById(codigo);
		if(grupo.isPresent()) {
			return ResponseEntity.ok(grupo.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_GRUPO') and #oauth2.hasScope('write')")
	public Grupo criar(@Valid @RequestBody Grupo grupo) {
		return grupoRepository.save(grupo);
	}		
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_GRUPO') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		grupoRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_GRUPO') and #oauth2.hasScope('write')")
	public ResponseEntity<Grupo> atualizar(@PathVariable Long codigo, @Valid @RequestBody Grupo grupo){
		Grupo grupoSalvo = grupoService.atualizar(codigo, grupo);
		return ResponseEntity.ok(grupoSalvo);
	}

}
