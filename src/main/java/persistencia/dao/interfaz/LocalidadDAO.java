package persistencia.dao.interfaz;

import java.util.List;

import dto.LocalidadDTO;
import dto.ProvinciaDTO;

public interface LocalidadDAO {

	public boolean insert(LocalidadDTO localidad);

	public boolean delete(LocalidadDTO localidad);
	
	public List<LocalidadDTO> readAll(ProvinciaDTO provincia);
	
	public boolean update(LocalidadDTO localidad);
	
	public LocalidadDTO get(int id);
}
