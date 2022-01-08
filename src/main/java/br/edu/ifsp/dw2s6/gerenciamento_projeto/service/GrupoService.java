package br.edu.ifsp.dw2s6.gerenciamento_projeto.service;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.ifsp.dw2s6.gerenciamento_projeto.model.Grupo;
import br.edu.ifsp.dw2s6.gerenciamento_projeto.repository.GrupoRepository;

@Service
public class GrupoService {
	
	@Autowired
	private GrupoRepository grupoRepository;

	public Grupo atualizar(Long codigo, @Valid Grupo grupo) {
		Grupo grupoSalvo = grupoRepository.findById(codigo)
				.orElseThrow(()-> new EmptyResultDataAccessException(1));
		BeanUtils.copyProperties(grupo, grupoSalvo, "codigo");
		return grupoRepository.save(grupoSalvo);
	}

}
