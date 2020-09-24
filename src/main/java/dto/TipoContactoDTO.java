package dto;

public class TipoContactoDTO {

	private int id;
	private String tipo;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		return getTipo();
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean ret = false;
		if(obj instanceof TipoContactoDTO) {
			TipoContactoDTO t = (TipoContactoDTO) obj;
			ret = t.getId() == this.id;
		}
		return ret;
	}
}
