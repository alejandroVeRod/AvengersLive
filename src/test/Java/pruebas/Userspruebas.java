package pruebas;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import modelo.mongodb.DAOEmpleado;
import modelo.mongodb.Empleado;
import modelo.mongodb.Fichaje;

public class Userspruebas {

	@Test 
	public void testUsuarioRegistrado() {
		Empleado empl =new Empleado();
		boolean resultado=empl.credencialesCorrectas("ruben.perez7@alu.uclm.es","736f907006c60320b8b8a643c21af99b");
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
	
	@Test
	public void testRecuperarContrasena() {
		Empleado empl =new Empleado();
		boolean resultado=empl.recuperarContrasena("Enrique.Armero@alu.uclm.es");
		assertTrue(resultado);
		
	}
	
	@Test
	public void testRecuperarContrasenaFalso() {
		Empleado empl =new Empleado();
		boolean resultado=empl.recuperarContrasena("Enrique.Armero@alu3138.uclm.es");
		assertTrue(!resultado);
		
	}
	
	@Test
	public void testRecuperarContrasenaFalsos() {
		Empleado empl =new Empleado();
		boolean resultado=empl.recuperarContrasena("Enrique.Armero@alu3138.uclm.es");
		assertFalse(resultado);
		
	}
	
	@Test
	public void testRequisitosContrasena() {
		Empleado empl =new Empleado();
		boolean resultado=empl.requisitosContrasena("1Informatica");
		assertTrue(resultado);
		
	}
	
	@Test
	public void testRequisitosContrasenaIncumplidos() {
		Empleado empl =new Empleado();
		boolean resultado=empl.requisitosContrasena("hola");
		assertFalse(resultado);
		
	}
	
	@Test
	public void testRequisitosContrasenaIncumplido() {
		Empleado empl =new Empleado();
		boolean resultado=empl.requisitosContrasena("hi");
		assertTrue(!resultado);
		
	}
	
	@Test

	public void testContrasenasIguales() {
		Empleado empl =new Empleado();
		assertTrue(empl.contrasenaCoincide("11","11"));
		
	}
	

	public void testCreedencialesCorrectasPassGrande() {
		Empleado empl =new Empleado();
		boolean resultado=empl.credencialesCorrectas("12345678","12349999999999999999999999999999999999999"
				+ "99999999999999999999999999999999999999999999999999"
				+ "999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999"
				+ "99999999999999999999999999999999999999999999999");
		assertTrue(resultado);
		
	}

	
	@Test 
	public void testCreedencialesCorrectasCorreoGrande() {
		Empleado empl =new Empleado();
		boolean resultado=empl.credencialesCorrectas("1234kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk"
				+ "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk"
				+ "kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk","Ruben123");
		assertTrue(!resultado);
		
	}




	}


