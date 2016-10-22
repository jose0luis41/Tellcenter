package interfaz;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.HeadlessException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import mundo.Negocio;
import mundo.NoEsDigitoException;
import mundo.Producto;
import mundo.YaExisteProductoException;

@SuppressWarnings("serial")
public class InterfazTellCenter extends JFrame{
	
	/**
	 * Relaciones con todos los paneles de la interfaz
	 */
	
	private PanelNuevosProductos panelNuevos;
	
	private PanelProductos panelProd;
	
	private PanelDescripcion panelDes;
	
	private PanelVentas panelVen;
	
	private PanelSuministros panelSuministros;
	
	private Negocio mundo;
	
	private double total;

	JTabbedPane  pestana = new JTabbedPane();
	JTabbedPane  pestana1 = new JTabbedPane();

	private PanelCrearMeses panelCrearMeses;

	
	
	public InterfazTellCenter(Negocio N){
		
		mundo= N;
		
		total = 0;
		setTitle("Celu Accesorios JC");
		setDefaultCloseOperation( DISPOSE_ON_CLOSE);
		setLayout(new BorderLayout());
		setResizable(false);
		
		GridBagConstraints gc = new GridBagConstraints();
		
		panelProd = new PanelProductos(this);
		gc.gridx=3;
		gc.gridy=0;
		gc.gridwidth=5;
		gc.gridheight=1;
		gc.weightx=0;
		gc.weighty=0;
		gc.anchor=GridBagConstraints.ABOVE_BASELINE_TRAILING;
		gc.fill = GridBagConstraints.BOTH;
		//add(panelProd, gc);
		
		
		
		JPanel panelInterno = new JPanel();
		panelInterno.setLayout(new BorderLayout());
	
		JPanel panelInterno1 = new JPanel();
		panelInterno1.setLayout(new BorderLayout());
		
		JPanel panelInterno2 = new JPanel();
		panelInterno2.setLayout(new BorderLayout());
		
		panelVen = new PanelVentas();
		gc.gridx=0;
		gc.gridy=1;
		gc.gridwidth=3;
		gc.gridheight=1;
		gc.weightx=0;
		gc.weighty=0;
		gc.anchor=GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.BOTH;
		
		
		panelDes = new PanelDescripcion(this);
		gc.gridx=0;
		gc.gridy=0;
		gc.gridwidth=1;
		gc.gridheight=1;
		gc.weightx=0;
		gc.weighty=0;
		gc.anchor=GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.BOTH;
		
		
		panelNuevos = new PanelNuevosProductos(this);
		gc.gridx=3;
		gc.gridy=1;
		gc.gridwidth=1;
		gc.gridheight=1;
		gc.weightx=0;
		gc.weighty=0;
		gc.anchor=GridBagConstraints.CENTER;
		gc.fill = GridBagConstraints.BOTH;
		
		panelSuministros = new PanelSuministros(this);
		panelCrearMeses = new PanelCrearMeses();
		
		panelInterno.add(panelProd,BorderLayout.CENTER);
		panelInterno.add(panelDes, BorderLayout.WEST);
		
		panelInterno1.add(panelNuevos,BorderLayout.CENTER);
		panelInterno1.add(panelVen, BorderLayout.WEST);
		
		panelInterno2.add(panelInterno,BorderLayout.CENTER);
		panelInterno2.add(panelInterno1, BorderLayout.SOUTH);
		
		pestana.addTab("Panel Negocio", null, panelInterno2, "Productos");
		
		pestana.addTab("Panel Suministros", null,panelSuministros, "Suministros" );
		
		pestana.addTab("Panel Informacion Meses",null, panelCrearMeses);
		
		add(pestana,BorderLayout.CENTER);

		panelProd.refrescarLista(mundo.darProductos());
		
		
	pack();
	
	
		
	}

	
	public boolean esDigito(double num){
		return mundo.esDigito(num);
	}
	
	
	public String retornarNombre(String codigo){
		return mundo.buscarProductoPorCodigo(codigo).getNombre();
	}
	
	public Producto encontrarProducto(String nom) {
		Producto encontrado = null;

		if (mundo.buscarProductoPorNombre(nom) != null) {

			encontrado = mundo.buscarProductoPorNombre(nom);

		} else {
			JOptionPane.showMessageDialog(this, "¡No se encontró el producto!",
					"Busquedad de Prodcutos", JOptionPane.ERROR_MESSAGE);
		}

		return encontrado;
	}
	
