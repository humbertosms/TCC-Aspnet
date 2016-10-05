package br.com.simplifiqueerp.service;

import br.com.simplifiqueerp.entidade.Contato;
import br.com.simplifiqueerp.persistencia.ContatoDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoService{
	private ContatoDAO db = new ContatoDAO();

	
	public boolean save(Contato contato) {
		try {
			db.save(contato);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean delete(Contato contato) {
		try {
			return db.delete(contato);
		} catch (SQLException e) {
			return false;
		}
	}
	
	// Lista todos os contatos de um id entidade
	public List<Contato>list(Long idEntidade){
		try {
			List<Contato> Contatos = db.list(idEntidade);
			return Contatos;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Contato>();
		}
	}

	// Deleta os contatos de um id entidade
	public boolean deleteFromIdEntidade(Long idEntidade){
		try {
			return db.deleteByIdEntidade(idEntidade);
		} catch (SQLException e) {
			return false;
		}
	}
}
