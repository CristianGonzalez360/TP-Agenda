package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import dto.LocalidadDTO;
import dto.ProvinciaDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.LocalidadDAO;

public class LocalidadDAOSQL implements LocalidadDAO {

	private static final String insert = "INSERT INTO localidad(nombre, provincia) VALUES (?,?)";
	private static final String delete = "DELETE FROM localidad WHERE idlocalidad = ?";
	private static final String readall = "SELECT * FROM localidad WHERE provincia = ?";
	private static final String update = "UPDATE localidad SET `nombre` = ? WHERE idLocalidad = ?";

	@Override
	public boolean insert(LocalidadDTO localidad) {
		boolean ret = false;
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setString(1, localidad.getNombre());
			statement.setInt(2, localidad.getProvincia().getId());
			if (statement.executeUpdate() > 0) // Si se ejecut� devuelvo true
				ret = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public boolean delete(LocalidadDTO localidad_a_eliminar) {
		boolean ret = false;
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(localidad_a_eliminar.getId()));
			chequeoUpdate = statement.executeUpdate();
			if (chequeoUpdate > 0) // Si se ejecutó devuelvo true
				ret = true;
		} catch (MySQLIntegrityConstraintViolationException e) {
			JOptionPane.showMessageDialog(null, "Un contacto vive en esta localidad, no se puede borrar!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;
	}

	@Override
	public List<LocalidadDTO> readAll(ProvinciaDTO provincia) {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<LocalidadDTO> localidades = new ArrayList<LocalidadDTO>();
		Conexion conexion = Conexion.getConexion();
		try {

			statement = conexion.getSQLConexion().prepareStatement(readall);
			statement.setInt(1, provincia.getId());
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				LocalidadDTO localidad = new LocalidadDTO(resultSet.getString("nombre"));
				localidad.setId(resultSet.getInt("idlocalidad"));
				localidad.setProvincia(provincia);
				localidades.add(localidad);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return localidades;
	}

	@Override
	public boolean update(LocalidadDTO localidad) {
		boolean ret = false;
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, localidad.getNombre());
			statement.setInt(2, localidad.getId());
			if (statement.executeUpdate() > 0) // Si se ejecut� devuelvo true
				ret = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public LocalidadDTO get(int id) {
		String query = "SELECT * FROM localidad WHERE idlocalidad = ?";
		ResultSet resultSet;
		LocalidadDTO localidad = null;
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(query);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();
			ProvinciaDAOSQL provincia = new ProvinciaDAOSQL();
			while (resultSet.next()) {
				localidad = new LocalidadDTO(resultSet.getString("nombre"));
				localidad.setId(resultSet.getInt("idlocalidad"));
				localidad.setProvincia(provincia.get(resultSet.getInt("provincia")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return localidad;
	}

}
