package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.SwingConstants;

import dto.DeporteDTO;
import dto.TipoContactoDTO;

public class VentanaDeportes extends JDialog{
	
	private static VentanaDeportes INSTANCE;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnBorrar;
	private JList<DeporteDTO> listaDeportes;
	private JLabel lblDeportes;

	public static VentanaDeportes getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new VentanaDeportes();
		}
		return INSTANCE;
	}
	
	private VentanaDeportes() {
		setBounds(100, 100, 410, 423);
		setAlwaysOnTop(true);
		JPanel panel = new JPanel(new BorderLayout());
		setContentPane(panel);
		
		listaDeportes = new JList<>();
		panel.add(listaDeportes);

		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		btnAgregar = new JButton("Agregar");
		panelBotones.add(btnAgregar);
		
		btnEditar = new JButton("Editar");
		panelBotones.add(btnEditar);

		btnBorrar = new JButton("Borrar");
		panelBotones.add(btnBorrar);
		
		panel.add(panelBotones, BorderLayout.SOUTH);
		
		lblDeportes = new JLabel("Tipos de Contacto");
		lblDeportes.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblDeportes, BorderLayout.NORTH);
		
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnEditar() {
		return btnEditar;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public void mostrarVentana() {
		this.setVisible(true);
	}

	public void llenarTabla(List<DeporteDTO> deportes) {
		DefaultListModel<DeporteDTO> dlm = new DefaultListModel<>();
		for (DeporteDTO deporte : deportes) {
			dlm.addElement(deporte);
		}
		this.listaDeportes.setModel(dlm);
	}

	public int obtenerFilaSeleccionada() {
		return this.listaDeportes.getSelectedIndex();
	}
}
