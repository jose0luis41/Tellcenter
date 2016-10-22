package mundo;

public class YaExisteProductoException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	
	
	public YaExisteProductoException(String causa, String nom){
		
		super(causa);
		nombre=nom;
		
	}


	public String getNombre() {
		return nombre;
	}


	

}
