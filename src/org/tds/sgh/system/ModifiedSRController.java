package org.tds.sgh.system;

import org.tds.sgh.business.CadenaHotelera;
import org.tds.sgh.business.Cliente;
import org.tds.sgh.business.Reserva;
import org.tds.sgh.dtos.DTO;
import org.tds.sgh.dtos.ReservaDTO;

public class ModifiedSRController extends SRController {

	private Cliente cliente;
	private Reserva reserva;
	private CadenaHotelera cadenaHotelera;
	
	public ModifiedSRController(CadenaHotelera ch) {
		super(ch);
	}
	
	@Override
	public ReservaDTO seleccionarReserva(long codigoReserva) throws Exception {
		if (cliente == null) {
			throw new Exception("Se debe seleccionar un Cliente previamente");
		}

		Reserva reservaSeleccionada = this.cadenaHotelera.seleccionarReserva(codigoReserva, cliente);
		reserva = reservaSeleccionada;
		return DTO.getInstance().map(reservaSeleccionada);
	}
}
