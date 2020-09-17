/**
 * 
 */
package persistencia.dao.mysql;

import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.LocalidadDAO;
import persistencia.dao.interfaz.PaisDAO;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.ProvinciaDAO;
import persistencia.dao.interfaz.TipoContactoDAO;

public class DAOSQLFactory implements DAOAbstractFactory {
	/*
	 * (non-Javadoc)
	 * 
	 * @see persistencia.dao.interfaz.DAOAbstractFactory#createPersonaDAO()
	 */
	public PersonaDAO createPersonaDAO() {
		return new PersonaDAOSQL();
	}

	@Override
	public LocalidadDAO createLocalidadDAO() {
		return new LocalidadDAOSQL();
	}

	@Override
	public TipoContactoDAO createTipoContactoDAO() {
		return new TipoContactoDAOSQL();
	}

	@Override
	public ProvinciaDAO createProvinciaDAO() {
		return new ProvinciaDAOSQL();
	}

	@Override
	public PaisDAO createPaisDAO() {
		return new PaisDAOSQL();
	}

}
