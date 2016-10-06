package br.com.simplifiqueerp.persistencia;

import br.com.simplifiqueerp.entidade.DadoBancario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DadoBancarioDAO  extends GenericDAO {

	public void save(DadoBancario conta) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			if (conta.getId() == null) {
				stmt = conn.prepareStatement("INSERT INTO dadoBancario (idEntidade, Banco, Agencia, AgenciaDigito, "
						+ "Conta, ContaDigito) VALUES(?,?,?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
			} else {
				stmt = conn.prepareStatement("UPDATE dadoBancario SET idEntidade=?, Banco=?, Agencia=?, AgenciaDigito=?, "
						+ "Conta=?, ContaDigito=?" + "WHERE id=?");
			}
			stmt.setLong(1, conta.getIdEntidade());
			stmt.setString(2, conta.getBanco());
			stmt.setString(3, conta.getAgencia());
			stmt.setString(4, conta.getAgenciaDigito());
			stmt.setString(5, conta.getConta());
			stmt.setString(6, conta.getContaDigito());

			if (conta.getId() != null) {
				// Update
				stmt.setLong(7, conta.getId());
			}

			int count = stmt.executeUpdate();
			if (count == 0) {
				throw new SQLException("Erro ao inserir o dado bancário");
			}
			// Se inseriu, lê o id auto incremento
			if (conta.getId() == null) {
				Long id = getGeneratedId(stmt);
				conta.setId(id);
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

	public boolean delete(DadoBancario conta) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("DELETE FROM dadoBancario WHERE id=?");
			stmt.setLong(1, conta.getId());
			
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
			stmt = conn.prepareStatement("DELETE FROM dadoBancario WHERE idEntidade=?");
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

	public List<DadoBancario> list(Long idEntidade) throws SQLException {
		List<DadoBancario> dadosbancarios = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM dadoBancario WHERE idEntidade=?");
			stmt.setLong(1, idEntidade);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				DadoBancario e = create(rs);
				dadosbancarios.add(e);
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
		return dadosbancarios;
	}

	public DadoBancario create(ResultSet rs) throws SQLException {
		DadoBancario conta = new DadoBancario();

		conta.setId(rs.getLong("id"));
		conta.setIdEntidade(rs.getLong("idEntidade"));
		conta.setBanco(rs.getString("Banco"));
		conta.setAgencia(rs.getString("Agencia"));
		conta.setAgenciaDigito(rs.getString("AgenciaDigito"));
		conta.setConta(rs.getString("Conta"));
		conta.setContaDigito(rs.getString("ContaDigito"));
		return conta;
	}
}