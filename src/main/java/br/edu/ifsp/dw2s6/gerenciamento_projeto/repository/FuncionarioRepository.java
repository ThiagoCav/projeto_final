package br.edu.ifsp.dw2s6.gerenciamento_projeto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifsp.dw2s6.gerenciamento_projeto.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{


}
