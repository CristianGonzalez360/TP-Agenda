package presentacion.vista;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.ListSelectionModel;
import dto.PaisDTO;
import dto.ProvinciaDTO;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;

public class PanelEditorPais extends JPanel {
	private JTextField txtNombre;
	private JButton btnGuardar;
	private JButton btnBorrar;
	private JButton btnAgregarProvincia;
	private JButton btnEditarProvincia;
	private JButton btnBorrarProvincia;
	private JList<ProvinciaDTO> listaProvincias;
	private JComboBox<PaisDTO> comboPaises;
	private JPanel panelCentral;
	private JPanel panelNorte2;
	private JLabel lblProvincias;
	
	public PanelEditorPais() {
		setLayout(new BorderLayout());
		
		JPanel panelNorte = new JPanel();
		add(panelNorte, BorderLayout.NORTH);
		
		JLabel lblPaises = new JLabel("Países:");
		panelNorte.add(lblPaises);
		
		comboPaises = new JComboBox<>();
		comboPaises.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
			          PaisDTO pais = (PaisDTO) e.getItem();
			          txtNombre.setText(pais.getNombre());
			          llenarLista(pais);
			       }
			}
		});
		panelNorte.add(comboPaises);
		
		JPanel panelSur = new JPanel();
		add(panelSur, BorderLayout.SOUTH);
		
		btnGuardar = new JButton("Guardar");
		panelSur.add(btnGuardar);
		
		btnBorrar = new JButton("Borrar");
		panelSur.add(btnBorrar);
		
		panelCentral = new JPanel();
		add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(0, 0));
		
		listaProvincias = new JList<>();
		panelCentral.add(listaProvincias, BorderLayout.CENTER);
		listaProvincias.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JPanel panelEste = new JPanel();
		panelCentral.add(panelEste, BorderLayout.EAST);
		panelEste.setLayout(new BoxLayout(panelEste, BoxLayout.Y_AXIS));
		
		btnAgregarProvincia = new JButton("Agregar");
		panelEste.add(btnAgregarProvincia);
		
		btnEditarProvincia = new JButton("Editar");
		panelEste.add(btnEditarProvincia);
		
		btnBorrarProvincia = new JButton("Borrar");
		panelEste.add(btnBorrarProvincia);
		
		panelNorte2 = new JPanel(new FlowLayout(FlowLayout.LEFT));		
		panelCentral.add(panelNorte2, BorderLayout.NORTH);
		
		JLabel lblNombre = new JLabel("Nombre:");
		panelNorte2.add(lblNombre);
		
		txtNombre = new JTextField();
		panelNorte2.add(txtNombre);
		txtNombre.setColumns(10);
		
		lblProvincias = new JLabel(" Provincias: ");
		lblProvincias.setVerticalAlignment(SwingConstants.TOP);
		panelCentral.add(lblProvincias, BorderLayout.WEST);
	}

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public JButton getBtnGuardar() {
		return btnGuardar;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public JButton getBtnAgregarProvincia() {
		return btnAgregarProvincia;
	}

	public JButton getBtnEditarProvincia() {
		return btnEditarProvincia;
	}

	public JButton getBtnBorrarProvincia() {
		return btnBorrarProvincia;
	}

	public JComboBox<PaisDTO> getComboPaises() {
		return comboPaises;
	}

	public void mostrarPaises(List<PaisDTO> paises) {
		PaisDTO pais = getPaisSeleccionado();
		comboPaises.removeAllItems();
		comboPaises.addItem(new PaisDTO("Nuevo"));
		for (PaisDTO p : paises) {
			comboPaises.addItem(p);
		}
		seleccionar(pais);
	}
	
	public void seleccionar(PaisDTO pais) {
		comboPaises.setSelectedItem(pais);
	}
	
	protected void llenarLista(PaisDTO pais) {
		List<ProvinciaDTO> provincias = pais.getProvincias();
		DefaultListModel<ProvinciaDTO>  dlm = new DefaultListModel<>();
		for(ProvinciaDTO provincia : provincias) {
			dlm.addElement(provincia);
		}
		this.listaProvincias.setModel(dlm);
	}

	public PaisDTO getPaisSeleccionado() {
		return (PaisDTO) this.comboPaises.getSelectedItem();
	}

	public ProvinciaDTO getProvinciaSeleccionada() {
		return this.listaProvincias.getSelectedValue();
	}
}
