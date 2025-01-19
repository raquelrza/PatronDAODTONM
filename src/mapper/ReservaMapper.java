package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import modelo.Entity.Reserva;

public class ReservaMapper implements Mapper<ResultSet, Reserva> {

	@Override
	public Reserva map(ResultSet result) {
		try {
			while (result.next()) {
				Object rs1 = result.getObject(1);
				Long convert1 = (Long) rs1;

				Object rs2 = result.getObject(2);
				Long convert2 = (Long) rs2;

				Object rs3 = result.getObject(3);
				LocalDate convert3 = (LocalDate) rs3;

				return new Reserva(convert1, convert2, convert3);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
