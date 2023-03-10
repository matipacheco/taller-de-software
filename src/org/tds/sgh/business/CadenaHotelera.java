package org.tds.sgh.business;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.tds.sgh.business.Reserva.Estado;
import org.tds.sgh.dtos.DTO;
import org.tds.sgh.infrastructure.Infrastructure;

import javax.persistence.*;

@Entity
public class CadenaHotelera
{
	// --------------------------------------------------------------------------------------------
	private long id;

	private String nombre;
	
	private Map<String, Cliente> clientes;

	private Map<String, Hotel> hoteles;

	private Map<String, TipoHabitacion> tiposHabitacion;
	
	// --------------------------------------------------------------------------------------------
	
	public CadenaHotelera(String nombre)
	{
		this.clientes = new HashMap<String, Cliente>();
		
		this.hoteles = new HashMap<String, Hotel>();
		
		this.nombre = nombre;
		
		this.tiposHabitacion = new HashMap<String, TipoHabitacion>();
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

	public String getNombre() {
		return this.nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@OneToMany(cascade=CascadeType.ALL)
	@MapKey(name="rut")
	public Map<String, Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(Map<String, Cliente> clientes) {
		this.clientes = clientes;
	}

	@OneToMany(cascade=CascadeType.ALL)
	@MapKey(name="nombre")
	public Map<String, Hotel> getHoteles() {
		return hoteles;
	}

	public void setHoteles(Map<String, Hotel> hoteles) {
		this.hoteles = hoteles;
	}

	@OneToMany(cascade=CascadeType.ALL)
	@MapKey(name="nombre")
	public Map<String, TipoHabitacion> getTiposHabitacion() {
		return tiposHabitacion;
	}

	public void setTiposHabitacion(Map<String, TipoHabitacion> tiposHabitacion) {
		this.tiposHabitacion = tiposHabitacion;
	}
	
	// CU ALTA CLIENTE
	public Cliente registrarCliente(
		String rut,
		String nombre,
		String direccion,
		String telefono,
		String mail) throws Exception
	{
		if (this.clientes.containsKey(rut))
		{
			throw new Exception("Ya existe un cliente con el RUT indicado.");
		}
		
		Cliente cliente = new Cliente(rut, nombre, direccion, telefono, mail);

		this.clientes.put(cliente.getRut(), cliente);
		
		return cliente;
	}
	
	public Hotel agregarHotel(String nombre, String pais) throws Exception
	{
		if (this.hoteles.containsKey(nombre))
		{
			throw new Exception("Ya existe un hotel con el nombre indicado.");
		}
		
		Hotel hotel = new Hotel(nombre, pais);
		
		this.hoteles.put(hotel.getNombre(), hotel);
		
		return hotel;
	}
	
	public TipoHabitacion agregarTipoHabitacion(String nombre) throws Exception
	{
		if (this.tiposHabitacion.containsKey(nombre))
		{
			throw new Exception("Ya existe un tipo de habitaci??n con el nombre indicado.");
		}
		
		TipoHabitacion tipoHabitacion = new TipoHabitacion(nombre);
		
		this.tiposHabitacion.put(tipoHabitacion.getNombre(), tipoHabitacion);
		
		return tipoHabitacion;
	}
	
	public Habitacion agregarHabitacion(Hotel hotel, TipoHabitacion tipoHabitacion, String nombre ) throws Exception {
		return hotel.agregarHabitacion(tipoHabitacion, nombre);
	}
	
	// CASO DE USO 8 - BUSCAR CLIENTE
	public Set<Cliente> buscarCliente(String patronNombreCliente)
	{
		
		if(patronNombreCliente == null)
			throw new NullPointerException();
		
		Set<Cliente> clientesEncontrados = new LinkedHashSet<Cliente>();
		
		for (Cliente cliente : this.clientes.values())
		{
			if (cliente.coincideElNombre(patronNombreCliente))
			{
				clientesEncontrados.add(cliente);
			}
		}
		
		return clientesEncontrados;
	}
	
	// CU8 seleccionarCliente
	public Cliente seleccionarCliente(String rut) throws Exception
	{
		Cliente cliente = this.clientes.get(rut);
		
		if (cliente == null)
		{
			throw new Exception("No existe un cliente con el nombre indicado.");
		}
		
		return cliente;
		
	}
	
	public Boolean confirmarDisponibilidad(String nombreHotel , String nombreTipoHabitaci??n, GregorianCalendar fechaInicio, GregorianCalendar fechaFin, Reserva reserva) throws Exception
	{
		Hotel hotel = hoteles.get(nombreHotel);
		
		if(this.tiposHabitacion.get(nombreTipoHabitaci??n) == null)
			throw new Exception("TipoHabitacion no existe");
		
		if(Infrastructure.getInstance().getCalendario().esPasada(fechaInicio))
			throw new Exception("Fecha pasada");
		
		if(Infrastructure.getInstance().getCalendario().esPosterior(fechaInicio, fechaFin))
			throw new Exception("Fecha inicio posterior");
		
		return hotel.confirmarDisponibilidad(nombreTipoHabitaci??n, fechaInicio, fechaFin, reserva);
	}
	
	public Reserva registrarReserva(Cliente cliente, String nombreHotel, String nombreTipoHabitacion, GregorianCalendar fechaInicial, GregorianCalendar fechaFinal, Boolean modificablePorHuesped)
	{
		Hotel hotel = hoteles.get(nombreHotel);
		TipoHabitacion tipoHabitacion = tiposHabitacion.get(nombreTipoHabitacion);
		return hotel.crearReserva(tipoHabitacion, cliente, fechaInicial, fechaFinal, modificablePorHuesped);
	}
	
	public Set<Hotel> sugerirAlternativas(String pais, String nombreTipoHabitacion, GregorianCalendar fechaInicial, GregorianCalendar fechaFinal, Reserva reserva) throws Exception
	{
		
		//Exception si no existe!
		if(this.tiposHabitacion.get(nombreTipoHabitacion) == null)
			throw new Exception("TipoHabitacion no existe");
		
		//Exception fecha pasada!
		if(Infrastructure.getInstance().getCalendario().esPasada(fechaInicial))
			throw new Exception("Fecha pasada");
		
		//Exception fecha final menor a fecha inicial!
		if (Infrastructure.getInstance().getCalendario().esAnterior(fechaFinal, fechaInicial))
			throw new Exception("Fecha final es anterior a fecha pasada");
		
		Set<Hotel> alternativas = new LinkedHashSet<Hotel>();

		for (Hotel hotel : hoteles.values())
		{
			if (pais == hotel.getPais()) {
				if (confirmarDisponibilidad(hotel.getNombre(), nombreTipoHabitacion, fechaInicial, fechaFinal, null)) {
					alternativas.add(hotel);
				}
			}
		}
		
		return alternativas;
	}
	
	public Set<Reserva> buscarReservasDelCliente(Cliente clienteSeleccionado) throws Exception
	{
		
		if(clienteSeleccionado == null)
			throw new Exception("No hay cliente seleccionado");
		
		Set<Reserva> reservasCliente = new LinkedHashSet<Reserva>();
		
		for (Hotel hotel : listarHoteles()) {
			Set<Reserva> reservas = hotel.getReservas();
			
			for (Reserva reserva : reservas) {
				if (reserva.esDelCliente(clienteSeleccionado) && reserva.getEstado() == Estado.Pendiente
					&& !Infrastructure.getInstance().getCalendario().esPasada(reserva.getFechaFin())) {
					reservasCliente.add(reserva);
				}
			}
		}
		return reservasCliente;
	}
	
	public Reserva modificarReserva(
			Reserva reserva,
			Cliente cliente,
			String nombreHotel,
			String nombreTipoHabitacion,
			GregorianCalendar fechaInicial,
			GregorianCalendar fechaFinal,
			Boolean modificadoPorHuesped) throws Exception
	{
		Hotel hotelAnterior = reserva.getHotel();
		Hotel hotelNuevo = buscarHotel(nombreHotel);
		
		if (!hotelNuevo.getNombre().equals(hotelAnterior.getNombre())) {
			hotelAnterior.quitarReserva(reserva);
		}
		
		hotelNuevo.agregarReserva(reserva);
		TipoHabitacion tipoHabitacionNuevo = buscarTipoHabitacion(nombreTipoHabitacion);
		
		Reserva reservaActualizada = reserva.update(tipoHabitacionNuevo, fechaInicial, fechaFinal, modificadoPorHuesped, hotelNuevo);
		reservaActualizada.enviarMail("reservaActualizada");
		return reservaActualizada;
	}
	
	public Set<Reserva> buscarReservasPendientes(String nombreHotel)
	{
		Hotel hotel = hoteles.get(nombreHotel);
		return hotel.buscarReservasPendientes();
	}

	public Reserva seleccionarReserva(Long codigo, Cliente cliente) throws Exception
	{	 
		
		 for(Hotel hotel: hoteles.values()) {
			 Reserva reserva = hotel.buscarReserva(codigo);
			 if(reserva != null)
				if(cliente != null)
					if(reserva.esDelCliente(cliente))
						return reserva;
					else
						return null;
				else
					return reserva;
		 }
		 
		 return null;
	}
	
	public Reserva registrarHuesped(Reserva reserva, String nombre, String documento)
	{
		return reserva.agregarHuesped(nombre, documento);
	}
	
	public Reserva tomarReserva(Reserva reserva)
	{
		Reserva reservaActualizada = reserva.tomarReserva();
		reservaActualizada.enviarMail("reservaTomada");
		Infrastructure.getInstance().getSistemaFacturacion().iniciarEstadia(DTO.getInstance().map(reservaActualizada));
		return reservaActualizada;
	}
	
	public Hotel buscarHotel(String nombre) throws Exception
	{
		Hotel hotel = this.hoteles.get(nombre);
		
		if (hotel == null)
		{
			throw new Exception("No existe un hotel con el nombre indicado.");
		}
		
		return hotel;
	}
	
	public TipoHabitacion buscarTipoHabitacion(String nombre) throws Exception
	{
		TipoHabitacion tipoHabitacion = this.tiposHabitacion.get(nombre);
		
		if (tipoHabitacion == null)
		{
			throw new Exception("No existe un tipo de habitaci??n con el nombre indicado.");
		}
		
		return tipoHabitacion;
	}
	
	public Set<Cliente> listarClientes()
	{
		return new HashSet<Cliente>(this.clientes.values());
	}
	
	public Set<Hotel> listarHoteles()
	{
		return new HashSet<Hotel>(this.hoteles.values());
	}
	
	public Set<TipoHabitacion> listarTiposHabitacion()
	{
		return new HashSet<TipoHabitacion>(this.tiposHabitacion.values());
	}
	
	public Set<Habitacion> listarHabitaciones(String nombreHotel) throws Exception {			
		Hotel hotel = this.buscarHotel(nombreHotel);
		return hotel.listarHabitaciones();
	}
	
	public Reserva cancelarReservaDelCliente(Reserva reserva) {
		Reserva reservaActualizada = reserva.cancelar();
		reservaActualizada.enviarMail("reservaCancelada");
		return reservaActualizada;
	}

	public Reserva seleccionarReservaModRes(long codigoReserva, Cliente cliente) throws Exception {
		if (cliente == null)
			return null;
		else
			return this.seleccionarReserva(codigoReserva, cliente);
	}
}
