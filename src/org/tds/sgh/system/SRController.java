package org.tds.sgh.system;

import java.util.GregorianCalendar;
import java.util.Set;

//amarillo
import org.tds.sgh.business.CadenaHotelera;
import org.tds.sgh.business.Cliente;
import org.tds.sgh.business.Reserva;
import org.tds.sgh.dtos.ClienteDTO;
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

	@Override
	public ClienteDTO registrarCliente(String rut, String nombre, String direccion, String telefono, String mail) {
		// this.cadenaHotelera.registrarCliente(rut, nombre, direccion, telefono, mail);
		return null;
	}

	@Override
	public Set<ReservaDTO> buscarReservasPendientes(String nombreHotel) throws Exception {
		// this.cadenaHotelera.buscarReservasPendientes(nombreHotel);
		return null;
	}

	@Override
	public ReservaDTO seleccionarReserva(long codigoReserva) throws Exception {
		// this.cadenaHotelera.seleccionarReserva(codigoReserva);
		return null;
	}

	@Override
	public ReservaDTO registrarHuesped(String nombre, String documento) throws Exception {
		// this.cadenaHotelera.registrarHuesped(reserva, nombre, documento);
		return null;
	}

	@Override
	public ReservaDTO tomarReserva() throws Exception {
		// this.cadenaHotelera.tomarReserva(reserva);
		return null;
	}

	@Override
	public ReservaDTO modificarReserva(String nombreHotel, String nombreTipoHabitacion, GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin, boolean modificablePorHuesped) throws Exception {
		// this.cadenaHotelera.modificarReserva(reserva, cliente, nombreHotel, nombreTipoHabitacion, fechaInicio, fechaFin, modificablePorHuesped);
		return null;
	}

	@Override
	public Set<ReservaDTO> buscarReservasDelCliente() throws Exception {
		// this.cadenaHotelera.buscarReservasDelCliente(cliente);
		return null;
	}

	@Override
	public Set<ClienteDTO> buscarCliente(String patronNombreCliente) {
		// this.cadenaHotelera.buscarCliente(patronNombreCliente);
		return null;
	}

	@Override
	public ClienteDTO seleccionarCliente(String rut) throws Exception {
		// this.cadenaHotelera.seleccionarCliente(rut);
		return null;
	}

	@Override
	public boolean confirmarDisponibilidad(String nombreHotel, String nombreTipoHabitacion,
			GregorianCalendar fechaInicio, GregorianCalendar fechaFin) throws Exception {
		// this.cadenaHotelera.confirmarDisponibilidad(nombreHotel, nombreTipoHabitacion, fechaInicio, fechaFin);
		return false;
	}

	@Override
	public ReservaDTO registrarReserva(String nombreHotel, String nombreTipoHabitacion, GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin, boolean modificablePorHuesped) throws Exception {
		// this.cadenaHotelera.registrarReserva(cliente, nombreHotel, nombreTipoHabitacion, fechaInicio, fechaFin, modificablePorHuesped);
		return null;
	}

	@Override
	public Set<HotelDTO> sugerirAlternativas(String pais, String nombreTipoHabitacion, GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin) throws Exception {
		// this.cadenaHotelera.sugerirAlternativas(pais, nombreTipoHabitacion, fechaInicio, fechaFin);
		return null;
	}

	@Override
	public ReservaDTO cancelarReservaDelCliente() throws Exception {
		// this.cadenaHotelera.cancelarReservaDelCliente(reserva); 
		return null;
	}

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
	
}
