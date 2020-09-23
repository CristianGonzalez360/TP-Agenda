package presentacion.controlador;

import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.jfree.chart.Effect3D;

import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.ProvinciaDTO;
import modelo.Agenda;
import presentacion.vista.PanelEditorLocalidad;
import presentacion.vista.PanelEditorPais;
import presentacion.vista.PanelEditorProvincia;
import presentacion.vista.VistaEditor;

public class ControladorEditor {

	private static final String PAIS = "panel_pais";
	private static final String PROVINCIA = "panel_provincia";
	private static final String LOCALIDAD = "panel_localidad";
	
	private VistaEditor vistaEditor;
	private Agenda agenda;
	private PanelEditorPais editorPais;
	private PanelEditorProvincia editorProvincia;
	private PanelEditorLocalidad editorLocalidad;

	public ControladorEditor(VistaEditor vistaEditor, Agenda agenda) {
		this.vistaEditor = vistaEditor;
		this.agenda = agenda;
		this.editorPais = this.vistaEditor.getEditorPais();
		this.editorProvincia = this.vistaEditor.getEditorProvincia();
		this.editorLocalidad = this.vistaEditor.getEditorLocalidad();
		iniciarEditorPais();
		iniciarEditorProvincia();
		iniciarEditorLocalidad();
	}

	private void iniciarEditorLocalidad() {
		List<PaisDTO> paises = agenda.obtenerPaises();
		editorLocalidad.mostrarPaises(paises);
		editorLocalidad.getBtnGuardar().addActionListener(gl -> guardarLodalidad(gl));
		editorLocalidad.getBtnBorrar().setActionCommand(LOCALIDAD);
		editorLocalidad.getBtnBorrar().addActionListener(bl -> borrarLocalidad(bl));
		editorLocalidad.getBtnAtras().addActionListener(a -> this.vistaEditor.mostar(VistaEditor.PROVINCIA));
	}

	private void borrarLocalidad(ActionEvent bl) {
		JButton boton = (JButton) bl.getSource();
		String command = boton.getActionCommand();
		LocalidadDTO localidad = null;
		if (command.equals(PROVINCIA)) {									//Para desde que panel borro.
			localidad = editorProvincia.getLocalidadSeleccionada();
		} else if (command.equals(LOCALIDAD)) {
			localidad = editorLocalidad.getLocalidadSeleccionada();
		}
		if(localidad != null && localidad.getId() != 0) {
			if(this.agenda.borrarLocalidad(localidad)) {
				JOptionPane.showMessageDialog(null, "Localidad borrada exitosamente" );
				actualizarLocalidades(localidad.getProvincia());
			}
		}
	}

	private void guardarLodalidad(ActionEvent gl) {
		if (editorLocalidad.validarDatos()) {
			ProvinciaDTO provincia = editorLocalidad.getProvinciaSeleccionada();
			LocalidadDTO localidad = editorLocalidad.getLocalidadSeleccionada();
			String nombre = editorLocalidad.getTxtNombre().getText();
			localidad.setProvincia(provincia);
			localidad.setNombre(nombre);
			if (editorLocalidad.getComboLocalidades().getSelectedIndex() == 0) { //Guardo una localidad nueva.
				agenda.agregarLocalidad(localidad);
			} else {															 //Edito una localodad.
				agenda.editarLocalidad(localidad);
			}
			JOptionPane.showMessageDialog(null, "Localidad agregada exitosamente" );
			actualizarLocalidades(provincia);
		}
	}

	private void iniciarEditorProvincia() {
		List<PaisDTO> paises = agenda.obtenerPaises();
		editorProvincia.mostrarPaises(paises);
		editorProvincia.getBtnGuardar().addActionListener(gp -> guardarProvincia(gp));
		editorProvincia.getBtnBorrar().setActionCommand(PROVINCIA);
		editorProvincia.getBtnBorrar().addActionListener(bp -> borrarProvincia(bp));
		editorProvincia.getBtnAgregarLocalidad().addActionListener(al -> agregarLocalidad(al));
		editorProvincia.getBtnEditarLicalidad().addActionListener(el -> editarLocalidad(el));
		editorProvincia.getBtnBorrarLocalidad().setActionCommand(PROVINCIA);
		editorProvincia.getBtnBorrarLocalidad().addActionListener(bl -> borrarLocalidad(bl));
		editorProvincia.getBtnAtras().addActionListener(a -> this.vistaEditor.mostar(VistaEditor.PAIS));
	}

	private void editarLocalidad(ActionEvent el) {
		LocalidadDTO localidad = editorProvincia.getLocalidadSeleccionada();
		if(localidad != null) {
			agregarLocalidad(el);
			editorLocalidad.seleccionar(localidad);
		}
	}

