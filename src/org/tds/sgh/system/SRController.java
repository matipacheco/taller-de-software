package org.tds.sgh.system;

import java.util.GregorianCalendar;
import java.util.Set;

import org.tds.sgh.business.CadenaHotelera;
import org.tds.sgh.business.Cliente;
import org.tds.sgh.business.Habitacion;
import org.tds.sgh.business.Hotel;
import org.tds.sgh.business.Reserva;
import org.tds.sgh.business.TipoHabitacion;
import org.tds.sgh.dtos.ClienteDTO;
import org.tds.sgh.dtos.DTO;
import org.tds.sgh.dtos.HabitacionDTO;
import org.tds.sgh.dtos.HotelDTO;
import org.tds.sgh.dtos.ReservaDTO;
import org.tds.sgh.dtos.TipoHabitacionDTO;

public class SRController implements IAltaClienteController, ICadenaController, ICancelarReservaController, IHacerReservaController, IIdentificarClienteEnRecepcionController, IIdentificarReservaClienteController, IModificarReservaController, ITomarReservaController  {
	
	private Cliente cliente;
	private Reserva reserva;
	private CadenaHotelera cadenaHotelera;
	
	public SRController(CadenaHotelera ch) {
		this.cadenaHotelera = ch;
	}

	@Override
	public ClienteDTO registrarCliente(String rut, String nombre, String direccion, String telefono, String mail) throws Exception {			
		Cliente clienteCreado = this.cadenaHotelera.registrarCliente(rut, nombre, direccion, telefono, mail);
		cliente = clienteCreado;

		return DTO.getInstance().map(clienteCreado);
	}

	@Override
	public Set<ReservaDTO> buscarReservasPendientes(String nombreHotel) throws Exception {
		Set<Reserva> reservasPendientes = this.cadenaHotelera.buscarReservasPendientes(nombreHotel);
		return DTO.getInstance().mapReservas(reservasPendientes);
	}

	@Override
	public ReservaDTO seleccionarReserva(long codigoReserva) throws Exception {
		Reserva reservaSeleccionada = this.cadenaHotelera.seleccionarReserva(codigoReserva);
		reserva = reservaSeleccionada;
		return DTO.getInstance().map(reservaSeleccionada);
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
		Cliente clienteSeleccionado = this.cadenaHotelera.seleccionarCliente(rut);
		cliente = clienteSeleccionado;
		return DTO.getInstance().map(clienteSeleccionado);
	}

	@Override
	public boolean confirmarDisponibilidad(String nombreHotel, String nombreTipoHabitacion,
			GregorianCalendar fechaInicio, GregorianCalendar fechaFin) throws Exception {
		return this.cadenaHotelera.confirmarDisponibilidad(nombreHotel, nombreTipoHabitacion, fechaInicio, fechaFin);
	}

	@Override
	public ReservaDTO registrarReserva(String nombreHotel, String nombreTipoHabitacion, GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin, boolean modificablePorHuesped) throws Exception {
		Reserva reservaCreada = this.cadenaHotelera.registrarReserva(cliente, nombreHotel, nombreTipoHabitacion, fechaInicio, fechaFin, modificablePorHuesped);
		reserva = reservaCreada;
		return DTO.getInstance().map(reservaCreada);
	}

	@Override
	public Set<HotelDTO> sugerirAlternativas(String pais, String nombreTipoHabitacion, GregorianCalendar fechaInicio,
			GregorianCalendar fechaFin) throws Exception {
		Set<Hotel> hoteles = this.cadenaHotelera.sugerirAlternativas(pais, nombreTipoHabitacion, fechaInicio, fechaFin);
		return DTO.getInstance().mapHoteles(hoteles);
	}

	@Override
	public ReservaDTO cancelarReservaDelCliente() throws Exception {
		Reserva reservaCancelada = this.cadenaHotelera.cancelarReservaDelCliente(reserva); 
		return DTO.getInstance().map(reservaCancelada);
	}

	@Override
	public ClienteDTO agregarCliente(String rut, String nombre, String direccion, String telefono, String mail) throws Exception
	{			
			return DTO.getInstance().map(this.cadenaHotelera.registrarCliente(rut, nombre, direccion, telefono, mail));
	}

	@Override
	public HabitacionDTO agregarHabitacion(String nombreHotel, String nombreTipoHabitacion, String nombre)
			throws Exception {
		Hotel hotel = this.cadenaHotelera.buscarHotel(nombreHotel);
		TipoHabitacion tipoHabitacion = this.cadenaHotelera.buscarTipoHabitacion(nombreTipoHabitacion);
		Habitacion habitacionNueva = this.cadenaHotelera.agregarHabitacion(hotel, tipoHabitacion, nombre);
		return DTO.getInstance().map(hotel, habitacionNueva);
	}

	@Override
	public HotelDTO agregarHotel(String nombre, String pais) throws Exception {
		return DTO.getInstance().map(this.cadenaHotelera.agregarHotel(nombre, pais));
	}

	@Override
	public TipoHabitacionDTO agregarTipoHabitacion(String nombre) throws Exception {
		TipoHabitacion tipoHabitacion = this.cadenaHotelera.agregarTipoHabitacion(nombre);
		return DTO.getInstance().map(tipoHabitacion);
	}

	@Override
	public Set<ClienteDTO> getClientes() {
		return DTO.getInstance().mapClientes(this.cadenaHotelera.listarClientes());
	}

	@Override
	public Set<HabitacionDTO> getHabitaciones(String nombreHotel) throws Exception {
		Hotel hotel = this.cadenaHotelera.buscarHotel(nombreHotel);
		return DTO.getInstance().mapHabitaciones(hotel, this.cadenaHotelera.listarHabitaciones(nombreHotel));
	}

	@Override
	public Set<HotelDTO> getHoteles() {
		return DTO.getInstance().mapHoteles(this.cadenaHotelera.listarHoteles());
	}

	@Override
	public Set<TipoHabitacionDTO> getTiposHabitacion() {
		return DTO.getInstance().mapTiposHabitacion(this.cadenaHotelera.listarTiposHabitacion());
	}	
}
