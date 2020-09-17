package modelo;

import java.util.List;

import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;
import dto.TipoContactoDTO;
import persistencia.dao.interfaz.DAOAbstractFactory;
import persistencia.dao.interfaz.LocalidadDAO;
import persistencia.dao.interfaz.PaisDAO;
import persistencia.dao.interfaz.PersonaDAO;
import persistencia.dao.interfaz.ProvinciaDAO;
import persistencia.dao.interfaz.TipoContactoDAO;

public class Agenda {
	private PersonaDAO persona;
	private LocalidadDAO localidad;
	private TipoContactoDAO tipoContacto;
	private PaisDAO pais;
	private ProvinciaDAO provincia;

	public Agenda(DAOAbstractFactory metodo_persistencia) {
		this.persona = metodo_persistencia.createPersonaDAO();
		this.localidad = metodo_persistencia.createLocalidadDAO();
		this.tipoContacto = metodo_persistencia.createTipoContactoDAO();
		this.pais = metodo_persistencia.createPaisDAO();
		this.provincia = metodo_persistencia.createProvinciaDAO();
	}

	public void agregarPersona(PersonaDTO nuevaPersona) {
		this.persona.insert(nuevaPersona);
	}
	
	public void modificarPersona(PersonaDTO persona_a_modificar) {
		this.persona.update(persona_a_modificar);
	}
	

	public void borrarPersona(PersonaDTO persona_a_eliminar) {
		this.persona.delete(persona_a_eliminar);
	}

	public List<PersonaDTO> obtenerPersonas() {
		return this.persona.readAll();
	}

	public List<LocalidadDTO> obtenerLocalidades(ProvinciaDTO provincia) {
		return this.localidad.readAll(provincia);
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

	public boolean borrarLocalidad(LocalidadDTO localidad) {
		return this.localidad.delete(localidad);
	}

	public List<PaisDTO> obtenerPaises() {
		return pais.readAll();
	}

	public void agregarPais(PaisDTO pais) {
		this.pais.insert(pais);
	}

	public boolean borrarPais(PaisDTO pais) {
		return this.pais.delete(pais);
	}

	public List<ProvinciaDTO> obtenerProvincias(PaisDTO pais) {
		return this.provincia.readAll(pais);
	}

	public void agregarProvincia(ProvinciaDTO provincia) {
		this.provincia.insert(provincia);
	}

	public boolean borrarProvincia(ProvinciaDTO provincia) {
		return this.provincia.delete(provincia);
	}

	public void editarProvincia(ProvinciaDTO provincia) {
		this.provincia.update(provincia);
	}

	public void editarPais(PaisDTO pais) {
		this.pais.update(pais);
	}

	public void agregarTipoDeContacto(TipoContactoDTO tipoContacto) {
		this.tipoContacto.insert(tipoContacto);
	}

	public void editarTipoDeContacto(TipoContactoDTO tipoContacto) {
		this.tipoContacto.update(tipoContacto);
	}

	public void borrarTipoDeContacto(TipoContactoDTO tipoContacto) {
		this.tipoContacto.delete(tipoContacto);
	}

	public List<TipoContactoDTO> obtenerTipoDeContacto() {
		return this.tipoContacto.readAll();
	}
}
