package org.tds.sgh.business;

//amarillo
public class TipoHabitacion
{
	
	private String nombre;
	
	// --------------------------------------------------------------------------------------------
	
	public TipoHabitacion(String nombre)
	{
		this.nombre = nombre;
	}
	
	
	// --------------------------------------------------------------------------------------------
	
	public boolean tienesNombre(String nombre) {
		
		return this.nombre == nombre;
		
	}
	
	public String getNombre()
	{
		return this.nombre;
	}
}
