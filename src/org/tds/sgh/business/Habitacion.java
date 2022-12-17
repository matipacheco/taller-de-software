package org.tds.sgh.business;

//verde
public class Habitacion
{
	// --------------------------------------------------------------------------------------------
	
	private String nombre;
	
	private TipoHabitacion tipoHabitacion;
	
	private Reserva reserva;
	// --------------------------------------------------------------------------------------------
	
	public Habitacion(TipoHabitacion tipoHabitacion, String nombre)
	{
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
		return null;
		
	}

	public Boolean asignada()
	{
		return null;
		
	}

	public void asignarReserva(Reserva reserva)
	{
		
	}
}
