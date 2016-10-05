package br.com.simplifiqueerp.util;

public class NumerosUtil {

	public static int toInt(Object numero){
		int retorno = (int) 0;
		try {
			retorno = (int) Integer.parseInt((String) numero);
		} catch (Exception e) {
			return retorno;
		}
		return retorno;
	}

	public static Long toLong(Object numero){
		Long retorno = (long) 0;
		try {
			retorno = (long) Double.parseDouble((String) numero);
		} catch (Exception e) {
			return retorno;
		}
		return retorno;
	}

	public static Double toDouble(Object numero){
		double retorno = 0;
		try {
			retorno = Double.parseDouble((String) numero);
		} catch (Exception e) {
			return retorno;
		}
		return retorno;
	}
}