package it.prova.dao.televisore;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import it.prova.dao.AbstractMySQLDAO;
import it.prova.model.Televisore;

public class TelevisoreDAOImpl extends AbstractMySQLDAO implements TelevisoreDAO {

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public TelevisoreDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Televisore> list() throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		ArrayList<Televisore> result = new ArrayList<Televisore>();

		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select * from televisore")) {

			while (rs.next()) {
				Televisore televisoreTemp = new Televisore();
				televisoreTemp.setMarca(rs.getString("MARCA"));
				televisoreTemp.setModello(rs.getString("MODELLO"));
				televisoreTemp.setPollici(rs.getInt("POLLICI"));
				televisoreTemp.setDataProduzione(
						rs.getDate("DATAPRODUZIONE") != null ? rs.getDate("DATAPRODUZIONE").toLocalDate() : null);
				televisoreTemp.setId(rs.getLong("ID"));
				result.add(televisoreTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	public Televisore get(Long idInput) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		Televisore result = null;
		try (PreparedStatement ps = connection.prepareStatement("select * from televisore where id=?")) {

			ps.setLong(1, idInput);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					result = new Televisore();
					result.setMarca(rs.getString("MARCA"));
					result.setModello(rs.getString("MODELLO"));
					result.setPollici(rs.getInt("POLLICI"));
					result.setDataProduzione(
							rs.getDate("DATAPRODUZIONE") != null ? rs.getDate("DATAPRODUZIONE").toLocalDate() : null);
					result.setId(rs.getLong("ID"));
				} else {
					result = null;
				}
			} // niente catch qui

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	public int update(Televisore input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"UPDATE televisore set marca=?, modello=?, pollici=?, dataproduzione=? where id=?;")) {
			ps.setString(1, input.getMarca());
			ps.setString(2, input.getModello());
			ps.setInt(3, input.getPollici());
			ps.setDate(4, java.sql.Date.valueOf(input.getDataProduzione()));
			ps.setLong(5, input.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;

	}

	public int insert(Televisore input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO televisore (marca, modello, pollici, dataproduzione) VALUES (?, ?, ?, ?);")) {
			ps.setString(1, input.getMarca());
			ps.setString(2, input.getModello());
			ps.setInt(3, input.getPollici());
			ps.setDate(4, java.sql.Date.valueOf(input.getDataProduzione()));
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	public int delete(Long idInput) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (idInput == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("DELETE FROM televisore where id=?;")) {
			ps.setLong(1, idInput);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	public List<Televisore> findByExample(Televisore input) throws Exception {
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		ArrayList<Televisore> result = new ArrayList<Televisore>();

		String query = "select * from user where 1=1 ";
		if (input.getMarca() != null && !input.getMarca().isEmpty()) {
			query += " and marca like '" + input.getMarca() + "%' ";
		}

		if (input.getModello() != null && !input.getModello().isEmpty()) {
			query += " and modello like '" + input.getModello() + "%' ";
		}

		if (input.getPollici() != 0) {
			query += " and login like '" + input.getPollici() + "%' ";
		}

		if (input.getDataProduzione() != null) {
			query += " and dataproduzione='" + java.sql.Date.valueOf(input.getDataProduzione()) + "' ";
		}

		try (Statement ps = connection.createStatement()) {
			ResultSet rs = ps.executeQuery(query);

			while (rs.next()) {
				Televisore televisoreTemp = new Televisore();
				televisoreTemp.setMarca(rs.getString("MARCA"));
				televisoreTemp.setModello(rs.getString("MODELLO"));
				televisoreTemp.setPollici(rs.getInt("POLLICI"));
				televisoreTemp.setDataProduzione(
						rs.getDate("DATAPRODUZIONE") != null ? rs.getDate("DATAPRODUZIONE").toLocalDate() : null);
				televisoreTemp.setId(rs.getLong("ID"));
				result.add(televisoreTemp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Televisore ilTelevisorePiuGrande() throws Exception {

		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		Televisore result = null;
		try (Statement ps = connection.createStatement()) {

			try (ResultSet rs = ps.executeQuery("select * from televisore t order by t.pollici desc;")) {
				if (rs.next()) {
					result = new Televisore();
					result.setMarca(rs.getString("MARCA"));
					result.setModello(rs.getString("MODELLO"));
					result.setPollici(rs.getInt("POLLICI"));
					result.setDataProduzione(
							rs.getDate("DATAPRODUZIONE") != null ? rs.getDate("DATAPRODUZIONE").toLocalDate() : null);
					result.setId(rs.getLong("ID"));
				} else {
					result = null;
				}
			} // niente catch qui

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int quantiProdottiInUnIntervalloDiDate(LocalDate before, LocalDate after) throws Exception {

		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (before == null || after == null)
			throw new Exception("Valore di input non ammesso.");
		int result = 0;

		try (PreparedStatement ps = connection
				.prepareStatement("select count(*) as count from televisore where dataproduzione between ? and ?;")) {
			ps.setDate(1, java.sql.Date.valueOf(before));
			ps.setDate(2, java.sql.Date.valueOf(after));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {

				result = rs.getInt("count");

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public String[] MarcheDiTelevisoriUltimiSeiM() throws Exception {

		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		List<String> marche = new ArrayList<>();

		try (PreparedStatement ps = connection.prepareStatement("select * from televisore where dataproduzione>?")) {

			ps.setDate(1, Date.valueOf(LocalDate.now().minusMonths(6)));
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {

					marche.add(rs.getString("marca"));
				}
			} // niente catch qui

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		String[] result = new String[marche.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = marche.get(i);
		}
		return result;
	}

}
