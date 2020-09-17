package dto;

import java.util.ArrayList;
import java.util.List;

public class ProvinciaDTO {

	private int id;
	private String nombre;
	private PaisDTO pais;
	private List<LocalidadDTO> localidades;
	
	public ProvinciaDTO(String nombre) {
		this.nombre = nombre;
		this.localidades = new ArrayList<LocalidadDTO>();
	}

	public ProvinciaDTO() {
	}

	public List<LocalidadDTO> getLocalidades() {
		return localidades;
	}

	public void setLocalidades(List<LocalidadDTO> localidades) {
		this.localidades = localidades;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public PaisDTO getPais() {
		return pais;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPais(PaisDTO pais) {
		this.pais = pais;
	}
	
	@Override
	public String toString() {
		return getNombre();
	}

	@Override
	public boolean equals(Object obj) {
		boolean ret = false;
		if(obj instanceof ProvinciaDTO) {
			ProvinciaDTO provincia = (ProvinciaDTO) obj;
			ret = this.id == provincia.getId();
		}
		return ret;
	}
}
