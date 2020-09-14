package dto;

public class LocalidadDTO {

	private int idLocalidad;
	private String nombre;

	public LocalidadDTO(String nombre) {
		super();
		this.nombre = nombre;
	}

	public int getIdLocalidad() {
		return idLocalidad;
	}

	public void setIdLocalidad(int idLocalidad) {
		this.idLocalidad = idLocalidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return getNombre();
	}
}
