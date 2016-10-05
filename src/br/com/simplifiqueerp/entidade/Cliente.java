package br.com.simplifiqueerp.entidade;

import java.io.Serializable;

public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Entidade entidade;
	private Double credito;

	public Cliente() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Entidade getEntidade() {
		return this.entidade;
	}

	public void setEntidade(Entidade entidade) {
		this.entidade = entidade;
	}

	public Double getCredito() {
		return credito;
	}

	public void setCredito(Double credito) {
		this.credito = credito;
	}

}