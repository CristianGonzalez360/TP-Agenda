package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import modelo.Agenda;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.EditorLocalidad;
import presentacion.vista.EditorPersona;
import presentacion.vista.VentanaLocalidades;
import presentacion.vista.VentanaPersona;
import presentacion.vista.Vista;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoContactoDTO;

public class Controlador implements ActionListener {
	private Vista vista;
	private List<PersonaDTO> personasEnTabla;
	private List<LocalidadDTO> localidades;
	private LocalidadDTO localidadSeleccionada;
	private VentanaPersona ventanaPersona;
	private EditorLocalidad editorLocalidad;
	private VentanaLocalidades ventanaLocalidades;
	
	private EditorPersona editorPersona;
	
	private Agenda agenda;

	public Controlador(Vista vista, Agenda agenda) {
		this.vista = vista;
		this.vista.getBtnAgregar().addActionListener(a -> ventanaAgregarPersona(a));
		
		this.vista.getBtnEditar().addActionListener(k -> modificarPersona(k));
		
		this.vista.getBtnBorrar().addActionListener(s -> borrarPersona(s));
		this.vista.getBtnReporte().addActionListener(r -> mostrarReporte(r));
		this.vista.getMntmLocalidades().addActionListener(l -> ventanaLocalidades(l));
		this.ventanaPersona = VentanaPersona.getInstance();
		this.ventanaPersona.getBtnAgregarPersona().addActionListener(p -> guardarPersona(p));
		this.editorLocalidad = EditorLocalidad.getInstance();
		this.editorLocalidad.getBtnAceptar().addActionListener(g -> guardarLocalidad(g));
		this.ventanaLocalidades = VentanaLocalidades.getInstance();
		this.ventanaLocalidades.getBtnAgregar().addActionListener(a -> ventanaAgregarLocalidad(a));
		this.ventanaLocalidades.getBtnEditar().addActionListener(e -> editarLocalidad(e));
		this.ventanaLocalidades.getBtnBorrar().addActionListener(b -> borrarLocalidad(b));
		
		this.editorPersona = EditorPersona.getInstance();
		//this.editorPersona.getBtnAgregarPersona().addActionListener(k -> modificarPersona(k));
		
		this.agenda = agenda;
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
		
			
		this.editorPersona.mostrarVentana();
		
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
	
	private void ventanaLocalidades(ActionEvent l) {
		refrescarLocalidades();
		this.ventanaLocalidades.mostrarVentana();
	}

	private void ventanaAgregarLocalidad(ActionEvent a) {
		this.editorLocalidad.getBtnAceptar().setActionCommand("agregar");//para indicar que voy a guardar una nueva localidad.
		this.editorLocalidad.mostrarVentana();
	}
	
	private void guardarLocalidad(ActionEvent a) {
		String accion = this.editorLocalidad.getBtnAceptar().getActionCommand();
		String nombre = this.editorLocalidad.getTxtNombre().getText();
		if(accion.equals("agregar")) {
			LocalidadDTO localidad = new LocalidadDTO(nombre);
			this.agenda.agregarLocalidad(localidad);
		}
		else if(accion.equals("editar")) {
			this.localidadSeleccionada.setNombre(nombre);
			this.agenda.editarLocalidad(localidadSeleccionada);
		}
		this.editorLocalidad.cerrar();
		refrescarLocalidades();
	}

	private void editarLocalidad(ActionEvent e) {
		int fila = this.ventanaLocalidades.obtenerFilaSeleccionada();
		if(fila>-1) {
			this.localidadSeleccionada = this.localidades.get(fila);
			this.editorLocalidad.getTxtNombre().setText(this.localidadSeleccionada.getNombre());
			this.editorLocalidad.getBtnAceptar().setActionCommand("editar");//para indicar que voy a actualizar una localidad existente.
			this.editorLocalidad.mostrarVentana();
		}
	}
	
	private void borrarLocalidad(ActionEvent b) {
		int fila = this.ventanaLocalidades.obtenerFilaSeleccionada();
		this.localidadSeleccionada = this.localidades.get(fila);
		this.agenda.borrarLocalidad(this.localidadSeleccionada);
		refrescarLocalidades();
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
		this.localidades = this.agenda.obtenerLocalidades();
		this.ventanaLocalidades.llenarTabla(localidades);
	}

	private void refrescarTabla() {
		this.personasEnTabla = agenda.obtenerPersonas();
		this.vista.llenarTabla(this.personasEnTabla);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
