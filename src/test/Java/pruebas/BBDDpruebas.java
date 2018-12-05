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

	private static MongoClient cliente = null;
	private static MongoDatabase bd = null;
	private MongoCursor<Document> elementos;
	private static MongoCollection<Document> dbEmpleadoTest;
	
	// Collecciones
	private String TestUsuarios = null;

	public BBDDpruebas() {

		
	}
	public static MongoCollection<Document> getCollection(String nombreColeccion){
		String acceso = "mongodb://usuarioGeneral:usuarioGeneral2018@ds233763.mlab.com:33763/avengerslive";
		MongoClientURI uri = new MongoClientURI(acceso);
		cliente = new MongoClient(uri);
		bd = cliente.getDatabase("avengerslive");
		return bd.getCollection(nombreColeccion);
		
		
	}
	
	@Test
	
	public void testInsertarEmpleado() {
			
		MongoCollection<Document> testEmpleado=getCollection("Empleados");

		
	}
	
	public void testAbrirFichaje() {
		
		MongoCollection<Document> testEmpleado=getCollection("Fichajes");

		
	}

	public void testEliminarEmpleado() {
		
		MongoCollection<Document> testEmpleado = getCollection("Empleados");
		
		Document doc = new Document();
		
		testEmpleado.insertOne(doc);
		doc.append("email", "pruebaenrique@hotmail.com");
		doc.append("contrasena", "Enrique1234");
		doc.append("nombre", "Enrique");
		doc.append("rol", "administrador");
		
		DBBroker db= new DBBroker();
		
		
	}
}

