package br.com.simplifiqueerp.service;

import br.com.simplifiqueerp.entidade.Usuario;
import br.com.simplifiqueerp.persistencia.UsuarioDAO;
import br.com.simplifiqueerp.entidade.Entidade;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioService{
	private UsuarioDAO db = new UsuarioDAO();

	// Lista todos os usuários
	public List<Usuario>getUsuarios(){
		try {
			List<Usuario> Usuarios = db.getUsuarios();
			return Usuarios;
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Usuario>();
		}
	}

	// Busca um usuário pelo id
	public Usuario getUsuarioById(Long id){
		try {
			Usuario usuario = db.getUsuarioById(id);
			if(usuario == null){
				usuario = new Usuario();
			}
			return usuario;
		} catch (SQLException e) {
			return null;
		}
	}

	// Busca um usuário pelo id da entidade pai
	public Usuario getUsuarioByIdEntidade(Long id){
		try {
			Usuario usuario = db.getUsuarioByIdEntidade(id);
			return usuario;
		} catch (SQLException e) {
			return null;
		}
	}
	
	// Deleta um usuário
	public boolean delete(Long id){
		try {
			return db.delete(id);
		} catch (SQLException e) {
			return false;
		}
	}

	// Deleta o usuário de uma entidade específica
	public boolean deleteFromEntidade(Entidade entidade){
		try {
			return db.deleteFromEntidade(entidade);
		} catch (SQLException e) {
			return false;
		}
	}
	
	// Salva ou atualiza o usuário
	public boolean save(Usuario usuario){
		try {
			db.save(usuario);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
}
