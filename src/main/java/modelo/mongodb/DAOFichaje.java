package modelo.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class DAOFichaje {

	private DBBroker db;
	private MongoCollection<Document> coleccion;

	public DAOFichaje() {

		db = new DBBroker();
		coleccion = db.devolverColeccion("Fichajes");

	}

	public void abrirFichaje(Fichaje fichaje) {
		Document documento = new Document();
		db.insertarDocumento(coleccion, documento
				.append("idEmpleado", fichaje.getIdEmpleado())
				.append("fechaFichaje", fichaje.getFechaFichaje())
				.append("horaEntrada", fichaje.getHoraEntrada())
				.append("horaCierre", null)
				.append("estado", fichaje.getEstado()));
	}

	public void cerrarFichaje(Empleado empleado, Fichaje fichaje) {
		Document documento = new Document();
		Document filtro = new Document();
		Document cambio = new Document();
		filtro.put("idEmpleado", empleado.getDni());
		filtro.put("estado", "Abierto");
		cambio.put("horaCierre", fichaje.getHoraCierre());
		cambio.put("estado", "Cerrado");
		documento.put("$set", cambio);
		db.actualizarDocumento(coleccion, filtro, documento);
		fichaje.setEstado("Cerrado");
	}

	public boolean fichajeRealizable(String idEmpleado, String fecha) {
		Document documento = new Document();
		MongoCursor<Document> elementos = db.documentosEnColeccion(coleccion);
		while(elementos.hasNext()) {
			documento = elementos.next();
			if(documento.get("idEmpleado").toString().equalsIgnoreCase(idEmpleado))
				if(documento.get("fechaFichaje").toString().equals(fecha))
					return false;

		}
		return true;
	}

	public boolean fichajeCerrable(String idEmpleado, String fecha, String estado) {
		Document documento = new Document();
		MongoCursor<Document> elementos = db.documentosEnColeccion(coleccion);
		while(elementos.hasNext()) {
			documento = elementos.next();
			if(documento.get("idEmpleado").toString().equalsIgnoreCase(idEmpleado))
				if(documento.get("fechaFichaje").toString().equals(fecha))
					if(documento.get("estado").toString().equals(estado))
						return true;

		}
		return false;
	}
	
	public List<Document> fichajesEmpleado(String idEmpleado){
		List<Document> fichajes = new ArrayList<Document>();
		Document documento = new Document();
		MongoCursor<Document> elementos = db.documentosEnColeccion(coleccion);
		while(elementos.hasNext()) {
			documento = elementos.next();
			if(documento.get("idEmpleado").toString().equalsIgnoreCase(idEmpleado))
				fichajes.add(documento);
		}
		
		return fichajes;
	}
	public boolean editarFichaje(String dni, String horAh, String horAm, String horAs, String horCh, String horCm,
			String horCs, String dia, String mes, String ano, String estado,Fichaje fichaje) {
		

		String horaEntrada=""; 
		String horaCierre="";
				if(horAh.length()==1){
					horaEntrada="0"+horAh+":";
							}else {
								horaEntrada=horAh+":";
							}
				
				if(horAm.length()==1){
					horaEntrada=horaEntrada+("0"+horAm)+":";
							}else {
								horaEntrada=horaEntrada+horAm+":";
							}

				if(horAs.length()==1){
					horaEntrada=horaEntrada+("0"+horAs);
							}else {
								horaEntrada=horaEntrada+horAs;
							}
				
				
				
				if(horCh.length()==1){
					horaCierre="0"+horCh+":";
							}else {
								horaCierre=horCh+":";
							}
				
				if(horCm.length()==1){
					horaCierre=horaCierre+("0"+horCm)+":";
							}else {
								horaCierre=horaCierre+horCm+":";
							}

				if(horCs.length()==1){
					horaCierre=horaCierre+("0"+horCs);
							}else {
								horaCierre=horaCierre+horCs;
							}
				
				String fechaFich="";
				if(dia.length()==1) {
					fechaFich="0"+dia+"/";
				}else
				fechaFich=fechaFich+dia+"/";
				fechaFich=fechaFich+mes+"/"+ano;
		Document documento = new Document();
		Document filtro = new Document();
		Document cambio = new Document();
		
		
		filtro.put("idEmpleado", dni);
		filtro.put("estado", fichaje.getEstado());
		filtro.put("horaEntrada",fichaje.getHoraEntrada());
		if(fichaje.getHoraCierre()!="")filtro.put("horaCierre",fichaje.getHoraCierre());else	filtro.put("horaCierre",null);
		filtro.put("fechaFichaje",fichaje.getFechaFichaje());
		cambio.put("horaEntrada",horaEntrada);
		
		cambio.put("horaCierre", horaCierre);
		cambio.put("estado", estado);
		cambio.put("fechaFichaje",fechaFich);
		documento.put("$set", cambio);
		db.actualizarDocumento(coleccion, filtro, documento);
		return true;
		
	}


}
