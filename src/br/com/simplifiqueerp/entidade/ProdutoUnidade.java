package br.com.simplifiqueerp.entidade;

import java.io.Serializable;

public class ProdutoUnidade implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private float fatorConversao;
	private int principal;
	private Produto produto;
	private Unidade unidade1;
	private Unidade unidade2;

	public ProdutoUnidade() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public float getFatorConversao() {
		return this.fatorConversao;
	}

	public void setFatorConversao(float fatorConversao) {
		this.fatorConversao = fatorConversao;
	}

	public int getPrincipal() {
		return this.principal;
	}

	public void setPrincipal(int principal) {
		this.principal = principal;
	}

	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Unidade getUnidade1() {
		return this.unidade1;
	}

	public void setUnidade1(Unidade unidade1) {
		this.unidade1 = unidade1;
	}

	public Unidade getUnidade2() {
		return this.unidade2;
	}

	public void setUnidade2(Unidade unidade2) {
		this.unidade2 = unidade2;
	}

}