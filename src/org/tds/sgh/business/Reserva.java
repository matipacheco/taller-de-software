package org.tds.sgh.business;

import java.util.GregorianCalendar;
import java.util.Set;

import org.tds.sgh.infrastructure.Infrastructure;

//amarillo
public class Reserva {

	private String estado;
	private int codigo;
	private GregorianCalendar fechaInicio;
	private GregorianCalendar fechaFin;
	private boolean modificablePorHuesped;
	private Cliente cliente;
	private TipoHabitacion tipoHabitacion;
	private Hotel hotel;
	private Set<Huesped> huespedes;

	private enum Estado {
  	
	}
	
	public Reserva (TipoHabitacion tipoHabitacion, Cliente cliente, GregorianCalendar fechaInicio, GregorianCalendar fechaFin, boolean mph, Hotel hotel) {
		this.tipoHabitacion = tipoHabitacion;
		this.cliente = cliente;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.modificablePorHuesped = mph;
		this.hotel = hotel;
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
			asunto = "Reserva creada exitosamente";
			mensaje = "Holi";
		}
		
		Infrastructure.getInstance().getSistemaMensajeria().enviarMail(destinatario, asunto, mensaje);
	}
	
	public boolean esDelCliente(Cliente clienteSeleccionado) {
		
		//no implementado
		return false;
	}
	
	public boolean estaPendiente() {
		
		//no implementado
		return false;
	}
	
	public void agregarHuesped(String nombre, String documento) {
		
		//no implementado
	}
	
	public Reserva tomarReserva() {
		
		//no implementado
		return this;
	}
	
	public boolean reservaPendienteEnRango(String nombreTipoHabitacion, GregorianCalendar fechaInicio, GregorianCalendar fechaFin) {
		
		//no implementado
		return false;
	}
	
	public Hotel getHotel() {
		return hotel;
	}
	
	public TipoHabitacion getTipo() {
		return tipoHabitacion;
	}
	
	public int getCodigo() {
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
	
	public String getTipoHabitacion() {
		return this.tipoHabitacion.getNombre();
	}
	
	public String getEstado(){
		return this.estado;
	}
	
	public Boolean getModificablePorHuesped() {
		return modificablePorHuesped; 
	}
	
	public Set<Huesped> getHuespedes(){
		return this.huespedes;
	}
	
}
