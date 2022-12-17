package org.tds.sgh.business;

import java.util.GregorianCalendar;

//amarillo
public class Reserva {

	private int codigo;
	private GregorianCalendar fechaInicio;
	private GregorianCalendar fechaFin;
	private boolean modificablePorHuesped;
	private Cliente cliente;
	private TipoHabitacion tipoHabitacion;
	private Hotel hotel;
	
	public Reserva() {
		
	}
	
	public boolean coincide(String nombreTipoHabitacion, GregorianCalendar fechaInicio, GregorianCalendar fechaFin) {
		
		
		//not implemented
		return true;
	}
	
	public Reserva create(TipoHabitacion tipoHabitacion, Cliente cliente, GregorianCalendar fechaInicio, GregorianCalendar fechaFin, boolean mph, Hotel hotel) {
		
		
		//no implementado
		return new Reserva();
	}
	
	public Reserva update(String nombreTipoHabitacion, GregorianCalendar fechaInicio, GregorianCalendar fechaFin, boolean mph, Hotel hotel) {
		
		
		//not implemented
		return new Reserva();
	}
	
	public void enviarMail(String destinatario, String asunto, String mensaje) {
		//no implementado
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
	
	
	
}
