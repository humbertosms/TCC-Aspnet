package br.com.simplifiqueerp.servlets;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.simplifiqueerp.entidade.Cidade;
import br.com.simplifiqueerp.service.CidadeService;

@WebServlet("/cidades/*")
public class CidadeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private CidadeService cidadeService = new CidadeService();

	private Gson gson;
	private Gson getGson() {
		
		if (gson == null) {
			gson = new GsonBuilder().setPrettyPrinting().create();
		}
		return gson;
	}
	
	// ---------------- HTTP GET ----------------
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String requestUri = request.getRequestURI();
		String uf = requestUri.replace("/Simplifique_ERP/cidades/", "").toUpperCase();
		
		List<Cidade> cidades = null;
		
		if (uf != null) {
			cidades = cidadeService.list(uf);
		} else {
			response.sendError(404, "Url inválida");
		}		
		
		/* json não formatado
		Gson gson = new Gson();
		String json = gson.toJson(cidades);
		*/

		// json formatado
		String json = getGson().toJson(cidades);
		// Escreve o json gerado na resposta http
		Writer writer = null;
		writer = response.getWriter();
		writer.write(json);
	}
}