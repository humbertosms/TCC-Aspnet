package br.com.simplifiqueerp.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataUtil {
	public static LocalDate strToLocalDate(String data) {
		// Conversão de string de data no formato de datas utilizado pelo
		// sistema: java.time.LocalDate
		try {

			DateTimeFormatter formatter;

			// Testa o formato da data (procura o caractere "-" para saber o formato da data)
			int Result = data.indexOf("-");

			if (Result != -1) {
				// Formato: yyyy-MM-dd
				formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			} else {
				// Formato: dd/MM/yyyy
				formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			}

			LocalDate date = LocalDate.parse(data, formatter);
			return date;
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static LocalDate getDataAtual(){
		return LocalDate.now();
	}
}
