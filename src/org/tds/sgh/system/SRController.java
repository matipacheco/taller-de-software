package org.tds.sgh.system;

import java.util.GregorianCalendar;
import java.util.Set;

//amarillo
import org.tds.sgh.business.CadenaHotelera;
import org.tds.sgh.business.Cliente;
import org.tds.sgh.business.Hotel;
import org.tds.sgh.business.Reserva;
import org.tds.sgh.dtos.ClienteDTO;
import org.tds.sgh.dtos.DTO;
import org.tds.sgh.dtos.HabitacionDTO;
import org.tds.sgh.dtos.HotelDTO;
import org.tds.sgh.dtos.ReservaDTO;
import org.tds.sgh.dtos.TipoHabitacionDTO;
import org.tds.sgh.infrastructure.Infrastructure;

public class SRController implements IAltaClienteController, ICadenaController, ICancelarReservaController, IHacerReservaController, IIdentificarClienteEnRecepcionController, IIdentificarReservaClienteController, IModificarReservaController, ITomarReservaController  {
	
	private Cliente cliente;
	private Reserva reserva;
	private CadenaHotelera cadenaHotelera;
	
	public SRController(CadenaHotelera ch) {
		this.cadenaHotelera = ch;
	}
	
	public SRController(CadenaHotelera ch, Reserva res, Cliente cl) {
		this.cadenaHotelera = ch;
		this.reserva = res;
		this.cliente= cl;
	}

	@Override
	public ClienteDTO registrarCliente(String rut, String nombre, String direccion, String telefono, String mail) {
		try {			
			Cliente cliente = this.cadenaHotelera.registrarCliente(rut, nombre, direccion, telefono, mail);
			return DTO.getInstance().map(cliente);
		} catch(Exception e) {
			return null;
		}
	}

	@Override
	public Set<ReservaDTO> buscarReservasPendientes(String nombreHotel) throws Exception {
		Set<Reserva> reservasPendientes = this.cadenaHotelera.buscarReservasPendientes(nombreHotel);
		return DTO.getInstance().mapReservas(reservasPendientes);
	}

	@Override
	public ReservaDTO seleccionarReserva(long codigoReserva) throws Exception {
		Reserva reserva = this.cadenaHotelera.seleccionarReserva(codigoReserva);
		return DTO.getInstance().map(reserva);
	}

	@Override
	public ReservaDTO registrarHuesped(String nombre, String documento) throws Exception {
		Reserva reserva = this.cadenaHotelera.registrarHuesped(this.reserva, nombre, documento);
		return DTO.getInstance().map(reserva);
	}

	@Override
	public ReservaDTO tomarReserva() throws Exception {
		Reserva reserva = this.cadenaHotelera.tomarReserva(this.reserva);
		return DTO.getInstance().map(reserva);
	}

	@Override
	public ReservaDTO modificarReserva(String nombreHotel, String nombreTipoHabitacion, GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin, boolean modificablePorHuesped) throws Exception {
		Reserva reserva = this.cadenaHotelera.modificarReserva(this.reserva, cliente, nombreHotel, nombreTipoHabitacion, fechaInicio, fechaFin, modificablePorHuesped);
		return DTO.getInstance().map(reserva);
	}

	@Override
	public Set<ReservaDTO> buscarReservasDelCliente() throws Exception {
		Set<Reserva> reservasCliente = this.cadenaHotelera.buscarReservasDelCliente(cliente);
		return DTO.getInstance().mapReservas(reservasCliente);
	}

	@Override
	public Set<ClienteDTO> buscarCliente(String patronNombreCliente) {
		Set<Cliente> clientes = this.cadenaHotelera.buscarCliente(patronNombreCliente);
		return DTO.getInstance().mapClientes(clientes);
	}

	@Override
	public ClienteDTO seleccionarCliente(String rut) throws Exception {
		Cliente clientes = this.cadenaHotelera.seleccionarCliente(rut);
		return DTO.getInstance().map(cliente);
	}

	@Override
	public boolean confirmarDisponibilidad(String nombreHotel, String nombreTipoHabitacion,
			GregorianCalendar fechaInicio, GregorianCalendar fechaFin) throws Exception {
		return this.cadenaHotelera.confirmarDisponibilidad(nombreHotel, nombreTipoHabitacion, fechaInicio, fechaFin);
	}

	@Override
	public ReservaDTO registrarReserva(String nombreHotel, String nombreTipoHabitacion, GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin, boolean modificablePorHuesped) throws Exception {
		Reserva reserva = this.cadenaHotelera.registrarReserva(cliente, nombreHotel, nombreTipoHabitacion, fechaInicio, fechaFin, modificablePorHuesped);
		return DTO.getInstance().map(reserva);
	}

	@Override
	public Set<HotelDTO> sugerirAlternativas(String pais, String nombreTipoHabitacion, GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin) throws Exception {
		Set<Hotel> hoteles = this.cadenaHotelera.sugerirAlternativas(pais, nombreTipoHabitacion, fechaInicio, fechaFin);
		return DTO.getInstance().mapHoteles(hoteles);
	}

	/*
	@Override
	public ReservaDTO cancelarReservaDelCliente() throws Exception {
		Reserva reservaCancelada = this.cadenaHotelera.cancelarReservaDelCliente(reserva); 
		return DTO.getInstance().map(reservaCancelada);
	}*/

	/**
	 * Ninguno de estos están diseñados, pero tiene sentido que lo haga la cadena hotelera,
	 * pues esta conoce todos sus clientes, hoteles, y tipos de habitación.
	 *  
	 * Nota: al crear cliente / hotel / tipo de habitación hay que agregarlo a la lista de clientes de la cadena hotelera 
	 */
	@Override
	public ClienteDTO agregarCliente(String rut, String nombre, String direccion, String telefono, String mail)
			throws Exception {
		// this.cadenaHotelera.agregarCliente(rut, nombre, direccion, telefono, mail);
		return null;
	}

	@Override
	public HabitacionDTO agregarHabitacion(String nombreHotel, String nombreTipoHabitacion, String nombre)
			throws Exception {
		// ????
		return null;
	}

	@Override
	public HotelDTO agregarHotel(String nombre, String pais) throws Exception {
		// this.cadenaHotelera.agregarTipoHabitacion(nombre);
		return null;
	}

	@Override
	public TipoHabitacionDTO agregarTipoHabitacion(String nombre) throws Exception {
		// this.cadenaHotelera.agregarTipoHabitacion(nombre);
		return null;
	}

	@Override
	public Set<ClienteDTO> getClientes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<HabitacionDTO> getHabitaciones(String nombreHotel) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<HotelDTO> getHoteles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<TipoHabitacionDTO> getTiposHabitacion() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReservaDTO cancelarReservaDelCliente() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
