package modelo.mongodb;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class DBBroker {

	private MongoClient cliente = null;
	private MongoDatabase bd = null;
	private MongoCursor<Document> elementos;
	private Document documento;
	

	public DBBroker() {

		if(cliente == null) {
			String acceso = "mongodb://usuarioGeneral:usuarioGeneral2018@ds233763.mlab.com:33763/avengerslive";
			MongoClientURI uri = new MongoClientURI(acceso);
			cliente = new MongoClient(uri);
			bd = cliente.getDatabase("avengerslive");
		}	
	}
	
	public MongoCollection<Document> devolverColeccion(String nombreColeccion) {
		MongoCollection<Document> coleccion = bd.getCollection(nombreColeccion);
		return coleccion;
		
	}
	
	public Document devolverDocumento(MongoCollection<Document> coleccion, String campo, String parametro) {
		elementos = documentosEnColeccion(coleccion);
		while(elementos.hasNext()) {
			documento = elementos.next();
			if(documento.get(campo).toString().equalsIgnoreCase(parametro))
				return documento;
		}
		
		return null;
	}
	
	public void insertarDocumento(MongoCollection<Document> coleccion, Document documento) {
		coleccion.insertOne(documento);
	}
	
	public boolean actualizarDocumento(MongoCollection<Document> coleccion, Document filtro, Document documento) {
		coleccion.updateOne(filtro, documento);
		return true;
	}
	
	public void borrarDocumento(MongoCollection<Document> coleccion, Document documento) {
		coleccion.findOneAndDelete(documento);
	}
	
	public MongoCursor<Document> documentosEnColeccion(MongoCollection<Document> coleccion) {
		return coleccion.find().iterator();		
	}



}
