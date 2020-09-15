package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import dto.LocalidadDTO;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTable;

public class VentanaLocalidades extends JDialog {

	private static VentanaLocalidades INSTANCE;
	private JButton btnAgregar;
	private JButton btnEditar;
	private JButton btnBorrar;
	private JTable tablaLocalidades;

	public static VentanaLocalidades getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new VentanaLocalidades();
		}
		return INSTANCE;
	}

	private VentanaLocalidades() {
		setBounds(100, 100, 410, 423);

		JPanel panel = new JPanel(new BorderLayout());
		setContentPane(panel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 282, 352);
		panel.add(scrollPane, BorderLayout.CENTER);
		
		tablaLocalidades = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tablaLocalidades.getTableHeader().setReorderingAllowed(false);
		tablaLocalidades.setBounds(0, 0, 1, 1);
		scrollPane.setViewportView(tablaLocalidades);

		JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		btnAgregar = new JButton("Agregar");
		panelBotones.add(btnAgregar);
		
		btnEditar = new JButton("Editar");
		panelBotones.add(btnEditar);

		btnBorrar = new JButton("Borrar");
		panelBotones.add(btnBorrar);
		
		panel.add(panelBotones, BorderLayout.SOUTH);
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

	public void llenarTabla(List<LocalidadDTO> localidades) {
		DefaultTableModel dtm = new DefaultTableModel();
		String[] identifiers = { "Localidades" };
		dtm.setColumnIdentifiers(identifiers);
		for (LocalidadDTO l : localidades) {
			String[] fila = { l.getNombre() };
			dtm.addRow(fila);
		}
		this.tablaLocalidades.setModel(dtm);
	}

	public int obtenerFilaSeleccionada() {
		return this.tablaLocalidades.getSelectedRow();
	}
}
