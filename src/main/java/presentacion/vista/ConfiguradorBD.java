package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import persistencia.conexion.DBProperties;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class ConfiguradorBD extends JDialog {

	private static final long serialVersionUID = -1915289007066938600L;
	private static ConfiguradorBD INSTANCE;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtUsuario;
	private JPasswordField pwdContrasea;
	private JTextField txtNombredebd;
	private JTextField txtPuerto;
	private JTextField txtIp;
	private JTextField txtPrefijo;
	private JButton okButton;
	private JButton cancelButton;

	private ConfiguradorBD() {
		setTitle("Propiedades de la BD");
		setBounds(100, 100, 303, 385);
		getContentPane().setLayout(new BorderLayout());

		setModal(true);

		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setBounds(12, 104, 124, 16);
		contentPanel.add(lblUsuario);

		JLabel lblContrasea = new JLabel("Contrase\u00F1a: ");
		lblContrasea.setBounds(12, 133, 124, 16);
		contentPanel.add(lblContrasea);

		JLabel lblNombreDeBase = new JLabel("Nombre de base: ");
		lblNombreDeBase.setBounds(12, 159, 124, 16);
		contentPanel.add(lblNombreDeBase);

		txtUsuario = new JTextField();
		txtUsuario.setBounds(148, 101, 125, 22);
		contentPanel.add(txtUsuario);
		txtUsuario.setColumns(10);

		pwdContrasea = new JPasswordField();
		pwdContrasea.setBounds(148, 130, 125, 19);
		contentPanel.add(pwdContrasea);

		txtNombredebd = new JTextField();
		txtNombredebd.setBounds(148, 156, 125, 22);
		contentPanel.add(txtNombredebd);
		txtNombredebd.setColumns(10);

		JLabel lblNotaNoModifique = new JLabel(
				"<html>Nota: No modifique estos datos si no es realmente necesario. En caso de tener problemas para conectar con la base de datos, comun\u00EDquese con el profesional a cargo de la misma.</html>");
		lblNotaNoModifique.setBounds(12, 194, 261, 98);
		contentPanel.add(lblNotaNoModifique);

		JLabel lblPuerto = new JLabel("Puerto:");
		lblPuerto.setBounds(12, 75, 56, 16);
		contentPanel.add(lblPuerto);

		txtPuerto = new JTextField();
		txtPuerto.setBounds(148, 72, 125, 22);
		contentPanel.add(txtPuerto);
		txtPuerto.setColumns(10);

		JLabel lblIp = new JLabel("IP:");
		lblIp.setBounds(12, 46, 56, 16);
		contentPanel.add(lblIp);

		txtIp = new JTextField();
		txtIp.setBounds(148, 43, 125, 22);
		contentPanel.add(txtIp);
		txtIp.setColumns(10);

		JLabel lblPrefijo = new JLabel("Prefijo:");
		lblPrefijo.setBounds(12, 17, 56, 16);
		contentPanel.add(lblPrefijo);

		txtPrefijo = new JTextField();
		txtPrefijo.setBounds(148, 14, 125, 22);
		contentPanel.add(txtPrefijo);
		txtPrefijo.setColumns(10);

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		okButton = new JButton("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(c -> dispose());
		buttonPane.add(cancelButton);
	}

	public JButton getOkButton() {
		return okButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public static ConfiguradorBD getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ConfiguradorBD();
		}
		return INSTANCE;
	}

	public JTextField getTxtUsuario() {
		return txtUsuario;
	}

	public JPasswordField getPwdContrasea() {
		return pwdContrasea;
	}

	public JTextField getTxtNombredebd() {
		return txtNombredebd;
	}

	public JTextField getTxtPuerto() {
		return txtPuerto;
	}

	public JTextField getTxtIp() {
		return txtIp;
	}

	public JTextField getTxtPrefijo() {
		return txtPrefijo;
	}

	public void mostrar(Properties dbProperties) {
		txtPrefijo.setText(dbProperties.getProperty("db_prefijo"));
		txtIp.setText(dbProperties.getProperty("db_ip"));
		txtPuerto.setText(dbProperties.getProperty("db_puerto"));
		txtNombredebd.setText(dbProperties.getProperty("db_nombre"));
		txtUsuario.setText(dbProperties.getProperty("db_usuario"));
		pwdContrasea.setText(dbProperties.getProperty("db_password"));
	}
}
