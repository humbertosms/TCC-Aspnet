package br.com.simplifiqueerp.entidade;

import java.io.Serializable;

public class Unidade implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String descricao;
	private String simbolo;

	public Unidade() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getSimbolo() {
		return this.simbolo;
	}

	public void setSimbolo(String simbolo) {
		this.simbolo = simbolo;
	}

}