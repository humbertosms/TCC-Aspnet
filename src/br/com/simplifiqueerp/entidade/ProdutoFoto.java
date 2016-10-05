package br.com.simplifiqueerp.entidade;

import java.io.Serializable;

public class ProdutoFoto implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private byte[] foto;
	private Item item;

	public ProdutoFoto() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getFoto() {
		return this.foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

}