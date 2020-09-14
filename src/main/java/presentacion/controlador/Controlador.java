package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import modelo.Agenda;
import presentacion.reportes.ReporteAgenda;
import presentacion.vista.VentanaPersona;
import presentacion.vista.Vista;
import dto.LocalidadDTO;
import dto.PersonaDTO;
import dto.TipoContactoDTO;

public class Controlador implements ActionListener {
	private Vista vista;
	private List<PersonaDTO> personasEnTabla;
	private VentanaPersona ventanaPersona;
	private Agenda agenda;

	public Controlador(Vista vista, Agenda agenda) {
		this.vista = vista;
		this.vista.getBtnAgregar().addActionListener(a -> ventanaAgregarPersona(a));
		this.vista.getBtnBorrar().addActionListener(s -> borrarPersona(s));
		this.vista.getBtnReporte().addActionListener(r -> mostrarReporte(r));
		this.ventanaPersona = VentanaPersona.getInstance();
		this.ventanaPersona.getBtnAgregarPersona().addActionListener(p -> guardarPersona(p));
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

	private void refrescarTabla() {
		this.personasEnTabla = agenda.obtenerPersonas();
		this.vista.llenarTabla(this.personasEnTabla);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
