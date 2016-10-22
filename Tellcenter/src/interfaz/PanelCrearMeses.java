package interfaz;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelCrearMeses extends JPanel{
	
	/**
	 * Serial de siempre
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtFecha;
	private Date fecha;
	private int anio;
	
	public PanelCrearMeses(){
		
		
		setLayout(null);
	
		fecha = new Date();
	
		Calendar fecha = new GregorianCalendar();
		anio = fecha.get(Calendar.YEAR);
		txtFecha = new JTextField();
		txtFecha.setBounds(60, 60, 500, 50);
		add(txtFecha);
		
		fechaActual();
	}
	
	
	@SuppressWarnings("deprecation")
	public void fechaActual(){
		txtFecha.setText(fecha.getMonth()+1+" / "+fecha.getDate()+" / "+anio);
	}
	

}
