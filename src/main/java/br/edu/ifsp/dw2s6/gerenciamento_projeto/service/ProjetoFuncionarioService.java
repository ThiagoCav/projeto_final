package br.edu.ifsp.dw2s6.gerenciamento_projeto.service;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.ifsp.dw2s6.gerenciamento_projeto.model.ProjetoFuncionario;
import br.edu.ifsp.dw2s6.gerenciamento_projeto.repository.ProjetoFuncionarioRepository;


@Service
public class ProjetoFuncionarioService {
	
	@Autowired
	private ProjetoFuncionarioRepository projetoFuncionarioRepository;
	
	public ProjetoFuncionario salvar(Long codigo, @Valid ProjetoFuncionario projetofuncionario) {
			ProjetoFuncionario projetoFuncionarioSalvo = projetoFuncionarioRepository.findById(codigo)
					.orElseThrow(()-> new EmptyResultDataAccessException(1));
			BeanUtils.copyProperties(projetofuncionario, projetoFuncionarioSalvo, "codigo");
			return projetoFuncionarioRepository.save(projetoFuncionarioSalvo);
		}
	
	public ProjetoFuncionario atualizar(Long codigo, @Valid ProjetoFuncionario projetoFuncionario) {
		ProjetoFuncionario projetoFuncionarioSalvo = projetoFuncionarioRepository.findById(codigo)
				.orElseThrow(()-> new EmptyResultDataAccessException(1));
		BeanUtils.copyProperties(projetoFuncionario, projetoFuncionarioSalvo, "codigo");
		return projetoFuncionarioRepository.save(projetoFuncionarioSalvo);
	}
	
	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		ProjetoFuncionario projetoFuncionarioSalvo = buscarPeloCodigo(codigo);
		projetoFuncionarioSalvo.setGestor(ativo);
		projetoFuncionarioRepository.save(projetoFuncionarioSalvo);
	}
	
	private ProjetoFuncionario buscarPeloCodigo(Long codigo) {
		ProjetoFuncionario projetoFuncionarioSalvo = projetoFuncionarioRepository.findById(codigo)
				.orElseThrow(()-> new EmptyResultDataAccessException(1));
		return projetoFuncionarioSalvo;
	}
	
	

}
