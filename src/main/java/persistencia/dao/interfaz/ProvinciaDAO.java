package persistencia.dao.interfaz;

import java.util.List;

import dto.PaisDTO;
import dto.ProvinciaDTO;

public interface ProvinciaDAO {

	public boolean insert(ProvinciaDTO provinvia);

	public boolean delete(ProvinciaDTO provinvia);
	
	public List<ProvinciaDTO> readAll(PaisDTO pais);
	
	public boolean update(ProvinciaDTO provinvia);
}
