package ar.com.hotelalura.controller;

import java.sql.Date;
import java.util.List;

import ar.com.hotelalura.DAO.HuespedDAO;
import ar.com.hotelalura.factory.ConnectionFactory;
import ar.com.hotelalura.modelo.Huesped;

public class HuespedController {

	private HuespedDAO huespedDAO;

	public HuespedController() {
		var factory = new ConnectionFactory();
		this.huespedDAO = new HuespedDAO(factory.recuperaConexion());
	}

	public List<Huesped> listarHuespedes() {
		return huespedDAO.listarHuespedes();
	}
	
	public List<Huesped> listarHuespedes(String busqueda) {
		return huespedDAO.listarHuespedes(busqueda);
	}
	
	public void guardar(Huesped huesped, int reserva) {
		huespedDAO.guardar(huesped, reserva);
	}

	public int modificarHuesped(String nombre, String apellido, Date fecha_nacimiento, String nacionalidad, int telefono, int id_reserva, Integer id) {
		return huespedDAO.modificarHuesped(nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva, id);
	}

	public int eliminarHuesped(Integer id) {
		return huespedDAO.eliminarHuesped(id);
	}
//    public List<Huesped> cargaReporte() {
//        
//        return this.categoriaDAO.listarConProductos();
//    }

}
