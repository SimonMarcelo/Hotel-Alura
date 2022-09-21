package ar.com.hotelalura.pruebas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ar.com.hotelalura.factory.ConnectionFactory;

public class PuebaDelete {

	public static void main(String[] args) throws SQLException {

		Connection con = new ConnectionFactory().recuperaConexion();

		Statement statement = con.createStatement();
		
		statement.execute("select * from v_huespedes_reservas;");
		
		ResultSet resultSet = statement.getResultSet();
		
		System.out.println(statement.getUpdateCount());
		System.out.println(resultSet);
		
	}

}
