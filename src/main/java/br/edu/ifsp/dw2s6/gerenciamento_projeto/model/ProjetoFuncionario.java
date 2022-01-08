package br.edu.ifsp.dw2s6.gerenciamento_projeto.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;

@Entity
@Table(name = "projeto_funcionario")
public class ProjetoFuncionario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull
	@Column(name = "carga_horaria")
	private Integer cargaHoraria;
	
	private Boolean gestor;
	
	@Column(name = "inicio_participacao")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate inicioParticipacao;

	@Column(name = "fim_participacao")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate fimParticipacao;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_funcionario")
	private Funcionario funcionario;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_projeto")
	private Projeto projeto;
	

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Integer getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public Boolean getGestor() {
		return gestor;
	}

	public void setGestor(Boolean gestor) {
		this.gestor = gestor;
	}

	public LocalDate getInicioParticipacao() {
		return inicioParticipacao;
	}

	public void setInicioParticipacao(LocalDate inicioParticipacao) {
		this.inicioParticipacao = inicioParticipacao;
	}

	public LocalDate getFimParticipacao() {
		return fimParticipacao;
	}

	public void setFimParticipacao(LocalDate fimParticipacao) {
		this.fimParticipacao = fimParticipacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProjetoFuncionario other = (ProjetoFuncionario) obj;
		return Objects.equals(codigo, other.codigo);
	}

}
