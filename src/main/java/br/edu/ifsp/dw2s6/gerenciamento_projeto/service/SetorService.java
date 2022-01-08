package br.edu.ifsp.dw2s6.gerenciamento_projeto.service;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.ifsp.dw2s6.gerenciamento_projeto.model.Setor;
import br.edu.ifsp.dw2s6.gerenciamento_projeto.repository.SetorRepository;

@Service
public class SetorService {
	
	@Autowired
	private SetorRepository setorRepository;

	public Setor atualizar(Long codigo, @Valid Setor setor) {
		Setor setorSalvo = setorRepository.findById(codigo)
				.orElseThrow(()-> new EmptyResultDataAccessException(1));
		BeanUtils.copyProperties(setor, setorSalvo, "codigo");
		return setorRepository.save(setorSalvo);
	}

}