	public void eliminarProducto(String cod,String nom){
		try{
			Producto eliminado = mundo.buscarProductoPorNombre(nom);
			if(eliminado!= null && mundo.eliminarProducto(cod,nom)){
				
				panelProd.refrescarLista(mundo.darProductos());
				JOptionPane.showMessageDialog( this, "Se ha eliminado correctamente el Producto! " , "Elminar Productos", JOptionPane.INFORMATION_MESSAGE );
				
			}
			
			
		}catch(Exception e){
			JOptionPane.showMessageDialog( this, e.getMessage()+" ", "Eliminar Prodcuto", JOptionPane.ERROR_MESSAGE );
		}
	}
	
	public ArrayList<Producto> darProductosAcabados(){
		ArrayList<Producto> acabados = new ArrayList<Producto>();
		ArrayList<Producto> actuales = mundo.darProductos();
		
		for (int i = 0; i < actuales.size(); i++) {
			Producto actual = actuales.get(i);
			if(actual!=null && actual.getCantidad()==0){
				acabados.add(actual);
			}
		}
		return acabados;
	}
	
	
	public void venderProducto(Producto produ,String cod, int cantidad ){
		try{
			if(mundo.venderProducto(produ,cod, cantidad)){
				JOptionPane.showMessageDialog( this, "Se ha vendido correctamente el Producto! " , "Venta de Productos", JOptionPane.INFORMATION_MESSAGE );
			}
		}catch(Exception e){
			JOptionPane.showMessageDialog( this,"No se pudo vender el producto ", "Venta de Prodcutos", JOptionPane.ERROR_MESSAGE );
		}
	}
	
	
	public void agregarProductoAListaSumi(Producto pro, String nom) {

		Producto encontrado = mundo.buscarProductoPorNombre(nom);
		if (encontrado != null) {

			panelSuministros.agregarProductosALista(pro);

		}

	}

	public void agregarProdcuto(String cod, String nom, double precio,
			int cantidad) {

		try {
			Producto produc = new Producto(cod, nom, precio, cantidad);
			if (mundo.agregarProducto(produc)) {

				// panelProd.agregarProductosALista(produc);
				panelProd.refrescarLista(mundo.darProductos());

				JOptionPane
						.showMessageDialog(this,
								"Se ha registrado correctamente el Producto! ",
								"Adición de Productos",
								JOptionPane.INFORMATION_MESSAGE);

			}

		} catch (YaExisteProductoException e) {
			JOptionPane.showMessageDialog(this, e.getMessage() + " ",
					"Adición de Prodcutos", JOptionPane.ERROR_MESSAGE);
		} catch (HeadlessException e) {
			e.printStackTrace();
		} catch (NoEsDigitoException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void  refrescarInformacion(){
		Producto nuevo = panelProd.indice();
		panelDes.cambiar(nuevo.getNombre());
		panelDes.cambiarCodigo(nuevo.getCodigo());
		panelDes.cambiarPrecio(nuevo.getPrecio());
		
		
	}
	
	
	public void actualizarPanelVentas(){
		int cantidad = Integer.parseInt(panelDes.darCantidadVenta());
		double precio = Double.parseDouble(panelDes.darPrecio());
		double totalVenta = mundo.calcularVenta(cantidad, precio);
		total += totalVenta;
		panelVen.refrescarVentas(Double.toString(totalVenta));
	}

	
	public void actualizaPanelVentasTotales(){
		panelVen.refrescarVentasTotales(Double.toString(total));
	}
	
	public PanelProductos darPanelP(){
		return panelProd;
	}
	
	public ArrayList<Producto> darProductos(){
		return mundo.darProductos();
	}

	
	public void actualizarPanelProducto() {
		panelDes.refrescar(panelProd.darProductoSeleccionado());
	}
	
	
	public static void main (String[] args){
		
		Negocio negocio = null;
		negocio = new Negocio("./data/Tellcenter.negocio");
		InterfazTellCenter ventana = new InterfazTellCenter(negocio);
		
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
		
		
	}


	public void dispose(){
		try{
			
			mundo.guardarInfo();
			super.dispose();
			
		}catch(Exception e){
			setVisible(true);
			int respuesta=JOptionPane.showConfirmDialog(this, "Problema a la hora de guardar los datos\n"+e.getMessage()+"\n �Quiere cerrar el programa sin guardar la informacion","Error",JOptionPane.YES_NO_OPTION);
			if (respuesta==JOptionPane.YES_OPTION){
				super.dispose();
			}
		}
	}


	public void filtrarProducto(String nomb) {
		ArrayList<Producto> productos = mundo.filtrarProducto(nomb);
		panelProd.refrescarLista(productos);
	}
	
	
	
	
	
}
