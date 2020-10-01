package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Rectangle;

public class EditorTipoContacto extends JDialog {
	
	private static EditorTipoContacto INSTANCE = null;
	private JTextField txtNombre;
	private JButton btnAceptar;
	
	public static EditorTipoContacto getInstance()
	{
		if(INSTANCE == null) {
			INSTANCE = new EditorTipoContacto(); 	
		}
		return INSTANCE;
	}

	private EditorTipoContacto() {
		setBounds(100, 100, 314, 108);
		setModal(true);
		getContentPane().setLayout(new BorderLayout(0, 0));
		JPanel panelCentral = new JPanel();
		
		getContentPane().add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		JLabel lblNombre = new JLabel("Nombre:");
		panelCentral.add(lblNombre);
		
		txtNombre = new JTextField();
		panelCentral.add(txtNombre);
		txtNombre.setColumns(10);
		
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(panelBotones, BorderLayout.SOUTH);
		
		btnAceptar = new JButton("Aceptar");
		panelBotones.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cerrar();
			}
		});
		panelBotones.add(btnCancelar);
	}
	
	public JButton getBtnAceptar() {
		return btnAceptar;
	}
	
	public JTextField getTxtNombre() {
		return txtNombre;
	}

	public void mostrarVentana() {
		setVisible(true);
	}

	public void cerrar() {
		getTxtNombre().setText(null);
		dispose();
	}
	
	public boolean validar() {
		boolean ret = true;
		if(getTxtNombre().getText().isEmpty()) {
			ret = false;
			JOptionPane.showMessageDialog(this, "Ingrece un nombre para el tipo de contacto.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return ret;
	}
}
