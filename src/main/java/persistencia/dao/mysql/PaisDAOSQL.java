package persistencia.dao.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import dto.PaisDTO;
import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PaisDAO;

public class PaisDAOSQL implements PaisDAO {

	private static final String insert = "INSERT INTO pais(nombre) VALUES (?)";
	private static final String delete = "DELETE FROM pais WHERE idPais = ?";
	private static final String readall = "SELECT * FROM pais";
	private static final String update = "UPDATE pais SET `nombre` = ? WHERE idPais = ?";

	@Override
	public boolean insert(PaisDTO pais) {
		boolean ret = false;
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setString(1, pais.getNombre());
			if (statement.executeUpdate() > 0)
				ret = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public boolean delete(PaisDTO pais) {
		boolean ret = false;
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setInt(1, pais.getId());
			chequeoUpdate = statement.executeUpdate();
			if (chequeoUpdate > 0) // Si se ejecutó devuelvo true
				ret = true;
		} catch (MySQLIntegrityConstraintViolationException e) {
			JOptionPane.showMessageDialog(null, "Hay provincias en este país, no se puede borrar!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public List<PaisDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet; // Guarda el resultado de la query
		ArrayList<PaisDTO> paises = new ArrayList<>();
		Conexion conexion = Conexion.getConexion();
		ProvinciaDAOSQL provinciaDaoSql = new ProvinciaDAOSQL();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				PaisDTO pais = new PaisDTO();
				pais.setId(resultSet.getInt("idPais"));
				pais.setNombre(resultSet.getString("nombre"));
				pais.setProvincias(provinciaDaoSql.readAll(pais));
				paises.add(pais);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return paises;
	}

	@Override
	public boolean update(PaisDTO pais) {
		boolean ret = false;
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, pais.getNombre());
			statement.setInt(2, pais.getId());
			if (statement.executeUpdate() > 0) // Si se ejecut� devuelvo true
				ret = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

}
