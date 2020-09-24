package dto;

public class DeporteDTO {

	private int id;
	private String nombre;
	
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
	
	@Override
	public String toString() {
		return getNombre();
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean ret = false;
		if(obj instanceof DeporteDTO) {
			DeporteDTO d = (DeporteDTO) obj;
			ret = d.getId() == this.id;
		}
		return ret;
	}
}
