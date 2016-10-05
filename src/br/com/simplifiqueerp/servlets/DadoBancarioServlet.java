package br.com.simplifiqueerp.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.simplifiqueerp.entidade.DadoBancario;
import br.com.simplifiqueerp.service.DadoBancarioService;
import br.com.simplifiqueerp.util.RegexUtil;

@WebServlet("/dadosbancarios/*")
public class DadoBancarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DadoBancarioService dadobancarioService = new DadoBancarioService();

	// ---------------- HTTP DELETE ----------------
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");		
		String requestUri = request.getRequestURI();
		Long id = RegexUtil.getIdFromURI("dadosbancarios", requestUri);
		DadoBancario conta = new DadoBancario();
		conta.setId(id);		

		if (id != null) {
			// Informou um id
			dadobancarioService.delete(conta);
		} else {
			response.sendError(404, "Url inválida");
		}
	}
}