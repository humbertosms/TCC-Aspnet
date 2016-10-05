package br.com.simplifiqueerp.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.simplifiqueerp.entidade.Fornecedor;
import br.com.simplifiqueerp.service.FornecedorService;

@WebServlet("/fornecedores/*")
public class FornecedorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FornecedorService fornecedorService = new FornecedorService();

	// ---------------- HTTP GET ----------------
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Fornecedor> fornecedores = new ArrayList<>();

		fornecedores = fornecedorService.list();
		req.setAttribute("lstFornecedores", fornecedores);
	}
}