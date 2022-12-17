package org.tds.sgh.business;

import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//verde
public class Hotel
{
	// --------------------------------------------------------------------------------------------
	
	private Map<String, Habitacion> habitaciones;
	
	private String nombre;
	
	private String pais;
	
	// --------------------------------------------------------------------------------------------
	
	public Hotel(String nombre, String pais)
	{
		this.habitaciones = new HashMap<String, Habitacion>();
		
		this.nombre = nombre;
		
		this.pais = pais;
	}
	
	// --------------------------------------------------------------------------------------------
	
	public Habitacion agregarHabitacion(TipoHabitacion tipoHabitacion, String nombre) throws Exception
	{
		if (this.habitaciones.containsKey(nombre))
		{
			throw new Exception("El hotel ya tiene una habitación con el nombre indicado.");
		}
		
		Habitacion habitacion = new Habitacion(tipoHabitacion, nombre);
		
		this.habitaciones.put(habitacion.getNombre(), habitacion);
		
		return habitacion;
	}
	
	public boolean confirmarDisponibilidad(String nombreTipoHabitacion, Date fechainicio, Date fechafin)
	{
		return true;
	}
	
	public Set<Reserva> buscarReserva(String codigoReserva)
	{
		return null;
		
	}
	
	public Set<Reserva> crearReserva(TipoHabitacion tipoHab, Cliente cliente, Date fechainicio, Date fechafin, Boolean modificadoPorHuesped)
	{
		return null;
	}
	
	public void quitarReserva(Reserva reserva)
	{
		
	}
	
	public void agregarReserva(Reserva reserva)
	{
		
	}
	
	public Set<List<Reserva>> buscarReservasPendientes()
	{
		
	}
	
	public Set<List<Habitacion>> getHabitaciones()
	{
		
	}
	
	public String getNombre()
	{
		return this.nombre;
	}
	
	public String getPais()
	{
		return this.pais;
	}
	
	public Set<Habitacion> listarHabitaciones()
	{
		return new HashSet<Habitacion>(this.habitaciones.values());
	}
}
