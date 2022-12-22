package org.tds.sgh.business;

import javax.persistence.*;

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
	public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}

	// --------------------------------------------------------------------------------------------

	public TipoHabitacion getTipoHabitacion()
	{
		return this.tipoHabitacion;
	}
	
	public Boolean esDeTipo(String nombreTipoHabitación)
	{
		return this.tipoHabitacion.getNombre() == nombreTipoHabitación;
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
