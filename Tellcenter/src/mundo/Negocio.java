package mundo;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Negocio implements Serializable{

	private static final long serialVersionUID = 1L;
	private Producto primero, ultimo;
	private String archivoPersistente;	
	
	public Negocio(String nArchivo){
		
		archivoPersistente = nArchivo;
		File archivo = new File(archivoPersistente);
		if(archivo.exists()){		
			cargarInfo(archivoPersistente);
		}else{
			primero = null;
			ultimo = null;
		}
		
	}
	
	public Producto darPrimerProducto(){
		return primero;
	}
	
	public Producto darUltimoProducto(){
		return ultimo;
	}

	public double calcularTotalVentas(){
		Producto actual = primero;
		double ventas = 0;
		while(actual!=null){
			ventas+=actual.getPrecio();
		}
		return ventas;
	}
	
	public double calcularVenta(int numero, double precio){
		return numero*precio;	
	}
	
	public double calcularVentasTotales(){
		Producto actual = primero;
		double total = 0;
		while(actual!=null ){
			total+=actual.getPrecio();
			actual = actual.getSiguiente();
		}
		return total;
	}

	
	public boolean esDigito(double numer){
		boolean es = false;
		String cambio = Double.toString(numer);
		if(cambio.toLowerCase()==cambio.toUpperCase()){
			es= true;
		}
		return es;
	}
	
	public boolean yaExisteProducto(String cod, String nom){
		boolean existe = false;
		if(primero==null && ultimo ==null){
			existe = false;
		}else{
			Producto actual = primero;
			while(actual!=null && !existe){
				if(actual.getCodigo().equalsIgnoreCase(cod) || actual.getNombre().equalsIgnoreCase(nom)){
					existe = true;
				}
					actual = actual.getSiguiente();
				}
			}
		
		return existe;
	}
	

	
	public boolean hayDosElementos(){
		boolean verdadero=false;
		
		if(primero != null && ultimo != null && primero.getSiguiente()==ultimo){
			verdadero = true;
		}
		return verdadero;
	}
	
	
	public boolean eliminarProducto(String cod, String nom)throws Exception{
		
		boolean elimino =false;

		if(!yaExisteProducto(cod,nom)){
			throw new Exception("El producto con el codigo "+cod+" no existe");
		}
		else{
		 if(hayUnSoloElemento()  && primero.getCodigo().equalsIgnoreCase(cod)){
				primero = null;
				ultimo = null;
				elimino = true;
			}else if(ultimo.getCodigo().equalsIgnoreCase(cod)){
				  Producto temp = ultimo.getAnterior();
				  temp.getSiguiente().setAnterior(null);
				  temp.setSiguiente(null);
					ultimo = temp;
					elimino = true;
			}
			else{
				Producto actual = primero;
				while(actual !=null && !actual.getCodigo().equalsIgnoreCase(cod)){
					actual=actual.getSiguiente();
				}
				
					if(primero.getCodigo().equalsIgnoreCase(cod)){
						Producto pri = primero.getSiguiente();
						primero.getSiguiente().setAnterior(null);
						primero.setSiguiente(null);
						primero = pri;
						elimino = true;
					}else{
						Producto temp = actual.getAnterior();
						temp.setSiguiente(actual.getSiguiente());
						actual.getSiguiente().setAnterior(temp);
					    actual.setSiguiente(null);
					    actual.setAnterior(null);
						elimino = true;
					}				
			}
		 	
		}
		
		
		return elimino;
	
	}
	
	public ArrayList<Producto> filtrarProducto(String nomb){
		
		ArrayList<Producto> encontrados = new ArrayList<Producto>();
		Producto actual = primero;
		
		while(actual!=null){
			if(actual.getNombre().contains(nomb)){
				encontrados.add(actual);
			}
			actual = actual.getSiguiente();
		}
		
		return encontrados;
	}
	
	public Producto buscarProductoPorNombre(String nomb) {
		Producto encontrado = null;

		if (primero != null && primero.getNombre().equalsIgnoreCase(nomb)) {
			encontrado = primero;
		} else if (ultimo != null && ultimo.getNombre().equalsIgnoreCase(nomb)) {
			encontrado = ultimo;
		} else {
			Producto actual = primero;
			boolean encontro = false;
			while (actual != null && !encontro) {

				if (actual.getNombre().equalsIgnoreCase(nomb)) {
					encontro = true;
					encontrado = actual;
				}

				actual = actual.getSiguiente();
			}

		}

		return encontrado;
	}
	
	public Producto buscarProductoPorCodigo(String codi) {
		Producto encontrado = null;

		if (primero != null && primero.getCodigo().equalsIgnoreCase(codi)) {
			encontrado = primero;
		} else if (ultimo != null && ultimo.getCodigo().equalsIgnoreCase(codi)) {
			encontrado = ultimo;
		} else {
			Producto actual = primero;
			boolean encontro = false;
			while (actual != null && !encontro) {

				if (actual.getCodigo().equalsIgnoreCase(codi)) {
					encontro = true;
					encontrado = actual;
				}

				actual = actual.getSiguiente();
			}

		}

		return encontrado;
	}
	
	
	
	
	public boolean hayUnSoloElemento(){
		boolean verdadero = false;
		
		if(primero!=null && ultimo!=null && primero==ultimo){
			verdadero = true;
		}
		return verdadero;
	}
	
	
	public boolean venderProducto(Producto pro,String cod, int cantidadVen)throws Exception{
		boolean vendido = false;
		
		if(!yaExisteProducto(pro.getCodigo(), pro.getNombre())){
			throw new Exception("El producto no existe en la lista de productos");
		}else{
			if(hayUnSoloElemento()){
				primero.setCantidad(primero.getCantidad()-cantidadVen);
				vendido = true;
			}else{
				Producto tempo = primero;
				while(tempo!= null && !tempo.getCodigo().equalsIgnoreCase(cod)){
					tempo = tempo.getSiguiente();
				}
				if(primero.getCodigo().equalsIgnoreCase(cod)){
					primero.setCantidad(primero.getCantidad()-cantidadVen);
					vendido = true;

				}else{
					tempo.setCantidad(tempo.getCantidad()-cantidadVen);
					vendido=true;
				}
			}
		}
		return vendido;
	}
	
	
	public boolean agregarProducto(Producto pro) throws YaExisteProductoException, NoEsDigitoException{
		
		boolean agrego = false;

		if(yaExisteProducto(pro.getCodigo(), pro.getNombre())){
			throw new YaExisteProductoException("El producto con el nombre "+pro.getNombre()+ " y codigo "+pro.getCodigo()+" ya ha sido agregado anteriormente " , pro.getNombre());
		}if(!esDigito(pro.getPrecio())){
			throw new NoEsDigitoException("El precio del producto debe contener solo n�meros", pro.getPrecio());
		}
		else if(primero == null && ultimo ==null){
				primero = pro;
				ultimo = pro;
				primero.setSiguiente(null);
				primero.setAnterior(null);
				agrego = true;
			}else{
				ultimo.setSiguiente(pro);
				ultimo.getSiguiente().setAnterior(ultimo);
				ultimo = pro;
				pro.setSiguiente(null);
				agrego = true;
				
			}
			
			return agrego;
	}

	
	public Producto localizarUltimo(){
		Producto localizado = null;
		
		if(primero!=null){
			Producto actual = primero;
			while(actual.getSiguiente()!=primero){
				actual = actual.getSiguiente();
			}
			localizado=actual;
		}
		return localizado;
	}
	
	
	public ArrayList<Producto> darProductos(){
		ArrayList<Producto> lista= new ArrayList<Producto>();
		Producto actual=primero;
		while(actual!=null){
			lista.add(actual);
			actual=actual.getSiguiente();
		}
		return lista;
	}
	
	
	public void cargarInfo(String archivo){
		try{
			FileInputStream infoFile= new FileInputStream(archivo);
			ObjectInputStream info= new ObjectInputStream(infoFile);
			Negocio negocio= (Negocio) info.readObject();
			
			primero=negocio.darPrimerProducto();
			ultimo=negocio.darUltimoProducto();
			info.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	
	public void guardarInfo(){
		try{
			FileOutputStream infoFile= new FileOutputStream(archivoPersistente, false);
			ObjectOutputStream info= new ObjectOutputStream(infoFile);
			info.writeObject(this);
			info.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	
}
