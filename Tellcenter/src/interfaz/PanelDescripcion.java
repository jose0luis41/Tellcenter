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

import mundo.Producto;

public class PanelDescripcion extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public final static String VENDER = "VENDER";
	
	private JLabel labID,labNombre, labPrecio,labNumero, labCantidad;
	private JTextField txtID, txtNombre, txtPrecio,txtNumeroVentas, txtCantidad;
	private JButton butVender;
	

	
	private InterfazTellCenter principal;
	
	public PanelDescripcion(InterfazTellCenter ventana){
		
		principal = ventana;
		
		setLayout(new GridBagLayout());
		TitledBorder borde = new TitledBorder("Informaci�n");
		setBorder(borde);
		
		GridBagConstraints gc = new GridBagConstraints();
	    gc.insets = new Insets(6,6,6,6);
		
	    
		labID = new JLabel("Codigo del Producto: ");
		gc.weightx = 1;
		gc.gridx = 0;
		gc.gridy = 0;
		add(labID,gc);
		
		
		txtID = new JTextField();
		txtID.setEditable(false);
		txtID.setPreferredSize(new Dimension(200,30));
		gc.weightx = 1;
		gc.gridx = 1;
		gc.gridy = 0;
		add(txtID,gc);
		
	
		
		labNombre = new JLabel("Nombre del Producto: ");
		gc.weightx = 1;
		gc.gridx = 0;
		gc.gridy = 1;
		add(labNombre,gc);
		
		txtNombre = new JTextField("                                                  ");
		txtNombre.setEditable(false);
		txtNombre.setPreferredSize(new Dimension(200,30));
		gc.weightx = 1;
		gc.gridx = 1;
		gc.gridy = 1;
		add(txtNombre,gc);
		
		
		
		labPrecio = new JLabel("Precio del Producto: ");
		gc.weightx = 1;
		gc.gridx = 0;
		gc.gridy = 2;
		add(labPrecio,gc);
		
		txtPrecio = new JTextField();
		txtPrecio.setEditable(false);
		txtPrecio.setPreferredSize(new Dimension(200,30));
		gc.weightx = 1;
		gc.gridx = 1;
		gc.gridy = 2;
		add(txtPrecio,gc);
		
		
		
		
		
		labCantidad = new JLabel("Cantidad disponible:");
		gc.weightx = 1;
		gc.gridx=0;
		gc.gridy=3;
		add(labCantidad,gc);
		
		txtCantidad = new JTextField("                                                             ");
		txtCantidad.setEditable(false);
		txtCantidad.setPreferredSize(new Dimension(140,30));
		gc.weightx = 1;
		gc.gridx=1;
		gc.gridy=3;
		add(txtCantidad,gc);
		
		
		labNumero = new JLabel("Cantidad Vendida:");
		gc.weightx = 1;
		gc.gridx=0;
		gc.gridy=4;
		add(labNumero,gc);
		
		txtNumeroVentas = new JTextField("1");
		txtNumeroVentas.setPreferredSize(new Dimension(140,30));
		gc.weightx = 1;
		gc.gridx=1;
		gc.gridy=4;
		add(txtNumeroVentas,gc);
		
		butVender = new JButton("Vender");
		butVender.setPreferredSize(new Dimension(100,30));
		butVender.addActionListener(this);
		butVender.setActionCommand(VENDER);
		gc.weightx = 1;
		gc.gridx = 1;
		gc.gridy = 6;
		add(butVender,gc);
		
		
		
	}

	public void refrescar(Producto nuevo){
		cambiar(nuevo.getNombre());
		cambiarCodigo(nuevo.getCodigo());
		cambiarPrecio(nuevo.getPrecio());
		cambiarCantidad(nuevo.getCantidad());
		
	}
	
	public void cambiarCantidad(int C){
		 txtCantidad.setText(Integer.toString(C));
	}
	public String darPrecio(){
		return txtPrecio.getText();
		
		
	}
	
	public void cambiar(String n){
		txtNombre.setText(n);
	}
	
	public void cambiarCodigo(String c){
		txtID.setText(c);
	}
	
	public void cambiarPrecio(double p){
		txtPrecio.setText(Double.toString(p));
	}
	
	public String darCantidadVenta(){
		return txtNumeroVentas.getText();
	}
	

	
	@Override
	public void actionPerformed(ActionEvent e){

		String comando = e.getActionCommand();
		
		if(comando.equalsIgnoreCase(VENDER)){
			
			// Obtengo los datos de los JTextField
			String precio = txtPrecio.getText();
			String cantidad = txtNumeroVentas.getText();
			String cantidadDispo = txtCantidad.getText();
			String nomb = txtNombre.getText();
			String codigo = txtID.getText();
			
			
			
			if(precio.equalsIgnoreCase("") || precio == null){
				JOptionPane.showMessageDialog( this, "Debe haber un producto seleccionado", "Ventas de Productos", JOptionPane.ERROR_MESSAGE );
				return;
			}
			if(nomb.equalsIgnoreCase("") || nomb == null){
				JOptionPane.showMessageDialog( this, "Debe haber un producto seleccionado", "Ventas de Productos", JOptionPane.ERROR_MESSAGE );
				return;
			}
			if(cantidadDispo.equalsIgnoreCase("") || cantidadDispo ==null){
				JOptionPane.showMessageDialog( this, "Debe haber un producto seleccionado", "Ventas de Productos", JOptionPane.ERROR_MESSAGE );
				return;
			}
			if(codigo.equalsIgnoreCase("") || codigo == null){
				JOptionPane.showMessageDialog( this, "Debe haber un producto seleccionado", "Ventas de Productos", JOptionPane.ERROR_MESSAGE );
				return;
			}
			//Cambios de tipo de dato
			double pre = Double.parseDouble(precio);
			int can =  Integer.parseInt(cantidad);
			int canDispo = Integer.parseInt(cantidadDispo);
			
			if(can>canDispo){
				JOptionPane.showMessageDialog( this, "La cantidad disponible es menor a la cantidad que se quiere vender", "Ventas de Productos", JOptionPane.ERROR_MESSAGE );
				return;
			}
			else{
			Producto vender = new Producto(codigo, nomb, pre, canDispo);	
		
			principal.venderProducto(vender,codigo, can);
			
			int nuevaCantidad = vender.getCantidad()-can;
		
			if(nuevaCantidad <=3 && nuevaCantidad>0){
				JOptionPane.showMessageDialog( this, "�La cantidad de producto "+ vender.getNombre()+ " est� proximo a agotarse!", "Ventas de Productos", JOptionPane.INFORMATION_MESSAGE);
			}
			
			if(nuevaCantidad==0){
			JOptionPane.showMessageDialog( this, "La cantidad del producto "+ vender.getNombre()+ " se ha agotado, �debes abastecer este producto!", "Ventas de Productos", JOptionPane.INFORMATION_MESSAGE);
			principal.agregarProductoAListaSumi(vender, nomb);
		
			}
				
			principal.actualizarPanelVentas();
			principal.actualizaPanelVentasTotales();
			txtID.setText("");
			txtNombre.setText("");
			txtPrecio.setText("");
			txtCantidad.setText("");
		
			
			
			}
		}
		
		
		
	}
	
	
	
}
