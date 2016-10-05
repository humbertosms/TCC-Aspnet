package br.com.simplifiqueerp.persistencia;

import br.com.simplifiqueerp.entidade.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO extends GenericDAO {

	public void save(Produto p) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			if (p.getId() == null) {
				stmt = conn.prepareStatement(
						"INSERT INTO Produto (Nome, Descricao, Localizacao, Observacao, Cadastro, Validade, Comissao) "
					  + "VALUES(?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			} else {
				stmt = conn.prepareStatement(
						"UPDATE Produto SET Nome=?, Descricao=?, Localizacao=?, Observacao=?, Cadastro=?, Validade=?, "
					  + "Comissao=? WHERE id=?");
			}
			stmt.setString(1, p.getNome());
			stmt.setString(2, p.getDescricao());
			stmt.setString(3, p.getLocalizacao());
			stmt.setString(4, p.getObservacao());
			if (p.getCadastro() == null) {
				stmt.setNull(5, Types.DATE);
			} else {
				stmt.setDate(5, java.sql.Date.valueOf(p.getCadastro()));
			}
			stmt.setLong(6, p.getValidade());
			stmt.setDouble(7, p.getComissao());

			if (p.getId() != null) {
				// Update
				stmt.setLong(8, p.getId());
			}

			int count = stmt.executeUpdate();
			if (count == 0) {
				throw new SQLException("Erro ao inserir o Produto");
			}

			// Se inseriu, lê o id auto incremento
			if (p.getId() == null) {
				Long id = getGeneratedId(stmt);
				p.setId(id);
			}

/*
			// Salva os documentos do produto
			List<Documento> documentos = p.getDocumentos();
			if (documentos != null) {
				for (Documento documento : documentos) {
					documento.setIdProduto(p.getId());
					documentoService.save(documento);
				}
			}

			// Salva os contatos da produto
			List<Contato> contatos = p.getContatos();
			if (contatos != null) {
				for (Contato contato : contatos) {
					contato.setIdProduto(p.getId());
					contatoService.save(contato);
				}
			}

			// Salva os endereços da produto
			List<Endereco> enderecos = p.getEnderecos();
			if (enderecos != null) {
				for (Endereco endereco : enderecos) {
					endereco.setIdProduto(p.getId());
					enderecoService.save(endereco);
				}
			}

			// Salva os dados bancários da produto
			List<DadoBancario> dadosbancarios = p.getDadosBancarios();
			if (dadosbancarios != null) {
				for (DadoBancario dadobancario : dadosbancarios) {
					dadobancario.setIdProduto(p.getId());
					dadoBancarioService.save(dadobancario);
				}
			}
*/
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	public boolean delete(Produto e) throws SQLException {

		Connection conn = null;
		PreparedStatement stmt = null;
		try {

			conn = getConnection();

			// Desabilita o commit automático até excluir todas as entidades relacionadas
			conn.setAutoCommit(false);
/*
			// Exclúi as entidades filhas
			clienteService.deleteFromProduto(e);
			colaboradorService.deleteFromProduto(e);
			fornecedorService.deleteFromProduto(e);
			usuarioService.deleteFromProduto(e);
			documentoService.deleteFromIdProduto(e.getId());
			enderecoService.deleteFromIdProduto(e.getId());
			contatoService.deleteFromIdProduto(e.getId());
			dadoBancarioService.deleteFromIdProduto(e.getId());
*/
			stmt = conn.prepareStatement("DELETE FROM Produto WHERE id=?");
			stmt.setLong(1, e.getId());

			int count = stmt.executeUpdate();
			boolean ok = count > 0;

			conn.commit();

			// Habilita novamente o commit automático, pois é o padrão do restante do sistema
			conn.setAutoCommit(true);

			return ok;
		} catch (Exception exc) {
			conn.rollback();
			return false;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	public Produto getById(Long id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM Produto WHERE id=?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Produto produto = create(rs);
				rs.close();
				return produto;
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

	public List<Produto> list() throws SQLException {
		List<Produto> produtos = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM Produto");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Produto e = create(rs);
				produtos.add(e);
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
		return produtos;
	}

	public Produto create(ResultSet rs) throws SQLException {
		Produto produto = new Produto();
		produto.setId(rs.getLong("id"));
		
		produto.setNome(rs.getString("Nome"));
		produto.setDescricao(rs.getString("Descricao"));
		produto.setLocalizacao(rs.getString("Localizacao"));
		produto.setObservacao(rs.getString("Observacao"));

		if (rs.getDate("Cadastro") == null) {
			produto.setCadastro(null);
		} else {
			produto.setCadastro(rs.getDate("Cadastro").toLocalDate());
		}
		produto.setValidade(rs.getInt("Validade"));
		produto.setComissao(rs.getDouble("Comissao"));

		// TODO: preencher entidades relacionadas
//		produto.setDadosBancarios(dadoBancarioService.getDadoBancarios(produto.getId()));

		return produto;
	}
}
