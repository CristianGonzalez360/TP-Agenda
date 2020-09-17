package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.ProvinciaDTO;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;

public class PanelEditorProvincia extends JPanel {
	
	private JTextField txtNombre;
	private JButton btnGuardar;
	private JButton btnBorrar;
	private JButton btnAgregarLocalidad;
	private JButton btnEditarLocalidad;
	private JButton btnBorrarLocalidad;
	private JList<LocalidadDTO> listaLocalidades;
	private JComboBox<PaisDTO> comboPaises;
	private JLabel lblProvincias;
	private JComboBox<ProvinciaDTO> comboProvincias;
	private JPanel panelCentral;
	private JPanel panelNorteCentral;
	private JLabel lblLocalidades;
	private JPanel panelCentralNorte;
	private JButton btnAtras;
	private JPanel panelOesteNorte;

	public PanelEditorProvincia() {
		setLayout(new BorderLayout(5, 0));
		
		JPanel panelNorte = new JPanel();
		add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new BorderLayout(0, 0));
		
		panelCentralNorte = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelNorte.add(panelCentralNorte, BorderLayout.CENTER);
		
		lblProvincias = new JLabel("Provincia:");
		panelCentralNorte.add(lblProvincias);
		
		comboProvincias = new JComboBox<>();
		panelCentralNorte.add(comboProvincias);
		
		panelOesteNorte = new JPanel();
		panelNorte.add(panelOesteNorte, BorderLayout.WEST);
		
		btnAtras = new JButton("Atras");
		panelOesteNorte.add(btnAtras);
				
		comboProvincias.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ProvinciaDTO provincia = (ProvinciaDTO) comboProvincias.getSelectedItem();
					txtNombre.setText(provincia.getNombre());
					llenarLista(provincia);
				}
			}
		});
		
		JPanel panelSur = new JPanel();
		add(panelSur, BorderLayout.SOUTH);
		
		btnGuardar = new JButton("Guardar");
		panelSur.add(btnGuardar);
		
		btnBorrar = new JButton("Borrar");
		panelSur.add(btnBorrar);
		
		panelCentral = new JPanel(new BorderLayout());
		add(panelCentral, BorderLayout.CENTER);
		
		listaLocalidades = new JList<>();
		panelCentral.add(listaLocalidades);
		listaLocalidades.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JPanel panelEste = new JPanel();
		panelCentral.add(panelEste, BorderLayout.EAST);
		panelEste.setLayout(new BoxLayout(panelEste, BoxLayout.Y_AXIS));
		
		btnAgregarLocalidad = new JButton("Agregar");
		panelEste.add(btnAgregarLocalidad);
		
		btnEditarLocalidad = new JButton("Editar");
		panelEste.add(btnEditarLocalidad);
		
		btnBorrarLocalidad = new JButton("Borrar");
		panelEste.add(btnBorrarLocalidad);
		
		panelNorteCentral = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelCentral.add(panelNorteCentral, BorderLayout.NORTH);
		
		JLabel lblNombre = new JLabel("Nombre:");
		panelNorteCentral.add(lblNombre);
		
		txtNombre = new JTextField();
		panelNorteCentral.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblPaises = new JLabel("País:");
		panelNorteCentral.add(lblPaises);
		
		comboPaises = new JComboBox<>();
		panelNorteCentral.add(comboPaises);
		
		lblLocalidades = new JLabel(" Localidades: ");
		lblLocalidades.setVerticalAlignment(SwingConstants.TOP);
		panelCentral.add(lblLocalidades, BorderLayout.WEST);
		comboPaises.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
			          PaisDTO pais = (PaisDTO) e.getItem();
			          mostrarProvincias(pais.getProvincias());
			       }
			}
		});
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

	public JButton getBtnAgregarLocalidad() {
		return btnAgregarLocalidad;
	}

	public JButton getBtnEditarLicalidad() {
		return btnEditarLocalidad;
	}

	public JButton getBtnBorrarLocalidad() {
		return btnBorrarLocalidad;
	}

	public JButton getBtnAtras() {
		return btnAtras;
	}

	public JComboBox<PaisDTO> getComboPaises() {
		return comboPaises;
	}

	public void mostrarPaises(List<PaisDTO> paises) {
		PaisDTO pais = getPaisSeleccionado();
		comboPaises.removeAllItems();
		for (PaisDTO p : paises) {
			comboPaises.addItem(p);
		}
		if (!paises.isEmpty() && pais != null) {
			seleccionar(pais);
		}
		else if(paises.isEmpty()) {
			comboProvincias.removeAllItems();
		}
		else {
			comboProvincias.setSelectedIndex(0);
		}
	}
	
	public void mostrarProvincias(List<ProvinciaDTO> provincias) {
		ProvinciaDTO provincia = getProvinciaSeleccionada();
		comboProvincias.removeAllItems();
		comboProvincias.addItem(new ProvinciaDTO("Nueva"));
		for (ProvinciaDTO p : provincias) {
			comboProvincias.addItem(p);
		}
		if(provincia != null) {
			seleccionar(provincia);
		}
		else {
			comboProvincias.setSelectedIndex(0);
		}
	}
	
	public void seleccionar(PaisDTO pais) {
		comboPaises.setSelectedItem(pais);
	}
	
	protected void llenarLista(ProvinciaDTO provincia) {
		List<LocalidadDTO> localidades = provincia.getLocalidades();
		DefaultListModel<LocalidadDTO>  dlm = new DefaultListModel<>();
		for(LocalidadDTO localidad : localidades) {
			dlm.addElement(localidad);
		}
		this.listaLocalidades.setModel(dlm);
	}

	public void seleccionar(ProvinciaDTO provincia) {
		comboProvincias.setSelectedItem(provincia);		
	}

	public ProvinciaDTO getProvinciaSeleccionada() {
		return (ProvinciaDTO) this.comboProvincias.getSelectedItem();
	}

	public PaisDTO getPaisSeleccionado() {
		return (PaisDTO) this.comboPaises.getSelectedItem();
	}

	public JComboBox<ProvinciaDTO> getComboProvincias() {
		return this.comboProvincias;
	}

	public LocalidadDTO getLocalidadSeleccionada() {
		return this.listaLocalidades.getSelectedValue();
	}
}
