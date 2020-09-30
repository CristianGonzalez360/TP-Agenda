package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.Agenda;
import persistencia.conexion.Conexion;
import persistencia.conexion.DBProperties;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.ConfiguradorBD;
import presentacion.vista.EditorDeporte;
import presentacion.vista.EditorTipoContacto;
import presentacion.vista.VentanaDeportes;
import presentacion.vista.VentanaPersona;
import presentacion.vista.VentanaTipoDeContacto;
import presentacion.vista.Vista;
import presentacion.vista.VistaEditor;
import dto.DeporteDTO;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoContactoDTO;

public class Controlador {
	private Vista vista;
	private List<PersonaDTO> personasEnTabla;
	private List<TipoContactoDTO> tiposContacto;
	private TipoContactoDTO tipoContactoSeleccionado;
	private DeporteDTO deporteSeleccionado;
	private PersonaDTO personaSeleccionada;
	private VentanaPersona ventanaPersona;
	private EditorTipoContacto editorTipoContacto;
	private EditorDeporte editorDeporte;
	private VentanaTipoDeContacto ventanaTipoContacto;
	
	private VistaEditor vistaEditor;
	private ControladorEditor controladorEditor;
	
	private ConfiguradorBD configuradorBD;
	private DBProperties dbProperties;
	
	private Agenda agenda;
	private List<DeporteDTO> deportes;
	private VentanaDeportes ventanaDeportes;

	public Controlador(Vista vista, Agenda agenda) {
		this.vista = vista;
		this.agenda = agenda;
		this.configuradorBD = ConfiguradorBD.getInstance();
		this.dbProperties = DBProperties.getInstance();
		this.ventanaTipoContacto = VentanaTipoDeContacto.getInstance();
		this.editorTipoContacto = EditorTipoContacto.getInstance();
		this.editorDeporte = EditorDeporte.getInstance();
		this.ventanaPersona = VentanaPersona.getInstance();
		this.ventanaDeportes = VentanaDeportes.getInstance();
		this.configuradorBD.getOkButton().addActionListener(c -> guardarDBProperties());
		this.configuradorBD.getCancelButton().addActionListener(c -> iniciar());
		this.vista.getBtnAgregar().addActionListener(a -> ventanaAgregarPersona(a));
		this.vista.getBtnEditar().addActionListener(k -> modificarPersona(k));
		this.vista.getBtnBorrar().addActionListener(s -> borrarPersona(s));
		this.vista.getBtnReporte().addActionListener(r -> mostrarReporte(r));
		this.vista.getMntmLocalidades().addActionListener(l -> editarLocalidades(l));
		this.vista.getMntmPaises().addActionListener(p -> editarPaises(p));
		this.vista.getMntmProvincias().addActionListener(p -> editarProvincia(p));
		this.vista.getMntmTiposDeContacto().addActionListener(vc -> ventanaTipoContacto(vc));
		this.vista.getMntmDeporte().addActionListener(vd -> ventanaDeporte(vd));
		this.vista.getMntmBaseDeDatos().addActionListener(bd -> configurarBD());
		this.ventanaTipoContacto.getBtnAgregar().addActionListener(ac -> agregarTipoContacto(ac));
		this.ventanaTipoContacto.getBtnEditar().addActionListener(ec -> editarTipoContacto(ec));
		this.ventanaTipoContacto.getBtnBorrar().addActionListener(bc -> borrarTipoContacto(bc));
		this.editorTipoContacto.getBtnAceptar().addActionListener(gc -> guardarTipoContacto(gc));
		this.ventanaDeportes.getBtnAgregar().addActionListener(ad -> agregarDeporte(ad));
		this.ventanaDeportes.getBtnEditar().addActionListener(ed -> editarDeporte(ed));
		this.ventanaDeportes.getBtnBorrar().addActionListener(bd -> borrarDeporte(bd));
		this.editorDeporte.getBtnAceptar().addActionListener(gd -> guardarDeporte(gd));
		this.ventanaPersona.getBtnAgregarPersona().addActionListener(p -> guardarPersona(p));
	}
	
	public void iniciar() {
		Conexion.getConexion().cerrarConexion();
		if (Conexion.getConexion().conectar()) {
			mostrar();
		}
		else {
			configurarBD();
		}
	}
	
	private void guardarDBProperties() {
		dbProperties.put("db_prefijo", configuradorBD.getTxtPrefijo().getText());
		dbProperties.put("db_ip", configuradorBD.getTxtIp().getText());
		dbProperties.put("db_puerto", configuradorBD.getTxtPuerto().getText());
		dbProperties.put("db_nombre", configuradorBD.getTxtNombredebd().getText());
		dbProperties.put("db_usuario", configuradorBD.getTxtUsuario().getText());
		dbProperties.put("db_password", new String(configuradorBD.getPwdContrasea().getPassword()));
		dbProperties.guardar();
		this.configuradorBD.dispose();
		iniciar();
	}
	
