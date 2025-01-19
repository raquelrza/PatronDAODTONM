package mapper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelo.Entity.Reserva;

public class ReservaAdapter implements Adapter {
	private Reserva reserva;

	public ReservaAdapter(Reserva reserva) {
		super();
		this.reserva = reserva;
	}

	@Override
	public void adapt(PreparedStatement instruccion) throws SQLException {
		instruccion.setLong(1, reserva.getIdPersona());
		instruccion.setLong(2, reserva.getIdHotel());

		/**
		 * solo funcona si la bbdd esperaw un date, si espera un varchar habria que
		 * poner setString y convertirlo String f = reserva.getFecha().toString();
		 */

		instruccion.setDate(3, Date.valueOf(reserva.getFecha()));

	}

}
