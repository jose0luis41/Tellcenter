package mundo;

import java.io.Serializable;

public class Producto implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigo;
	private String nombre;
	private double precio;
	private int cantidad;
	
	private Producto siguiente;
	private Producto anterior;
	
	
	
	public Producto getSiguiente() {
		return siguiente;
	}



	public void setSiguiente(Producto siguiente) {
		this.siguiente = siguiente;
	}


	
	public int getCantidad() {
		return cantidad;
	}



	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}



	public Producto getAnterior() {
		return anterior;
	}



	public void setAnterior(Producto anterior) {
		this.anterior = anterior;
	}



	public Producto(String ElCodigo, String elNombre, double elPrecio, int laCantidad){
		
		codigo = ElCodigo;
		nombre = elNombre;
		precio = elPrecio;
		cantidad = laCantidad;
	}



	public String getCodigo() {
		return codigo;
	}



	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public double getPrecio() {
		return precio;
	}



	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
	
	public String toString(){
		return "Nombre: "+nombre;
	}
}
