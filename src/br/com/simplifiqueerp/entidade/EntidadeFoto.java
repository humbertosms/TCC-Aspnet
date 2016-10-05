package br.com.simplifiqueerp.entidade;

import java.io.Serializable;

public class EntidadeFoto implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private byte[] foto;
	private Entidade entidade;

	public EntidadeFoto() {
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

	public Entidade getEntidade() {
		return this.entidade;
	}

	public void setEntidade(Entidade entidade) {
		this.entidade = entidade;
	}

}