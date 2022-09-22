package ar.com.hotelalura.controller;

import java.sql.Date;
import java.util.List;

import ar.com.hotelalura.DAO.ReservaDAO;
import ar.com.hotelalura.factory.ConnectionFactory;
import ar.com.hotelalura.modelo.Reserva;

public class ReservaController {

	private ReservaDAO reservaDAO;

	public ReservaController() {
		var factory = new ConnectionFactory();
		this.reservaDAO = new ReservaDAO(factory.recuperaConexion());

	}

	public int modificar(Date fecha_entrada, Date fecha_salida, String valor, String forma_Pago, Integer id) {
		return reservaDAO.modificar(fecha_entrada, fecha_salida, valor, forma_Pago, id);
	}

	public int eliminar(Integer id) {
		return reservaDAO.eliminar(id);
	}

	public List<Reserva> listarReservas() {
		return reservaDAO.listarReservas();
	}

	public List<Reserva> listarReservas(String busqueda) {
		return reservaDAO.listarReservas(busqueda);
	}
	
	public void guardar(Reserva reserva) {
		reservaDAO.guardar(reserva);
	}

}
