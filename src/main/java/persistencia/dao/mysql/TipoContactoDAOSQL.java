package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import dto.TipoContactoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.TipoContactoDAO;

public class TipoContactoDAOSQL implements TipoContactoDAO {

	private static final String insert = "INSERT INTO tipoContacto(tipo) VALUES (?)";
	private static final String delete = "DELETE FROM tipoContacto WHERE idTipoContacto = ?";
	private static final String readall = "SELECT * FROM tipoContacto";
	private static final String update = "UPDATE tipoContacto SET `tipo` = ? WHERE idTipoContacto = ?";

	@Override
	public boolean insert(TipoContactoDTO tipoContacto) {
		boolean ret = false;
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setString(1, tipoContacto.getTipo());
			if (statement.executeUpdate() > 0)
				ret = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public boolean delete(TipoContactoDTO tipoContacto_a_eliminar) {
		boolean ret = false;
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, tipoContacto_a_eliminar.getIdTipoContacto());
			chequeoUpdate = statement.executeUpdate();
			if (chequeoUpdate > 0) // Si se ejecutó devuelvo true
				ret = true;
		} catch (MySQLIntegrityConstraintViolationException e) {
			JOptionPane.showMessageDialog(null, "Hay contactos de este tipo, no se puede borrar!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public List<TipoContactoDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<TipoContactoDTO> tiposDeContacto = new ArrayList<TipoContactoDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				TipoContactoDTO tipoContacto = new TipoContactoDTO();
				tipoContacto.setIdTipoContacto(resultSet.getInt("idTipoContacto"));
				tipoContacto.setTipo(resultSet.getString("tipo"));
				tiposDeContacto.add(tipoContacto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tiposDeContacto;
	}

	@Override
	public boolean update(TipoContactoDTO tipoContacto_a_editar) {
		boolean ret = false;
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, tipoContacto_a_editar.getTipo());
			statement.setInt(2, tipoContacto_a_editar.getIdTipoContacto());
			if (statement.executeUpdate() > 0) // Si se ejecut� devuelvo true
				ret = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	public TipoContactoDTO get(int id) {
		String query = "SELECT * FROM tipoContacto WHERE idTipoContacto = ?";
		ResultSet resultSet;
		TipoContactoDTO tipoContacto = null;
		Conexion conexion = Conexion.getConexion();
		PreparedStatement statement;
		try {
			statement = conexion.getSQLConexion().prepareStatement(query);
			statement.setInt(1, id);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				tipoContacto = new TipoContactoDTO();
				tipoContacto.setIdTipoContacto(resultSet.getInt("idTipoContacto"));
				tipoContacto.setTipo(resultSet.getString("tipo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tipoContacto;
	}
}
