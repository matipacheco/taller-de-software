package org.tds.sgh.business;

import javax.persistence.*;

@Entity
public class Habitacion
{
	// --------------------------------------------------------------------------------------------
	
	private long id;
	private String nombre;

	private Hotel hotel;
	
	private Reserva reserva;
	
	private TipoHabitacion tipoHabitacion;
	
	// --------------------------------------------------------------------------------------------
	
	public Habitacion(TipoHabitacion tipoHabitacion, String nombre)
	{
		this.reserva = null;
		this.nombre = nombre;
		this.tipoHabitacion = tipoHabitacion;
	}
	
	public Habitacion(Hotel hotel, TipoHabitacion tipoHabitacion, String nombre)
	{
		this.reserva = null;
		this.hotel = hotel;
		this.nombre = nombre;
		this.tipoHabitacion = tipoHabitacion;
	}
	
	// --------------------------------------------------------------------------------------------

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@ManyToOne(cascade=CascadeType.ALL)
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	@OneToOne(cascade=CascadeType.ALL)
	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	@OneToOne(cascade=CascadeType.ALL)
	public TipoHabitacion getTipoHabitacion() {
		return this.tipoHabitacion;
	}

	public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}

	// --------------------------------------------------------------------------------------------
	
	public Boolean esDeTipo(String nombreTipoHabitaci√≥n)
	{
		return this.tipoHabitacion.getNombre() == nombreTipoHabitaci√≥n;
	}

	public Boolean asignada()
	{
		return this.reserva != null;
	}

	public void asignarReserva(Reserva reserva)
	{
		this.reserva = reserva;
	}
	
	public void liberarHabitacion() {
		this.reserva = null;
	}
}
