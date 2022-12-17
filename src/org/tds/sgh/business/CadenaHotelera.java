package org.tds.sgh.business;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// verde
public class CadenaHotelera
{
	// --------------------------------------------------------------------------------------------
	
	private Map<String, Cliente> clientes;
	
	private Map<String, Hotel> hoteles;
	
	private String nombre;
	
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
			throw new Exception("Ya existe un tipo de habitación con el nombre indicado.");
		}
		
		TipoHabitacion tipoHabitacion = new TipoHabitacion(nombre);
		
		this.tiposHabitacion.put(tipoHabitacion.getNombre(), tipoHabitacion);
		
		return tipoHabitacion;
	}
	
	public Set<Cliente> buscarCliente(String patronNombreCliente)
	{
		Set<Cliente> clientesEncontrados = new HashSet<Cliente>();
		
		for (Cliente cliente : this.clientes.values())
		{
			if (cliente.coincideElNombre(patronNombreCliente))
			{
				clientesEncontrados.add(cliente);
			}
		}
		
		return clientesEncontrados;
	}
	
	public Cliente seleccionarCliente(String rut) throws Exception
	{
		Cliente cliente = this.clientes.get(rut);
		
		if (cliente == null)
		{
			throw new Exception("No existe un cliente con el nombre indicado.");
		}
		
		return cliente;
		
	}
	
	public Boolean confirmarDisponibilidad(String nombreHotel , String nombreTipoHabitación, GregorianCalendar fechainicio, GregorianCalendar fechafin)
	{
		return true;
	}
	
	public Reserva registrarReserva(Cliente cliente, String nombreHotel, String nombreTipoHotel, GregorianCalendar fechaInicial, GregorianCalendar fechaFinal, Boolean modificadoPorHuesped)
	{
		return null;
	}
	
	public Set <Hotel> sugerirAlternativas(String pais, String nombreTipoHotel, GregorianCalendar fechaInicial, GregorianCalendar fechaFinal)
	{
		return null;
	}
	
	public Set<Reserva> buscarReservaDelCliente(Cliente clienteSeleccionado)
	{
		return null;
	}
	
	public Reserva seleccionarReserva(Long codigo)
	{
		return null;
		
	}
	
	public Reserva modificarReserva(Reserva reserva, Cliente cliente, String nombreHotel, String nombreTipoHotel, GregorianCalendar fechaInicial, GregorianCalendar fechaFinal, Boolean modificadoPorHuesped)
	{
		return null;
	}
	
	public Set<Reserva> buscarReservasPendientes(String nombreHotel)
	{
		return null;
		
	}
	
	public Reserva buscarReserva(String codigoReserva)
	{
		return null;	
	}
	
	public Reserva registrarHuesped(Reserva reserva, String nombre, String documento)
	{
		return null;
	}
	
	public Reserva tomarReserva(Reserva reserva)
	{
		return null;
	}
	
	public Set<Reserva> buscarReservasDelCliente(Cliente clienteSeleccionado)
	{
		return null;
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
			throw new Exception("No existe un tipo de habitación con el nombre indicado.");
		}
		
		return tipoHabitacion;
	}
	
	public String getNombre()
	{
		return this.nombre;
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
}
