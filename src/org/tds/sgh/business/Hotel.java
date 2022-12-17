package org.tds.sgh.business;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

//verde
public class Hotel
{
	// --------------------------------------------------------------------------------------------
	private String pais;
	private String nombre;
	
	private Set<Reserva> reservas;
	private Map<String, Habitacion> habitaciones;
	// --------------------------------------------------------------------------------------------
	
	public Hotel(String nombre, String pais)
	{
		this.habitaciones = new HashMap<String, Habitacion>();
		
		this.nombre = nombre;
		
		this.pais = pais;
		this.reservas = new HashSet<Reserva>();
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
	
	public boolean confirmarDisponibilidad(String nombreTipoHabitacion, GregorianCalendar fechainicio, GregorianCalendar fechafin)
	{
		int CantHabs;
		
		return true;
	}
	
	public Reserva buscarReserva(String codigoReserva)
	{
		return null;	
	}
	
	
	public Reserva crearReserva(TipoHabitacion tipoHab, Cliente cliente, GregorianCalendar fechainicio, GregorianCalendar fechafin, Boolean modificablePorHuesped)
	{
		Reserva reservaCreada = new Reserva(tipoHab, cliente, fechainicio, fechafin, modificablePorHuesped, this);
		reservaCreada.enviarMail("reservaCreada");
		this.reservas.add(reservaCreada);

		return reservaCreada;
	}
	
	public void quitarReserva(Reserva reserva)
	{
		
	}
	
	public void agregarReserva(Reserva reserva)
	{
		
	}
	
	public Set<Reserva> buscarReservasPendientes()
	{
		return null;
	}
	
	public Set<Habitacion> getHabitaciones()
	{
		return null;
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
