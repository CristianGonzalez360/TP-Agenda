package persistencia.conexion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DBProperties {
	
	private Properties dbProperties;
	private static DBProperties instance;
	private Logger log = Logger.getLogger(DBProperties.class);

	public static DBProperties getInstance() {
		if (instance == null)
			instance = new DBProperties();
		return instance;
	}
	
	private DBProperties() {
		readProperties();
	}

	public Properties getDbProperties() {
		return dbProperties;
	}

	public String get(String key) {
		return dbProperties.getProperty(key);
	}

	public void put(String key, String value) {
		dbProperties.put(key, value);
	}

	/*
	 * Lee las propiedades de la base de datos para poder conectarce
	 */
	private void readProperties() {
		dbProperties = new Properties();
		try {
			dbProperties.load(new FileInputStream(System.getProperty("user.dir") +"/config/dataBase.properties"));
		} catch (IOException e) {
			log.error("No se encontro archivo de configuración.");
			log.info("creando archivo de configuración");
			File folder = new File("config");
			if (!folder.exists()) {
				folder.mkdir();
			}
			guardar();
		}
	}

	public void guardar() {
		try {
			FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "/config/dataBase.properties");
			dbProperties.store(fos, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
