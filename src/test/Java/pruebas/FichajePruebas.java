package pruebas;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import modelo.mongodb.Empleado;
import modelo.mongodb.Fichaje;

public class FichajePruebas {

	@Test 
	public void testFichajeRegistrado() {
		Fichaje fic =new Fichaje();
		boolean resultado=fic.editarFichaje("05980503C", "09","38", "14", "09", "43", "25", "06", "11", "2018", "Cerrado");
		assertTrue(resultado);
		
	}
	
	@Test 
	public void testFichajeNoRegistrados() {
		Fichaje fic =new Fichaje();
		boolean resultado=fic.editarFichaje("05980509P", "0","38", "14", "09", "43", "25", "06", "11", "2018", "Abierto");
		assertFalse(!resultado);
		
	}

	@Test
	public void CerrarFichajeFecha() {
		
		Fichaje fichaje =new Fichaje("705849111","5/11/2018","23:51:00");
		Empleado empl =new Empleado("Enrique.Armero@alu.uclm.es","Enrique123");
		
		fichaje.cerrarFichaje("23:59:99", empl);
		
		assertTrue(fichaje.getHoraCierre().equals("23:59:99")&&fichaje.getIdEmpleado().equals("705849111"));
	
	}
	
	
	@Test
	public void CerrarFichajeNuevoDia() {
		
		Fichaje fichaje =new Fichaje("705849111","5/11/2018","23:51:00");
		Empleado empl =new Empleado("Enrique.Armero@alu.uclm.es","Enrique123");
		
		fichaje.cerrarFichaje("00:00:00", empl);
		
		assertTrue(fichaje.getHoraCierre().equals("00:00:00")&&fichaje.getIdEmpleado().equals("705849111"));
	
	}
}
