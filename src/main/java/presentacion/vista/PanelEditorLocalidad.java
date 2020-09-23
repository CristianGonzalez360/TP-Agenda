package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.ProvinciaDTO;

public class PanelEditorLocalidad extends JPanel {
	
	private JTextField txtNombre;
	private JButton btnGuardar;
	private JButton btnBorrar;
	private JComboBox<PaisDTO> comboPaises;
	private JLabel lblProvincias;
	private JComboBox<ProvinciaDTO> comboProvincias;
	private JComboBox<LocalidadDTO> comboLocalidades;
	private JPanel panelNorte2;
	private JPanel panelCentral;
	private JPanel panelCentralNorte;
	private JPanel panelOesteNorte;
	private JButton btnAtras;

	public PanelEditorLocalidad() {
		setLayout(new BorderLayout());
		
		JPanel panelNorte = new JPanel();
		add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new BorderLayout(0, 0));
		
		panelCentralNorte = new JPanel();
		panelNorte.add(panelCentralNorte, BorderLayout.CENTER);
		
		JLabel lblLocalidad = new JLabel("Localidad:");
		panelCentralNorte.add(lblLocalidad);
		
		comboLocalidades = new JComboBox<>();
		panelCentralNorte.add(comboLocalidades);
		
		panelOesteNorte = new JPanel();
		panelNorte.add(panelOesteNorte, BorderLayout.WEST);
		
		btnAtras = new JButton("Atras");
		panelOesteNorte.add(btnAtras);
		
		comboLocalidades.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					LocalidadDTO localidad = (LocalidadDTO) comboLocalidades.getSelectedItem();
					txtNombre.setText(localidad.getNombre());
				}
			}
		});
		
		JPanel panelSur = new JPanel();
		add(panelSur, BorderLayout.SOUTH);
		
		btnGuardar = new JButton("Guardar");
		panelSur.add(btnGuardar);
		
		btnBorrar = new JButton("Borrar");
		panelSur.add(btnBorrar);
		
		panelCentral = new JPanel();
		add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(0, 0));
		
		panelNorte2 = new JPanel();
		panelCentral.add(panelNorte2, BorderLayout.NORTH);
		
		JLabel lblNombre = new JLabel("Nombre:");
		panelNorte2.add(lblNombre);
		
		txtNombre = new JTextField();
		panelNorte2.add(txtNombre);
		txtNombre.setColumns(10);
		
		JLabel lblPaises = new JLabel("País:");
		panelNorte2.add(lblPaises);
		
		comboPaises = new JComboBox<>();
		panelNorte2.add(comboPaises);
		comboPaises.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
			          PaisDTO pais = (PaisDTO) e.getItem();
			          mostrarProvincias(pais.getProvincias());
			       }
			}
		});
		
		lblProvincias = new JLabel("Provincia:");
		panelNorte2.add(lblProvincias);
		
		comboProvincias = new JComboBox<>();
		panelNorte2.add(comboProvincias);
		comboProvincias.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ProvinciaDTO provincia = (ProvinciaDTO) comboProvincias.getSelectedItem();
					mostrarLocalidades(provincia.getLocalidades());
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
			comboLocalidades.addItem(new LocalidadDTO("Nueva"));
		}
	}
	
	public void mostrarProvincias(List<ProvinciaDTO> provincias) {
		ProvinciaDTO provincia = getProvinciaSeleccionada();
		comboProvincias.removeAllItems();
		for (ProvinciaDTO p : provincias) {
			comboProvincias.addItem(p);
		}
		if(!provincias.isEmpty() && provincia != null) {
			seleccionar(provincia);
		}
		else if(provincias.isEmpty()) {
			comboLocalidades.removeAllItems();
			comboLocalidades.addItem(new LocalidadDTO("Nueva"));
		}
		else {
			comboLocalidades.setSelectedIndex(0);
		}
	}
	
	public void mostrarLocalidades(List<LocalidadDTO> localidades) {
		LocalidadDTO localidad = getLocalidadSeleccionada(); 
		comboLocalidades.removeAllItems();
		comboLocalidades.addItem(new LocalidadDTO("Nueva"));
		for(LocalidadDTO l : localidades) {
			comboLocalidades.addItem(l);
		}
		if(localidad != null) {
			seleccionar(localidad);
		}
		else {
			comboLocalidades.setSelectedIndex(0);
		}
	}
	
	public void seleccionar(PaisDTO pais) {
		comboPaises.setSelectedItem(pais);
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

	public JComboBox<LocalidadDTO> getComboLocalidades() {
		return comboLocalidades;
	}

	public void seleccionar(LocalidadDTO localidad) {
		this.comboLocalidades.setSelectedItem(localidad);
	}

	public LocalidadDTO getLocalidadSeleccionada() {
		return (LocalidadDTO) this.comboLocalidades.getSelectedItem();
	}
	
	public boolean validarDatos() {
		String mensaje = "";
		if(getTxtNombre().getText().equals("") ) {
			mensaje += "Ingrese un nombre";
		}
		else if(getComboProvincias().getSelectedItem() == null) {
			mensaje += "\nSeleccione una provincia";
		}
		
		if(!mensaje.isEmpty()) {
			JOptionPane.showMessageDialog(null, mensaje, "Error al guardar", JOptionPane.ERROR_MESSAGE);
		}
		return mensaje.isEmpty();
	}
}
