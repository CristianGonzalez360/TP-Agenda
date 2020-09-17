package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.ProvinciaDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.ProvinciaDAO;

public class ProvinciaDAOSQL implements ProvinciaDAO {

	private static final String insert = "INSERT INTO provincia(nombre, pais) VALUES (?,?)";
	private static final String delete = "DELETE FROM provincia WHERE idProvincia = ?";
	private static final String readall = "SELECT * FROM provincia WHERE pais = ?";
	private static final String update = "UPDATE provincia SET `nombre` = ? WHERE idProvincia = ?";

	@Override
	public boolean insert(ProvinciaDTO provincia) {
		boolean ret = false;
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setString(1, provincia.getNombre());
			statement.setInt(2, provincia.getPais().getId());
			if (statement.executeUpdate() > 0)
				ret = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public boolean delete(ProvinciaDTO provinvia) {
		boolean ret = false;
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, provinvia.getId());
			chequeoUpdate = statement.executeUpdate();
			if (chequeoUpdate > 0) // Si se ejecutó devuelvo true
				ret = true;
		} catch (MySQLIntegrityConstraintViolationException e) {
			JOptionPane.showMessageDialog(null, "Hay localidades en esta provincia, no se puede borrar!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public List<ProvinciaDTO> readAll(PaisDTO pais) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<ProvinciaDTO> provincias = new ArrayList<>();
		Conexion conexion = Conexion.getConexion();
		LocalidadDAOSQL localidadDaoSql = new LocalidadDAOSQL();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			statement.setInt(1, pais.getId());
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				ProvinciaDTO provincia = new ProvinciaDTO();
				provincia.setId(resultSet.getInt("idProvincia"));
				provincia.setNombre(resultSet.getString("nombre"));
				provincia.setPais(pais);
				provincia.setLocalidades(localidadDaoSql.readAll(provincia));
				provincias.add(provincia);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return provincias;
	}

	@Override
	public boolean update(ProvinciaDTO provincia) {
		boolean ret = false;
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, provincia.getNombre());
			statement.setInt(2, provincia.getId());
			if (statement.executeUpdate() > 0) // Si se ejecut� devuelvo true
				ret = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	public ProvinciaDTO get(int id) {
		String query = "SELECT * FROM provincia WHERE idProvincia = ?";
		ResultSet resultSet;
		ProvinciaDTO provincia = null;
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(query);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			PaisDAOSQL pais = new PaisDAOSQL();
			while (resultSet.next()) {
				provincia = new ProvinciaDTO(resultSet.getString("nombre"));
				provincia.setId(resultSet.getInt("idProvincia"));
				provincia.setPais(pais.get(resultSet.getInt("pais")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return provincia;
	}

}
