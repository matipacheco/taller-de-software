package org.tds.sgh.business;

import java.util.GregorianCalendar;

//amarillo
public class Reserva {

	private int codigo;
	private GregorianCalendar fechaInicio;
	private GregorianCalendar fechaFin;
	private boolean modificablePorHuesped;
	
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
	
}
