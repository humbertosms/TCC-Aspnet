package br.com.simplifiqueerp.entidade;

import java.io.Serializable;

public class Item implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String codigoBarrasFabrica;
	private String codigoBarrasInterno;
	private double estoque;
	private String referenciaFabrica;
	private String referenciaInterna;
	private Produto produto;
	private Cor cor;
	private Tamanho tamanho;

	public Item() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigoBarrasFabrica() {
		return this.codigoBarrasFabrica;
	}

	public void setCodigoBarrasFabrica(String codigoBarrasFabrica) {
		this.codigoBarrasFabrica = codigoBarrasFabrica;
	}

	public String getCodigoBarrasInterno() {
		return this.codigoBarrasInterno;
	}

	public void setCodigoBarrasInterno(String codigoBarrasInterno) {
		this.codigoBarrasInterno = codigoBarrasInterno;
	}

	public double getEstoque() {
		return this.estoque;
	}

	public void setEstoque(double estoque) {
		this.estoque = estoque;
	}

	public String getReferenciaFabrica() {
		return this.referenciaFabrica;
	}

	public void setReferenciaFabrica(String referenciaFabrica) {
		this.referenciaFabrica = referenciaFabrica;
	}

	public String getReferenciaInterna() {
		return this.referenciaInterna;
	}

	public void setReferenciaInterna(String referenciaInterna) {
		this.referenciaInterna = referenciaInterna;
	}

	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Cor getCor() {
		return this.cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	public Tamanho getTamanho() {
		return this.tamanho;
	}

	public void setTamanho(Tamanho tamanho) {
		this.tamanho = tamanho;
	}

}