package org.tds.sgh.business;

import java.util.GregorianCalendar;
import java.util.Set;

import org.tds.sgh.infrastructure.Infrastructure;

public class Reserva {

	public enum Estado {
		Pendiente,
		Tomada,
		Finalizada,
		Cancelada,
		NoTomada,
	}

	private Estado estado;
	private long codigo;
	private GregorianCalendar fechaInicio;
	private GregorianCalendar fechaFin;
	private boolean modificablePorHuesped;
	private Cliente cliente;
	private TipoHabitacion tipoHabitacion;
	private Hotel hotel;
	private Set<Huesped> huespedes;

	
	
	public Reserva (TipoHabitacion tipoHabitacion, Cliente cliente, GregorianCalendar fechaInicio, GregorianCalendar fechaFin, boolean mph, Hotel hotel) {
		this.tipoHabitacion = tipoHabitacion;
		this.cliente = cliente;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.modificablePorHuesped = mph;
		this.hotel = hotel;
		this.estado = Estado.Pendiente;
	}
	
	public boolean coincide(String nombreTipoHabitacion, GregorianCalendar fechaInicio, GregorianCalendar fechaFin) {
		//not implemented
		return true;
	}
	
	public Reserva update(String nombreTipoHabitacion, GregorianCalendar fechaInicio, GregorianCalendar fechaFin, boolean mph, Hotel hotel) {		
		//not implemented
		// return new Reserva();
		return null;
	}
	
	public void enviarMail(String evento) {
		String asunto = "", mensaje = "";
		String destinatario = this.cliente.getMail();

		switch(evento) {
		case "reservaCreada":
			asunto = "Reserva " + Long.toString(this.getCodigo()) + " creada exitosamente";
			mensaje = "Tu reserva entre las fechas " + this.getFechaInicio().toString() + " y " + this.getFechaFin().toString() + " ha sido creada exitosamente";
		case "reservaTomada":
			asunto = "Tu estadía ha comenzado!";
			mensaje = "Bienvenido!";
		}
		
		Infrastructure.getInstance().getSistemaMensajeria().enviarMail(destinatario, asunto, mensaje);
	}
	
	public boolean esDelCliente(Cliente clienteSeleccionado) {
		
		//no implementado
		return false;
	}

	public boolean estaPendiente() {
		return estado == Estado.Pendiente;
	}
	
	public Reserva agregarHuesped(String nombre, String documento) {
		Huesped huesped = new Huesped(nombre, documento);
		this.huespedes.add(huesped);
		return this;
	}
	
	public Reserva tomarReserva() {
		Set<Habitacion> habitacionesHotel = hotel.listarHabitaciones();

		for (Habitacion habitacion : habitacionesHotel) {
			if (habitacion.esDeTipo(this.tipoHabitacion.getNombre()) && !habitacion.asignada()) {
				habitacion.asignarReserva(this);
				break;
			}
		}
		
		this.estado = Estado.Tomada;		
		return this;
	}
	
	public boolean reservaPendienteEnRango(String nombreTipoHabitacion, GregorianCalendar fechainicio, GregorianCalendar fechafin) {
		if(this.tipoHabitacion.getNombre().equals(nombreTipoHabitacion)) {
		
			if(
				!((Infrastructure.getInstance().getCalendario().esAnterior(fechainicio, this.fechaInicio)
			&& Infrastructure.getInstance().getCalendario().esAnterior(fechafin, this.fechaInicio)) || 
			 Infrastructure.getInstance().getCalendario().esPosterior(fechainicio, this.fechaFin))
			 
			 
			 )
			 
			  {
				return true;
			}
			
		}
		
		return false;
	}
	
	public Hotel getHotel() {
		return hotel;
	}
	
	public TipoHabitacion getTipo() {
		return tipoHabitacion;
	}
	
	public long getCodigo() {
		return codigo;
	}
	
	public GregorianCalendar getFechaInicio() {
		return fechaInicio;
	}
	
	public GregorianCalendar getFechaFin() {
		return fechaFin;
	}
	
	public String rutCliente() {
		return this.cliente.getRut();
	}
	
	public TipoHabitacion getTipoHabitacion() {
		return this.tipoHabitacion;
	}
	
	public Estado getEstado(){
		return this.estado;
	}
	
	public Boolean getModificablePorHuesped() {
		return modificablePorHuesped; 
	}
	
	public Set<Huesped> getHuespedes(){
		return this.huespedes;
	}
	
}
