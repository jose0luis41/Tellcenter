package interfaz;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

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

public class PanelProductos extends JPanel implements ActionListener, ListSelectionListener, KeyListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public final static String ELIMINAR = "ELIMINAR";
	public final static String BUSCAR = "BUSCAR";
	
	private InterfazTellCenter principal;
	
	
	private JList<Producto> lista;

	private DefaultListModel<Producto> modelo; 
	private JTextField txtCodigo, txtNombre;
	private JButton butCodigo, butNombre;
	
	public PanelProductos(InterfazTellCenter ventana){
	
		
		principal = ventana;
		setLayout(new GridBagLayout());
		TitledBorder borde = new TitledBorder("Productos de Celulares");
		setBorder(borde);
		
		modelo = new DefaultListModel<Producto>();
		
	
		
		GridBagConstraints gbc= new GridBagConstraints();
		  lista = new JList<Producto>();
		 
            lista.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
            lista.addListSelectionListener(this);
	        JScrollPane scroll= new JScrollPane(lista);
	        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	        gbc = new GridBagConstraints( 0, 0, 3, 3, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets( 5, 5, 5, 5 ), 0, 0 );
	        scroll.setPreferredSize(new Dimension(300,300));
	        add(scroll, gbc);
	        
	        butCodigo = new JButton("Eliminar por c�digo: ");
	        butCodigo.setPreferredSize(new Dimension(100,30));
	        butCodigo.addActionListener(this);
	        butCodigo.setActionCommand(ELIMINAR);
	    	gbc.weightx = 1;
			gbc.gridx = 1;
			gbc.gridy = 7;
			add(butCodigo,gbc);
			
			txtCodigo = new JTextField();
			txtCodigo.setPreferredSize(new Dimension(100,30));
			gbc.weightx = 1;
			gbc.gridx = 4;
			gbc.gridy = 7;
			add(txtCodigo,gbc);
			
			butNombre = new JButton("Buscar por nombre: ");
	        butNombre.setPreferredSize(new Dimension(100,30));
	        butNombre.addActionListener(this);
	        butNombre.setActionCommand(BUSCAR);
	    	gbc.weightx = 1;
			gbc.gridx = 1;
			gbc.gridy = 4;
			add(butNombre,gbc);
			
			txtNombre = new JTextField("");
			txtNombre.setPreferredSize(new Dimension(100,30));
			gbc.weightx = 1;
			gbc.gridx = 4;
			gbc.gridy = 4;
			add(txtNombre,gbc);
			txtNombre.addKeyListener(this);
	}
	

	
	public void refrescarLista(ArrayList<Producto> productos){
		modelo.removeAllElements();

		for (int i = 0; i < productos.size(); i++) {
			modelo.addElement(productos.get(i));			
		}
		
		lista.setModel(modelo);
		lista.revalidate();
	}

	



	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		int index = lista.getSelectedIndex();
		if(index>=0){
			principal.actualizarPanelProducto();
		}
	}
	
	
	public Producto darProductoSeleccionado(){
		int index = lista.getSelectedIndex();
		Producto seleccionado = (Producto)modelo.getElementAt(index);
		return seleccionado;
	}
				

	public Producto indice(){
		int index = lista.getSelectedIndex();
		return (Producto)modelo.getElementAt(index);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand();
		String codigo = txtCodigo.getText();
		String nombre = txtNombre.getText();
		
		String nombrePorCodigo = principal.retornarNombre(codigo);
		
		if(comando.equalsIgnoreCase(ELIMINAR)){
			principal.eliminarProducto(codigo,nombrePorCodigo);
			txtCodigo.setText("");
		}else if(comando.equalsIgnoreCase(BUSCAR)){
			principal.filtrarProducto(nombre);
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		String nombre = txtNombre.getText();
		int cod = (int)e.getKeyChar();
		
		if(cod==8){
			principal.filtrarProducto(nombre);
		}else{
		principal.filtrarProducto(nombre+e.getKeyChar());		
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}


	

}
