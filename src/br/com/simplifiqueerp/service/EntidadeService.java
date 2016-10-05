package br.com.simplifiqueerp.service;

import br.com.simplifiqueerp.entidade.Endereco;
import br.com.simplifiqueerp.entidade.Entidade;
import br.com.simplifiqueerp.persistencia.EntidadeDAO;
import br.com.simplifiqueerp.util.DataUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntidadeService {
	
	EntidadeDAO db = new EntidadeDAO();
	
	// Salva ou atualiza a entidade
	public boolean save(Entidade entidade){
		try {
			
			// Se a data de cadastro não está preenchida, seta a data atual
			if(entidade.getCadastro() == null){
				entidade.setCadastro(DataUtil.getDataAtual());
			}
			
			// Garante que não existirão dois endereços principais
			Boolean encontrouprincipal = false;
			if (entidade.getEnderecos() != null){
				for (Endereco endereco : entidade.getEnderecos()) {
					if (!encontrouprincipal) {
						encontrouprincipal = endereco.getPrincipal();
					}else{
						// Encontrou algum endereço marcado como principal
						// Marca os demais com não principais
						if (encontrouprincipal){}{
							endereco.setPrincipal(false);
						}
					}
				}
			}

			db.save(entidade);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public Entidade getById(Long id) {
		try {
			Entidade entidade = db.getById(id);
			return entidade;
		} catch (SQLException e) {
			return null;
		}
	}

	public List<Entidade> list() {
		try {
			List<Entidade> Entidades = db.list();
			return Entidades;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Entidade>();
		}
	}

	public List<Entidade> listagem(){
		try {
			List<Entidade> Entidades = db.listagem();
			return Entidades;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Entidade>();
		}
	}
	public boolean delete(Entidade entidade) {
		try {
			return db.delete(entidade);
		} catch (SQLException e) {
			return false;
		}
	}
}