package br.com.simplifiqueerp.persistencia;

import br.com.simplifiqueerp.entidade.Endereco;
import br.com.simplifiqueerp.service.CidadeService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class EnderecoDAO extends GenericDAO {

	public void save(Endereco endereco) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			if (endereco.getId() == null) {
				stmt = conn.prepareStatement(
						"INSERT INTO endereco (idEntidade, Principal, CEP, Logradouro, Numero, "
						+ "Complemento, Bairro, idCidade, Referencia) VALUES(?,?,?,?,?,?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
			} else {
				stmt = conn.prepareStatement("UPDATE endereco SET idEntidade=?, Principal=?, CEP=?, Logradouro=?, "
						+ "Numero=?, Complemento=?, Bairro=?, idCidade=?, Referencia=?" + "WHERE id=?");
			}
			stmt.setLong(1, endereco.getIdEntidade());
			stmt.setLong(2, (endereco.getPrincipal()) ? 1 : 0);
			stmt.setString(3, endereco.getCep());
			stmt.setString(4, endereco.getLogradouro());
			stmt.setString(5, endereco.getNumero());
			stmt.setString(6, endereco.getComplemento());
			stmt.setString(7, endereco.getBairro());
			stmt.setLong(8, endereco.getCidade().getId());
			stmt.setString(9, endereco.getReferencia());

			if (endereco.getId() != null) {
				// Update
				stmt.setLong(10, endereco.getId());
			}

			int count = stmt.executeUpdate();
			if (count == 0) {
				throw new SQLException("Erro ao inserir a Endereço");
			}
			// Se inseriu, lê o id auto incremento
			if (endereco.getId() == null) {
				Long id = getGeneratedId(stmt);
				endereco.setId(id);
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

	public boolean delete(Endereco doc) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("DELETE FROM endereco WHERE id=?");
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
			stmt = conn.prepareStatement("DELETE FROM endereco WHERE idEntidade=?");
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

	public List<Endereco> list(Long idEntidade) throws SQLException {
		List<Endereco> enderecos = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM endereco WHERE idEntidade=?");
			stmt.setLong(1, idEntidade);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Endereco e = create(rs);
				enderecos.add(e);
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
		return enderecos;
	}

	public Endereco create(ResultSet rs) throws SQLException {
		Endereco endereco = new Endereco();
		CidadeService cs = new CidadeService();
		
		endereco.setId(rs.getLong("id"));
		endereco.setIdEntidade(rs.getLong("idEntidade"));
		endereco.setPrincipal(rs.getLong("Principal")==1? true : false);
		endereco.setCep(rs.getString("Cep"));
		endereco.setLogradouro(rs.getString("Logradouro"));
		endereco.setNumero(rs.getString("Numero"));
		endereco.setComplemento(rs.getString("Complemento"));
		endereco.setBairro(rs.getString("Bairro"));
		endereco.setCidade(cs.getById(rs.getLong("IdCidade")));
		endereco.setReferencia(rs.getString("Referencia"));

		return endereco;
	}
}
