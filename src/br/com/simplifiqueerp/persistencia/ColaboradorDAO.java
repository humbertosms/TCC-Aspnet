package br.com.simplifiqueerp.persistencia;

import br.com.simplifiqueerp.entidade.Colaborador;
import br.com.simplifiqueerp.entidade.Entidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ColaboradorDAO extends GenericDAO {

	public void save(Colaborador c) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			if (c.getId() == null) {
				stmt = conn.prepareStatement("INSERT INTO colaborador (idEntidade, Comissao) VALUES(?,?)",
						Statement.RETURN_GENERATED_KEYS);
			} else {
				stmt = conn.prepareStatement(
						"UPDATE Colaborador SET idEntidade=?, Comissao=?" + "WHERE id=?");
			}
			stmt.setLong(1, c.getEntidade().getId());
			stmt.setDouble(2, c.getComissao());

			if (c.getId() != null) {
				// Update
				stmt.setLong(3, c.getId());
			}

			int count = stmt.executeUpdate();
			if (count == 0) {
				throw new SQLException("Erro ao inserir a Colaborador");
			}
			// Se inseriu, lê o id auto incremento
			if (c.getId() == null) {
				Long id = getGeneratedId(stmt);
				c.setId(id);
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
			stmt = conn.prepareStatement("DELETE FROM colaborador WHERE id=?");
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
			stmt = conn.prepareStatement("DELETE FROM colaborador WHERE idEntidade=?");
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
	
	public Colaborador getById(Long id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM colaborador WHERE id=?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Colaborador colaborador = create(rs);
				rs.close();
				return colaborador;
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

	public Colaborador getColaboradorByIdEntidade(Long id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM colaborador WHERE idEntidade=?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Colaborador colaborador = create(rs);
				rs.close();
				return colaborador;
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
	
	public List<Colaborador> list() throws SQLException {
		List<Colaborador> colaboradors = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM colaborador");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Colaborador e = create(rs);
				colaboradors.add(e);
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
		return colaboradors;
	}

	public Colaborador create(ResultSet rs) throws SQLException {
		Colaborador colaborador = new Colaborador();
		colaborador.setId(rs.getLong("id"));
		// Seta o objeto entidade que há dentro do colaborador
		EntidadeDAO entidadeDAO = new EntidadeDAO();
		colaborador.setEntidade(entidadeDAO.getById(rs.getLong("idEntidade")));
		colaborador.setComissao(rs.getDouble("Comissao"));

		return colaborador;
	}
}
