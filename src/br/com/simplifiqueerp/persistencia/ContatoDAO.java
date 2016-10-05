package br.com.simplifiqueerp.persistencia;

import br.com.simplifiqueerp.entidade.Contato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO extends GenericDAO {

	public void save(Contato cont) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			if (cont.getId() == null) {
				stmt = conn.prepareStatement(
						"INSERT INTO contato (idEntidade, Nome, Telefone, Fax, Celular, Email) VALUES(?,?,?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
			} else {
				stmt = conn.prepareStatement(
						"UPDATE Contato SET idEntidade=?, Nome=?, Telefone=?, Fax=?, Celular=?, Email=? WHERE id=?");
			}
			stmt.setLong(1, cont.getIdEntidade());
			stmt.setString(2, cont.getNome());
			stmt.setString(3, cont.getTelefone());
			stmt.setString(4, cont.getFax());
			stmt.setString(5, cont.getCelular());
			stmt.setString(6, cont.getEmail());

			if (cont.getId() != null) {
				// Update
				stmt.setLong(7, cont.getId());
			}

			int count = stmt.executeUpdate();
			if (count == 0) {
				throw new SQLException("Erro ao inserir a Contato");
			}
			// Se inseriu, lê o id auto incremento
			if (cont.getId() == null) {
				Long id = getGeneratedId(stmt);
				cont.setId(id);
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

	public boolean delete(Contato cont) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("DELETE FROM Contato WHERE id=?");
			stmt.setLong(1, cont.getId());

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

	public boolean deleteByIdEntidade(Long idEntidade) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("DELETE FROM Contato WHERE idEntidade=?");
			stmt.setLong(1, idEntidade);

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

	public List<Contato> list(Long idEntidade) throws SQLException {
		List<Contato> Contatos = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM Contato WHERE idEntidade=?");
			stmt.setLong(1, idEntidade);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Contato e = create(rs);
				Contatos.add(e);
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
		return Contatos;
	}

	public Contato create(ResultSet rs) throws SQLException {
		Contato contato = new Contato();

		contato.setId(rs.getLong("id"));
		contato.setIdEntidade(rs.getLong("idEntidade"));
		contato.setNome(rs.getString("Nome"));
		contato.setTelefone(rs.getString("Telefone"));
		contato.setFax(rs.getString("Fax"));
		contato.setCelular(rs.getString("Celular"));
		contato.setEmail(rs.getString("Email"));

		return contato;
	}
}
