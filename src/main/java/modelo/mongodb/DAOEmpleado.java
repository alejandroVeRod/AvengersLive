package modelo.mongodb;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class DAOEmpleado {

	private DBBroker db;
	private MongoCollection<Document> coleccion;

	public DAOEmpleado() {

		db = new DBBroker();
		coleccion = db.devolverColeccion("Empleados");
	}

	public String contrasenaDeEmpleado(String emailEmpleado) {
		Document documentoEmail = null;
		String contrasenaEmpleado = "";
		documentoEmail = documentoEmpleado(emailEmpleado);
		if(documentoEmail != null)
			contrasenaEmpleado = documentoEmail.get("contrasena").toString();
		return contrasenaEmpleado;
	}

	public String dniEmpleado(String emailEmpleado) {
		Document documentoEmail = null;
		documentoEmail = documentoEmpleado(emailEmpleado);	
		return documentoEmail.get("_id").toString();
	}

	public String rolEmpleado(String emailEmpleado) {
		Document documentoEmail = null;
		documentoEmail = documentoEmpleado(emailEmpleado);	
		return documentoEmail.get("rol").toString();
	}	

	public String nombreEmpleado(String emailEmpleado) {
		Document documentoEmail = documentoEmpleado(emailEmpleado);
		return documentoEmail.get("nombre").toString();
	}

	public void cambiarContrasena(String email, String nuevaContrasena) {
		Document documento = new Document();
		Document filtro = new Document();
		Document cambio = new Document();
		filtro.put("email", email);
		cambio.put("contrasena", nuevaContrasena);
		documento.put("$set", cambio);
		db.actualizarDocumento(coleccion, filtro, documento);			
	}

	public boolean existeEmpleado(String emailEmpleado) {
		if(documentoEmpleado(emailEmpleado) != null)
			return true;
		return false;
	}

	public boolean empleadoExiste(String emailDni) {
		Document documento = new Document();
		MongoCursor<Document> elementos = db.documentosEnColeccion(coleccion);
		while(elementos.hasNext()) {
			documento = elementos.next();
			if(documento.get("_id").toString().equalsIgnoreCase(emailDni))
				return true;
			if(documento.get("email").toString().equalsIgnoreCase(emailDni))
				return true;
		}
		return false;
	}
	
	public String emailEmpleado(String dni) {
		Document documento = new Document();
		MongoCursor<Document> elementos = db.documentosEnColeccion(coleccion);
		while(elementos.hasNext()) {
			documento = elementos.next();
			if(documento.get("_id").toString().equalsIgnoreCase(dni))
				return documento.get("email").toString();
		}
		return null;
	}

	public void eliminarEmpleado(String emailEmpleado) {
		Document documento = new Document("email", emailEmpleado);
		db.borrarDocumento(coleccion, documento);
	}

	public Document documentoEmpleado(String emailEmpleado) {
		return db.devolverDocumento(coleccion, "email", emailEmpleado);
	}

	public void darAltaUsuario(Empleado empleado) {
		Document documento = new Document();
		db.insertarDocumento(coleccion, documento
				.append("_id", empleado.getDni())
				.append("email", empleado.getEmail())
				.append("contrasna", empleado.getContrasena())
				.append("nombre", empleado.getNombre())
				.append("rol", empleado.getRol()));		
	}

	public void cambiarRol(String emailEmpleado, String nuevoRol) {
		Document documento = new Document();
		Document filtro = new Document();
		Document cambio = new Document();
		filtro.put("email", emailEmpleado);
		cambio.put("rol", nuevoRol);
		documento.put("$set", cambio);
		db.actualizarDocumento(coleccion, filtro, documento);		
	}


}
