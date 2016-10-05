package br.com.simplifiqueerp.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.simplifiqueerp.entidade.Endereco;
import br.com.simplifiqueerp.service.EnderecoService;
import br.com.simplifiqueerp.util.RegexUtil;

@WebServlet("/enderecos/*")
public class EnderecoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EnderecoService enderecoService = new EnderecoService();

	// ---------------- HTTP DELETE ----------------
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");		
		String requestUri = request.getRequestURI();
		Long id = RegexUtil.getIdFromURI("enderecos", requestUri);
		Endereco endereco = new Endereco();
		endereco.setId(id);	

		if (id != null) {
			// Informou um id
			enderecoService.delete(endereco);
		} else {
			response.sendError(404, "Url inválida");
		}
	}
}