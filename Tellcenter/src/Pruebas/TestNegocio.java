package Pruebas;

import mundo.Negocio;
import mundo.Producto;
import junit.framework.TestCase;

public class TestNegocio extends TestCase{
	
	private Negocio negocio;
	
	private void setupEscenario1(){
		negocio = new Negocio("/negocio");
		
		Producto p1 = new Producto("1", "Memoria", 60000, 10);
		Producto p2 = new Producto("2", "Samsung", 520000, 4);
		Producto p3 = new Producto("3", "BlackBerry", 260000, 17);
		Producto p4 = new Producto("4", "Sim", 4000, 20);
		Producto p5 = new Producto("5", "Forros BlackBerry", 7000, 50);
		Producto p6 = new Producto("6", "Screen", 6000, 20);
		Producto p7 = new Producto("7", "Control", 90000, 15);
		Producto p8 = new Producto("8", "Teclado", 100000, 50);
		try{
		negocio.agregarProducto(p1);
		negocio.agregarProducto(p2);
		negocio.agregarProducto(p3);
		negocio.agregarProducto(p4);
		negocio.agregarProducto(p5);
		negocio.agregarProducto(p6);
		negocio.agregarProducto(p7);
		negocio.agregarProducto(p8);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void setupEscenario2(){
		negocio = new Negocio("/negocio");
	}
	
	@SuppressWarnings("unused")
	private void setupEscenario3(){
		negocio = new Negocio("/negocio");
		Producto p1 = new Producto("1", "Memoria", 60000, 10);
		try{
		negocio.agregarProducto(p1);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void testAgregar(){
		setupEscenario1();
		
		assertEquals("Memoria", negocio.darPrimerProducto().getNombre());
		assertEquals("2", negocio.darPrimerProducto().getSiguiente().getCodigo());
		assertEquals("Teclado", negocio.darUltimoProducto().getNombre());
		assertEquals("No se est� retornando lo esperado", "Control", negocio.darUltimoProducto().getAnterior().getNombre());
	}
	
	public void testAgregar2(){
		setupEscenario2();
		Producto p7 = new Producto("7", "Control", 90000, 15);
		try{
		negocio.agregarProducto(p7);
		assertEquals(negocio.darUltimoProducto(), p7);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Caso donde el escenario est� vacio
	 */
	public void testBuscar(){
		setupEscenario2();
		Producto p5 = new Producto("5", "Forros BlackBerry", 7000, 50);
		Producto p6 = new Producto("6", "Screen", 6000, 20);
		Producto p4 = new Producto("4", "Sim", 4000, 20);
		try{
		negocio.agregarProducto(p5);
		assertEquals(p5, negocio.buscarProductoPorNombre("Forros BlackBerry"));
		negocio.agregarProducto(p6);
		negocio.agregarProducto(p4);
		assertEquals(p4, negocio.buscarProductoPorNombre("Sim"));	
		assertEquals(p6, negocio.buscarProductoPorNombre("Sim").getAnterior());
		negocio.eliminarProducto("5","Forros BlackBerry");
		assertFalse(negocio.buscarProductoPorNombre("Forros BlackBerry")!=null);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
