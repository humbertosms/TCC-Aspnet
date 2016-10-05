package br.com.simplifiqueerp.entidade;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

public class Venda implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private int cancelada;
	private LocalDate data;
	private Time hora;
	private String observacao;
	private LocalDate prevFaturamento;
	private String tipo;
	private double totalGeral;
	private double totalProdutos;
	private double totalServicos;
	private Cliente cliente;
	private List<VendaProdutoItem> vendaProdutoItems;

	public Venda() {
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

	public LocalDate getPrevFaturamento() {
		return this.prevFaturamento;
	}

	public void setPrevFaturamento(LocalDate prevFaturamento) {
		this.prevFaturamento = prevFaturamento;
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

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<VendaProdutoItem> getVendaProdutoItems() {
		return this.vendaProdutoItems;
	}

	public void setVendaProdutoItems(List<VendaProdutoItem> vendaProdutoItems) {
		this.vendaProdutoItems = vendaProdutoItems;
	}

	public VendaProdutoItem addVendaProdutoItem(VendaProdutoItem vendaProdutoItem) {
		getVendaProdutoItems().add(vendaProdutoItem);
		return vendaProdutoItem;
	}

	public VendaProdutoItem removeVendaProdutoItem(VendaProdutoItem vendaProdutoItem) {
		getVendaProdutoItems().remove(vendaProdutoItem);
		return vendaProdutoItem;
	}

}