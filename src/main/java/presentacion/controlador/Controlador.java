package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import modelo.Agenda;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.EditorTipoContacto;
//import presentacion.vista.EditorPersona;
import presentacion.vista.VentanaPersona;
import presentacion.vista.VentanaTipoDeContacto;
import presentacion.vista.Vista;
import presentacion.vista.VistaEditor;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoContactoDTO;

public class Controlador implements ActionListener {
	private Vista vista;
	private List<PersonaDTO> personasEnTabla;
	private List<TipoContactoDTO> localidades;
	private TipoContactoDTO tipoContactoSeleccionado;
	private VentanaPersona ventanaPersona;
	private EditorTipoContacto editorTipoContacto;
	private VentanaTipoDeContacto ventanaTipoContacto;
	
	private VistaEditor vistaEditor;
	private ControladorEditor controladorEditor;
	//private EditorPersona editorPersona;
	
	private Agenda agenda;

	public Controlador(Vista vista, Agenda agenda) {
		this.vista = vista;
		this.ventanaTipoContacto = VentanaTipoDeContacto.getInstance();
		this.editorTipoContacto = EditorTipoContacto.getInstance();
		this.vista.getBtnAgregar().addActionListener(a -> ventanaAgregarPersona(a));
		this.vista.getBtnEditar().addActionListener(k -> modificarPersona(k));
		this.vista.getBtnBorrar().addActionListener(s -> borrarPersona(s));
		this.vista.getBtnReporte().addActionListener(r -> mostrarReporte(r));
		this.vista.getMntmLocalidades().addActionListener(l -> editarLocalidades(l));
		this.vista.getMntmPaises().addActionListener(p -> editarPaises(p));
		this.vista.getMntmProvincias().addActionListener(p -> editarProvincia(p));
		this.vista.getMntmTiposDeContacto().addActionListener(vc -> ventanaTipoContacto(vc));
		this.ventanaTipoContacto.getBtnAgregar().addActionListener(ac -> agregarTipoContacto(ac));
		this.ventanaTipoContacto.getBtnEditar().addActionListener(ec -> editarTipoContacto(ec));
		this.ventanaTipoContacto.getBtnBorrar().addActionListener(bc -> borrarTipoContacto(bc));
		this.editorTipoContacto.getBtnAceptar().addActionListener(gc -> guardarTipoContacto(gc));
		this.ventanaPersona = VentanaPersona.getInstance();
		this.ventanaPersona.getBtnAgregarPersona().addActionListener(p -> guardarPersona(p));
		
		this.vistaEditor = new VistaEditor();
		this.controladorEditor = new ControladorEditor(vistaEditor, agenda);
		
		//this.editorPersona = EditorPersona.getInstance();
		//this.editorPersona.getBtnAgregarPersona().addActionListener(k -> modificarPersona(k));
		
		this.agenda = agenda;
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
		// Llena al combo con las localidades disponibles
		List<LocalidadDTO> localidades = agenda.obtenerLocalidades();
		DefaultComboBoxModel<LocalidadDTO> dcm = new DefaultComboBoxModel<>();
		for (LocalidadDTO l : localidades)
			dcm.addElement(l);
		ventanaPersona.getComboLocalidad().setModel(dcm);
		// Llena al combo con los tipos de contacto disponibles
		List<TipoContactoDTO> tiposDeContacto = agenda.obtenerTiposDeContacto();
		DefaultComboBoxModel<TipoContactoDTO> dcm2 = new DefaultComboBoxModel<>();
		for (TipoContactoDTO t : tiposDeContacto) {
			dcm2.addElement(t);
		}
		ventanaPersona.getComboTipoContacto().setModel(dcm2);

		this.ventanaPersona.mostrarVentana();
	}
	
	private void modificarPersona(ActionEvent k) {
		
			
	//	this.editorPersona.mostrarVentana();
		
	}
	
	private void guardarPersona(ActionEvent p) {
		String nombre = this.ventanaPersona.getTxtNombre().getText();
		String telefono = ventanaPersona.getTxtTelefono().getText();
		String calle = ventanaPersona.getTxtCalle().getText();
		Date nacimiento = ventanaPersona.getChooserNacimiento().getDate();
		int altura = Integer.parseInt(ventanaPersona.getTxtAltura().getText());
		int piso = Integer.parseInt(ventanaPersona.getTxtPiso().getText());
		String departamento = ventanaPersona.getTxtDepartamento().getText();
		LocalidadDTO localidad = (LocalidadDTO) ventanaPersona.getComboLocalidad().getSelectedItem();
		String email = ventanaPersona.getTxtEmail().getText();
		TipoContactoDTO tipoContacto = (TipoContactoDTO) ventanaPersona.getComboTipoContacto().getSelectedItem();
		PersonaDTO nuevaPersona = new PersonaDTO(0, nombre, telefono, calle, nacimiento, altura, piso, departamento,
				localidad, email, tipoContacto);
		this.agenda.agregarPersona(nuevaPersona);
		this.refrescarTabla();
		this.ventanaPersona.cerrar();
	}
		
	private void guardarTipoContacto(ActionEvent a) {
		String accion = this.editorTipoContacto.getBtnAceptar().getActionCommand();
		String tipo = this.editorTipoContacto.getTxtNombre().getText();
		if(accion.equals("agregar")) {
			TipoContactoDTO tipoContacto = new TipoContactoDTO();
			tipoContacto.setTipo(tipo);;
			this.agenda.agregarTipoDeContacto(tipoContacto);
		}
		else if(accion.equals("editar")) {
			this.tipoContactoSeleccionado.setTipo(tipo);
			this.agenda.editarTipoDeContacto(tipoContactoSeleccionado);
		}
		this.editorTipoContacto.cerrar();
		refrescarLocalidades();
	}
	
	private void ventanaTipoContacto(ActionEvent l) {
		refrescarLocalidades();
		this.ventanaTipoContacto.mostrarVentana();
	}

	private void agregarTipoContacto(ActionEvent a) {
		this.editorTipoContacto.getBtnAceptar().setActionCommand("agregar");//para indicar que voy a guardar una nueva localidad.
		this.editorTipoContacto.mostrarVentana();
	}

	private void editarTipoContacto(ActionEvent e) {
		int fila = this.ventanaTipoContacto.obtenerFilaSeleccionada();
		if(fila>-1) {
			this.tipoContactoSeleccionado = this.localidades.get(fila);
			this.editorTipoContacto.getTxtNombre().setText(this.tipoContactoSeleccionado.getTipo());
			this.editorTipoContacto.getBtnAceptar().setActionCommand("editar");//para indicar que voy a actualizar un tipo de contacto existente.
			this.editorTipoContacto.mostrarVentana();
		}
	}
	
	private void borrarTipoContacto(ActionEvent b) {
		int fila = this.ventanaTipoContacto.obtenerFilaSeleccionada();
		if(fila > -1) {
			this.tipoContactoSeleccionado = this.localidades.get(fila);
			this.agenda.borrarTipoDeContacto(this.tipoContactoSeleccionado);
			refrescarLocalidades();
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

		this.refrescarTabla();
	}

	public void inicializar() {
		this.refrescarTabla();
		this.vista.show();
	}
	
	private void refrescarLocalidades() {
		this.localidades = this.agenda.obtenerTipoDeCoontacto();
		this.ventanaTipoContacto.llenarTabla(localidades);
	}

	private void refrescarTabla() {
		this.personasEnTabla = agenda.obtenerPersonas();
		this.vista.llenarTabla(this.personasEnTabla);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
