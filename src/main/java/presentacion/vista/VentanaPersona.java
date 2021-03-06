package presentacion.vista;

import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;
import dto.DeporteDTO;
import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;
import dto.TipoContactoDTO;
import java.awt.BorderLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.SwingConstants;

public class VentanaPersona extends JDialog {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtCalle;
	private JDateChooser chooserNacimiento;
	private JTextField txtAltura;
	private JTextField txtPiso;
	private JTextField txtDepartamento;
	private JTextField txtEmail;
	private JComboBox<ProvinciaDTO> comboProvincia;
	private JComboBox<PaisDTO> comboPais;
	private JComboBox<LocalidadDTO> comboLocalidad;
	private JComboBox<TipoContactoDTO> comboTipoContacto;

	private JButton btnAgregarPersona;
	private static VentanaPersona INSTANCE;
	private JComboBox<DeporteDTO> comboDeporte;

	public static VentanaPersona getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new VentanaPersona();
		}
		return INSTANCE;
	}

	private VentanaPersona() {
		super();
		setResizable(false);
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 326, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);

		// Labels

		JLabel lblNombreYApellido = new JLabel("Nombre y apellido*");
		lblNombreYApellido.setBounds(10, 4, 113, 14);
		panel.add(lblNombreYApellido);

		JLabel lblTelfono = new JLabel("Telefono*");
		lblTelfono.setBounds(10, 29, 113, 14);
		panel.add(lblTelfono);

		JLabel lblNacimiento = new JLabel("Nacimiento");
		lblNacimiento.setBounds(10, 204, 113, 14);
		panel.add(lblNacimiento);

		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setBounds(10, 104, 113, 14);
		panel.add(lblCalle);

		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setBounds(10, 129, 113, 14);
		panel.add(lblAltura);

		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setBounds(10, 154, 113, 14);
		panel.add(lblPiso);

		JLabel lblDepartamento = new JLabel("Departamento");
		lblDepartamento.setBounds(10, 179, 113, 14);
		panel.add(lblDepartamento);

		JLabel lblEmail = new JLabel("Email*");
		lblEmail.setBounds(10, 54, 113, 14);
		panel.add(lblEmail);

		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(10, 304, 113, 14);
		panel.add(lblLocalidad);

		JLabel lblTipoDeContacto = new JLabel("TipoDeContacto*");
		lblTipoDeContacto.setBounds(10, 79, 113, 14);
		panel.add(lblTipoDeContacto);

		// Texts

		txtNombre = new JTextField();
		txtNombre.setBounds(133, 1, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(133, 26, 164, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);

		txtCalle = new JTextField();
		txtCalle.setBounds(133, 101, 164, 20);
		panel.add(txtCalle);
		txtCalle.setColumns(10);

		chooserNacimiento = new JDateChooser();
		chooserNacimiento.setBounds(133, 201, 164, 20);
		panel.add(chooserNacimiento);

		txtAltura = new JTextField();
		txtAltura.setBounds(133, 126, 164, 20);
		panel.add(txtAltura);
		txtAltura.setColumns(10);

		txtPiso = new JTextField();
		txtPiso.setBounds(133, 151, 164, 20);
		panel.add(txtPiso);
		txtPiso.setColumns(10);

		txtDepartamento = new JTextField();
		txtDepartamento.setBounds(133, 176, 164, 20);
		panel.add(txtDepartamento);
		txtDepartamento.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setBounds(133, 51, 164, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);

		comboLocalidad = new JComboBox<LocalidadDTO>() {
			@Override
			public Object getSelectedItem() {
				Object ret = null;
				int indice = getSelectedIndex();
				if(indice != 0) {
					ret = super.getSelectedItem();
				}
				return ret;
			}
		};
			
		comboLocalidad.setBounds(133, 301, 164, 20);
		panel.add(comboLocalidad);

		comboTipoContacto = new JComboBox<>();
		comboTipoContacto.setBounds(133, 76, 164, 20);
		panel.add(comboTipoContacto);

		JLabel lblPas = new JLabel("País");
		lblPas.setBounds(10, 254, 113, 14);
		panel.add(lblPas);

		comboPais = new JComboBox<>();
		comboPais.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					PaisDTO pais = (PaisDTO) comboPais.getSelectedItem();
					mostrarProvincias(pais.getProvincias());
				}
			}
		});
		comboPais.setBounds(133, 251, 164, 20);
		panel.add(comboPais);

		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setBounds(10, 279, 113, 14);
		panel.add(lblProvincia);

		comboProvincia = new JComboBox<>();
		comboProvincia.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					ProvinciaDTO provincia = (ProvinciaDTO) comboProvincia.getSelectedItem();
					mostrarLocalidades(provincia.getLocalidades());
				}
			}
		});
		comboProvincia.setBounds(133, 276, 164, 20);
		panel.add(comboProvincia);
		
		JLabel lblDeporte = new JLabel("Deporte*");
		lblDeporte.setBounds(10, 229, 113, 14);
		panel.add(lblDeporte);
		
		comboDeporte = new JComboBox<>();
		comboDeporte.setBounds(133, 226, 164, 20);
		panel.add(comboDeporte);

		JPanel panelBotones = new JPanel();
		contentPane.add(panelBotones, BorderLayout.SOUTH);

		// Boton

		btnAgregarPersona = new JButton("Guardar");
		panelBotones.add(btnAgregarPersona);
		
		JLabel lblInformacion = new JLabel("Los datos marcados con * son obligatorios.");
		lblInformacion.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblInformacion, BorderLayout.NORTH);

		this.setVisible(false);
	}

	public void mostrarVentana() {
		this.setVisible(true);
	}

	// Getter

	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public JTextField getTxtTelefono() {
		return txtTelefono;
	}

	public JTextField getTxtCalle() {
		return txtCalle;
	}

	public JDateChooser getChooserNacimiento() {
		return chooserNacimiento;
	}

	public JTextField getTxtAltura() {
		return txtAltura;
	}

	public JTextField getTxtPiso() {
		return txtPiso;
	}

	public JTextField getTxtDepartamento() {
		return txtDepartamento;
	}

	public JTextField getTxtEmail() {
		return txtEmail;
	}

	public JComboBox<LocalidadDTO> getComboLocalidad() {
		return comboLocalidad;
	}

	public JComboBox<TipoContactoDTO> getComboTipoContacto() {
		return comboTipoContacto;
	}

	public JComboBox<PaisDTO> getComboPais() {
		return comboPais;
	}

	public JComboBox<DeporteDTO> getComboDeporte() {
		return comboDeporte;
	}

	public JButton getBtnAgregarPersona() {
		return btnAgregarPersona;
	}

	// Cerrar

	public void cerrar() {
		this.txtNombre.setText(null);
		this.txtTelefono.setText(null);
		this.txtCalle.setText(null);
		this.chooserNacimiento.setDate(null);
		this.txtAltura.setText(null);
		this.txtPiso.setText(null);
		this.txtDepartamento.setText(null);
		this.txtEmail.setText(null);
		if (this.comboPais.getItemCount() > 0) {
			this.comboPais.setSelectedIndex(0);
		}
		if (this.comboTipoContacto.getItemCount() > 0) {
			this.comboTipoContacto.setSelectedIndex(0);
		}
		if (this.comboDeporte.getItemCount() > 0) {
			this.comboDeporte.setSelectedIndex(0);
		}
		
		this.dispose();
	}

	public void mostrarPaises(List<PaisDTO> paises) {
		getComboPais().removeAllItems();
		getComboPais().addItem(new PaisDTO());
		for (PaisDTO pais : paises) {
			getComboPais().addItem(pais);
		}
	}

	protected void mostrarLocalidades(List<LocalidadDTO> localidades) {
		comboLocalidad.removeAllItems();
		getComboLocalidad().addItem(new LocalidadDTO());
		for (LocalidadDTO l : localidades) {
			comboLocalidad.addItem(l);
		}
	}

	protected void mostrarProvincias(List<ProvinciaDTO> provincias) {
		comboProvincia.removeAllItems();
		comboProvincia.addItem(new ProvinciaDTO());
		for (ProvinciaDTO p : provincias) {
			comboProvincia.addItem(p);
		}
	}

	public void mostrarTiposDeContacto(List<TipoContactoDTO> tipos) {
		DefaultComboBoxModel<TipoContactoDTO> dcm = new DefaultComboBoxModel<>();
		for (TipoContactoDTO t : tipos) {
			dcm.addElement(t);
		}
		getComboTipoContacto().setModel(dcm);
	}

	public void mostrarPersona(PersonaDTO persona) {
		this.txtNombre.setText(persona.getNombre());
		this.txtTelefono.setText(persona.getTelefono());
		this.txtCalle.setText(persona.getCalle());
		this.chooserNacimiento.setDate(persona.getNacimiento());
		this.txtAltura.setText(persona.getAltura() + "");
		this.txtPiso.setText(persona.getPiso() + "");
		this.txtDepartamento.setText(persona.getDepartamento());
		this.txtEmail.setText(persona.getEmail());
		LocalidadDTO localidad = persona.getLocalidad();
		ProvinciaDTO provincia = null;
		PaisDTO pais = null;
		if(localidad != null) {
			provincia = localidad.getProvincia();
			pais = provincia.getPais();
		}
		this.comboPais.setSelectedItem(pais);
		this.comboProvincia.setSelectedItem(provincia);
		this.comboLocalidad.setSelectedItem(persona.getLocalidad());
		this.comboTipoContacto.setSelectedItem(persona.getTipoContacto());
		this.comboDeporte.setSelectedItem(persona.getDeporte());
	}

	public boolean validarDatos() {
		String mensaje = "";
		if (!getTxtNombre().getText().matches("[a-zA-Záéíóú ]+"))
			mensaje = "El contacto debe tener un nombre!";
		if (!getTxtTelefono().getText().matches("[0-9\\-\\+ ]+"))
			mensaje = mensaje + "\nEl contacto debe tener un número de teléfono! (números, espacios y signos + y -)";
//		if (getTxtCalle().getText().trim().isEmpty())
//			mensaje = mensaje + "\nDebe ingresar el nombre de la calle donde vive el contacto!";
//		if (getChooserNacimiento().getDate() == null)
//			mensaje = mensaje + "\nDebe seleccionar una fecha de nacimiento!";
		if (getChooserNacimiento().getDate() != null && getChooserNacimiento().getDate().after(new Date()))
			mensaje = mensaje + "\nDebe seleccionar una fecha anterior al día de hoy!";
		if (!getTxtAltura().getText().isEmpty() && !getTxtAltura().getText().matches("[0-9]+"))
			mensaje = mensaje + "\nDebe especificar un número de calle válido!";
		if (!getTxtPiso().getText().isEmpty() && !getTxtPiso().getText().matches("[0-9]+"))
			mensaje = mensaje + "\nDebe especificar un piso válido!";
//		if (getComboLocalidad().getSelectedIndex() == -1)
//			mensaje = mensaje + "\nDebe seleccionar una localidad!";
		if (!getTxtEmail().getText().matches(
				"(?:[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*|\\\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\\\")@(?:(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"))
			mensaje = mensaje + "\nSe debe ingresar una dirección de email válida!";
		if (getComboTipoContacto().getSelectedIndex() == -1)
			mensaje = mensaje + "\nDebe seleccionar un tipo de contacto!";
		if(getComboDeporte().getSelectedIndex() == -1) 
			mensaje = mensaje + "\nDebe seleccionar un deporte!";
		
		if (!mensaje.isEmpty()) {
			JOptionPane.showMessageDialog(this, mensaje, "Error al guardar", JOptionPane.ERROR_MESSAGE);
		}

		return mensaje.isEmpty();
	}

	public void mostrarDeportes(List<DeporteDTO> deportes) {
		DefaultComboBoxModel<DeporteDTO> dcm = new DefaultComboBoxModel<>();
		for (DeporteDTO d : deportes) {
			dcm.addElement(d);
		}
		getComboDeporte().setModel(dcm);
	}
}
