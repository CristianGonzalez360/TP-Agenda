package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditorDeporte extends JDialog {
	
	private static EditorDeporte INSTANCE = null;
	private JTextField txtNombre;
	private JButton btnAceptar;
	
	public static EditorDeporte getInstance()
	{
		if(INSTANCE == null) {
			INSTANCE = new EditorDeporte(); 	
		}
		return INSTANCE;
	}

	private EditorDeporte() {
		setBounds(new Rectangle(0, 0, 314, 108));
		
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
			JOptionPane.showMessageDialog(this, "Ingrece un nombre para el deporte.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		return ret;
	}
}
