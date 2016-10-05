package br.com.simplifiqueerp.entidade;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Produto implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String descricao;
	private String localizacao;
	private String observacao;
	private LocalDate cadastro;
	private int validade;
	private double comissao;	
	private List<Item> items;
	private List<Lote> lotes;	
	private List<ProdutoGrupo> produtoGrupos;
	private List<ProdutoUnidade> produtoUnidades;

	public Produto() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public LocalDate getCadastro() {
		return cadastro;
	}

	public void setCadastro(LocalDate cadastro) {
		this.cadastro = cadastro;
	}

	public int getValidade() {
		return validade;
	}

	public void setValidade(int validade) {
		this.validade = validade;
	}

	public double getComissao() {
		return comissao;
	}

	public void setComissao(double comissao) {
		this.comissao = comissao;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public List<Lote> getLotes() {
		return lotes;
	}

	public void setLotes(List<Lote> lotes) {
		this.lotes = lotes;
	}

	public List<ProdutoGrupo> getProdutoGrupos() {
		return produtoGrupos;
	}

	public void setProdutoGrupos(List<ProdutoGrupo> produtoGrupos) {
		this.produtoGrupos = produtoGrupos;
	}

	public List<ProdutoUnidade> getProdutoUnidades() {
		return produtoUnidades;
	}

	public void setProdutoUnidades(List<ProdutoUnidade> produtoUnidades) {
		this.produtoUnidades = produtoUnidades;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}