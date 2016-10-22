package interfaz;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

public class PanelVentas extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel labVenta;
	private JLabel labVentasTotales;
	private JTextField txtVentas;
	private JTextField txtVentasTotales;
	
	
	public PanelVentas(){
		
		setLayout(new GridBagLayout());
		TitledBorder borde = new TitledBorder("Ventas JC");
		setBorder(borde);
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		labVenta = new JLabel("Venta:");
		labVenta.setPreferredSize(new Dimension(50,100));
		gbc.weightx = 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(labVenta,gbc);
		
		txtVentas = new JTextField();
		txtVentas.setPreferredSize(new Dimension(200,30));
		txtVentas.setEditable(false);
		gbc.weightx = 1;
		gbc.gridx = 1;
		gbc.gridy = 0;
		add(txtVentas,gbc);
		
		labVentasTotales = new JLabel("Ventas Totales:");
		labVentasTotales.setPreferredSize(new Dimension(100,100));
		gbc.weightx = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(labVentasTotales,gbc);
		
		txtVentasTotales = new JTextField();
		txtVentasTotales.setPreferredSize(new Dimension(200,30));
		txtVentasTotales.setEditable(false);
		gbc.weightx = 1;
		gbc.gridx = 1;
		gbc.gridy = 1;
		add(txtVentasTotales,gbc);
		
		
		
	}
	
	public String getVentas(){
		return txtVentas.getText();
	}
	
	
	public void refrescarVentas(String ventas){
		txtVentas.setText(ventas);
	}
	
	public void refrescarVentasTotales(String ventas){
		txtVentasTotales.setText(ventas);
	}

	public String getVentasTotales() {
		// TODO Auto-generated method stub
		return txtVentasTotales.getText();
	}


}
