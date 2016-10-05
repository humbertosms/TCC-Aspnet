package br.com.simplifiqueerp.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.simplifiqueerp.entidade.Cidade;
import br.com.simplifiqueerp.persistencia.CidadeDAO;

public class CidadeService{	

	private CidadeDAO db = new CidadeDAO();
	
	public boolean save(Cidade entidade) {
		try {
			db.save(entidade);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public Cidade getById(Long id) {
		try {
			Cidade entidade = db.getById(id);
			return entidade;
		} catch (SQLException e) {
			return null;
		}
	}

	public List<Cidade> list(String uf) {
		try {
			List<Cidade> Entidades = db.list(uf);
			return Entidades;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Cidade>();
		}
	}

	public boolean delete(Cidade entidade) {
		try {
			return db.delete(entidade);
		} catch (SQLException e) {
			return false;
		}
	}
}
