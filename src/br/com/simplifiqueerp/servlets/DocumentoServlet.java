package br.com.simplifiqueerp.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.simplifiqueerp.entidade.Documento;
import br.com.simplifiqueerp.service.DocumentoService;
import br.com.simplifiqueerp.util.RegexUtil;

@WebServlet("/documentos/*")
public class DocumentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DocumentoService documentoService = new DocumentoService();

	// ---------------- HTTP DELETE ----------------
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");		
		String requestUri = request.getRequestURI();
		Long id = RegexUtil.getIdFromURI("documentos", requestUri);
		Documento doc = new Documento();
		doc.setId(id);	

		if (id != null) {
			// Informou um id
			documentoService.delete(doc);
		} else {
			response.sendError(404, "Url inválida");
		}
	}
}