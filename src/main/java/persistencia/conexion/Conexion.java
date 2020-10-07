package persistencia.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class Conexion {
	public static Conexion instancia;
	private Connection connection;
	private boolean conectado;
	private DBProperties dbprops = DBProperties.getInstance();
	private Logger log = Logger.getLogger(Conexion.class);

	private Conexion() {
	}

	public static Conexion getConexion() {
		if (instancia == null) {
			instancia = new Conexion();
		}
		return instancia;
	}

	public Connection getSQLConexion() {
		return this.connection;
	}

	public void cerrarConexion() {
		try {
			if(this.connection != null) {
				this.connection.close();
				log.info("Conexion cerrada");
			}
		} catch (SQLException e) {
			log.error("Error al cerrar la conexión!", e);
		}
		instancia = null;
	}

	public boolean conectar() {
		boolean ret = false;
		try {
			String connectionString = dbprops.get("db_prefijo") + "//" + dbprops.get("db_ip") + ":"
					+ dbprops.get("db_puerto") + "/" + dbprops.get("db_nombre") + "?autoReconnect=true&useSSL=false";
			
			Class.forName("com.mysql.jdbc.Driver"); // quitar si no es necesario
			
			connection = DriverManager.getConnection(connectionString, dbprops.get("db_usuario"),
					dbprops.get("db_password"));
			
			
			//this.connection = DriverManager.getConnection(
			//		"jdbc:mysql://localhost:3306/agenda?autoReconnect=true&useSSL=false", "root", "root");
			// le agregue ?autoReconnect=true&useSSL=false para evitar el warning--

			// this.connection.setAutoCommit(false); saque esto porque sino no guarda.
			log.info("Conexión exitosa");
			ret = true;
		} catch (Exception e) {
			log.error("Conexión fallida", e);
		}
		conectado = ret;
		return ret;
	}
	
	public boolean estaConectado() {
		return conectado;
	}
}
