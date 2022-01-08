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

import br.edu.ifsp.dw2s6.gerenciamento_projeto.model.ProjetoFuncionario;
import br.edu.ifsp.dw2s6.gerenciamento_projeto.repository.ProjetoFuncionarioRepository;
import br.edu.ifsp.dw2s6.gerenciamento_projeto.service.ProjetoFuncionarioService;

@RestController
@RequestMapping("/projetofuncionario")
public class ProjetoFuncionarioResource {
	
	@Autowired
	private ProjetoFuncionarioRepository projetoFuncionarioRepository;
	
	@Autowired
	private ProjetoFuncionarioService projetoFuncionarioService;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PROJETO_FUNCIONARIO') and #oauth2.hasScope('read')")
	public List<ProjetoFuncionario> listar(){
		return projetoFuncionarioRepository.findAll();
	}
	
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PROJETO_FUNCIONARIO') and #oauth2.hasScope('read')")
	public ResponseEntity<ProjetoFuncionario> buscarPeloCodigo(@PathVariable Long codigo){
		Optional<ProjetoFuncionario> projetoFuncionario = projetoFuncionarioRepository.findById(codigo);
		if(projetoFuncionario.isPresent()) {
			return ResponseEntity.ok(projetoFuncionario.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PROJETO_FUNCIONARIO') and #oauth2.hasScope('write')")
	public ProjetoFuncionario criar(@Valid @RequestBody ProjetoFuncionario projetoFuncionario) {
		return projetoFuncionarioRepository.save(projetoFuncionario);
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_PROJETO_FUNCIONARIO') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		projetoFuncionarioRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PROJETO_FUNCIONARIO') and #oauth2.hasScope('write')")
	public ResponseEntity<ProjetoFuncionario> atualizar(@PathVariable Long codigo, @Valid @RequestBody ProjetoFuncionario projetoFuncionario){
		ProjetoFuncionario projetoFuncionarioSalvo = projetoFuncionarioService.atualizar(codigo, projetoFuncionario);
		return ResponseEntity.ok(projetoFuncionarioSalvo);
	}
	
	@PutMapping("/{codigo}/gestor")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PROJETO_FUNCIONARIO') and #oauth2.hasScope('read')")
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean gestor){
		projetoFuncionarioService.atualizarPropriedadeAtivo(codigo, gestor);
	}

}
