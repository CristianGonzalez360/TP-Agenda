package modelo;

import java.util.List;

import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoContactoDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.LocalidadDAO;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.TipoContactoDAO;

public class Agenda {
	private PersonaDAO persona;
	private LocalidadDAO localidad;
	private TipoContactoDAO tipoContacto;

	public Agenda(DAOAbstractFactory metodo_persistencia) {
		this.persona = metodo_persistencia.createPersonaDAO();
		this.localidad = metodo_persistencia.createLocalidadDAO();
		this.tipoContacto = metodo_persistencia.createTipoContactoDAO();
	}

	public void agregarPersona(PersonaDTO nuevaPersona) {
		this.persona.insert(nuevaPersona);
	}

	public void borrarPersona(PersonaDTO persona_a_eliminar) {
		this.persona.delete(persona_a_eliminar);
	}

	public List<PersonaDTO> obtenerPersonas() {
		return this.persona.readAll();
	}

	public List<LocalidadDTO> obtenerLocalidades() {
		return this.localidad.readAll();
	}

	public List<TipoContactoDTO> obtenerTiposDeContacto() {
		return this.tipoContacto.readAll();
	}

	public void agregarLocalidad(LocalidadDTO localidad) {
		this.localidad.insert(localidad);
	}

	public void editarLocalidad(LocalidadDTO localidad) {
		this.localidad.update(localidad);
	}

	public void borrarLocalidad(LocalidadDTO localidad) {
		this.localidad.delete(localidad);
	}
}
