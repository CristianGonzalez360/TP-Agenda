package persistencia.dao.interfaz;

import java.util.List;

import dto.DeporteDTO;

public interface DeporteDAO {
	public boolean insert(DeporteDTO deporte);

	public boolean delete(DeporteDTO deporte);
	
	public List<DeporteDTO> readAll();
	
	public boolean update(DeporteDTO deporte);
}
