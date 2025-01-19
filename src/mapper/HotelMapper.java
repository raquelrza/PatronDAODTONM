package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Entity.Hotel;

public class HotelMapper implements Mapper<ResultSet, Hotel> {

	@Override
	public Hotel map(ResultSet resultado) {
		try {
			while (resultado.next()) {
				Object rs1 = resultado.getObject(1);
				long convert1 = (long) rs1;

				Object rs2 = resultado.getObject(2);
				String convert2 = (String) rs2;

				return new Hotel(convert2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
