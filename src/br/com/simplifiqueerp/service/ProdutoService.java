package br.com.simplifiqueerp.service;

import br.com.simplifiqueerp.entidade.Produto;
import br.com.simplifiqueerp.persistencia.ProdutoDAO;
import br.com.simplifiqueerp.util.DataUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoService {
	
	ProdutoDAO db = new ProdutoDAO();
	
	// Salva ou atualiza o produto
	public boolean save(Produto produto){
		try {			
			// Se a data de cadastro não está preenchida, seta a data atual
			if(produto.getCadastro() == null){
				produto.setCadastro(DataUtil.getDataAtual());
			}			
			db.save(produto);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public Produto getById(Long id) {
		try {
			Produto produto = db.getById(id);
			return produto;
		} catch (SQLException e) {
			return null;
		}
	}

	public List<Produto> list() {
		try {
			List<Produto> Produtos = db.list();
			return Produtos;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Produto>();
		}
	}

	public boolean delete(Produto produto) {
		try {
			return db.delete(produto);
		} catch (SQLException e) {
			return false;
		}
	}
}
