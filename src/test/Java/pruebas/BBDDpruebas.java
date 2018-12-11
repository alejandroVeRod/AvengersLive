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
	public void testResolverIncidencia() {

		MongoCollection<Document> testIncidencias = getCollection("Incidencias");

		Document doc = new Document("_id", new Document("$regex", "03"));
		Document doc2 = new Document().append("$set", new Document().append("mensaje", "resuelta"));

		testIncidencias.updateOne(doc, doc2);

	}

}
