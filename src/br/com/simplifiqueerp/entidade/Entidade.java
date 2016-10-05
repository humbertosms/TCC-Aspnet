package br.com.simplifiqueerp.entidade;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Entidade implements Serializable{
	private static final long serialVersionUID = 1L;

	private Long id;
	private String tipoPessoa;
	private String nome;
	private String apelido;
	private String cpf_cnpj;
	private String rg_ie;
	private LocalDate nascimento;
	private String telefone;
	private String fax;
	private String celular;
	private String email;
	private String site;
	private String observacao;
	private LocalDate cadastro;	
	private List<Contato> contatos;
	private List<Documento> documentos;
	private List<Endereco> enderecos;
	private List<DadoBancario> dadosBancarios;
	private List<EntidadeFoto> entidadeFotos;

	public Entidade() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getCpf_cnpj() {
		return cpf_cnpj;
	}

	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}

	public String getRg_ie() {
		return rg_ie;
	}

	public void setRg_ie(String rg_ie) {
		this.rg_ie = rg_ie;
	}

	public LocalDate getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
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

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public List<DadoBancario> getDadosBancarios() {
		return dadosBancarios;
	}

	public void setDadosBancarios(List<DadoBancario> dadosBancarios) {
		this.dadosBancarios = dadosBancarios;
	}

	public List<EntidadeFoto> getEntidadeFotos() {
		return entidadeFotos;
	}

	public void setEntidadeFotos(List<EntidadeFoto> entidadeFotos) {
		this.entidadeFotos = entidadeFotos;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

}