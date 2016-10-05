package br.com.simplifiqueerp.entidade;

import java.io.Serializable;

public class ProdutoGrupo implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private Produto produto;
	private Grupo grupo;

	public ProdutoGrupo() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Grupo getGrupo() {
		return this.grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

}