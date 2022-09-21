package ar.com.hotelalura.modelo;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;


public class Huesped {

	private Integer id;
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private String nacionalidad;
	private int telefono;
	private int nroReserva;
	private List<Reserva> reserva;

	public Huesped(int id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad, int telefono, int nroReserva) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.nroReserva = nroReserva;
	}
	
	
	public Huesped(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, int telefono, int nroReserva) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.nacionalidad = nacionalidad;
		this.telefono = telefono;
		this.nroReserva = nroReserva;
		}


	public String getNombre() {
		return this.nombre;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public int getNroReserva() {
		return nroReserva;
	}

	public void setNroReserva(int nroReserva) {
		this.nroReserva = nroReserva;
	}

	public List<Reserva> getReserva() {
		return reserva;
	}

	public void setReserva(List<Reserva> reserva) {
		this.reserva = reserva;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void agregar(Reserva reserva) {
		if (this.reserva == null) {
			this.reserva = new ArrayList<>();
		}
		
		this.reserva.add(reserva);
	}
	
	@Override
	public String toString() {
		return "nombre: " + this.nombre + ", apellido: " + this.apellido + ", fecha de nacimiento: " + this.fechaNacimiento + ", nacionalidad: " + this.nacionalidad + ", teléfono: " + this.telefono + ", reserva nro: " + this.nroReserva;
	}

	public List<Reserva> getReservas() {
		return this.reserva;
	}
	
}
