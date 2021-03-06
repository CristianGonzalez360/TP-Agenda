package persistencia.dao.mysql;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import persistencia.conexion.Conexion;
import persistencia.dao.interfaz.PersonaDAO;
import dto.LocalidadDTO;
import dto.PersonaDTO;

public class PersonaDAOSQL implements PersonaDAO {
	private static final String insert = "INSERT INTO personas(idPersona,nombre,telefono,calle,Nacimiento,altura,piso,departamento,email,localidad,tipoDeContacto,deporte) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String delete = "DELETE FROM personas WHERE idPersona = ?";
	private static final String readall = "SELECT * FROM personas";
	private static final String update = "UPDATE `agenda`.`personas` SET `Nombre` = ?, `Telefono` = ?, `Calle` = ?, `Nacimiento` = ?, `Altura` = ?, `Piso` = ?, `Departamento` = ?, `Email` = ?,`Localidad` = ?,`tipoDeContacto` = ?, `deporte` = ? WHERE `idPersona` = ?";

	public boolean insert(PersonaDTO persona) {
		boolean ret = false;
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(insert);
			statement.setInt(1, persona.getIdPersona());
			statement.setString(2, persona.getNombre());
			statement.setString(3, persona.getTelefono());
			statement.setString(4, persona.getCalle());
			if(persona.getNacimiento() != null)
				statement.setDate(5, new Date(persona.getNacimiento().getTime()));
			else
				statement.setDate(5, null);
			statement.setString(6, persona.getAltura());
			statement.setString(7, persona.getPiso());
			statement.setString(8, persona.getDepartamento());
			statement.setString(9, persona.getEmail());
			if(persona.getLocalidad() != null)
				statement.setInt(10, persona.getLocalidad().getId());
			else
				statement.setInt(10, 0);
			statement.setInt(11, persona.getTipoContacto().getId()); 
			if(persona.getDeporte() != null)
				statement.setInt(12, persona.getDeporte().getId());
			else
				statement.setInt(12, 0);
			if (statement.executeUpdate() > 0)
				ret = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	public boolean delete(PersonaDTO persona_a_eliminar) {
		boolean ret = false;
		PreparedStatement statement;
		int chequeoUpdate = 0;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(delete);
			statement.setString(1, Integer.toString(persona_a_eliminar.getIdPersona()));
			chequeoUpdate = statement.executeUpdate();
			if (chequeoUpdate > 0)
				ret = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	public List<PersonaDTO> readAll() {
		PreparedStatement statement;
		ResultSet resultSet;
		ArrayList<PersonaDTO> personas = new ArrayList<PersonaDTO>();
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(readall);
			resultSet = statement.executeQuery();
			LocalidadDAOSQL localidad = new LocalidadDAOSQL();
			TipoContactoDAOSQL tipoDeContacto = new TipoContactoDAOSQL();
			DeporteDAOSQL deporte = new DeporteDAOSQL();
			while (resultSet.next()) {
				personas.add(new PersonaDTO(resultSet.getInt("idPersona"), resultSet.getString("Nombre"),
						resultSet.getString("Telefono"), resultSet.getString("Calle"), resultSet.getDate("Nacimiento"),
						resultSet.getString("Altura"), resultSet.getString("Piso"), resultSet.getString("Departamento"),
						localidad.get(resultSet.getInt("localidad")), resultSet.getString("Email"),
						tipoDeContacto.get(resultSet.getInt("tipoDeContacto")), deporte.get(resultSet.getInt("deporte"))));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return personas;
	}

	public boolean update(PersonaDTO persona) {
		boolean ret = false;
		PreparedStatement statement;
		Conexion conexion = Conexion.getConexion();
		try {
			statement = conexion.getSQLConexion().prepareStatement(update);
			statement.setString(1, persona.getNombre());
			statement.setString(2, persona.getTelefono());
			statement.setString(3, persona.getCalle());
			if(persona.getNacimiento() != null)
				statement.setDate(4, new Date(persona.getNacimiento().getTime()));
			else
				statement.setDate(4, null);
			statement.setString(5, persona.getAltura());
			statement.setString(6, persona.getPiso());
			statement.setString(7, persona.getDepartamento());
			statement.setString(8, persona.getEmail());
			if(persona.getLocalidad() != null)
				statement.setInt(9, persona.getLocalidad().getId());
			else
				statement.setInt(9, 0);
			statement.setInt(10, persona.getTipoContacto().getId()); 
			if(persona.getDeporte() != null)
				statement.setInt(11, persona.getDeporte().getId());
			else
				statement.setInt(11, 0);
			statement.setInt(12, persona.getIdPersona());

			if (statement.executeUpdate() > 0)
				ret = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
}
