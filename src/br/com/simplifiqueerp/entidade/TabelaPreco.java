package br.com.simplifiqueerp.entidade;

import java.io.Serializable;

public class TabelaPreco implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private int preco;
	private Item item;

	private Plano plano;

	public TabelaPreco() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPreco() {
		return this.preco;
	}

	public void setPreco(int preco) {
		this.preco = preco;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Plano getPlano() {
		return this.plano;
	}

	public void setPlano(Plano plano) {
		this.plano = plano;
	}

}