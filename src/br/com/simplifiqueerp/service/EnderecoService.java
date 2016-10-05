package br.com.simplifiqueerp.service;

import br.com.simplifiqueerp.entidade.Endereco;
import br.com.simplifiqueerp.persistencia.EnderecoDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnderecoService{
	private EnderecoDAO db = new EnderecoDAO();

	// Lista todos os enderecos de um id entidade
	public List<Endereco>getEnderecos(Long idEntidade){
		try {
			List<Endereco> Enderecos = db.list(idEntidade);
			return Enderecos;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Endereco>();
		}
	}

	// Deleta os enderecos de um id entidade
	public boolean deleteFromIdEntidade(Long idEntidade){
		try {
			return db.deleteByIdEntidade(idEntidade);
		} catch (SQLException e) {
			return false;
		}
	}

	// Deleta um endereco
	public boolean delete(Endereco endereco){
		try {
			return db.delete(endereco);
		} catch (SQLException e) {
			return false;
		}
	}

	// Salva ou atualiza o endereco
	public boolean save(Endereco endereco){
		try {			
			db.save(endereco);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
}
