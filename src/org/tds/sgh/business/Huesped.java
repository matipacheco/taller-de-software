package org.tds.sgh.business;

import javax.persistence.*;

@Entity
public class Huesped {
	private long id;
	
	private String nombre;
	
	private String documento;

	public Huesped(String nombre, String documento) {
		this.nombre = nombre;
		this.documento = documento;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getNombre() {
		return this.nombre;
	}

	public String getDocumento() {
		return this.documento;
	}


}
