package presentacion.vista;

import java.util.Date;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;
import dto.TipoContactoDTO;
import java.awt.BorderLayout;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class VentanaPersona extends JFrame {
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
	

	public static VentanaPersona getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new VentanaPersona();
		}
		return INSTANCE;
	}

	private VentanaPersona() {
		super();
		this.setTitle("Agregar Contacto");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 343, 469);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(null);

		// Labels

		JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
		lblNombreYApellido.setBounds(10, 4, 113, 14);
		panel.add(lblNombreYApellido);

		JLabel lblTelfono = new JLabel("Telefono");
		lblTelfono.setBounds(10, 35, 113, 14);
		panel.add(lblTelfono);

		

		JLabel lblNacimiento = new JLabel("Nacimiento");
		lblNacimiento.setBounds(10, 66, 113, 14);
		panel.add(lblNacimiento);
		
		
		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setBounds(10, 94, 113, 14);
		panel.add(lblCalle);

		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setBounds(10, 128, 113, 14);
		panel.add(lblAltura);

		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setBounds(10, 159, 113, 14);
		panel.add(lblPiso);

		JLabel lblDepartamento = new JLabel("Departamento");
		lblDepartamento.setBounds(10, 190, 113, 14);
		panel.add(lblDepartamento);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 221, 113, 14);
		panel.add(lblEmail);

		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(10, 314, 113, 14);
		panel.add(lblLocalidad);

		JLabel lblTipoDeContacto = new JLabel("TipoDeContacto");
		lblTipoDeContacto.setBounds(10, 345, 113, 14);
		panel.add(lblTipoDeContacto);

		// Texts

		txtNombre = new JTextField();
		txtNombre.setBounds(133, 1, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(133, 32, 164, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);

		txtCalle = new JTextField();
		txtCalle.setBounds(133, 94, 164, 20);
		panel.add(txtCalle);
		txtCalle.setColumns(10);

		chooserNacimiento = new JDateChooser();
		chooserNacimiento.setBounds(133, 66, 164, 20);
		panel.add(chooserNacimiento);

		txtAltura = new JTextField();
		txtAltura.setBounds(133, 125, 164, 20);
		panel.add(txtAltura);
		txtAltura.setColumns(10);

		txtPiso = new JTextField();
		txtPiso.setBounds(133, 156, 164, 20);
		panel.add(txtPiso);
		txtPiso.setColumns(10);

		txtDepartamento = new JTextField();
		txtDepartamento.setBounds(133, 187, 164, 20);
		panel.add(txtDepartamento);
		txtDepartamento.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setBounds(133, 218, 164, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);

		comboLocalidad = new JComboBox<>();
		comboLocalidad.setBounds(133, 311, 164, 20);
		panel.add(comboLocalidad);

		comboTipoContacto = new JComboBox<>();
		comboTipoContacto.setBounds(133, 342, 164, 20);
		panel.add(comboTipoContacto);
		
		JLabel lblPas = new JLabel("País");
		lblPas.setBounds(10, 252, 113, 14);
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
		comboPais.setBounds(133, 249, 164, 20);
		panel.add(comboPais);
		
		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setBounds(10, 283, 113, 14);
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
		comboProvincia.setBounds(133, 280, 164, 20);
		panel.add(comboProvincia);
		
		JPanel panelBotones = new JPanel();
		contentPane.add(panelBotones, BorderLayout.SOUTH);
		
		// Boton
		
		btnAgregarPersona = new JButton("Guardar");
		panelBotones.add(btnAgregarPersona);

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

	public JButton getBtnAgregarPersona() {
		return btnAgregarPersona;
	}

	// Cerrar

	public void cerrar() {
		this.txtNombre.setText(null);
		this.txtTelefono.setText(null);
		this.txtCalle.setText(null);
		this.chooserNacimiento.setDate(new Date());
		this.txtAltura.setText(null);
		this.txtPiso.setText(null);
		this.txtDepartamento.setText(null);
		this.txtEmail.setText(null);
		if(this.comboPais.getItemCount() > 0) {
			this.comboPais.setSelectedIndex(0);
		}
		if(this.comboTipoContacto.getItemCount() > 0) {
			this.comboTipoContacto.setSelectedIndex(0);
		}
		this.dispose();
	}
	
	public void mostrarPaises(List<PaisDTO> paises) {
		getComboPais().removeAllItems();
		for (PaisDTO pais : paises) {
			getComboPais().addItem(pais);
		}
	}
	
	protected void mostrarLocalidades(List<LocalidadDTO> localidades) {
		comboLocalidad.removeAllItems();
		for (LocalidadDTO l : localidades) {
			comboLocalidad.addItem(l);
		}
	}

	protected void mostrarProvincias(List<ProvinciaDTO> provincias) {
		comboProvincia.removeAllItems();
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
		this.comboLocalidad.setSelectedItem(persona.getLocalidad());
		this.comboProvincia.setSelectedItem(persona.getLocalidad().getProvincia());
		this.comboPais.setSelectedItem(persona.getLocalidad().getProvincia().getPais());
		this.comboTipoContacto.setSelectedItem(persona.getTipoContacto());
	}
}
