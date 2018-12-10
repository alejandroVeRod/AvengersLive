package pruebas;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import modelo.mongodb.Empleado;
import modelo.mongodb.Fichaje;

public class Userspruebas {

	@Test 
	public void testUsuarioRegistrado() {
		Empleado empl =new Empleado();
		boolean resultado=empl.credencialesCorrectas("Enrique.Armero@alu.uclm.es","1234");
		assertTrue(resultado);
		
	}


	@Test 
	public void testUsuarioNoRegistrado() {
		Empleado empl =new Empleado();
		boolean resultado=empl.credencialesCorrectas("Enrique.Armero@alu.uclm.es","5678");
		assertFalse(resultado);
		
	}
	
	@Test
	public void testPasswordCorrectaEmailNo() {
		Empleado empl =new Empleado();
		boolean resultado=empl.credencialesCorrectas("Enrique.Armero3138@alu.uclm.es","Enrique123");
		assertFalse(resultado);
		
	}
	
	@Test
	public void testPasswordEmailFalsos() {
		Empleado empl =new Empleado();
		boolean resultado=empl.credencialesCorrectas("Enrique.Armero3138@alu.uclm.es","123432424");
		assertTrue(!resultado);
		
	}


	
}
