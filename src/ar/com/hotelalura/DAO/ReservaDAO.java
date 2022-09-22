package ar.com.hotelalura.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ar.com.hotelalura.factory.ConnectionFactory;
import ar.com.hotelalura.modelo.Reserva;

public class ReservaDAO {

	final private Connection con;

	public ReservaDAO(Connection con) {
		this.con = con;
	}

	public void guardar(Reserva reserva) {

		try {
			final PreparedStatement statement = con.prepareStatement(
					"INSERT INTO RESERVAS (Fecha_entrada, Fecha_salida, Valor, Forma_Pago) VALUES (?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			try (statement) {
				ejecutaRegistro(reserva, statement);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	private void ejecutaRegistro(Reserva reserva, PreparedStatement statement) throws SQLException {

		statement.setDate(1, reserva.getCheckin()); 
		statement.setDate(2, reserva.getCheckout());
		statement.setString(3, reserva.getValorReserva());
		statement.setString(4, reserva.getFormaPago());

		statement.execute();

		final ResultSet resultSet = statement.getGeneratedKeys();

		try (resultSet) {

			while (resultSet.next()) {
				reserva.setId(resultSet.getInt(1));
				System.out.println(String.format("Fue guardada la reserva %s", reserva));

			}
		}
	}

	public List<Reserva> listarReservas() {

		List<Reserva> resultado = new ArrayList<>();

		final Connection con = new ConnectionFactory().recuperaConexion();

		try (con) {

			final PreparedStatement statement = con
					.prepareStatement("select Id, Fecha_entrada, Fecha_salida, Valor, Forma_Pago from reservas");

			try (statement) {

				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {
					while (resultSet.next()) {
						Reserva fila = new Reserva(resultSet.getInt("Id"), resultSet.getDate("Fecha_entrada"),
								resultSet.getDate("Fecha_salida"), resultSet.getString("Valor"),
								resultSet.getString("Forma_Pago"));

						resultado.add(fila);

					}
				}
			}
			return resultado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int eliminar(Integer id) {
		try {
			final PreparedStatement statement = con.prepareStatement("DELETE FROM reservas WHERE ID = ?");

			try (statement) {
				statement.setInt(1, id);
				statement.execute();

				int updateCount = statement.getUpdateCount();

				return updateCount;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "NO PUEDE ELIMINAR UNA RESERVA QUE TENGA ASOCIADOS HUÉSPEDES");
			return 0;
		}
	}

	public int modificar(Date fecha_entrada, Date fecha_salida, String valor, String forma_Pago, Integer id) {
		try {
			final PreparedStatement statement = con.prepareStatement("UPDATE RESERVAS SET " + " FECHA_ENTRADA = ?, "
					+ " FECHA_SALIDA = ?," + " VALOR = ?," + " FORMA_PAGO = ?" + " WHERE ID = ?");

			try (statement) {
				statement.setDate(1, (java.sql.Date) fecha_entrada);
				statement.setDate(2, (java.sql.Date) fecha_salida);
				statement.setString(3, valor);
				statement.setString(4, forma_Pago);
				statement.setInt(5, id);
				System.out.println(statement);
				statement.execute();

				int updateCount = statement.getUpdateCount();

				return updateCount;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Reserva> listarReservas(String busqueda) {
		List<Reserva> resultado = new ArrayList<>();

		final Connection con = new ConnectionFactory().recuperaConexion();

		try (con) {

			var querySelect = "select ID, FECHA_ENTRADA, FECHA_SALIDA, VALOR, FORMA_PAGO from RESERVAS WHERE Id = ?";

			final PreparedStatement statement = con.prepareStatement(querySelect);

			try (statement) {

				statement.setString(1, busqueda);

				statement.execute();

				final ResultSet resultSet = statement.getResultSet();

				try (resultSet) {
					while (resultSet.next()) {
						Reserva fila = new Reserva(resultSet.getInt("ID"), resultSet.getDate("FECHA_ENTRADA"),
								resultSet.getDate("FECHA_SALIDA"), resultSet.getString("VALOR"),
								resultSet.getString("FORMA_PAGO"));

						resultado.add(fila);
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return resultado;
	}

}
