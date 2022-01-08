package br.edu.ifsp.dw2s6.gerenciamento_projeto.controler;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
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
import br.edu.ifsp.dw2s6.gerenciamento_projeto.model.Funcionario;
import br.edu.ifsp.dw2s6.gerenciamento_projeto.repository.FuncionarioRepository;
import br.edu.ifsp.dw2s6.gerenciamento_projeto.service.FuncionarioService;


@RestController
@RequestMapping("/funcionario")
public class FuncionarioResource {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_FUNCIONARIO') and #oauth2.hasScope('read')")
	public List<Funcionario> listar(){
		return funcionarioRepository.findAll();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_FUNCIONARIO') and #oauth2.hasScope('write')")
	public Funcionario criar(@Valid @RequestBody Funcionario funcionario, HttpServletResponse response) {
		return funcionarioRepository.save(funcionario);
	}
	
	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_FUNCIONARIO') and #oauth2.hasScope('read')")
	public ResponseEntity<Funcionario> buscarPeloCodigo(@PathVariable Long codigo){
		Optional<Funcionario> funcionario = funcionarioRepository.findById(codigo);
		if(funcionario.isPresent()) {
			return ResponseEntity.ok(funcionario.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_FUNCIONARIO') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		funcionarioRepository.deleteById(codigo);
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_FUNCIONARIO') and #oauth2.hasScope('write')")
	public ResponseEntity<Funcionario> atualizar(@PathVariable Long codigo, @Valid @RequestBody Funcionario funcionario){
		Funcionario funcionarioSalvo = funcionarioService.atualizar(codigo, funcionario);
		return ResponseEntity.ok(funcionarioSalvo);
	}
	
	
	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_FUNCIONARIO') and #oauth2.hasScope('read')")
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo){
		funcionarioService.atualizarPropriedadeAtivo(codigo, ativo);
	}
	
}
	
