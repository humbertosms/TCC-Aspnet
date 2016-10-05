package br.com.simplifiqueerp.entidade;

import java.io.Serializable;

public class Documento implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String tipo;
	private String numero;	
	private Long idEntidade;

	public Documento() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getIdEntidade() {
		return idEntidade;
	}

	public void setIdEntidade(Long idEntidade) {
		this.idEntidade = idEntidade;
	}

}