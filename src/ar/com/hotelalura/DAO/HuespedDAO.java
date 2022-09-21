package ar.com.hotelalura.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ar.com.hotelalura.modelo.Huesped;
import ar.com.hotelalura.factory.ConnectionFactory;

public class HuespedDAO {

	private Connection con;

	public HuespedDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Huesped huesped, int reserva) {

		try {
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO HUESPEDES (nombre, apellido, fecha_de_nacimiento, nacionalidad, telefono, id_reserva) VALUES (?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				ejecutaRegistro(huesped, reserva, statement);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void ejecutaRegistro(Huesped huesped, int reserva, PreparedStatement statement) throws SQLException {

		statement.setString(1, huesped.getNombre());
		statement.setString(2, huesped.getApellido());
		statement.setDate(3, huesped.getFechaNacimiento());
		statement.setString(4, huesped.getNacionalidad());
		statement.setInt(5, huesped.getTelefono());
		statement.setInt(6, (reserva));

		statement.execute();

		final ResultSet resultSet = statement.getGeneratedKeys();

		try (resultSet) {

			while (resultSet.next()) {
				huesped.setId(resultSet.getInt(1));
				System.out.println(String.format("Fue guardado el huesped en la reserva %s", reserva));

			}
		}
	}

	public List<Huesped> listarHuespedes() {
		List<Huesped> resultado = new ArrayList<>();
		try {
			var querySelect = "SELECT ID, NOMBRE, APELLIDO, FECHA_DE_NACIMIENTO, NACIONALIDAD, TELEFONO, ID_RESERVA FROM HUESPEDES";
			System.out.println(querySelect);

			final PreparedStatement statement = con.prepareStatement(querySelect);

			try (statement) {

				final ResultSet resultSet = statement.executeQuery();

				try (resultSet) {
					while (resultSet.next()) {
						var huesped = new Huesped(resultSet.getInt("Id"), resultSet.getString("Nombre"),
								resultSet.getString("Apellido"), resultSet.getDate("Fecha_de_Nacimiento"),
								resultSet.getString("Nacionalidad"), resultSet.getInt("Telefono"),
								resultSet.getInt("Id_reserva"));
						resultado.add(huesped);
					}
				}
				;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return resultado;
	}

	public List<Huesped> listarHuespedes(String busqueda) {
		List<Huesped> resultado = new ArrayList<>();

		final Connection con = new ConnectionFactory().recuperaConexion();

		try (con) {

			var querySelect = "select ID, NOMBRE, APELLIDO, FECHA_DE_NACIMIENTO, NACIONALIDAD, TELEFONO, ID_RESERVA from V_HUESPEDES_RESERVAS WHERE nombre = ? or apellido = ? or Busqueda = ?";

			final PreparedStatement statement = con.prepareStatement(querySelect);

			try (statement) {

				statement.setString(1, busqueda);
				statement.setString(2, busqueda);
				statement.setString(3, busqueda);

				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {
					while (resultSet.next()) {
						Huesped fila = new Huesped(resultSet.getInt("ID"), resultSet.getString("NOMBRE"),
								resultSet.getString("APELLIDO"), resultSet.getDate("FECHA_DE_NACIMIENTO"),
								resultSet.getString("NACIONALIDAD"), resultSet.getInt("TELEFONO"),
								resultSet.getInt("ID_RESERVA"));

						resultado.add(fila);

					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return resultado;
	}

	public int eliminarHuesped(Integer id) {
		try {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM HUESPEDES WHERE ID = ?");

			try (statement) {
				statement.setInt(1, id);
				statement.execute();

				int updateCount = statement.getUpdateCount();

				return updateCount;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int modificarHuesped(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, int telefono,
			int id_reserva, Integer id) {
		try {
			final PreparedStatement statement = con.prepareStatement(
					"UPDATE HUESPEDES SET " + " NOMBRE = ?, " + " APELLIDO = ?," + " FECHA_DE_NACIMIENTO = ?,"
							+ " NACIONALIDAD = ?," + " TELEFONO = ?," + " ID_RESERVA = ?" + " WHERE ID = ?");

			try (statement) {
				statement.setString(1, nombre);
				statement.setString(2, apellido);
				statement.setDate(3, fechaNacimiento);
				statement.setString(4, nacionalidad);
				statement.setInt(5, telefono);
				statement.setInt(6, id_reserva);
				statement.setInt(7, id);
				statement.execute();

				int updateCount = statement.getUpdateCount();

				return updateCount;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