	public void mostrar() {	
		this.vistaEditor = new VistaEditor();
		this.controladorEditor = new ControladorEditor(vistaEditor, agenda);
		refrescarVentanaPersonas();
		this.refrescarTabla();
		this.vista.show();
	}
	
	private void configurarBD() {
		this.vista.ocultar();
		this.configuradorBD.mostrar(dbProperties.getDbProperties());
		this.configuradorBD.setVisible(true);
	}

	private void editarLocalidades(ActionEvent l) {
		vistaEditor.mostar(VistaEditor.LOCALIDAD);
		vistaEditor.setVisible(true);
	}

	private void editarProvincia(ActionEvent p) {
		vistaEditor.mostar(VistaEditor.PROVINCIA);
		vistaEditor.setVisible(true);
	}

	private void editarPaises(ActionEvent p) {
		vistaEditor.mostar(VistaEditor.PAIS);
		vistaEditor.setVisible(true);
	}

	private void ventanaAgregarPersona(ActionEvent a) {
		refrescarVentanaPersonas();
		this.ventanaPersona.getBtnAgregarPersona().setActionCommand("agregar");
		this.ventanaPersona.setTitle("Agragar Contacto");
		this.ventanaPersona.mostrarVentana();
	}
	
	private void modificarPersona(ActionEvent k) {
		refrescarVentanaPersonas();
		int fila = vista.getTablaPersonas().getSelectedRow();
		if(fila>-1){
			this.personaSeleccionada = personasEnTabla.get(fila);
			this.ventanaPersona.getBtnAgregarPersona().setActionCommand("editar");
			this.ventanaPersona.setTitle("Editar Contacto");
			this.ventanaPersona.mostrarPersona(personaSeleccionada);	
			this.ventanaPersona.mostrarVentana();
		}
	}
	
	private void guardarPersona(ActionEvent p) {
		if(ventanaPersona.validarDatos()) {
			String nombre = this.ventanaPersona.getTxtNombre().getText();
			String telefono = ventanaPersona.getTxtTelefono().getText();
			String calle = ventanaPersona.getTxtCalle().getText();
			Date nacimiento = ventanaPersona.getChooserNacimiento().getDate();
			String altura = ventanaPersona.getTxtAltura().getText();
			String piso = ventanaPersona.getTxtPiso().getText();
			String departamento = ventanaPersona.getTxtDepartamento().getText();
			LocalidadDTO localidad = (LocalidadDTO) ventanaPersona.getComboLocalidad().getSelectedItem();
			String email = ventanaPersona.getTxtEmail().getText();
			TipoContactoDTO tipoContacto = (TipoContactoDTO) ventanaPersona.getComboTipoContacto().getSelectedItem();
			DeporteDTO deporte = (DeporteDTO) ventanaPersona.getComboDeporte().getSelectedItem();
			String accion = this.ventanaPersona.getBtnAgregarPersona().getActionCommand();
		
			if(accion.equals("agregar")) {
				PersonaDTO nuevaPersona = new PersonaDTO(0, nombre, telefono, calle, nacimiento, altura, piso, departamento,
						localidad, email, tipoContacto, deporte);
				this.agenda.agregarPersona(nuevaPersona);
			}
			else if(accion.equals("editar")) {
				personaSeleccionada.setNombre(nombre);
				personaSeleccionada.setTelefono(telefono);
				personaSeleccionada.setCalle(calle);
				personaSeleccionada.setNacimiento(nacimiento);
				personaSeleccionada.setAltura(altura);
				personaSeleccionada.setPiso(piso);
				personaSeleccionada.setDepartamento(departamento);
				personaSeleccionada.setLocalidad(localidad);
				personaSeleccionada.setEmail(email);
				personaSeleccionada.setTipoContacto(tipoContacto);
				personaSeleccionada.setDeporte(deporte);
				this.agenda.editarPersona(personaSeleccionada);
			}
			this.refrescarTabla();
			JOptionPane.showMessageDialog(null, "Datos guardados exitosamente" );
			this.ventanaPersona.cerrar();
		}		
	}
		
	private void guardarTipoContacto(ActionEvent a) {
		if (this.editorTipoContacto.validar()) {
			String accion = this.editorTipoContacto.getBtnAceptar().getActionCommand();
			String tipo = this.editorTipoContacto.getTxtNombre().getText();
			if (accion.equals("agregar")) {
				TipoContactoDTO tipoContacto = new TipoContactoDTO();
				tipoContacto.setTipo(tipo);
				;
				this.agenda.agregarTipoDeContacto(tipoContacto);
			} else if (accion.equals("editar")) {
				this.tipoContactoSeleccionado.setTipo(tipo);
				this.agenda.editarTipoDeContacto(tipoContactoSeleccionado);
			}
			JOptionPane.showMessageDialog(null, "Datos guardados exitosamente");
			this.editorTipoContacto.cerrar();
			refrescarTiposContacto();
		}
	}
	
