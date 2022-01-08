package br.edu.ifsp.dw2s6.gerenciamento_projeto.service;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.ifsp.dw2s6.gerenciamento_projeto.model.Projeto;
import br.edu.ifsp.dw2s6.gerenciamento_projeto.repository.ProjetoRespository;

@Service
public class ProjetoService {
	
	
	@Autowired
	private ProjetoRespository projetoRepository;
	
	public Projeto salvar(Long codigo, @Valid Projeto projeto) {
			Projeto projetoSalvo = projetoRepository.findById(codigo)
					.orElseThrow(()-> new EmptyResultDataAccessException(1));
			BeanUtils.copyProperties(projeto, projetoSalvo, "codigo");
			return projetoRepository.save(projetoSalvo);
		}
	
	public Projeto atualizar(Long codigo, @Valid Projeto projeto) {
		Projeto projetoSalvo = projetoRepository.findById(codigo)
				.orElseThrow(()-> new EmptyResultDataAccessException(1));
		BeanUtils.copyProperties(projeto, projetoSalvo, "codigo");
		return projetoRepository.save(projetoSalvo);
	}
	
	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Projeto projetoSalvo = buscarPeloCodigo(codigo);
		projetoSalvo.setAtivo(ativo);
		projetoRepository.save(projetoSalvo);
	}
	
	private Projeto buscarPeloCodigo(Long codigo) {
		Projeto projetoSalvo = projetoRepository.findById(codigo)
				.orElseThrow(()-> new EmptyResultDataAccessException(1));
		return projetoSalvo;
	}
}

