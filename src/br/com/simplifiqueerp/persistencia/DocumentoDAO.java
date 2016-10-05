package br.com.simplifiqueerp.persistencia;

import br.com.simplifiqueerp.entidade.Documento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DocumentoDAO  extends GenericDAO {

	public void save(Documento doc) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			if (doc.getId() == null) {
				stmt = conn.prepareStatement("INSERT INTO documento (idEntidade, Tipo, Numero) VALUES(?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
			} else {
				stmt = conn.prepareStatement("UPDATE documento SET idEntidade=?, Tipo=?, Numero=?" + "WHERE id=?");
			}
			stmt.setLong(1, doc.getIdEntidade());
			stmt.setString(2, doc.getTipo());
			stmt.setString(3, doc.getNumero());
			
			if (doc.getId() != null) {
				// Update
				stmt.setLong(4, doc.getId());
			}

			int count = stmt.executeUpdate();
			if (count == 0) {
				throw new SQLException("Erro ao inserir a Documento");
			}
			// Se inseriu, lê o id auto incremento
			if (doc.getId() == null) {
				Long id = getGeneratedId(stmt);
				doc.setId(id);
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

	public boolean delete(Documento doc) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("DELETE FROM documento WHERE id=?");
			stmt.setLong(1, doc.getId());
			
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
			stmt = conn.prepareStatement("DELETE FROM documento WHERE idEntidade=?");
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

	public List<Documento> list(Long idEntidade) throws SQLException {
		List<Documento> documentos = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM Documento WHERE idEntidade=?");
			stmt.setLong(1, idEntidade);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Documento e = create(rs);
				documentos.add(e);
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
		return documentos;
	}

	public Documento create(ResultSet rs) throws SQLException {
		Documento documento = new Documento();

		documento.setId(rs.getLong("id"));
		documento.setIdEntidade(rs.getLong("idEntidade"));
		documento.setTipo(rs.getString("Tipo"));
		documento.setNumero(rs.getString("Numero"));		

		return documento;
	}
}
