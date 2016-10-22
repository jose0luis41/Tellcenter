package interfaz;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class PanelNuevosProductos extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static String AGREGAR = "AGREGAR";
	
	private JLabel labCodigo, labNombre, labPrecio, labCantidad;
	private JTextField txtCodigo, txtNombre, txtPrecio,txtCantidad;
	private JButton butAgregar;
	//private PanelProductos p;
	
	
	private InterfazTellCenter principal;
	
	
	public PanelNuevosProductos(InterfazTellCenter ventana){
		
		principal = ventana;
		//p = new PanelProductos(ventana);
		setLayout(new GridBagLayout());
		TitledBorder borde = new TitledBorder("Nuevos Productos");
		setBorder(borde);
		
		
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(6,6,6,6);
		
		labCodigo = new JLabel("Codigo del Producto: ");
		gbc.weightx = 0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(labCodigo,gbc);
		
		
		txtCodigo = new JTextField();
		txtCodigo.setPreferredSize(new Dimension(200,30));
		gbc.weightx =0;
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(txtCodigo,gbc);
		
	
		
		labNombre = new JLabel("Nombre del Producto: ");
		gbc.weightx = 0;
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(labNombre,gbc);
		
		txtNombre = new JTextField();
		
		txtNombre.setPreferredSize(new Dimension(200,30));
		gbc.weightx = 0;
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(txtNombre,gbc);
		
		
		
		labPrecio = new JLabel("Precio del Producto: ");
		gbc.weightx = 0;
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(labPrecio,gbc);
		
		txtPrecio = new JTextField();
		txtPrecio.setPreferredSize(new Dimension(200,30));
		gbc.weightx = 0;
		gbc.gridx = 1;
		gbc.gridy = 2;
		add(txtPrecio,gbc);
		
		labCantidad = new JLabel("Cantidad:");
		gbc.weightx = 0;
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(labCantidad,gbc);
		
		txtCantidad = new JTextField();
		txtCantidad.setPreferredSize(new Dimension(200,30));
		gbc.weightx = 0;
		gbc.gridx = 1;
		gbc.gridy = 3;
		add(txtCantidad,gbc);
		
		butAgregar = new JButton("Agregar");
		butAgregar.addActionListener(this);
		butAgregar.setActionCommand(AGREGAR);
		gbc.weightx = 0;
		gbc.gridx = 7;
		gbc.gridy = 1;
		add(butAgregar,gbc);
		
		
	}

	

	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String comando = e.getActionCommand();
		if(comando.equalsIgnoreCase(AGREGAR)){
			
			String nom = txtNombre.getText();
			String id = txtCodigo.getText();
			
			
			String precio = txtPrecio.getText();
			
			String cant = txtCantidad.getText();
			
			
			if(nom==null || nom.equals("")){
				JOptionPane.showMessageDialog( this, "Debe ingresar el nombre del producto", "Adici�n de Productos", JOptionPane.ERROR_MESSAGE );
				return;
			}
			if(id==null || id.equals("")){
				JOptionPane.showMessageDialog( this, "Debe ingresar el codigo del producto", "Adici�n de Productos", JOptionPane.ERROR_MESSAGE );
				return;
			}
			
			if(cant == null || !cant.matches("[0-9]+")){
				JOptionPane.showMessageDialog(this, "La cantidad solo debe contener numeros o no se introdujo el valor en el campo cantidad");
			}

			if(!precio.matches("[0-9]+")){
				
				
				JOptionPane.showMessageDialog(this, "El precio solo debe contener numeros");
			}
			
			double cambio= Double.parseDouble(precio);
			int cantCam= Integer.parseInt(cant);
			
				principal.agregarProdcuto(id, nom, cambio, cantCam);
				txtCodigo.setText("");
				txtNombre.setText("");
				txtPrecio.setText("");
				txtCantidad.setText("");
				
			}
		




	}
	
	
	
	
	public String DarNombre(){
		String nom = txtNombre.getText();
		return nom;
	}
	
	
	public String darID(){
		String id = txtCodigo.getText();
		return id;
	}
	
	public String darPrecio(){
		String precio = txtPrecio.getText();
		return precio;
	}
}
