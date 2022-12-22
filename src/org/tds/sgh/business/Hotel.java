package org.tds.sgh.business;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.*;

public class Hotel
{
	// --------------------------------------------------------------------------------------------
	
	private long id;
	private String pais;
	private String nombre;
	
	private Set<Reserva> reservas;
	private Map<String, Habitacion> habitaciones;
	// --------------------------------------------------------------------------------------------
	
	public Hotel(String nombre, String pais)
	{
		this.pais = pais;
		this.nombre = nombre;		
		this.reservas = new HashSet<Reserva>();
		this.habitaciones = new HashMap<String, Habitacion>();
		
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

	@OneToMany(cascade=CascadeType.ALL)
	public Set<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(Set<Reserva> reservas) {
		this.reservas = reservas;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@OneToMany(cascade=CascadeType.ALL)
	@MapKey(name="nombre")
	public void setHabitaciones(Map<String, Habitacion> habitaciones) {
		this.habitaciones = habitaciones;
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

	public boolean confirmarDisponibilidad(String nombreTipoHabitacion, GregorianCalendar fechainicio, GregorianCalendar fechafin, Reserva reservaCliente)
	{
		int cantHabs = 0;

		for (Habitacion habitacion : habitaciones.values()) {
			TipoHabitacion tipo = habitacion.getTipoHabitacion();
			if (tipo.getNombre().equals(nombreTipoHabitacion)) {
				cantHabs++;
			}
		}
		int contReservas = 0;
		
		if(reservaCliente != null) {
			if(reservas.size() == 1 && cantHabs == 1)
				return true;
		}

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
		long codigo = reservas.size() + 1;
		Reserva reservaCreada = new Reserva(codigo ,tipoHab, cliente, fechainicio, fechafin, modificablePorHuesped, this);
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
		
		Set<Reserva> reservasPendientes = new LinkedHashSet<Reserva>();
		
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
		return new LinkedHashSet<Habitacion>(this.habitaciones.values());
	}
}
