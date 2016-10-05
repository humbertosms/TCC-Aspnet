package br.com.simplifiqueerp.entidade;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

public class Compra implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private int cancelada;
	private LocalDate data;
	private Time hora;
	private String observacao;
	private String tipo;
	private double totalGeral;
	private double totalProdutos;
	private double totalServicos;
	private Fornecedor fornecedor;
	private List<CompraProdutoItem> compraProdutoItems;

	public Compra() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCancelada() {
		return this.cancelada;
	}

	public void setCancelada(int cancelada) {
		this.cancelada = cancelada;
	}

	public LocalDate getData() {
		return this.data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Time getHora() {
		return this.hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public double getTotalGeral() {
		return this.totalGeral;
	}

	public void setTotalGeral(double totalGeral) {
		this.totalGeral = totalGeral;
	}

	public double getTotalProdutos() {
		return this.totalProdutos;
	}

	public void setTotalProdutos(double totalProdutos) {
		this.totalProdutos = totalProdutos;
	}

	public double getTotalServicos() {
		return this.totalServicos;
	}

	public void setTotalServicos(double totalServicos) {
		this.totalServicos = totalServicos;
	}

	public Fornecedor getFornecedor() {
		return this.fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<CompraProdutoItem> getCompraProdutoItems() {
		return this.compraProdutoItems;
	}

	public void setCompraProdutoItems(List<CompraProdutoItem> compraProdutoItems) {
		this.compraProdutoItems = compraProdutoItems;
	}

	public CompraProdutoItem addCompraProdutoItem(CompraProdutoItem compraProdutoItem) {
		getCompraProdutoItems().add(compraProdutoItem);
		return compraProdutoItem;
	}

	public CompraProdutoItem removeCompraProdutoItem(CompraProdutoItem compraProdutoItem) {
		getCompraProdutoItems().remove(compraProdutoItem);
		return compraProdutoItem;
	}

}