package org.tds.sgh.business;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import org.tds.sgh.infrastructure.ICalendario;
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
	private Habitacion habitacion;
	private ICalendario cal;
	
	
	public Reserva (TipoHabitacion tipoHabitacion, Cliente cliente, GregorianCalendar fechaInicio, GregorianCalendar fechaFin, boolean mph, Hotel hotel) {
		this.tipoHabitacion = tipoHabitacion;
		this.cliente = cliente;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.modificablePorHuesped = mph;
		this.hotel = hotel;
		this.estado = Estado.Pendiente;
		this.huespedes = new HashSet<Huesped>();
		this.habitacion = null;
		this.cal = Infrastructure.getInstance().getCalendario();
	}
	
	public boolean coincide(String nombreTipoHabitacion, GregorianCalendar fechaInicio, GregorianCalendar fechaFin) {
		//not implemented
		return true;
	}
	
	public Reserva update(TipoHabitacion tipoHabitacion, GregorianCalendar fechaInicio, GregorianCalendar fechaFin, boolean mph, Hotel hotel) throws Exception {		
		
		if(!this.modificablePorHuesped)
			throw new Exception("No es modificable");
		
		this.tipoHabitacion = tipoHabitacion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.modificablePorHuesped = mph;
		this.hotel = hotel;
		
		
		
		return this;
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
		case "reservaCancelada":
			asunto = "Tu reserva ha sido cancelada";
			mensaje = "Adiosin";
		case "reservaActualizada":
			asunto = "Tu reserva ha sido actualizada";
			mensaje = "no se";
		}
		
		Infrastructure.getInstance().getSistemaMensajeria().enviarMail(destinatario, asunto, mensaje);
	}
	
	public boolean esDelCliente(Cliente clienteSeleccionado) {
		return cliente.getRut() == clienteSeleccionado.getRut();
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
		Habitacion habitacionAsignada = null;
		Set<Habitacion> habitacionesHotel = hotel.listarHabitaciones();

		for (Habitacion habitacion : habitacionesHotel) {
			if (habitacion.esDeTipo(this.tipoHabitacion.getNombre()) && !habitacion.asignada()) {
				habitacion.asignarReserva(this);
				habitacionAsignada = habitacion;
				break;
			}
		}
		
		
		this.habitacion = habitacionAsignada;
		this.estado = Estado.Tomada;		
		return this;
	}
	
	public boolean reservaPendienteEnRango(String nombreTipoHabitacion, GregorianCalendar fechainicio, GregorianCalendar fechafin) {
		if(this.tipoHabitacion.getNombre().equals(nombreTipoHabitacion)) {
			
			if(
				!(
					(cal.esAnterior(fechainicio, this.fechaInicio) && (cal.esAnterior(fechafin, this.fechaInicio) || cal.esMismoDia(fechafin, this.fechaInicio) ) ) 
					|| ( cal.esPosterior(fechainicio, this.fechaFin) || cal.esMismoDia(fechainicio, this.fechaFin) )
				)
			 
			 
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
	
	public String getNombreHabitacion() {
		if (habitacion != null) {			
			return this.habitacion.getNombre();
		}
		return null;
	}
	
	public Reserva cancelar() {
		this.estado = Estado.Cancelada;
		if (this.habitacion != null) {			
			this.habitacion.liberarHabitacion();
			this.habitacion = null;
		}
		return this;
	}
	
}
