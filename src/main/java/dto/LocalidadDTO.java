package dto;

public class LocalidadDTO {

	private int id;
	private String nombre;
	private ProvinciaDTO provincia;
	

	public LocalidadDTO(String nombre) {
		this.nombre = nombre;
	}

	public LocalidadDTO() {

	}

	public int getId() {
		return id;
	}

	public void setId(int idLocalidad) {
		this.id = idLocalidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public ProvinciaDTO getProvincia() {
		return provincia;
	}

	public void setProvincia(ProvinciaDTO provincia) {
		this.provincia = provincia;
	}

	@Override
	public String toString() {
		return getNombre();
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean ret = false;
		if(obj instanceof LocalidadDTO) {
			LocalidadDTO localidad = (LocalidadDTO) obj;
			ret = this.id == localidad.getId();
		}
		return ret;
	}

}
