package br.com.simplifiqueerp.entidade;

import java.io.Serializable;
import java.time.LocalDate;

public class Lote implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private double estoque;
	private LocalDate fabricacao;
	private String lote;
	private Produto produto;

	public Lote() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getEstoque() {
		return this.estoque;
	}

	public void setEstoque(double estoque) {
		this.estoque = estoque;
	}

	public LocalDate getFabricacao() {
		return this.fabricacao;
	}

	public void setFabricacao(LocalDate fabricacao) {
		this.fabricacao = fabricacao;
	}

	public String getLote() {
		return this.lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}