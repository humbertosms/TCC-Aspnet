package br.com.simplifiqueerp.persistencia;

import br.com.simplifiqueerp.entidade.Cidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CidadeDAO extends GenericDAO {
	
	public void save(Cidade cidade) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			if (cidade.getId() == null) {
				stmt = conn.prepareStatement("INSERT INTO cidade (id, Nome, UF) VALUES(?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
			} else {
				stmt = conn.prepareStatement("UPDATE cidade SET id=?, Nome=?, UF=?" + "WHERE id=?");
			}
			stmt.setLong(1, cidade.getId());
			stmt.setString(2, cidade.getNome());
			stmt.setString(3, cidade.getUf());
			
			if (cidade.getId() != null) {
				// Update
				stmt.setLong(4, cidade.getId());
			}

			int count = stmt.executeUpdate();
			if (count == 0) {
				throw new SQLException("Erro ao inserir a Cidade");
			}
			// Se inseriu, lê o id auto incremento
			if (cidade.getId() == null) {
				Long id = getGeneratedId(stmt);
				cidade.setId(id);
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

	public boolean delete(Cidade cidade) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("DELETE FROM cidade WHERE id=?");
			stmt.setLong(1, cidade.getId());
			
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
	
	public Cidade getById(Long id) throws SQLException {
		Cidade cidade = new Cidade();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM cidade WHERE id=?");
			stmt.setLong(1, id);

			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				cidade = create(rs);
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
		return cidade;
	}
	
	public List<Cidade> list(String uf) throws SQLException {
		List<Cidade> cidades = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM cidade WHERE uf=? ORDER BY Nome");
			stmt.setString(1, uf);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Cidade e = create(rs);
				cidades.add(e);
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
		return cidades;
	}
	
	public Cidade create(ResultSet rs) throws SQLException {
		Cidade cidade = new Cidade();

		cidade.setId(rs.getLong("id"));
		cidade.setNome(rs.getString("Nome"));
		cidade.setUf(rs.getString("UF"));		

		return cidade;
	}
}
