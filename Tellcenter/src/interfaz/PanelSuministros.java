package interfaz;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import mundo.Producto;

public class PanelSuministros extends JPanel implements ListSelectionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	private JList<Producto> listaSumi;
	@SuppressWarnings("unused")
	private JScrollPane barra;
	@SuppressWarnings("unused")
	private Producto producto;
	private DefaultListModel<Producto> modelo; 
	private JTextField txtCodigo, txtNombre;
	private JButton butCodigo, butNombre;
	
	@SuppressWarnings("unused")
	private InterfazTellCenter principal;
	
	public PanelSuministros(InterfazTellCenter ventana) {

		principal = ventana;

		setLayout(null);
		TitledBorder borde = new TitledBorder("Productos de Celulares");
		setBorder(borde);

		modelo = new DefaultListModel<Producto>();
		listaSumi = new JList<Producto>();
		listaSumi.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listaSumi.addListSelectionListener(this);
		JScrollPane scroll = new JScrollPane(listaSumi);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setPreferredSize(new Dimension(300, 300));
		scroll.setBounds(10, 30, 250, 400);
		add(scroll);

		butCodigo = new JButton("Eliminar por codigo: ");
		butCodigo.setPreferredSize(new Dimension(100, 30));
		

		add(butCodigo);

		txtCodigo = new JTextField();
		txtCodigo.setPreferredSize(new Dimension(100, 30));

		add(txtCodigo);

		butNombre = new JButton("Buscar por nombre: ");
		butNombre.setPreferredSize(new Dimension(100, 30));
		

		add(butNombre);

		txtNombre = new JTextField();
		txtNombre.setPreferredSize(new Dimension(100, 30));
		add(txtNombre);

	}



	public void agregarProductosALista(Producto k){	
		modelo.addElement(k);
		listaSumi.setModel(modelo);
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
