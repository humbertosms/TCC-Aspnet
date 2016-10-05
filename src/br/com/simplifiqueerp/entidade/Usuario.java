package br.com.simplifiqueerp.entidade;

import java.io.Serializable;

public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String login;
	private String senha;
	private Entidade entidade;

	public Usuario() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Entidade getEntidade() {
		return this.entidade;
	}

	public void setEntidade(Entidade entidade) {
		this.entidade = entidade;
	}

}