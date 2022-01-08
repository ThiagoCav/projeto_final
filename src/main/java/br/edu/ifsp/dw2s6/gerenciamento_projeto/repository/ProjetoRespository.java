package br.edu.ifsp.dw2s6.gerenciamento_projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsp.dw2s6.gerenciamento_projeto.model.Projeto;

public interface ProjetoRespository extends JpaRepository<Projeto, Long> {

}
