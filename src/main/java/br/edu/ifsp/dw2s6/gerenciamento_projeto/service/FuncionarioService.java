package br.edu.ifsp.dw2s6.gerenciamento_projeto.service;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.ifsp.dw2s6.gerenciamento_projeto.model.Funcionario;
import br.edu.ifsp.dw2s6.gerenciamento_projeto.repository.FuncionarioRepository;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	public Funcionario atualizar(Long codigo, @Valid Funcionario funcionario) {
		Funcionario funcionarioSalvo = funcionarioRepository.findById(codigo)
				.orElseThrow(()-> new EmptyResultDataAccessException(1));
		BeanUtils.copyProperties(funcionario, funcionarioSalvo, "codigo");
		return funcionarioRepository.save(funcionarioSalvo);
	}
	
	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Funcionario funcionarioSalvo = buscarPeloCodigo(codigo);
		funcionarioSalvo.setAtivo(ativo);
		funcionarioRepository.save(funcionarioSalvo);
	}
	
	private Funcionario buscarPeloCodigo(Long codigo) {
		Funcionario funcionarioSalvo = funcionarioRepository.findById(codigo)
				.orElseThrow(()-> new EmptyResultDataAccessException(1));
		return funcionarioSalvo;
	}

}
