package presentacion.vista;

import javax.swing.JDialog;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Rectangle;

public class VistaEditor extends JDialog{
	public static final String PAIS = "pais";
	public static final String PROVINCIA = "provincia";
	public static final String LOCALIDAD = "localidad";
	
	private JPanel panelVistas;
	private CardLayout layout;
	private PanelEditorPais editorPais;
	private PanelEditorProvincia editorProvincia;
	private PanelEditorLocalidad editorLocalodad;

	public VistaEditor() {
		setBounds(new Rectangle(100, 100, 500, 300));
		
		setModal(true);
		panelVistas = new JPanel();
		layout = new CardLayout(0, 0);
		panelVistas.setLayout(layout);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		this.editorPais = new PanelEditorPais();
		panelVistas.add(editorPais, PAIS);
		
		this.editorProvincia = new PanelEditorProvincia();
		panelVistas.add(editorProvincia, PROVINCIA);
		
		this.editorLocalodad = new PanelEditorLocalidad();
		panelVistas.add(editorLocalodad, LOCALIDAD);
		
		getContentPane().add(panelVistas, BorderLayout.CENTER);
	}

	public PanelEditorPais getEditorPais() {
		return this.editorPais;
	}
	
	public PanelEditorProvincia getEditorProvincia() {
		return editorProvincia;
	}
	
	public PanelEditorLocalidad getEditorLocalidad() {
		return editorLocalodad;
	}

	public void mostar(String vista) {
		this.layout.show(panelVistas, vista);
		setTitle("Editar " + vista);
	}

}
