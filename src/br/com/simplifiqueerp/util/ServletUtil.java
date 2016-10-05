package br.com.simplifiqueerp.util;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletResponse;

public class ServletUtil {
	public static void writeXML(HttpServletResponse response, String xml) throws IOException {
		// Escreve o XML na response do servlet com application/xml
		if (xml != null) {
			PrintWriter writer = response.getWriter();
			response.setContentType("application/xml;charset=UTF-8");
			writer.write(xml);
			writer.close();
		}
	}

	public static void writeJSON(HttpServletResponse response, String json) throws IOException {
		// Escreve o JSON na response do servlet com application/json
		if (json != null) {
			PrintWriter writer = response.getWriter();
			response.setContentType("application/json;charset=UTF-8");
			writer.write(json);
			writer.close();
		}
	}
}
