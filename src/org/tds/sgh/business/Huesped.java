package org.tds.sgh.business;
import java.util.GregorianCalendar;

public class Huesped {
	private String nombre;
	
	private String documento;
	
	private GregorianCalendar fechaInicio;
	
	private GregorianCalendar fechaFin;


	public Huesped(String nombre, String documento) {
		this.nombre = nombre;
		this.documento = documento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getDocumento() {
		return this.documento;
	}


}
