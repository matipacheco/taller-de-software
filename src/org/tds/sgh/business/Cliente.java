package org.tds.sgh.business;

import javax.persistence.*;

@Entity
public class Cliente
{
	// --------------------------------------------------------------------------------------------
	
	private long id;

	private String direccion;
	
	private String mail;
	
	private String nombre;
	
	private String rut;
	
	private String telefono;
	
	// --------------------------------------------------------------------------------------------
	
	public Cliente(String rut, String nombre, String direccion, String telefono, String mail)
	{
		this.rut = rut;
		this.mail = mail;		
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
	}
	
	// --------------------------------------------------------------------------------------------
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}

	protected void setId(long id) {
		this.id = id;
	}
	
	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getMail() {
		return this.mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRut() {
		return this.rut;
	}

	public void setRut(String rut) {
		this.rut = rut;
	}
	
	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	// --------------------------------------------------------------------------------------------

	public boolean coincideElNombre(String patronNombreCliente)
	{
		return this.nombre.matches(patronNombreCliente);
	}
}
