package br.com.simplifiqueerp.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GenericDAO extends BaseDAO{

	// id gerado com o campo auto incremento
	public static Long getGeneratedId(Statement stmt) throws SQLException {
		ResultSet rs = stmt.getGeneratedKeys();
		if (rs.next()) {
			Long id = rs.getLong(1);
			return id;
		}
		return 0L;
	}
}
