package br.com.simplifiqueerp.service;

import br.com.simplifiqueerp.entidade.DadoBancario;
import br.com.simplifiqueerp.persistencia.DadoBancarioDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DadoBancarioService{
	private DadoBancarioDAO db = new DadoBancarioDAO();

	// Lista todos os dados bancários de um id entidade
	public List<DadoBancario>getDadoBancarios(Long idEntidade){
		try {
			List<DadoBancario> DadoBancarios = db.list(idEntidade);
			return DadoBancarios;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<DadoBancario>();
		}
	}

	// Deleta os dados bancários de um id entidade
	public boolean deleteFromIdEntidade(Long idEntidade){
		try {
			return db.deleteByIdEntidade(idEntidade);
		} catch (SQLException e) {
			return false;
		}
	}

	// Deleta um dado bancário
	public boolean delete(DadoBancario cadobancario){
		try {
			return db.delete(cadobancario);
		} catch (SQLException e) {
			return false;
		}
	}

	// Salva ou atualiza o dado bancário
	public boolean save(DadoBancario cadobancario){
		try {
			db.save(cadobancario);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
}