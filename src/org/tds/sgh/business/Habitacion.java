package org.tds.sgh.business;

public class Habitacion
{
	// --------------------------------------------------------------------------------------------
	
	private String nombre;
	private Hotel hotel;
	
	private TipoHabitacion tipoHabitacion;
	
	private Reserva reserva;
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
	
	public String getNombre()
	{
		return this.nombre;
	}
	
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
