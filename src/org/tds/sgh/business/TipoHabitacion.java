package org.tds.sgh.business;

import javax.persistence.*;

@Entity
public class TipoHabitacion
{
	
	private long id;
	private String nombre;
	
	// --------------------------------------------------------------------------------------------
	
	public TipoHabitacion(String nombre)
	{
		this.nombre = nombre;
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	// --------------------------------------------------------------------------------------------

	public boolean tienesNombre(String nombre) {
		return this.nombre == nombre;
	}
}
