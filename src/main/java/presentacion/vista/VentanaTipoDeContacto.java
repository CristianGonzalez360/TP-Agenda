package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JPanel;
import dto.TipoContactoDTO;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;

public class VentanaTipoDeContacto extends JDialog {

	private static VentanaTipoDeContacto INSTANCE;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnBorrar;
	private JList<TipoContactoDTO> listaTiposDeContacto;
	private JLabel lblTiposDeContacto;

	public static VentanaTipoDeContacto getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new VentanaTipoDeContacto();
		}
		return INSTANCE;
	}

	private VentanaTipoDeContacto() {
		setBounds(100, 100, 410, 423);
		setModal(true);
		JPanel panel = new JPanel(new BorderLayout());
		setContentPane(panel);
		
		listaTiposDeContacto = new JList<>();
		panel.add(listaTiposDeContacto);

		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		btnAgregar = new JButton("Agregar");
		panelBotones.add(btnAgregar);
		
		btnEditar = new JButton("Editar");
		panelBotones.add(btnEditar);

		btnBorrar = new JButton("Borrar");
		panelBotones.add(btnBorrar);
		
		panel.add(panelBotones, BorderLayout.SOUTH);
		
		lblTiposDeContacto = new JLabel("Tipos de Contacto");
		lblTiposDeContacto.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblTiposDeContacto, BorderLayout.NORTH);
		
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

	public void llenarTabla(List<TipoContactoDTO> tipoContacto) {
		DefaultListModel<TipoContactoDTO> dlm = new DefaultListModel<>();
		for (TipoContactoDTO tipo : tipoContacto) {
			dlm.addElement(tipo);
		}
		this.listaTiposDeContacto.setModel(dlm);
	}

	public int obtenerFilaSeleccionada() {
		return this.listaTiposDeContacto.getSelectedIndex();
	}
}
