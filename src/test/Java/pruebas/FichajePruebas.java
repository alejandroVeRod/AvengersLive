package pruebas;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import modelo.mongodb.Empleado;
import modelo.mongodb.Fichaje;

public class FichajePruebas {

	@Test 
	public void testUsuarioRegistrado() {
		Fichaje fic =new Fichaje();
		boolean resultado=fic.editarFichaje("05980503C", "09","38", "14", "09", "43", "25", "06", "11", "2018", "Cerrado");
		assertTrue(resultado);
		
	}
	
	@Test 
	public void testUsuarioNoRegistrado() {
		Fichaje fic =new Fichaje();
		boolean resultado=fic.editarFichaje("05980509P", "0","38", "14", "09", "43", "25", "06", "11", "2018", "Cerrado");
		assertTrue(!resultado);
		
	}
	
	@Test 
	public void testUsuarioNoRegistrados() {
		Fichaje fic =new Fichaje();
		boolean resultado=fic.editarFichaje("05980509P", "0","38", "14", "09", "43", "25", "06", "11", "2018", "Abierto");
		assertFalse(!resultado);
		
	}

}
