package org.tds.sgh.system;

import org.tds.sgh.business.CadenaHotelera;
import org.tds.sgh.business.Reserva;
import org.tds.sgh.dtos.DTO;
import org.tds.sgh.dtos.ReservaDTO;

public class SRControllerModificarReserva extends SRController {

	public SRControllerModificarReserva(CadenaHotelera ch) {
		super(ch);
	}

	@Override
	public ReservaDTO seleccionarReserva(long codigoReserva) throws Exception {
		Reserva reservaSeleccionada = this.cadenaHotelera.seleccionarReservaModRes(codigoReserva, cliente);
	
		reserva = reservaSeleccionada;
		return DTO.getInstance().map(reservaSeleccionada);
	}
	
}
