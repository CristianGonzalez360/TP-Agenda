package presentacion.vista;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Rectangle;

public class EditorLocalidad extends JDialog {
	
	private static EditorLocalidad INSTANCE = null;
	private JTextField txtNombre;
	private JButton btnAceptar;
	
	public static EditorLocalidad getInstance()
	{
		if(INSTANCE == null) {
			INSTANCE = new EditorLocalidad(); 	
		}
		return INSTANCE;
	}

	private EditorLocalidad() {
		setBounds(new Rectangle(0, 0, 314, 108));
		getContentPane().setLayout(new BorderLayout(0, 0));
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(null);
		
		getContentPane().add(panelCentral, BorderLayout.CENTER);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 11, 46, 14);
		panelCentral.add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(66, 8, 225, 20);
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
}
