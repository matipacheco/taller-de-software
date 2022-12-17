package org.tds.sgh.business;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
		int cantHabs = 0;

		for (Habitacion habitacion : habitaciones.values()) {
			TipoHabitacion tipo = habitacion.getTipoHabitacion();
			if (tipo.getNombre().equals(nombreTipoHabitacion)) {
				cantHabs++;
			}
		}
		int contReservas = 0;

		for (Reserva reserva : reservas) {
			Boolean reservaPendienteEnRango = reserva.reservaPendienteEnRango(nombreTipoHabitacion, fechainicio, fechafin);
			if (reservaPendienteEnRango) {
				contReservas++;
			}
		}

		return cantHabs > contReservas;
	}
	
	public Reserva buscarReserva(Long codigoReserva)
	{
		for(Reserva reserva : reservas)
			if(reserva.getCodigo() == codigoReserva)
				return reserva;
		
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
		this.reservas.remove(reserva);
	}
	
	public void agregarReserva(Reserva reserva)
	{
		this.reservas.add(reserva);
	}
	
	public Set<Reserva> getReservasHotel()
	{
		return this.reservas;
	}

	public Set<Reserva> buscarReservasPendientes()
	{
		
		Set<Reserva> reservasPendientes = new HashSet<Reserva>();
		
		for(Reserva reserva: this.reservas) {
			
			if(reserva.estaPendiente()) {
				reservasPendientes.add(reserva);
			}
			
		}
		return reservasPendientes;
	}

	public Map<String, Habitacion> getHabitaciones()
	{
		return habitaciones;
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
