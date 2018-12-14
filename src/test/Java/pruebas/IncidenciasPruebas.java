package pruebas;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import modelo.mongodb.Empleado;
import modelo.mongodb.Incidencia;

public class IncidenciasPruebas {

@Test
public void testResolverIncidencia() {
	Incidencia in =new Incidencia();
	boolean resultado=in.resolver("70593055Y", "04/12/2018", "04/12/2018", "Se ha abierto un nuevo fichaje teniendo otro sin cerrar");
	assertTrue(resultado);
}

@Test
public void testNoResolverIncidencia() {
	Incidencia in =new Incidencia();
	boolean resultado=in.resolver("70593", "04/12/2018", "04/12/2018", "Se ha abierto un nuevo fichaje teniendo otro sin cerrar");
	assertFalse(!resultado);
}

@Test
public void testIncidenciaSincerrar() {
	Incidencia in =new Incidencia();
	Empleado em = new Empleado();
	boolean resultado=in.generarIncidenciaFichajeSinCerrar(em);
	assertTrue(resultado);
}

@Test
public void testCambiarMensaje() {
	Incidencia in =new Incidencia();
	boolean resultado=in.cambiarMensaje("71720781L", "Cambio de vacaciones", "Dias libres");
	assertTrue(resultado);
}

@Test
public void testNoCambiarMensaje() {
	Incidencia in =new Incidencia();
	boolean resultado=in.cambiarMensaje("717207848P", "C", "Dias libres");
	assertFalse(resultado);
}


}
