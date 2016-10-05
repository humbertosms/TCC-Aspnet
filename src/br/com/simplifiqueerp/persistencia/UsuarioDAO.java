package br.com.simplifiqueerp.persistencia;

import br.com.simplifiqueerp.entidade.Usuario;
import br.com.simplifiqueerp.entidade.Entidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends GenericDAO {

	public void save(Usuario u) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			if (u.getId() == null) {
				stmt = conn.prepareStatement("INSERT INTO Usuario (idEntidade, Login, Senha) VALUES(?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
			} else {
				stmt = conn.prepareStatement(
						"UPDATE Usuario SET idEntidade=?, Login=?, Senha=?" + "WHERE id=?");
			}
			stmt.setLong(1, u.getEntidade().getId());
			stmt.setString(2, u.getLogin());
			stmt.setString(3, u.getSenha());

			if (u.getId() != null) {
				// Update
				stmt.setLong(4, u.getId());
			}

			int count = stmt.executeUpdate();
			if (count == 0) {
				throw new SQLException("Erro ao inserir a Usuario");
			}
			// Se inseriu, lê o id auto incremento
			if (u.getId() == null) {
				Long id = getGeneratedId(stmt);
				u.setId(id);
			}

		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	public boolean delete(Long id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("DELETE FROM Usuario WHERE id=?");
			stmt.setLong(1, id);
			int count = stmt.executeUpdate();
			boolean ok = count > 0;
			return ok;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	public boolean deleteFromEntidade(Entidade entidade) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("DELETE FROM Usuario WHERE idEntidade=?");
			stmt.setLong(1, entidade.getId());
			int count = stmt.executeUpdate();
			boolean ok = count > 0;
			return ok;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	public Usuario getUsuarioById(Long id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM Usuario WHERE id=?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Usuario usuario = criarUsuario(rs);
				rs.close();
				return usuario;
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}
	
	public Usuario getUsuarioByIdEntidade(Long id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM Usuario WHERE idEntidade=?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Usuario usuario = criarUsuario(rs);
				rs.close();
				return usuario;
			}
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return null;
	}

	public List<Usuario> getUsuarios() throws SQLException {
		List<Usuario> usuarios = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM Usuario");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Usuario e = criarUsuario(rs);
				usuarios.add(e);
			}
			rs.close();
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return usuarios;
	}

	private Usuario criarUsuario(ResultSet rs) throws SQLException {
		Usuario usuario = new Usuario();
		usuario.setId(rs.getLong("id"));
		// Seta o objeto entidade que há dentro do usuario
		EntidadeDAO entidadeDAO = new EntidadeDAO();
		usuario.setEntidade(entidadeDAO.getById(rs.getLong("idEntidade")));
		usuario.setLogin(rs.getString("Login"));
		usuario.setSenha(rs.getString("Senha"));

		return usuario;
	}
}
