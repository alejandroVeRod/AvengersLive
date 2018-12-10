package modelo.mongodb;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Incidencia {

	private String idEmpleado, emailEmpleado, tipo, mensaje, fechaInicio, fechaFin, comentario;
	private ObjectId _id;
	private DAOIncidencia dao = new DAOIncidencia();

	public Incidencia() {

	}

	public Incidencia(String idEmpleado, String tipo, String mensaje, String fechaInicio, String fechaFin, String comentario) {
		this.idEmpleado = idEmpleado;
		this.tipo = tipo;
		this.mensaje = mensaje;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.comentario = comentario;
		dao.registrarIncidencia(this);

	}
	public Incidencia(String idEmpleado, String tipo,String email, String mensaje, String fechaInicio, String fechaFin, String comentario) {
		this.idEmpleado = idEmpleado;
		this.tipo = tipo;
		this.emailEmpleado=email;
		this.mensaje = mensaje;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.comentario = comentario;
		

	}

	public Incidencia(ObjectId _id, String idEmpleado, String tipo, String mensaje, String fechaInicio, String fechaFin, String comentario) {
		Empleado empleado = new Empleado();
		this._id = _id;
		this.idEmpleado = idEmpleado;
		this.emailEmpleado = empleado.emailEmpleado(idEmpleado);
		this.tipo = tipo;
		this.mensaje = mensaje;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.comentario = comentario;

	}

	public boolean incidenciaMismoTipo(String idEmpleado, String tipo, String mensaje) {		
		 dao.comprobarIncidencia(idEmpleado, tipo, mensaje);
		 return true;
	}

	public List<Incidencia> listaIncidencias(List<Document> listaDocIncidencias){
		List<Incidencia> listaIncidenciasPropias = new ArrayList<Incidencia>();
		Document documentoIncidencia = new Document();
		for(int i = 0; i < listaDocIncidencias.size(); i++) {
			documentoIncidencia = listaDocIncidencias.get(i);
			Incidencia incidenciaPropia = new Incidencia(
					documentoIncidencia.getObjectId(_id),
					documentoIncidencia.get("idEmpleado").toString(),
					documentoIncidencia.get("tipo").toString(),
					documentoIncidencia.get("mensaje").toString(),
					documentoIncidencia.get("fechaInicio").toString(),
					documentoIncidencia.get("fechaFin").toString(),
					documentoIncidencia.get("comentario").toString()
					);
			listaIncidenciasPropias.add(incidenciaPropia);
		}
		return listaIncidenciasPropias;
	}

	public boolean cambiarMensaje(String idEmpleado, String comentario, String nuevoMensaje) {
		dao.cambiarMensaje(idEmpleado, comentario, nuevoMensaje);
		return true;
	}

	public boolean generarIncidenciaFichajeSinCerrar(Empleado empleado) {
		DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
		Fichaje fichaje = new Fichaje();
		DateFormat horaCierre = new SimpleDateFormat("HH:mm:ss");
		@SuppressWarnings("unused")
		Incidencia incidencia = new Incidencia(
				empleado.getDni(),
				"Comunicar errores en el marcaje",
				"En espera",
				fecha.format(new Date()),
				fecha.format(new Date()),
				"Se ha abierto un nuevo fichaje teniendo otro sin cerrar"			
				);
		fichaje.cerrarFichaje(horaCierre.format(new Date()), empleado);
		return true;
	}

	public List<Incidencia> incidenciasFiltradas(String[] arrayTipo, String[] arrayValor, String rol, String idEmpleado){
		List<Document> totalIncidencias = new ArrayList<Document>();
		List<Incidencia> incidenciasFinales = new ArrayList<Incidencia>();
		Empleado empleado = new Empleado();
		String dni;
		SimpleDateFormat castFecha = new SimpleDateFormat("dd/MM/yyyy");
		if(rol.equalsIgnoreCase("gestor"))
			totalIncidencias = dao.consultarIncidenciasGestor();
		else
			totalIncidencias = dao.consultarIncidenciasPropias(idEmpleado);
		try {
			for(int i = 0; i < arrayTipo.length; i++) {

				//No se puede buscar directamente por email, así que tenemos que conseguir el dni de la colección Empleados
				if(arrayTipo[i].equalsIgnoreCase("email")) {
					dni = empleado.dniEmpleado(arrayValor[i]);
					totalIncidencias = dao.filtrar("idEmpleado", dni, totalIncidencias);
				//Las fechas tienen que ser filtradas de forma diferente
				}else if(arrayTipo[i].equalsIgnoreCase("fechaInicio") || arrayTipo[i].equalsIgnoreCase("fechaFin")) {
					try {
						totalIncidencias = dao.filtrarFecha(arrayTipo[i], castFecha.parse(arrayValor[i]), totalIncidencias);
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}else
					totalIncidencias = dao.filtrar(arrayTipo[i], arrayValor[i], totalIncidencias);


			}
		}catch(Exception e) {
			incidenciasFinales = listaIncidencias(totalIncidencias);
			return incidenciasFinales;
		}
		incidenciasFinales = listaIncidencias(totalIncidencias);

		return incidenciasFinales;
	}


	public String getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(String idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public String getEmailEmpleado() {
		return emailEmpleado;
	}

	public void setEmailEmpleado(String emailEmpleado) {
		this.emailEmpleado = emailEmpleado;
	}
	public boolean resolver(String id, String fechaIn, String fechaFin, String comen) {
		dao.resolver(id,fechaIn,fechaFin,comen);
		return true;
		
	}

}
