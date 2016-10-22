package mundo;

public class NoEsDigitoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double precio;
	
	public NoEsDigitoException(String causa, double pre){
		super(causa);
		precio= pre;
	}

	public double getPrecio() {
		return precio;
	}

	
	
	
	
	
}
