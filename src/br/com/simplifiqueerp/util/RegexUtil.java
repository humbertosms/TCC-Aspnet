package br.com.simplifiqueerp.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;

public class RegexUtil {

	// Verificar se a URL está no padrão "/recurso/id" e retorna o id
	public static Long getIdFromURI(String recurso, String requestUri) throws ServletException {

		Pattern regexById = Pattern.compile("/" + recurso + "/([0-9]*)");

		// Verifica o ID
		Matcher matcher = regexById.matcher(requestUri);
		if (matcher.find() && matcher.groupCount() > 0) {
			String s = matcher.group(1);
			if (s != null && s.trim().length() > 0) {
				Long id = Long.parseLong(s);
				return id;
			}
		}
		return null;
	}
}
