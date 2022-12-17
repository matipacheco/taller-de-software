package org.tds.sgh.system;

import org.tds.sgh.business.CadenaHotelera;


public class ControllerFactory implements IControllerFactory
{
	// --------------------------------------------------------------------------------------------
	
	private CadenaHotelera cadenaHotelera;
	
	// --------------------------------------------------------------------------------------------
	
	public ControllerFactory(CadenaHotelera cadenaHotelera)
	{
		this.cadenaHotelera = cadenaHotelera;
	}
	
	// --------------------------------------------------------------------------------------------
	
	@Override
	public ICadenaController createCadenaController()
	{
		return new SRController(this.cadenaHotelera);
	}
	
	@Override
	public ICancelarReservaController createCancelarReservaController()
	{
		return new SRController(this.cadenaHotelera);
	}
	
	@Override
	public IHacerReservaController createHacerReservaController()
	{
		return new SRController(this.cadenaHotelera);
	}
	
	@Override
	public IModificarReservaController createModificarReservaController()
	{
		return new SRController(this.cadenaHotelera);
	}
	
	@Override
	public ITomarReservaController createTomarReservaController()
	{
		return new SRController(this.cadenaHotelera);
	}
}
