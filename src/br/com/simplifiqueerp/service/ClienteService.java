package br.com.simplifiqueerp.service;

import br.com.simplifiqueerp.entidade.Cliente;
import br.com.simplifiqueerp.persistencia.ClienteDAO;
import br.com.simplifiqueerp.entidade.Entidade;

import java.sql.SQLException;

public class ClienteService{
	private ClienteDAO db = new ClienteDAO();

	// Busca um cliente pelo id da entidade pai
	public Cliente getByIdEntidade(Long id){
		try {
			Cliente cliente = db.getClienteByIdEntidade(id);
			if(cliente == null){
				cliente = new Cliente();
			}
			return cliente;
		} catch (SQLException e) {
			return null;
		}
	}

	// Deleta o cliente de uma entidade específica
	public boolean deleteFromEntidade(Entidade entidade){
		try {
			return db.deleteFromEntidade(entidade);
		} catch (SQLException e) {
			return false;
		}
	}
	
	// Salva ou atualiza o cliente
	public boolean save(Cliente cliente){
		try {
			
			if(cliente.getCredito() == null){
				cliente.setCredito((double) 0);
			}

			db.save(cliente);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
}
