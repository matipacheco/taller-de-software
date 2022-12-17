package org.tds.sgh.system;

//amarillo
import org.tds.sgh.business.CadenaHotelera;
import org.tds.sgh.dtos.ClienteDTO;
import org.tds.sgh.infrastructure.Infrastructure;

public class SRController implements IAltaClienteController  {
	
	private CadenaHotelera cadenaHotelera; 

	@Override
	public ClienteDTO registrarCliente(String rut, String nombre, String direccion, String telefono, String mail) {
		// Infrastructure.getInstance().getSistemaMensajeria().enviarMail(direccion, telefono, mail);
		// Infrastructure.getInstance().getCalendario().getHoy();
		// Infrastructure.getInstance().getCalendario().esAnterior(null, null)
	}
	
}
