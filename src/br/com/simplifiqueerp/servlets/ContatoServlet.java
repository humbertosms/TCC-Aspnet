package br.com.simplifiqueerp.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.simplifiqueerp.entidade.Contato;
import br.com.simplifiqueerp.service.ContatoService;
import br.com.simplifiqueerp.util.RegexUtil;

@WebServlet("/contatos/*")
public class ContatoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ContatoService contatoService = new ContatoService();

	// ---------------- HTTP DELETE ----------------
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String requestUri = request.getRequestURI();
		Long id = RegexUtil.getIdFromURI("contatos", requestUri);
		Contato contato = new Contato();
		contato.setId(id);	

		if (id != null) {
			// Informou um id
			contatoService.delete(contato);
		} else {
			response.sendError(404, "Url inválida");
		}
	}
}