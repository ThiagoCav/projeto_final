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


import br.edu.ifsp.dw2s6.gerenciamento_projeto.model.Setor;
import br.edu.ifsp.dw2s6.gerenciamento_projeto.repository.SetorRepository;
import br.edu.ifsp.dw2s6.gerenciamento_projeto.service.SetorService;

@RestController
@RequestMapping("/setor")
public class SetorResource {
	
	@Autowired
	private SetorRepository setorRepository;
	
	@Autowired
	private SetorService setorService;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_SETOR') and #oauth2.hasScope('read')")
	public List<Setor> listar(){
		return setorRepository.findAll();
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_SETOR') and #oauth2.hasScope('read')")
	public ResponseEntity<Setor> buscarPeloCodigo(@PathVariable Long codigo){
		Optional<Setor> setor = setorRepository.findById(codigo);
		if(setor.isPresent()) {
			return ResponseEntity.ok(setor.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_SETOR') and #oauth2.hasScope('write')")
	public Setor criar(@Valid @RequestBody Setor setor) {
		return setorRepository.save(setor);
	}		
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_SETOR') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		setorRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_SETOR') and #oauth2.hasScope('write')")
	public ResponseEntity<Setor> atualizar(@PathVariable Long codigo, @Valid @RequestBody Setor setor){
		Setor setorSalvo = setorService.atualizar(codigo, setor);
		return ResponseEntity.ok(setorSalvo);
	}
}
