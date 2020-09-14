package dto;

public class TipoContactoDTO {

	private int idTipoContacto;
	private String tipo;

	public int getIdTipoContacto() {
		return idTipoContacto;
	}
	public void setIdTipoContacto(int idTipoContacto) {
		this.idTipoContacto = idTipoContacto;
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
}
