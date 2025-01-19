package modelo.ImplDAOJDBC;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;

import mapper.ReservaAdapter;
import mapper.ReservaMapper;
import modelo.AbstractDAO.ReservaDAO;
import modelo.Entity.Hotel;
import modelo.Entity.Persona;
import modelo.Entity.Reserva;
import modelo.acceso.AccessJdbc;

public class RerservaDAOJdbc implements ReservaDAO {
	private AccessJdbc accessJdbc;
	private ReservaMapper reservaMapper;
	PersonaDAOJdbc persona;
	HotelDAOJdbc hotele;

	public RerservaDAOJdbc(AccessJdbc accessJdbc, ReservaMapper reservaMapper) {
		super();
		this.accessJdbc = accessJdbc;
		this.reservaMapper = reservaMapper;
	}

	@Override
	public void create(Reserva entidad) {
		String sql = "insert into " + accessJdbc.getBBDD() + ".reserva(idPersona,idHotel,fecha) values (?,?,?))";
		accessJdbc.update(sql, new ReservaAdapter(entidad));
	}

	@Override
	public Collection<Reserva> findAll() {
		ResultSet rs = accessJdbc.execute("select * from " + accessJdbc.getBBDD() + ".reserva");
		Collection<Reserva> reservas = new ArrayList<Reserva>();
		try {
			while (rs.next()) {
				reservas.add(new ReservaMapper().map(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return reservas;
	}

	@Override
	public Reserva findById(Long id) {
		String sql = "SELECT * FROM " + accessJdbc.getBBDD() + ".reserva WHERE id LIKE " + id;
		ResultSet conjuntoResultados = accessJdbc.execute(sql);
		return reservaMapper.map(conjuntoResultados);
	}

	@Override
	public Reserva delete(Long id) {
		Reserva byId = findById(id);
		if (byId != null) {
			String sql = "delete from " + accessJdbc.getBBDD() + ".reserva where idPersona= " + String.valueOf(id);
			accessJdbc.executeUpdate(sql);
			return byId;
		}
		return null;

	}

	@Override
	public Reserva findByPersonaHotel(String name, String hotel) {
		Persona p = persona.findByName(name);
		Long idPerson = p.getId();
		Hotel h = hotele.findByName(hotel);
		Long idHotel = h.getId();
		String sql = "select * from " + accessJdbc.getBBDD() + ".reserva where idPersona LIKE " + idPerson
				+ "and idHotel like " + idHotel;

		ResultSet execute = accessJdbc.execute(sql);
		return reservaMapper.map(execute);
	}

}
