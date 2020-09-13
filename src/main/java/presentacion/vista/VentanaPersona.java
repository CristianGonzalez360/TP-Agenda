package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class VentanaPersona extends JFrame 
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private JTextField txtCalle;
	private JTextField txtNacimiento;
	private JTextField txtAltura;
	private JTextField txtPiso;
	private JTextField txtDepartamento;
	private JTextField txtEmail;
	private JTextField txtLocalidad;
	private JTextField txtTipoContacto;
	
	private JButton btnAgregarPersona;
	private static VentanaPersona INSTANCE;
	
	public static VentanaPersona getInstance()
	{
		if(INSTANCE == null)
		{
			INSTANCE = new VentanaPersona(); 	
			return new VentanaPersona();
		}
		else
			return INSTANCE;
	}

	private VentanaPersona() 
	{
		super();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 343, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 307, 550);
		contentPane.add(panel);
		panel.setLayout(null);
		
		//Labels
		
		JLabel lblNombreYApellido = new JLabel("Nombre y apellido");
		lblNombreYApellido.setBounds(10, 1, 113, 14);
		panel.add(lblNombreYApellido);
		
		JLabel lblTelfono = new JLabel("Telefono");
		lblTelfono.setBounds(10, 50, 113, 14);
		panel.add(lblTelfono);
		
		JLabel lblCalle = new JLabel("Calle");
		lblCalle.setBounds(10, 100, 113, 14);
		panel.add(lblCalle);
		
		JLabel lblNacimiento = new JLabel("Nacimiento");
		lblNacimiento.setBounds(10, 150, 113, 14);
		panel.add(lblNacimiento);
		
		JLabel lblAltura = new JLabel("Altura");
		lblAltura.setBounds(10, 200, 113, 14);
		panel.add(lblAltura);
		
		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setBounds(10, 250, 113, 14);
		panel.add(lblPiso);
		
		JLabel lblDepartamento = new JLabel("Departamento");
		lblDepartamento.setBounds(10, 300, 113, 14);
		panel.add(lblDepartamento);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(10, 350, 113, 14);
		panel.add(lblEmail);

		JLabel lblLocalidad = new JLabel("Localidad");
		lblLocalidad.setBounds(10, 400, 113, 14);
		panel.add(lblLocalidad);
		
		JLabel lblTipoDeContacto = new JLabel("TipoDeContacto");
		lblTipoDeContacto.setBounds(10, 450, 113, 14);
		panel.add(lblTipoDeContacto);
		
		// Texts
		
		txtNombre = new JTextField();
		txtNombre.setBounds(133, 1, 164, 20);
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(133, 50, 164, 20);
		panel.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		txtCalle = new JTextField();
		txtCalle.setBounds(133, 100, 164, 20);
		panel.add(txtCalle);
		txtCalle.setColumns(10);
				
		txtNacimiento = new JTextField();
		txtNacimiento.setBounds(133, 150, 164, 20);
		panel.add(txtNacimiento);
		txtNacimiento.setColumns(10);
		
		txtAltura = new JTextField();
		txtAltura.setBounds(133, 200, 164, 20);
		panel.add(txtAltura);
		txtAltura.setColumns(10);
		
		txtPiso = new JTextField();
		txtPiso.setBounds(133, 250, 164, 20);
		panel.add(txtPiso);
		txtPiso.setColumns(10);
		
		txtDepartamento = new JTextField();
		txtDepartamento.setBounds(133, 300, 164, 20);
		panel.add(txtDepartamento);
		txtDepartamento.setColumns(10);
		
		txtEmail = new JTextField();
		txtEmail.setBounds(133, 350, 164, 20);
		panel.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtLocalidad = new JTextField();
		txtLocalidad.setBounds(133, 400, 164, 20);
		panel.add(txtLocalidad);
		txtLocalidad.setColumns(10);
				
		txtTipoContacto = new JTextField();
		txtTipoContacto.setBounds(133, 450, 164, 20);
		panel.add(txtTipoContacto);
		txtTipoContacto.setColumns(10);
		
		
		// Boton
		
		btnAgregarPersona = new JButton("Agregar");
		btnAgregarPersona.setBounds(208, 500, 89, 23);
		panel.add(btnAgregarPersona);
		
		this.setVisible(false);
	}
	
	public void mostrarVentana()
	{
		this.setVisible(true);
	}
	
	
	//Getters
	
	public JTextField getTxtNombre() 
	{
		return txtNombre;
	}

	public JTextField getTxtTelefono() 
	{
		return txtTelefono;
	}
	
	public JTextField getTxtCalle() 
	{
		return txtCalle;
	}

	public JTextField getTxtNacimiento()
	{
		return txtNacimiento;
	}
	
	public JTextField getTxtAltura() 
	{
		return txtAltura;
	}

	public JTextField getTxtPiso() 
	{
		return txtPiso;
	}
	
	public JTextField getTxtDepartamento() 
	{
		return txtDepartamento;
	}

	public JTextField getTxtEmail() 
	{
		return txtEmail;
	}
	
	public JTextField getTxtLocalidad() 
	{
		return txtLocalidad;
	}

	public JTextField getTxtTipoContacto() 
	{
		return txtTipoContacto;
	}

	public JButton getBtnAgregarPersona() 
	{
		return btnAgregarPersona;
	}

	
	//Cerrar
	
	public void cerrar()
	{
	
		 this.txtNombre.setText(null);
		 this.txtTelefono.setText(null);
		 this.txtCalle.setText(null);
		 this.txtNacimiento.setText(null);
		 this.txtAltura.setText(null);
		 this.txtPiso.setText(null);
		 this.txtDepartamento.setText(null);
		 this.txtEmail.setText(null);
		 this.txtLocalidad.setText(null);
		 this.txtTipoContacto.setText(null);

		 this.dispose();
	}
	
}

