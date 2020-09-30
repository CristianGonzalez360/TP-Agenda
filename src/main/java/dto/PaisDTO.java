package dto;

import java.util.ArrayList;
import java.util.List;

public class PaisDTO {

	private int id;
	private String nombre;
	private List<ProvinciaDTO> provincias;
	
	public PaisDTO(String nombre) {
		this.setNombre(nombre);
		this.provincias = new ArrayList<>();
	}

	public PaisDTO() {
		this.provincias = new ArrayList<>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<ProvinciaDTO> getProvincias() {
		return provincias;
	}

	public void setProvincias(List<ProvinciaDTO> provincias) {
		this.provincias = provincias;
	}
	
	@Override
	public String toString() {
		return getNombre();
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean ret = false;
		if(obj instanceof PaisDTO) {
			PaisDTO p = (PaisDTO) obj;
			ret = p.getId() == this.id;
		}
		return ret;
	}
}
