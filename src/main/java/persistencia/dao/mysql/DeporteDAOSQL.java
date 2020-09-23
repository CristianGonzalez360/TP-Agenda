package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import dto.DeporteDTO;
import dto.TipoContactoDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.DeporteDAO;

public class DeporteDAOSQL implements DeporteDAO {
	
	private static final String insert = "INSERT INTO deporte(nombre) VALUES (?)";
	private static final String delete = "DELETE FROM deporte WHERE id = ?";
	private static final String readall = "SELECT * FROM deporte";
	private static final String update = "UPDATE deporte SET `nombre` = ? WHERE id = ?";


	@Override
	public boolean insert(DeporteDTO deporte) {
		boolean ret = false;
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setString(1, deporte.getNombre());
			if (statement.executeUpdate() > 0)
				ret = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public boolean delete(DeporteDTO deporte) {
		boolean ret = false;
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, deporte.getId());
			chequeoUpdate = statement.executeUpdate();
			if (chequeoUpdate > 0) // Si se ejecutó devuelvo true
				ret = true;
		} catch (MySQLIntegrityConstraintViolationException e) {
			JOptionPane.showMessageDialog(null, "Hay contactos que les gusta este doporte, no se puede borrar!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public List<DeporteDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<DeporteDTO> deportes = new ArrayList<>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				DeporteDTO deporte = new DeporteDTO();
				deporte.setId(resultSet.getInt("id"));
				deporte.setNombre(resultSet.getString("nombre"));
				deportes.add(deporte);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deportes;
	}

	@Override
	public boolean update(DeporteDTO deporte) {
		boolean ret = false;
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, deporte.getNombre());
			statement.setInt(2, deporte.getId());
			if (statement.executeUpdate() > 0) // Si se ejecut� devuelvo true
				ret = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

}
