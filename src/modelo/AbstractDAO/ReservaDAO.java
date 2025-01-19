package modelo.AbstractDAO;

import modelo.Entity.Reserva;

public interface ReservaDAO extends GenericDAO<Reserva, Long> {
	public Reserva findByPersonaHotel(String name, String hotel);
}
