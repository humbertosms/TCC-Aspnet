package br.com.simplifiqueerp.persistencia;

import br.com.simplifiqueerp.entidade.Contato;
import br.com.simplifiqueerp.entidade.DadoBancario;
import br.com.simplifiqueerp.entidade.Documento;
import br.com.simplifiqueerp.entidade.Endereco;
import br.com.simplifiqueerp.entidade.Entidade;
import br.com.simplifiqueerp.service.ClienteService;
import br.com.simplifiqueerp.service.ColaboradorService;
import br.com.simplifiqueerp.service.ContatoService;
import br.com.simplifiqueerp.service.DadoBancarioService;
import br.com.simplifiqueerp.service.DocumentoService;
import br.com.simplifiqueerp.service.EnderecoService;
import br.com.simplifiqueerp.service.FornecedorService;
import br.com.simplifiqueerp.service.UsuarioService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class EntidadeDAO extends GenericDAO {

	private ClienteService clienteService = new ClienteService();
	private ColaboradorService colaboradorService = new ColaboradorService();
	private FornecedorService fornecedorService = new FornecedorService();
	private UsuarioService usuarioService = new UsuarioService();
	private DocumentoService documentoService = new DocumentoService();
	private ContatoService contatoService = new ContatoService();
	private EnderecoService enderecoService = new EnderecoService();
	private DadoBancarioService dadoBancarioService = new DadoBancarioService();
	
	public void save(Entidade e) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			if (e.getId() == null) {
				stmt = conn.prepareStatement(
						"INSERT INTO Entidade (TipoPessoa, Nome, Apelido, CPF_CNPJ, RG_IE, Nascimento, "
						+ "Telefone, Fax, Celular, Email, Site, Observacao, Cadastro) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)",
						Statement.RETURN_GENERATED_KEYS);
			} else {
				stmt = conn.prepareStatement(
						"UPDATE Entidade SET TipoPessoa=?, Nome=?, Apelido=?, CPF_CNPJ=?, RG_IE=?, Nascimento=?, "
						+ "Telefone=?, Fax=?, Celular=?, Email=?, Site=?, Observacao=?, Cadastro=? WHERE id=?");
			}
			stmt.setString(1, e.getTipoPessoa());
			stmt.setString(2, e.getNome());
			stmt.setString(3, e.getApelido());
			stmt.setString(4, e.getCpf_cnpj());
			stmt.setString(5, e.getRg_ie());
			if (e.getNascimento() == null) {
				stmt.setNull(6, Types.DATE);
			} else {
				stmt.setDate(6, java.sql.Date.valueOf(e.getNascimento()));
			}			
			stmt.setString(7, e.getTelefone());
			stmt.setString(8, e.getFax());
			stmt.setString(9, e.getCelular());
			stmt.setString(10, e.getEmail());
			stmt.setString(11, e.getSite());			
			stmt.setString(12, e.getObservacao());
			stmt.setDate(13, java.sql.Date.valueOf(e.getCadastro()));

			if (e.getId() != null) {
				// Update
				stmt.setLong(14, e.getId());
			}

			int count = stmt.executeUpdate();
			if (count == 0) {
				throw new SQLException("Erro ao inserir a Entidade");
			}

			// Se inseriu, lê o id auto incremento
			if (e.getId() == null) {
				Long id = getGeneratedId(stmt);
				e.setId(id);
			}
			
			// Salva os documentos da entidade
			List<Documento> documentos = e.getDocumentos();
			if (documentos != null) {
				for (Documento documento : documentos) {
					documento.setIdEntidade(e.getId());
					documentoService.save(documento);
				}
			}
			
			// Salva os contatos da entidade
			List<Contato> contatos = e.getContatos();
			if (contatos != null) {
				for (Contato contato : contatos) {
					contato.setIdEntidade(e.getId());
					contatoService.save(contato);
				}
			}
			
			// Salva os endereços da entidade
			List<Endereco> enderecos = e.getEnderecos();
			if (enderecos != null) {
				for (Endereco endereco : enderecos) {
					endereco.setIdEntidade(e.getId());
					enderecoService.save(endereco);
				}
			}
			
			// Salva os dados bancários da entidade
			List<DadoBancario> dadosbancarios = e.getDadosBancarios();
			if (dadosbancarios != null) {
				for (DadoBancario dadobancario : dadosbancarios) {
					dadobancario.setIdEntidade(e.getId());
					dadoBancarioService.save(dadobancario);
				}
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

	public boolean delete(Entidade e) throws SQLException {

		Connection conn = null;
		PreparedStatement stmt = null;
		try {

			conn = getConnection();

			// Desabilita o commit automático até excluir todas as entidades
			// relacionadas
			conn.setAutoCommit(false);

			// Exclúi as entidades filhas
			clienteService.deleteFromEntidade(e);
			colaboradorService.deleteFromEntidade(e);
			fornecedorService.deleteFromEntidade(e);
			usuarioService.deleteFromEntidade(e);
			documentoService.deleteFromIdEntidade(e.getId());
			enderecoService.deleteFromIdEntidade(e.getId());
			contatoService.deleteFromIdEntidade(e.getId());
			dadoBancarioService.deleteFromIdEntidade(e.getId());
			
			stmt = conn.prepareStatement("DELETE FROM Entidade WHERE id=?");
			stmt.setLong(1, e.getId());

			int count = stmt.executeUpdate();
			boolean ok = count > 0;

			conn.commit();

			// Habilita novamente o commit automático, pois é o padrão do
			// restante do sistema
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

	public Entidade getById(Long id) throws SQLException {
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM Entidade WHERE id=?");
			stmt.setLong(1, id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				Entidade entidade = create(rs,false);
				rs.close();
				return entidade;
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

	public List<Entidade> list() throws SQLException {
		List<Entidade> entidades = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM Entidade");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Entidade e = create(rs,false);
				entidades.add(e);
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
		return entidades;
	}


	public List<Entidade> listagem() throws SQLException {
		List<Entidade> entidades = new ArrayList<>();
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = getConnection();
			stmt = conn.prepareStatement("SELECT * FROM Entidade");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Entidade e = create(rs,true);
				entidades.add(e);
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
		return entidades;
	}

	public Entidade create(ResultSet rs, Boolean listagem) throws SQLException {
		// listagem - Para geração de tabelas e relatórios, não recupera registros dependentes
		
		Entidade entidade = new Entidade();
		entidade.setId(rs.getLong("id"));
		entidade.setTipoPessoa(rs.getString("TipoPessoa"));
		entidade.setNome(rs.getString("Nome"));
		entidade.setApelido(rs.getString("Apelido"));
		entidade.setCpf_cnpj(rs.getString("CPF_CNPJ"));
		entidade.setRg_ie(rs.getString("RG_IE"));
		if (rs.getDate("Nascimento") == null) {
			entidade.setNascimento(null);
		} else {
			entidade.setNascimento(rs.getDate("Nascimento").toLocalDate());
		}
		entidade.setTelefone(rs.getString("Telefone"));
		entidade.setFax(rs.getString("Fax"));
		entidade.setCelular(rs.getString("Celular"));
		entidade.setEmail(rs.getString("Email"));
		entidade.setSite(rs.getString("Site"));		
		entidade.setObservacao(rs.getString("Observacao"));
		entidade.setCadastro(rs.getDate("Cadastro").toLocalDate());

		if (!listagem){
		// Nas montagens de listas e relatórios, não são necessários os registros dependentes
			entidade.setDocumentos(documentoService.getDocumentos(entidade.getId()));
			entidade.setContatos(contatoService.list(entidade.getId()));
			entidade.setEnderecos(enderecoService.getEnderecos(entidade.getId()));
			entidade.setDadosBancarios(dadoBancarioService.getDadoBancarios(entidade.getId()));
		}

		return entidade;
	}
}
