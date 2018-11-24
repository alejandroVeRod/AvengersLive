package modelo.mongodb;

import org.apache.commons.codec.digest.DigestUtils;
import org.bson.Document;

import com.mongodb.client.MongoCollection;

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
				.append("contrasena", DigestUtils.md5Hex(empleado.getContrasena()))
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