	private void agregarLocalidad(ActionEvent al) {
		ProvinciaDTO provincia = editorProvincia.getProvinciaSeleccionada();
		if(provincia != null && provincia.getId() != 0) {
			editorLocalidad.seleccionar(editorProvincia.getPaisSeleccionado());
			editorLocalidad.seleccionar(provincia);
			editorLocalidad.getComboLocalidades().setSelectedIndex(0);
			this.vistaEditor.mostar(VistaEditor.LOCALIDAD);
		}
	}

	private void borrarProvincia(ActionEvent bp) {
		JButton boton = (JButton) bp.getSource();
		String command = boton.getActionCommand();
		ProvinciaDTO provincia = null;
		if (command.equals(PAIS)) {											//Para desde que panel borro.
			provincia = editorPais.getProvinciaSeleccionada();
		} else if (command.equals(PROVINCIA)) {
			provincia = editorProvincia.getProvinciaSeleccionada();
		}
		if(provincia != null && provincia.getId() != 0) {
			if(this.agenda.borrarProvincia(provincia)) {
				JOptionPane.showMessageDialog(null, "Provincia borrada exitosamente" );
				actualizarProvincias(provincia.getPais());
			}
		}
	}

	private void guardarProvincia(ActionEvent gp) {
		if (editorProvincia.validarDatos()) {
			PaisDTO pais = editorProvincia.getPaisSeleccionado();
			String nombre = editorProvincia.getTxtNombre().getText();
			ProvinciaDTO provincia = editorProvincia.getProvinciaSeleccionada();
			provincia.setNombre(nombre);
			provincia.setPais(pais);
			if (editorProvincia.getComboProvincias().getSelectedIndex() == 0) { // Guarda una provincia nueva;
				this.agenda.agregarProvincia(provincia);
			} else { 															// Edita una provincia
				agenda.editarProvincia(provincia);
			}
			JOptionPane.showMessageDialog(null, "Provincia agregada exitosamente" );
			actualizarProvincias(pais);
		}
	}

	private void iniciarEditorPais() {
		editorPais.mostrarPaises(agenda.obtenerPaises());
		editorPais.getComboPaises().setSelectedIndex(0);
		editorPais.getBtnGuardar().addActionListener(ap -> guardarPais(ap));
		editorPais.getBtnBorrar().addActionListener(bp -> borrarPais(bp));
		editorPais.getBtnAgregarProvincia().addActionListener(ap -> agregarProvincia(ap));
		editorPais.getBtnEditarProvincia().addActionListener(ep -> editarProvincia(ep));
		editorPais.getBtnBorrarProvincia().setActionCommand(PAIS);
		editorPais.getBtnBorrarProvincia().addActionListener(bp -> borrarProvincia(bp));
	}

	private void editarProvincia(ActionEvent ep) {
		ProvinciaDTO provincia = editorPais.getProvinciaSeleccionada();
		if(provincia != null) {
			agregarProvincia(ep);
			editorProvincia.seleccionar(provincia);
		}
	}
	
	private void agregarProvincia(ActionEvent ap) {
		PaisDTO pais = editorPais.getPaisSeleccionado();
		if(pais != null && pais.getId() != 0) {
			editorProvincia.seleccionar(editorPais.getPaisSeleccionado());
			editorProvincia.getComboProvincias().setSelectedIndex(0);
			vistaEditor.mostar(VistaEditor.PROVINCIA);
		}
	}
	
	private void borrarPais(ActionEvent bp) {
		PaisDTO pais = editorPais.getPaisSeleccionado();
		this.agenda.borrarPais(pais);
		JOptionPane.showMessageDialog(null, "Pais borrado exitosamente" );
		actualizarPaises();
	}

	private void guardarPais(ActionEvent ap) {
		if (editorPais.validarDatos()) {
			String nombre = editorPais.getTxtNombre().getText();
			PaisDTO pais = editorPais.getPaisSeleccionado();
			pais.setNombre(nombre);
			if (editorPais.getComboPaises().getSelectedIndex() == 0) { // Guearda un pais nuevo.
				agenda.agregarPais(pais);
			} else { 												   // Edita un pais.
				this.agenda.editarPais(pais);
			}
			JOptionPane.showMessageDialog(null, "Pais agregado exitosamente");
			actualizarPaises();
		}
	}
	
	private void actualizarPaises() {
		List<PaisDTO> paises = agenda.obtenerPaises();
		editorPais.mostrarPaises(paises);
		editorProvincia.mostrarPaises(paises);
		editorLocalidad.mostrarPaises(paises);
	}
	
	private void actualizarProvincias(PaisDTO pais) {
		actualizarPaises();
		List<ProvinciaDTO> provincias = agenda.obtenerProvincias(pais);
		editorProvincia.mostrarProvincias(provincias);
		editorLocalidad.mostrarProvincias(provincias);
	}
	
	private void actualizarLocalidades(ProvinciaDTO provincia) {
		actualizarProvincias(provincia.getPais());
		editorLocalidad.mostrarLocalidades(agenda.obtenerLocalidades(provincia));
	}
	
}
