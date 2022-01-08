package br.edu.ifsp.dw2s6.gerenciamento_projeto.model;

import java.time.LocalDate;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
@Table(name = "projeto")
public class Projeto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@NotNull
	private String nome;
	
	@NotNull
	private String descricao;
	
	@Column(name = "inicio_projeto")
	@DateTimeFormat(pattern = "dd/MM/yyyy", iso = ISO.DATE_TIME)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate inicioProjeto;

	@Column(name = "fim_projeto")
	@DateTimeFormat(pattern = "dd/MM/yyyy", iso = ISO.DATE_TIME)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate fimProjeto;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "codigo_setor")
	private Setor setor;
	
	private Boolean ativo;

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getInicioProjeto() {
		return inicioProjeto;
	}

	public void setInicioProjeto(LocalDate inicioProjeto) {
		this.inicioProjeto = inicioProjeto;
	}

	public LocalDate getFimProjeto() {
		return fimProjeto;
	}

	public void setFimProjeto(LocalDate fimProjeto) {
		this.fimProjeto = fimProjeto;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Projeto other = (Projeto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
