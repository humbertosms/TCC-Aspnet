package br.com.simplifiqueerp.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.simplifiqueerp.entidade.Produto;
import br.com.simplifiqueerp.service.ProdutoService;

import br.com.simplifiqueerp.util.DataUtil;
import br.com.simplifiqueerp.util.RegexUtil;
import br.com.simplifiqueerp.util.NumerosUtil;

@WebServlet("/produtos/*")
public class ProdutoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ProdutoService produtoService = new ProdutoService();

	// ---------------- HTTP GET ----------------
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");

		String requestUri = req.getRequestURI();
		Long id = RegexUtil.getIdFromURI("produtos", requestUri);

		if (requestUri.indexOf("novo") != -1){
			Produto produto = new Produto();
			req.setAttribute("objProduto", produto);
			req.getRequestDispatcher("/paginas/produtos/cadastro.jsp").forward(req, resp);
			return;
		}

		if (id != null) {
			// Informou um id
			Produto produto = produtoService.getById(id);

			if (produto != null) {

				req.setAttribute("objProduto", produto);				
				
				req.getRequestDispatcher("/paginas/produtos/cadastro.jsp").forward(req, resp);
			} else {
				resp.sendError(404, "Produto não encontrada");
			}

		} else {

			List<Produto> lstProdutos = produtoService.list();

			req.setAttribute("lstProdutos", lstProdutos);			
			req.getRequestDispatcher("/paginas/produtos/lista.jsp").forward(req, resp);
		}
	}

	// ---------------- HTTP POST ----------------
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");

		// Cria o produto
		Produto produto = getProdutoFromRequest(request);
		
		// Salva o produto
		produtoService.save(produto);

		request.setAttribute("objProduto", produto);		
		request.getRequestDispatcher("/paginas/produtos/cadastro.jsp").forward(request, resp);

	}

	// ---------------- HTTP DELETE ----------------
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");

		String requestUri = req.getRequestURI();
		Long id = RegexUtil.getIdFromURI("produtos", requestUri);
		Produto produto = new Produto();
		produto.setId(id);

		if (id != null) {
			// Informou um id
			produtoService.delete(produto);
		} else {
			resp.sendError(404, "Url inválida");
		}
	}

	// Cria um produto com os parâmetros do request
	private Produto getProdutoFromRequest(HttpServletRequest req) throws ServletException {
		Produto prod = new Produto();
		String id = req.getParameter("id");

		if (id != null && id != "") {
			// Se informou o id, busca o objeto do banco de dados
			prod = produtoService.getById(Long.parseLong(id));
		}
		prod.setNome(req.getParameter("nome"));
		prod.setDescricao(req.getParameter("descricao"));
		prod.setLocalizacao(req.getParameter("localizacao"));
		prod.setObservacao(req.getParameter("observacao"));
		prod.setCadastro(DataUtil.strToLocalDate(req.getParameter("cadastro")));			
		prod.setValidade(NumerosUtil.toInt(req.getParameter("validade")));
		prod.setComissao(NumerosUtil.toDouble(req.getParameter("comissao")));

		return prod;
	}
}