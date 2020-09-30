package presentacion.vista;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.DeporteDTO;
import dto.LocalidadDTO;
import dto.PaisDTO;
import dto.PersonaDTO;
import dto.ProvinciaDTO;
import dto.TipoContactoDTO;

import javax.swing.JButton;

import persistencia.conexion.Conexion;

public class Vista {
	private JFrame frame;
	private JTable tablaPersonas;
	private JButton btnAgregar;
	private JButton btnEditar; 
	private JButton btnBorrar;
	private JButton btnReporte;
	private DefaultTableModel modelPersonas;
	private String[] nombreColumnas = { "Nombre y apellido", "Telefono", "Nacimiento","Calle",  "Altura", "Piso",
			"Departamento", "EMail","Pais", "Provincia", "Localidad", "TipoDeContacto", "Deporte" };
	private JMenuItem mntmLocalidades;
	private JMenuItem mntmTiposDeContacto;
	private JMenuItem mntmProvincias;
	private JMenuItem mntmPaises;
	private JMenuItem mntmDeporte;
	private JMenuItem mntmBaseDeDatos;

	public Vista() {
		super();
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 850, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		frame.setContentPane(panel);
		panel.setLayout(new BorderLayout());

		JScrollPane spPersonas = new JScrollPane();
		spPersonas.setBounds(10, 11, 814, 182);
		panel.add(spPersonas, BorderLayout.CENTER);

		modelPersonas = new DefaultTableModel(null, nombreColumnas);
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

		btnEditar = new JButton("Editar");
		botones.add(btnEditar);

		btnBorrar = new JButton("Borrar");
		botones.add(btnBorrar);

		btnReporte = new JButton("Reporte");
		botones.add(btnReporte);

		JMenuBar menuBar = new JMenuBar();
		frame.getContentPane().add(menuBar, BorderLayout.NORTH);

		JMenu mnAdministracin = new JMenu("Administración");
		menuBar.add(mnAdministracin);
		
		mntmPaises = new JMenuItem("Paises");
		mnAdministracin.add(mntmPaises);
		
		mntmProvincias = new JMenuItem("Provincias");
		mnAdministracin.add(mntmProvincias);

		mntmLocalidades = new JMenuItem("Localidades");
		mnAdministracin.add(mntmLocalidades);
		
		mntmTiposDeContacto = new JMenuItem("Tipos de Contacto");
		mnAdministracin.add(mntmTiposDeContacto);
		
		mntmDeporte = new JMenuItem("Deporte");
		mnAdministracin.add(mntmDeporte);
		
		JMenu mnConfiguracion = new JMenu("Configuración");
		menuBar.add(mnAdministracin);
		
		mntmBaseDeDatos = new JMenuItem("Base de Datos");
		mnConfiguracion.add(mntmBaseDeDatos);
		menuBar.add(mnConfiguracion);

		panel.add(botones, BorderLayout.SOUTH);
	}

	public void show() {
		this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int confirm = JOptionPane.showOptionDialog(null, "¿Estás seguro que quieres salir de la Agenda?",
						"Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
				if (confirm == 0) {
					Conexion.getConexion().cerrarConexion();
					System.exit(0);
				}
			}
		});
		this.frame.setVisible(true);
	}

	public JButton getBtnAgregar() {
		return btnAgregar;
	}

	public JButton getBtnEditar() // Editar
	{
		return btnEditar;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public JButton getBtnReporte() {
		return btnReporte;
	}

	public JMenuItem getMntmLocalidades() {
		return mntmLocalidades;
	}

	public JMenuItem getMntmProvincias() {
		return mntmProvincias;
	}

	public JMenuItem getMntmPaises() {
		return mntmPaises;
	}

	public JMenuItem getMntmTiposDeContacto() {
		return mntmTiposDeContacto;
	}

	public JMenuItem getMntmDeporte() {
		return mntmDeporte;
	}

	public JMenuItem getMntmBaseDeDatos() {
		return mntmBaseDeDatos;
	}

	public DefaultTableModel getModelPersonas() {
		return modelPersonas;
	}

	public JTable getTablaPersonas() {
		return tablaPersonas;
	}

	public String[] getNombreColumnas() {
		return nombreColumnas;
	}

	public void llenarTabla(List<PersonaDTO> personasEnTabla) {
		this.getModelPersonas().setRowCount(0); // Para vaciar la tabla
		this.getModelPersonas().setColumnCount(0);
		this.getModelPersonas().setColumnIdentifiers(this.getNombreColumnas());

		for (PersonaDTO persona : personasEnTabla) {

			LocalidadDTO localidad = persona.getLocalidad();
			ProvinciaDTO provincia = null;
			PaisDTO pais = null;
			if(localidad != null) {
				provincia = localidad.getProvincia();
				pais = provincia.getPais();
			}
			
			Object[] fila = { persona.getNombre(), persona.getTelefono(),getFecha(persona.getNacimiento()), persona.getCalle(),
					 persona.getAltura(), persona.getPiso(),
					persona.getDepartamento(), persona.getEmail(), pais ,provincia, persona.getLocalidad(),
					persona.getTipoContacto().getTipo(), persona.getDeporte() }; 
			this.getModelPersonas().addRow(fila);
		}
	}

	private String getFecha(Date d) {
		SimpleDateFormat Formato = new SimpleDateFormat("dd/MM/yyyy");
		if (d != null) {
			return Formato.format(d);
		} else {
			return null;
		}
	}

	public void ocultar() {
		this.frame.setVisible(false);
	}
}
