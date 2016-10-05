package br.com.simplifiqueerp.service;

import br.com.simplifiqueerp.entidade.Fornecedor;
import br.com.simplifiqueerp.persistencia.FornecedorDAO;
import br.com.simplifiqueerp.entidade.Entidade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FornecedorService{
	private FornecedorDAO db = new FornecedorDAO();

	// Lista todos os fornecedors
	public List<Fornecedor>list(){
		try {
			List<Fornecedor> Fornecedors = db.getFornecedors();
			return Fornecedors;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Fornecedor>();
		}
	}

	// Busca um fornecedor pelo id
	public Fornecedor getById(Long id){
		try {
			Fornecedor fornecedor = db.getById(id);
			if(fornecedor == null){
				fornecedor = new Fornecedor();
			}
			return fornecedor;
		} catch (SQLException e) {
			return null;
		}
	}

	// Busca um fornecedor pelo id da entidade pai
	public Fornecedor getFornecedorByIdEntidade(Long id){
		try {
			Fornecedor fornecedor = db.list(id);
			return fornecedor;
		} catch (SQLException e) {
			return null;
		}
	}

	// Deleta um fornecedor
	public boolean delete(Long id){
		try {
			return db.delete(id);
		} catch (SQLException e) {
			return false;
		}
	}

	// Deleta o fornecedor de uma entidade específica
	public boolean deleteFromEntidade(Entidade entidade){
		try {
			return db.deleteFromEntidade(entidade);
		} catch (SQLException e) {
			return false;
		}
	}
	
	// Salva ou atualiza o fornecedor
	public boolean save(Fornecedor fornecedor){
		try {
			db.save(fornecedor);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
}
