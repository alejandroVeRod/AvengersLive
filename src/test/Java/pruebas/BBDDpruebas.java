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

	public void testEliminarEmpleado() {
		
		MongoCollection<Document> testEmpleado=getCollection("Empleados"); 
		 
		Document doc = new Document(); 

		doc.append("_id", "38292749"); 
		doc.append("email", "minchata9882@gmieal.com"); 
		doc.append("contrasena", "1234"); 
		doc.append("nombre", "Enrique"); 	 
		doc.append("rol", "administrador"); 
 
		DBBroker db=new DBBroker(); 
 
		db.insertarDocumento(testEmpleado, doc); 
		
		FindIterable<Document> ite= testEmpleado.find(doc); 
 
		db.borrarDocumento(testEmpleado, doc);
		
		assertFalse(ite.iterator().hasNext());		 

	}
	
}

