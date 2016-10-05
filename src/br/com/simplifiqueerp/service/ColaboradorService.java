package br.com.simplifiqueerp.service;

import br.com.simplifiqueerp.entidade.Colaborador;
import br.com.simplifiqueerp.persistencia.ColaboradorDAO;
import br.com.simplifiqueerp.entidade.Entidade;

import java.sql.SQLException;

public class ColaboradorService{
	private ColaboradorDAO db = new ColaboradorDAO();

	public boolean save(Colaborador colaborador) {
		try {
			db.save(colaborador);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	// Busca um colaborador pelo id da entidade pai
	public Colaborador getByIdEntidade(Long id){
		try {
			Colaborador colaborador = db.getColaboradorByIdEntidade(id);
			if(colaborador == null){
				colaborador = new Colaborador();
			}
			return colaborador;
		} catch (SQLException e) {
			return null;
		}
	}

	// Deleta o colaborador de uma entidade específica
	public boolean deleteFromEntidade(Entidade entidade){
		try {
			return db.deleteFromEntidade(entidade);
		} catch (SQLException e) {
			return false;
		}
	}	
}
