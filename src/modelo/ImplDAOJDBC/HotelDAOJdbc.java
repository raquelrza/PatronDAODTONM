package modelo.ImplDAOJDBC;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import mapper.HotelAdapter;
import mapper.HotelMapper;
import modelo.AbstractDAO.HotelDAO;
import modelo.Entity.Hotel;
import modelo.acceso.AccessJdbc;

public class HotelDAOJdbc implements HotelDAO {

	private AccessJdbc accessJdbc;
	private HotelMapper hotelMapper;

	public HotelDAOJdbc(AccessJdbc accessJdbc, HotelMapper hotelMapper) {
		super();
		this.accessJdbc = accessJdbc;
		this.hotelMapper = hotelMapper;
	}

	@Override
	public void create(Hotel entidad) {
		String sql = "INSERT INTO " + accessJdbc.getBBDD() + ".hotel(nombre) VALUES (?)";
		accessJdbc.update(sql, new HotelAdapter(entidad));

	}

	@Override
	public Collection<Hotel> findAll() {
		ResultSet rs = accessJdbc.execute("select * from hotel");
		Collection<Hotel> hoteles = new ArrayList<>();
		try {
			while (rs.next()) {
				hoteles.add(new HotelMapper().map(rs));

			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return hoteles;
	}

	@Override
	public Hotel findById(Long id) {
		String sql = "select * from " + accessJdbc.getBBDD() + ".hotel where id like " + id;
		ResultSet rs = accessJdbc.execute(sql);
		return hotelMapper.map(rs);
	}

	@Override
	public Hotel delete(Long id) {
		Hotel byId = findById(id);
		if (byId != null) {
			String sql = "delete from" + accessJdbc.getBBDD() + ".hotel where id = " + String.valueOf(id);
			accessJdbc.executeUpdate(sql);
			return byId;
		}
		return null;
	}

	@Override
	public Hotel findByName(String name) {
		String sql = "select * from " + accessJdbc.getBBDD() + ".hotel where nombre like '" + name + "'";
		ResultSet rs = accessJdbc.execute(sql);
		return hotelMapper.map(rs);
	}

}
