package ar.com.hotelalura.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

	private DataSource dataSource;

	public ConnectionFactory() {
		var comboPooledDataSource = new ComboPooledDataSource();
		comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost/hotel_alura?useTimeZone=true&serverTimeZone=UTC");
		comboPooledDataSource.setUser("root");
		comboPooledDataSource.setPassword("root");
		comboPooledDataSource.setMaxPoolSize(10);

		this.dataSource = comboPooledDataSource;
	}

	public Connection recuperaConexion() {
		try {
			return this.dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	/*public static void main(String[] args) {
		try {
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost/hotel_alura?useTimeZone=true&serverTimeZone=UTC", "root", "root");
			
			PreparedStatement statement = con.prepareStatement("Select * from huespedes");
			
			statement.execute();
			
			System.out.println("Cerrando conexion");
			
			con.close();
		} catch (SQLException e) {
			System.out.println("No se conectó");
		}
	}*/

}