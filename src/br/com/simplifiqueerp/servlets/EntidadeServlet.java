package br.com.simplifiqueerp.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.simplifiqueerp.entidade.Cliente;
import br.com.simplifiqueerp.entidade.Colaborador;
import br.com.simplifiqueerp.entidade.Documento;
import br.com.simplifiqueerp.entidade.Contato;
import br.com.simplifiqueerp.entidade.DadoBancario;
import br.com.simplifiqueerp.entidade.Endereco;
import br.com.simplifiqueerp.entidade.Entidade;
import br.com.simplifiqueerp.entidade.Fornecedor;
import br.com.simplifiqueerp.entidade.Usuario;
import br.com.simplifiqueerp.service.CidadeService;
import br.com.simplifiqueerp.service.ClienteService;
import br.com.simplifiqueerp.service.ColaboradorService;
import br.com.simplifiqueerp.service.EntidadeService;
import br.com.simplifiqueerp.service.FornecedorService;
import br.com.simplifiqueerp.service.UsuarioService;

import br.com.simplifiqueerp.util.DataUtil;
import br.com.simplifiqueerp.util.RegexUtil;

@WebServlet("/entidades/*")
// Se o @WebServlet("/entidades") estiver sem a barra
// (@WebServlet("entidades")), dá erro ao iniciar o TomCat
public class EntidadeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private EntidadeService entidadeService = new EntidadeService();
	private ClienteService clienteService = new ClienteService();
	private ColaboradorService colaboradorService = new ColaboradorService();
	private FornecedorService fornecedorService = new FornecedorService();
	private UsuarioService usuarioService = new UsuarioService();

	// ---------------- HTTP GET ----------------
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");

		String requestUri = req.getRequestURI();
		Long id = RegexUtil.getIdFromURI("entidades", requestUri);

		if (requestUri.indexOf("novo") != -1) {
			// Usuário solicitou um novo cadastro
			Entidade entidade = new Entidade();
			req.setAttribute("objEntidade", entidade);
			req.getRequestDispatcher("/paginas/entidades/cadastro.jsp").forward(req, resp);
		} else if (id != null) {
			// Requisitou uma entidade informando um id
			Entidade entidade = entidadeService.getById(id);

			if (entidade != null) {

				req.setAttribute("objEntidade", entidade);
				req.setAttribute("objCliente", clienteService.getByIdEntidade(entidade.getId()));
				req.setAttribute("objColaborador", colaboradorService.getByIdEntidade(entidade.getId()));
				req.setAttribute("objFornecedor", fornecedorService.getFornecedorByIdEntidade(entidade.getId()));
				req.setAttribute("objUsuario", usuarioService.getUsuarioByIdEntidade(entidade.getId()));

				req.getRequestDispatcher("/paginas/entidades/cadastro.jsp").forward(req, resp);
			} else {
				resp.sendError(404, "Entidade não encontrada");
			}

		} else if (requestUri.indexOf("listagem") != -1) {
			// Listagem simples de entidades
			List<Entidade> lstEntidades = entidadeService.listagem();

			req.setAttribute("lstEntidades", lstEntidades);
			req.getRequestDispatcher("/paginas/entidades/lista.jsp").forward(req, resp);
		} else {
			// Listagem completa (com registros dependentes) de entidades
			List<Entidade> lstEntidades = entidadeService.list();

			req.setAttribute("lstEntidades", lstEntidades);
			req.getRequestDispatcher("/paginas/entidades/lista.jsp").forward(req, resp);
		}
	}

	// ---------------- HTTP POST ----------------
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");

		// Cria a entidade
		Entidade entidade = getEntidadeFromRequest(request);

		Cliente cliente;
		Colaborador colaborador;
		Fornecedor fornecedor;
		Usuario usuario;

		// Salva a entidade
		entidadeService.save(entidade);

		// Se o usuário marcou a opção cliente, cria um cliente vinculado à
		// entidade
		if (request.getParameter("cliente") != null) {
			String id = request.getParameter("idcliente");

			if (id != null && id != "") {
				// Se informou o id, busca o objeto do banco de dados
				cliente = clienteService.getByIdEntidade(Long.parseLong(id));
			} else {
				cliente = new Cliente();
			}

			cliente.setEntidade(entidade);
			String credito = request.getParameter("credito");

			if (credito != "") {
				cliente.setCredito(Double.parseDouble(credito));
			} else {
				cliente.setCredito((double) 0);
			}

			// Salva o cliente
			clienteService.save(cliente);
			request.setAttribute("objCliente", cliente);
		}

		// Se o usuário marcou a opção colaborador, cria um colaborador
		// vinculado à entidade
		if (request.getParameter("colaborador") != null) {
			String id = request.getParameter("idcolaborador");

			if (id != null && id != "") {
				// Se informou o id, busca o objeto do banco de dados
				colaborador = colaboradorService.getByIdEntidade(Long.parseLong(id));
			} else {
				colaborador = new Colaborador();
			}
			colaborador.setEntidade(entidade);

			String comissao = request.getParameter("comissao");
			if (comissao != "") {
				colaborador.setComissao(Double.parseDouble(comissao));
			} else {
				colaborador.setComissao(0);
			}

			// Salva o colaborador
			colaboradorService.save(colaborador);
			request.setAttribute("objColaborador", colaborador);
		}

		// Se o usuário marcou a opção fornecedor, cria um fornecedor vinculado
		// à entidade
		if (request.getParameter("fornecedor") != null) {
			String id = request.getParameter("idfornecedor");

			if (id != null && id != "") {
				// Se informou o id, busca o objeto do banco de dados
				fornecedor = fornecedorService.getById(Long.parseLong(id));
			} else {
				fornecedor = new Fornecedor();
			}
			fornecedor.setEntidade(entidade);

			// Salva o fornecedor
			fornecedorService.save(fornecedor);
			request.setAttribute("objFornecedor", fornecedor);
		}

		// Se o usuário marcou a opção usuário, cria um usuário vinculado à
		// entidade
		if (request.getParameter("usuario") != null) {
			String id = request.getParameter("idusuario");

			if (id != null && id != "") {
				// Se informou o id, busca o objeto do banco de dados
				usuario = usuarioService.getUsuarioById(Long.parseLong(id));
			} else {
				usuario = new Usuario();
			}
			usuario.setEntidade(entidade);
			usuario.setLogin(request.getParameter("login"));
			usuario.setSenha(request.getParameter("senha"));

			// Salva o usuário
			usuarioService.save(usuario);
			request.setAttribute("objUsuario", usuario);
		}

		request.setAttribute("objEntidade", entidade);
		request.getRequestDispatcher("paginas/entidades/cadastro.jsp").forward(request, resp);

	}

	// ---------------- HTTP DELETE ----------------
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");

		String requestUri = req.getRequestURI();
		Long id = RegexUtil.getIdFromURI("entidades", requestUri);
		Entidade ent = new Entidade();
		ent.setId(id);

		if (id != null) {
			// Informou um id
			entidadeService.delete(ent);
		} else {
			resp.sendError(404, "Url inválida");
		}
	}

	// Cria uma entidade com os parâmetros do request
	private Entidade getEntidadeFromRequest(HttpServletRequest req) throws ServletException {
		Entidade e = new Entidade();
		String id = req.getParameter("id");

		if (id != null && id != "") {
			// Se informou o id, busca o objeto do banco de dados
			e = entidadeService.getById(Long.parseLong(id));
		}
		e.setTipoPessoa(req.getParameter("tipopessoa"));
		e.setNome(req.getParameter("nome"));
		e.setApelido(req.getParameter("apelido"));
		e.setCpf_cnpj(req.getParameter("cpf_cnpj"));
		e.setRg_ie(req.getParameter("rg_ie"));
		e.setNascimento(DataUtil.strToLocalDate(req.getParameter("nascimento")));
		e.setTelefone(req.getParameter("telefone"));
		e.setFax(req.getParameter("fax"));
		e.setCelular(req.getParameter("celular"));
		e.setEmail(req.getParameter("email"));
		e.setSite(req.getParameter("site"));
		e.setCadastro(DataUtil.strToLocalDate(req.getParameter("cadastro")));
		e.setObservacao(req.getParameter("observacao"));

		// Obtém os documentos inseridos na tabela
		String[] idDocumento = req.getParameterValues("idDocumento");
		String[] tipoDocumento = req.getParameterValues("tipoDocumento");
		String[] numeroDocumento = req.getParameterValues("numeroDocumento");
		Documento documento;
		List<Documento> docs = new ArrayList<>();

		if (tipoDocumento != null) {
			for (int x = 0; x < tipoDocumento.length; x++) {
				documento = new Documento();
				try {
					documento.setId(Long.parseLong(idDocumento[x]));
				} catch (Exception e2) {
					documento.setId(null);
				}
				documento.setIdEntidade(e.getId());
				documento.setTipo(tipoDocumento[x]);
				documento.setNumero(numeroDocumento[x]);
				docs.add(documento);
			}
			e.setDocumentos(docs);
		}

		// Obtém os contatos inseridos na tabela
		String[] idContato = req.getParameterValues("idContato");
		String[] nomeContato = req.getParameterValues("nomeContato");
		String[] telefoneContato = req.getParameterValues("telefoneContato");
		String[] faxContato = req.getParameterValues("faxContato");
		String[] celularContato = req.getParameterValues("celularContato");
		String[] emailContato = req.getParameterValues("emailContato");

		Contato contato;
		List<Contato> contatos = new ArrayList<>();

		if (nomeContato != null) {
			for (int x = 0; x < nomeContato.length; x++) {
				contato = new Contato();
				try {
					contato.setId(Long.parseLong(idContato[x]));
				} catch (Exception e2) {
					contato.setId(null);
				}
				contato.setIdEntidade(e.getId());
				contato.setNome(nomeContato[x]);
				contato.setTelefone(telefoneContato[x]);
				contato.setFax(faxContato[x]);
				contato.setCelular(celularContato[x]);
				contato.setEmail(emailContato[x]);
				contatos.add(contato);
			}
			e.setContatos(contatos);
		}

		// Obtém os enderecos inseridos na tabela
		String[] idEndereco = req.getParameterValues("idEndereco");
		String[] principalEndereco = req.getParameterValues("principalEndereco");
		String[] cepEndereco = req.getParameterValues("cepEndereco");
		String[] logradouroEndereco = req.getParameterValues("logradouroEndereco");
		String[] numeroEndereco = req.getParameterValues("numeroEndereco");
		String[] complementoEndereco = req.getParameterValues("complementoEndereco");
		String[] bairroEndereco = req.getParameterValues("bairroEndereco");
		String[] idCidadeEndereco = req.getParameterValues("idCidadeEndereco");
		String[] referenciaEndereco = req.getParameterValues("referenciaEndereco");

		Endereco endereco;
		List<Endereco> enderecos = new ArrayList<>();

		if (logradouroEndereco != null) {
			for (int x = 0; x < logradouroEndereco.length; x++) {
				endereco = new Endereco();
				try {
					endereco.setId(Long.parseLong(idEndereco[x]));
				} catch (Exception e2) {
					endereco.setId(null);
				}
				endereco.setIdEntidade(e.getId());
				endereco.setPrincipal(principalEndereco[x].equalsIgnoreCase("Sim") ? true : false);
				endereco.setCep(cepEndereco[x]);
				endereco.setLogradouro(logradouroEndereco[x]);
				endereco.setNumero(numeroEndereco[x]);
				endereco.setComplemento(complementoEndereco[x]);
				endereco.setBairro(bairroEndereco[x]);
				endereco.setCidade(new CidadeService().getById(Long.parseLong(idCidadeEndereco[x])));
				endereco.setReferencia(referenciaEndereco[x]);
				enderecos.add(endereco);
			}
			e.setEnderecos(enderecos);
		}

		// Obtém os dados bancários inseridos na tabela
		String[] idDadoBancario = req.getParameterValues("idDadoBancario");
		String[] bancoDadoBancario = req.getParameterValues("bancoDadoBancario");
		String[] agenciaDadoBancario = req.getParameterValues("agenciaDadoBancario");
		String[] agenciaDigitoDadoBancario = req.getParameterValues("agenciaDigitoDadoBancario");
		String[] contaDadoBancario = req.getParameterValues("contaDadoBancario");
		String[] contaDigitoDadoBancario = req.getParameterValues("contaDigitoDadoBancario");

		DadoBancario dadoBancario;
		List<DadoBancario> dadosBancarios = new ArrayList<>();

		if (contaDadoBancario != null) {
			for (int x = 0; x < bancoDadoBancario.length; x++) {
				dadoBancario = new DadoBancario();
				try {
					dadoBancario.setId(Long.parseLong(idDadoBancario[x]));
				} catch (Exception e2) {
					dadoBancario.setId(null);
				}
				dadoBancario.setIdEntidade(e.getId());
				dadoBancario.setBanco(bancoDadoBancario[x]);
				dadoBancario.setAgencia(agenciaDadoBancario[x]);
				dadoBancario.setAgenciaDigito(agenciaDigitoDadoBancario[x]);
				dadoBancario.setConta(contaDadoBancario[x]);
				dadoBancario.setContaDigito(contaDigitoDadoBancario[x]);
				dadosBancarios.add(dadoBancario);
			}
			e.setDadosBancarios(dadosBancarios);
		}

		return e;
	}
}
