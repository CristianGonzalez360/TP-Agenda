package persistencia.dao.interfaz;

import java.util.List;

import dto.LocalidadDTO;
import dto.PersonaDTO;

public interface PersonaDAO 
{
	
	public boolean insert(PersonaDTO persona);

	public boolean delete(PersonaDTO persona_a_eliminar);
	
	public List<PersonaDTO> readAll();
	
	public boolean update(PersonaDTO persona_a_modificar); //editar

	public LocalidadDTO get(int id);
}
