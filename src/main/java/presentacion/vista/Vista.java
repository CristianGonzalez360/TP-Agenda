package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.PersonaDTO;

import javax.swing.JButton;

import persistencia.conexion.Conexion;

public class Vista
{
	private JFrame frame;
	private JTable tablaPersonas;
	private JButton btnAgregar;
	private JButton btnBorrar;
	private JButton btnReporte;
	private DefaultTableModel modelPersonas;
	private String[] nombreColumnas = {"Nombre y apellido","Telefono","Calle","Nacimiento","Altura","Piso","Departamento","Mail","Localidad","TipoDeContacto"};

	public Vista() 
	{
		super();
		initialize();
	}

	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		frame.setContentPane(panel);
		panel.setLayout(new BorderLayout());
		
		JScrollPane spPersonas = new JScrollPane();
		spPersonas.setBounds(10, 11, 414, 182);
		panel.add(spPersonas, BorderLayout.CENTER);
		
		modelPersonas = new DefaultTableModel(null,nombreColumnas);
		tablaPersonas = new JTable(modelPersonas) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		tablaPersonas.getColumnModel().getColumn(0).setPreferredWidth(103);
		tablaPersonas.getColumnModel().getColumn(0).setResizable(false);
		tablaPersonas.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaPersonas.getColumnModel().getColumn(1).setResizable(false);
		
		spPersonas.setViewportView(tablaPersonas);
		
		JPanel botones = new JPanel();
		
		btnAgregar = new JButton("Agregar");
		botones.add(btnAgregar);
		
		JButton btnEditar = new JButton("Editar");
		botones.add(btnEditar);
		
		btnBorrar = new JButton("Borrar");
		botones.add(btnBorrar);
		
		btnReporte = new JButton("Reporte");
		botones.add(btnReporte);
		
		panel.add(botones, BorderLayout.SOUTH);
	}
	
	public void show()
	{
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() 
		{
			@Override
		    public void windowClosing(WindowEvent e) {
		        int confirm = JOptionPane.showOptionDialog(
		             null, "¿Estás seguro que quieres salir de la Agenda?", 
		             "Confirmación", JOptionPane.YES_NO_OPTION,
		             JOptionPane.QUESTION_MESSAGE, null, null, null);
		        if (confirm == 0) {
		        	Conexion.getConexion().cerrarConexion();
		           System.exit(0);
		        }
		    }
		});
		this.frame.setVisible(true);
	}
	
	public JButton getBtnAgregar() 
	{
		return btnAgregar;
	}

	public JButton getBtnBorrar() 
	{
		return btnBorrar;
	}
	
	public JButton getBtnReporte() 
	{
		return btnReporte;
	}
	
	public DefaultTableModel getModelPersonas() 
	{
		return modelPersonas;
	}
	
	public JTable getTablaPersonas()
	{
		return tablaPersonas;
	}

	public String[] getNombreColumnas() 
	{
		return nombreColumnas;
	}


	public void llenarTabla(List<PersonaDTO> personasEnTabla) {
		this.getModelPersonas().setRowCount(0); //Para vaciar la tabla
		this.getModelPersonas().setColumnCount(0);
		this.getModelPersonas().setColumnIdentifiers(this.getNombreColumnas());
		
		for (PersonaDTO persona : personasEnTabla)
		{
			Object[] fila = {
					persona.getNombre(), 
					persona.getTelefono(),
					persona.getCalle(),
					getFecha(persona.getNacimiento()),
					persona.getAltura(),
					persona.getPiso(),
					persona.getDepartamento(),
					persona.getEmail(),
					persona.getLocalidad().getNombre(),
					persona.getTipoContacto().getTipo()};
			this.getModelPersonas().addRow(fila);
		}			
	}
	
	private String getFecha(Date d)	{
		SimpleDateFormat Formato = new SimpleDateFormat("dd/MM/yyyy");
		if (d != null) {
			return Formato.format(d);
		} else {
			return null;
		}
	}
}
