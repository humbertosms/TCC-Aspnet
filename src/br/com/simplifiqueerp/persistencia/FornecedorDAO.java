package br.com.simplifiqueerp.persistencia;

import br.com.simplifiqueerp.entidade.Fornecedor;
import br.com.simplifiqueerp.entidade.Entidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO extends GenericDAO {

	public void save(Fornecedor f) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			if (f.getId() == null) {
				stmt = conn.prepareStatement("INSERT INTO fornecedor (idEntidade) VALUES(?)",
						Statement.RETURN_GENERATED_KEYS);
			} else {
				stmt = conn.prepareStatement("UPDATE fornecedor SET idEntidade=?" + "WHERE id=?");
			}
			stmt.setLong(1, f.getEntidade().getId());

			if (f.getId() != null) {
				// Update
				stmt.setLong(2, f.getId());
			}

			int count = stmt.executeUpdate();
			if (count == 0) {
				throw new SQLException("Erro ao inserir a Fornecedor");
			}
			// Se inseriu, lê o id auto incremento
			if (f.getId() == null) {
				Long id = getGeneratedId(stmt);
				f.setId(id);
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
			stmt = conn.prepareStatement("DELETE FROM fornecedor WHERE id=?");
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
			stmt = conn.prepareStatement("DELETE FROM fornecedor WHERE idEntidade=?");
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

	public Fornecedor getById(Long id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM fornecedor WHERE id=?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Fornecedor fornecedor = create(rs);
				rs.close();
				return fornecedor;
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

	public Fornecedor list(Long id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM fornecedor WHERE idEntidade=?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Fornecedor fornecedor = create(rs);
				rs.close();
				return fornecedor;
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
	
	public List<Fornecedor> getFornecedors() throws SQLException {
		List<Fornecedor> fornecedors = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM fornecedor");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Fornecedor e = create(rs);
				fornecedors.add(e);
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
		return fornecedors;
	}

	public Fornecedor create(ResultSet rs) throws SQLException {
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setId(rs.getLong("id"));
		// Seta o objeto entidade que há dentro do fornecedor
		EntidadeDAO entidadeDAO = new EntidadeDAO();
		fornecedor.setEntidade(entidadeDAO.getById(rs.getLong("idEntidade")));

		return fornecedor;
	}
}