	private void ventanaTipoContacto(ActionEvent l) {
		refrescarTiposContacto();
		this.ventanaTipoContacto.mostrarVentana();
	}

	private void agregarTipoContacto(ActionEvent a) {
		this.editorTipoContacto.getBtnAceptar().setActionCommand("agregar");//para indicar que voy a guardar una nueva localidad.
		this.editorTipoContacto.mostrarVentana();
	}

	private void editarTipoContacto(ActionEvent e) {
		int fila = this.ventanaTipoContacto.obtenerFilaSeleccionada();
		if(fila>-1) {
			this.tipoContactoSeleccionado = this.tiposContacto.get(fila);
			this.editorTipoContacto.getTxtNombre().setText(this.tipoContactoSeleccionado.getTipo());
			this.editorTipoContacto.getBtnAceptar().setActionCommand("editar");//para indicar que voy a actualizar un tipo de contacto existente.
			this.editorTipoContacto.mostrarVentana();
		}
	}
	
	private void borrarTipoContacto(ActionEvent b) {
		int fila = this.ventanaTipoContacto.obtenerFilaSeleccionada();
		if(fila > -1) {
			this.tipoContactoSeleccionado = this.tiposContacto.get(fila);
			this.agenda.borrarTipoDeContacto(this.tipoContactoSeleccionado);
			
			JOptionPane.showMessageDialog(null, "Tipo contacto eliminado exitosamente" );
			refrescarTiposContacto();
		}
	}

	private void guardarDeporte(ActionEvent a) {
		if (editorDeporte.validar()) {
			String accion = this.editorDeporte.getBtnAceptar().getActionCommand();
			String tipo = this.editorDeporte.getTxtNombre().getText();
			if (accion.equals("agregar")) {
				DeporteDTO deporte = new DeporteDTO();
				deporte.setNombre(tipo);
				;
				this.agenda.agregarDeporte(deporte);
			} else if (accion.equals("editar")) {
				this.deporteSeleccionado.setNombre(tipo);
				this.agenda.editarDeporte(deporteSeleccionado);
			}
			JOptionPane.showMessageDialog(null, "Datos guardados exitosamente");
			this.editorDeporte.cerrar();
			refrescarDeportes();
		}
	}
	
	private void refrescarDeportes() {
		this.deportes = this.agenda.obtenerDeportes();
		this.ventanaDeportes.llenarTabla(deportes);
	}
	
	private void ventanaDeporte(ActionEvent l) {
		refrescarDeportes();
		this.ventanaDeportes.mostrarVentana();
	}

	private void agregarDeporte(ActionEvent a) {
		this.editorDeporte.getBtnAceptar().setActionCommand("agregar");//para indicar que voy a guardar una nueva localidad.
		this.editorDeporte.mostrarVentana();
	}

	private void editarDeporte(ActionEvent e) {
		int fila = this.ventanaDeportes.obtenerFilaSeleccionada();
		if(fila>-1) {
			this.deporteSeleccionado = this.deportes.get(fila);
			this.editorDeporte.getTxtNombre().setText(this.deporteSeleccionado.getNombre());
			this.editorDeporte.getBtnAceptar().setActionCommand("editar");//para indicar que voy a actualizar un tipo de contacto existente.
			this.editorDeporte.mostrarVentana();
		}
	}
	
	private void borrarDeporte(ActionEvent b) {
		int fila = this.ventanaDeportes.obtenerFilaSeleccionada();
		if(fila > -1) {
			this.deporteSeleccionado = this.deportes.get(fila);
			this.agenda.borrarDeporte(this.deporteSeleccionado);
			
			JOptionPane.showMessageDialog(null, "Tipo contacto eliminado exitosamente" );
			refrescarDeportes();
		}
	}

	private void mostrarReporte(ActionEvent r) {
		ReporteAgenda reporte = new ReporteAgenda(agenda.obtenerPersonas());
		reporte.mostrar();
	}

	public void borrarPersona(ActionEvent s) {
		int[] filasSeleccionadas = this.vista.getTablaPersonas().getSelectedRows();
		for (int fila : filasSeleccionadas) {
			this.agenda.borrarPersona(this.personasEnTabla.get(fila));
		}
		JOptionPane.showMessageDialog(null , "Contacto eliminado exitosamente");
		this.refrescarTabla();
	}
	
	private void refrescarTiposContacto() {
		this.tiposContacto = this.agenda.obtenerTipoDeContacto();
		this.ventanaTipoContacto.llenarTabla(tiposContacto);
	}

	public void refrescarVentanaPersonas() {
		this.ventanaPersona.mostrarPaises(this.agenda.obtenerPaises());
		this.ventanaPersona.mostrarTiposDeContacto(this.agenda.obtenerTipoDeContacto());
		this.ventanaPersona.mostrarDeportes(this.agenda.obtenerDeportes());
	}
	
	private void refrescarTabla() {
		this.personasEnTabla = agenda.obtenerPersonas();
		this.vista.llenarTabla(this.personasEnTabla);
	}
}
