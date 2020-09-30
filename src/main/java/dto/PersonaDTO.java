package dto;

import java.util.Date;

public class PersonaDTO 
{
	private int idPersona;
	private String nombre;
	private String telefono;
	private String calle;
	private Date Nacimiento;
	private String altura;
	private String piso;
	private String departamento;
	private LocalidadDTO localidad;
	private String email;
	private TipoContactoDTO tipoContacto;
	private DeporteDTO deporte;

	public PersonaDTO(int idPersona, String nombre, String telefono) /// borrar??
	{
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
	}
	
	public PersonaDTO(int idPersona, String nombre, String telefono, String calle, Date Nacimiento, String altura,
			String piso, String departamento, LocalidadDTO localidad, String email, TipoContactoDTO tipoContacto, DeporteDTO deporte) {
		super();
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.telefono = telefono;
		this.calle = calle;
		this.Nacimiento = Nacimiento;
		this.altura = altura;
		this.piso = piso;
		this.departamento = departamento;
		this.localidad = localidad;
		this.email = email;
		this.tipoContacto = tipoContacto;
		this.setDeporte(deporte);
	}

	public int getIdPersona() 
	{
		return this.idPersona;
	}

	public void setIdPersona(int idPersona) 
	{
		this.idPersona = idPersona;
	}

	public String getNombre() 
	{
		return this.nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public String getTelefono() 
	{
		return this.telefono;
	}

	public void setTelefono(String telefono) 
	{
		this.telefono = telefono;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getAltura() {
		return altura;
	}

	public void setAltura(String altura) {
		this.altura = altura;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public LocalidadDTO getLocalidad() {
		return localidad;
	}

	public void setLocalidad(LocalidadDTO localidad) {
		this.localidad = localidad;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public TipoContactoDTO getTipoContacto() {
		return tipoContacto;
	}

	public void setTipoContacto(TipoContactoDTO tipoContacto) {
		this.tipoContacto = tipoContacto;
	}

	public Date getNacimiento() {
		return Nacimiento;
	}

	public void setNacimiento(Date Nacimiento) {
		this.Nacimiento = Nacimiento;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public DeporteDTO getDeporte() {
		return deporte;
	}

	public void setDeporte(DeporteDTO deporte) {
		this.deporte = deporte;
	}
}
