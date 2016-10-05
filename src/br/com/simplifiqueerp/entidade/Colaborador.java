package br.com.simplifiqueerp.entidade;

import java.io.Serializable;

public class Colaborador implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private double comissao;
	private Entidade entidade;

	public Colaborador() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getComissao() {
		return this.comissao;
	}

	public void setComissao(double comissao) {
		this.comissao = comissao;
	}

	public Entidade getEntidade() {
		return this.entidade;
	}

	public void setEntidade(Entidade entidade) {
		this.entidade = entidade;
	}

}