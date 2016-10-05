package br.com.simplifiqueerp.entidade;

import java.io.Serializable;

public class Cidade implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String uf;

	public Cidade() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUf() {
		return this.uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

}