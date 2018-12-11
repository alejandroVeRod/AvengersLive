package pruebas;

import static org.junit.Assert.*;

import org.bson.Document;
import org.junit.Test;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import modelo.mongodb.DBBroker;
import modelo.mongodb.Empleado;
import modelo.mongodb.Fichaje;
import modelo.mongodb.Incidencia;

public class BBDDpruebas {

	private MongoClient cliente = null;
	private MongoDatabase bd = null;
	private MongoCursor<Document> elementos;
	private MongoCollection<Document> dbEmpleadoTest;

	// Collecciones
	private String TestUsuarios = null;

	public BBDDpruebas() {

	}

	public MongoCollection<Document> getCollection(String nombreColeccion) {
		String acceso = "mongodb://usuarioGeneral:usuarioGeneral2018@ds233763.mlab.com:33763/avengerslive";
		MongoClientURI uri = new MongoClientURI(acceso);
		cliente = new MongoClient(uri);
		bd = cliente.getDatabase("avengerslive");
		return bd.getCollection(nombreColeccion);

	}

	@Test 

	public void testInsertarEmpleado() {
		MongoCollection<Document> testEmpleado = getCollection("Empleados");
		Document doc = new Document();
		doc.append("email", "c");
		doc.append("contrasena", "c");
		doc.append("nombre", "c c c");
		doc.append("rol", "c");
		DBBroker db = new DBBroker();
		db.insertarDocumento(testEmpleado, doc);
		FindIterable<Document> ite = testEmpleado.find(doc);
		assertTrue(ite.iterator().hasNext());
	}

	@Test 
	public void testEliminarEmpleado() {

		MongoCollection<Document> testEmpleado = getCollection("Empleados");

		Document doc = new Document();

		doc.append("_id", "38292749");
		doc.append("email", "minchata9882@gmieal.com");
		doc.append("contrasena", "1234");
		doc.append("nombre", "Enrique");
		doc.append("rol", "administrador");

		DBBroker db = new DBBroker();
		db.insertarDocumento(testEmpleado, doc);
		FindIterable<Document> ite = testEmpleado.find(doc);
		db.borrarDocumento(testEmpleado, doc);
		assertFalse(ite.iterator().hasNext());		 

	}

	@Test 
	public void testAbrirFichaje() {
		MongoCollection<Document> testFichaje = getCollection("Fichajes");
		Document doc = new Document();
		doc.append("idEmpleado", "04");
		doc.append("fechaFichaje", "07/12/2018");
		doc.append("horaEntrada", "00:00:00");
		doc.append("horaCierre", null);
		doc.append("Estado", "abierto");
		DBBroker db = new DBBroker();
		db.insertarDocumento(testFichaje, doc);
		FindIterable<Document> ite = testFichaje.find(doc);
		assertTrue(ite.iterator().hasNext());
	}

	@Test 

	public void testCerrarFichaje() {

		MongoCollection<Document> testFichaje = getCollection("Fichajes");

		Document doc = new Document("_id", new Document("$regex", "04"));
		Document doc2 = new Document().append("$set",
				new Document().append("horaCierre", "00:30:00").append("Estado", "Cerrado"));

		testFichaje.updateOne(doc, doc2);

	}
	@Test 
	public void testInsertarIncidencia() {

		MongoCollection<Document> testIncidencia = getCollection("Incidencias");

		Document doc = new Document();
		doc.append("idEmpleado", "03");
		doc.append("tipo", "Fichaje");
		doc.append("mensaje", "en espera");
		doc.append("fechaInicio", "09/12/2018");
		doc.append("fechaFin", "09/12/2018");
		doc.append("comentario", "No cerre el fichaje de ayer");
		DBBroker db = new DBBroker();
		db.insertarDocumento(testIncidencia, doc);
		FindIterable<Document> ite = testIncidencia.find(doc);
		assertTrue(ite.iterator().hasNext());

	}
	@Test 
	public void testEliminarIncidencia() {

		MongoCollection<Document> testIncidencia = getCollection("Incidencias");

		Document doc = new Document();

		doc.append("_id", "03");
		doc.append("idEmpleado", "03");
		doc.append("tipo", "Fichaje");
		doc.append("mensaje", "en espera");
		doc.append("fechaInicio", "09/12/2018");
		doc.append("fechaFin", "09/12/2018");
		doc.append("comentario", "No cerre el fichaje de ayer");
		testIncidencia.deleteOne(doc);

	}
	
	@Test 
	public void testResolverIncidenci() {

		MongoCollection<Document> testIncidencias = getCollection("Incidencias");

		Document doc = new Document("_id", new Document("$regex", "03"));
		Document doc2 = new Document().append("$set", new Document().append("mensaje", "resuelta"));

		testIncidencias.updateOne(doc, doc2);

	}
	
	@Test 
	public void testUsuarioRegistrado() {
		Empleado empl =new Empleado();
		boolean resultado=empl.credencialesCorrectas("Enrique.Armero@alu.uclm.es","1234");
		assertTrue(!resultado);
		
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
		assertTrue(!resultado);
	}


	@Test
	public void ComprobarCambiarContrasenaCadenaBlanco_CadenaExigida() {
		Empleado empl=new Empleado();

		empl.cambiarContrasena("","mapiaranda@gmail.com","Contrasena1");
		assertTrue(!empl.getContrasena().equals("Contrasena1"));		
	}


}
