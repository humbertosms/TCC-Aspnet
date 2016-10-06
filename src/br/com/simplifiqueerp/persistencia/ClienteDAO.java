package br.com.simplifiqueerp.persistencia;

import br.com.simplifiqueerp.entidade.Cliente;
import br.com.simplifiqueerp.entidade.Entidade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO extends GenericDAO {

	public void save(Cliente c) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			if (c.getId() == null) {
				stmt = conn.prepareStatement("INSERT INTO cliente (idEntidade, Credito) VALUES(?,?)",
						Statement.RETURN_GENERATED_KEYS);
			} else {
				stmt = conn.prepareStatement("UPDATE cliente SET idEntidade=?, Nascimento=?, Credito=? " + "WHERE id=?");
			}
			stmt.setLong(1, c.getEntidade().getId());
			stmt.setDouble(2, c.getCredito());

			if (c.getId() != null) {
				// Update
				stmt.setLong(3, c.getId());
			}

			int count = stmt.executeUpdate();
			if (count == 0) {
				throw new SQLException("Erro ao inserir a Cliente");
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
			stmt = conn.prepareStatement("DELETE FROM cliente WHERE id=?");
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
			stmt = conn.prepareStatement("DELETE FROM cliente WHERE idEntidade=?");
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
	
	public Cliente getById(Long id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM cliente WHERE id=?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Cliente cliente = create(rs);
				rs.close();
				return cliente;
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

	public Cliente getClienteByIdEntidade(Long id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM cliente WHERE idEntidade=?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Cliente cliente = create(rs);
				rs.close();
				return cliente;
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
	
	public List<Cliente> getClientes() throws SQLException {
		List<Cliente> clientes = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM cliente");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Cliente e = create(rs);
				clientes.add(e);
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
		return clientes;
	}

	public Cliente create(ResultSet rs) throws SQLException {
		Cliente cliente = new Cliente();
		cliente.setId(rs.getLong("id"));
		// Seta o objeto entidade que h� dentro do cliente
		EntidadeDAO entidadeDAO = new EntidadeDAO();
		cliente.setEntidade(entidadeDAO.getById(rs.getLong("idEntidade")));
		cliente.setCredito(rs.getDouble("Credito"));
		return cliente;
	}
}
