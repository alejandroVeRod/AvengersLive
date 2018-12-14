package modelo.mongodb;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.or;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

public class DBBroker {

	private MongoClient cliente = null;
	private static MongoDatabase bd = null;
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
	
	public static String getToken(String Token) {
		Document tok=new Document();
		tok.append("token", Token);
		FindIterable<Document > token=bd.getCollection("Tokens").find(tok);
		Document result=token.iterator().next();
		return result.toString();
	}
	public static boolean insertToken(MongoCollection<Document> coleccion,String Token, String email) {
		Bson filtro=null;
		filtro=eq("token",Token);
		String savedToken=(String) coleccion.find(filtro).iterator().next().get("token");
//		String savedEmail=
//		String emailToken=;
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
