package org.tds.sgh.business;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import org.tds.sgh.infrastructure.ICalendario;
import org.tds.sgh.infrastructure.Infrastructure;

import javax.persistence.*;

@Entity
public class Reserva {

	public enum Estado {
		Pendiente,
		Tomada,
		Finalizada,
		Cancelada,
		NoTomada,
	}

	private long id;
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
	
	// --------------------------------------------------------------------------------------------
	
	public Reserva (long codigo, TipoHabitacion tipoHabitacion, Cliente cliente, GregorianCalendar fechaInicio, GregorianCalendar fechaFin, boolean mph, Hotel hotel) {
		this.codigo = codigo;
		this.tipoHabitacion = tipoHabitacion;
		this.cliente = cliente;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.modificablePorHuesped = mph;
		this.hotel = hotel;
		this.estado = Estado.Pendiente;
		this.huespedes = new HashSet<Huesped>();
		this.habitacion = null;
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

	@OneToOne(cascade=CascadeType.ALL)
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@OneToOne(cascade=CascadeType.ALL)
	public Habitacion getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	public TipoHabitacion getTipoHabitacion() {
		return this.tipoHabitacion;
	}

	public void setTipoHabitacion(TipoHabitacion tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	
	public GregorianCalendar getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(GregorianCalendar fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public void setFechaFin(GregorianCalendar fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	public GregorianCalendar getFechaFin() {
		return fechaFin;
	}
	
	@OneToOne(cascade=CascadeType.ALL)
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	
	public Estado getEstado(){
		return this.estado;
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public Boolean getModificablePorHuesped() {
		return modificablePorHuesped; 
	}
	
	public void setModificablePorHuesped(boolean modificablePorHuesped) {
		this.modificablePorHuesped = modificablePorHuesped;
	}
	
	@OneToMany(cascade=CascadeType.ALL)
	public Set<Huesped> getHuespedes(){
		return this.huespedes;
	}
	
	public void setHuespedes(Set<Huesped> huespedes) {
		this.huespedes = huespedes;
	}
	
	public String rutCliente() {
		return this.cliente.getRut();
	}
	
	// --------------------------------------------------------------------------------------------
	
	
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
		try {
			return cliente.getRut() == clienteSeleccionado.getRut();
		}
		catch(Exception ex) {
			return false;
		}
		
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
		ICalendario cal = Infrastructure.getInstance().getCalendario();
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
	
	public String nombreHabitacion() {
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
