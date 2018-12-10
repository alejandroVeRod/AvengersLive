package modelo.mongodb;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

public class Fichaje {

	private String idEmpleado, fechaFichaje, horaEntrada, horaCierre, estado;
	private DAOFichaje dao = new DAOFichaje();
	
	public Fichaje() {
		
	}
	public Fichaje(String idEmpleado, String fechaFichaje, String horaEntrada) {
		this.idEmpleado = idEmpleado;
		this.fechaFichaje = fechaFichaje;
		this.horaEntrada = horaEntrada;
		this.estado = "Abierto";
		dao.abrirFichaje(this);		
	}
	public Fichaje(String idEmpleado, String fechaFichaje, String horaEntrada,String horaCierre,String estado) {
		this.idEmpleado = idEmpleado;
		this.fechaFichaje = fechaFichaje;
		this.horaEntrada = horaEntrada;
		this.horaCierre=horaCierre;
		this.estado = estado;
			
	}
	
	public void cerrarFichaje(String horaCierre, Empleado empleado) {
		this.horaCierre = horaCierre;
		dao.cerrarFichaje(empleado, this);
	}
	
	public boolean comprobarFichaje(String idEmpleado, String fecha) {		
		return dao.fichajeRealizable(idEmpleado, fecha);
	}
	
	public boolean comprobarCierre(String idEmpleado, String fecha, String estado) {
		return dao.fichajeCerrable(idEmpleado, fecha, estado);
	}
	
	public List<Document> fichajesEmpleado(String idEmpleado){
		return dao.fichajesEmpleado(idEmpleado);
	}
	
	public String getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(String idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	
	public String getFechaFichaje() {
		return fechaFichaje;
	}
	public void setFechaFichaje(String fechaFichaje) {
		this.fechaFichaje = fechaFichaje;
	}
	public String getHoraEntrada() {
		return horaEntrada;
	}
	public void setHoraEntrada(String horaFichaje) {
		this.horaEntrada = horaFichaje;
	}
	public String getHoraCierre() {
		return horaCierre;
	}
	public void setHoraCierre(String horaCierre) {
		this.horaCierre = horaCierre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public boolean editarFichaje(String dni ,String horAh,String horAm,String horAs,String horCh ,
			String horCm,String horCs,String dia,String mes,String ano,String estado) {
				dao.editarFichaje(dni,horAh,horAm,horAs,horCh,horCm,horCs,dia,mes,ano,estado,this); 
				return true;
			}
	
	public boolean fichajesAbiertos(String id) {
		boolean est=false;
		List<Document> fichajes;
		fichajes=fichajesEmpleado(id);
		for(int i=0;i<fichajes.size();i++) {
			if(fichajes.get(i).get("estado").equals("Abierto"))est=true;
		}
		return est;
		
	}

	
}
