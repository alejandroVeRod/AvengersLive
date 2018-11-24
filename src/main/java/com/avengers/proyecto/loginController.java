package com.avengers.proyecto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFileChooser;

import org.apache.commons.codec.digest.DigestUtils;
import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import modelo.mongodb.Empleado;
import modelo.mongodb.Fichaje;
import modelo.mongodb.Incidencia;

@Controller
public class loginController {
	Empleado empleado = new Empleado();
	Calendar calendario = new GregorianCalendar();
	Fichaje fichaje = new Fichaje();
	Incidencia incidencia = new Incidencia();

	@RequestMapping("login.htm")
	public ModelAndView redireccion() {
		ModelAndView MV= new ModelAndView();
		MV.setViewName("login");
		return MV;
	}
	
//	@RequestMapping(value = "atras.htm", method = RequestMethod.POST)
//	public ModelAndView atras(HttpServletRequest request, ModelMap model) throws Exception {
//		String estado = null;
//		List<Document> listaFichajes = new ArrayList<Document>();
//		Document fich = null;
//		if(!listaFichajes.isEmpty()) {
//			for (int i=0; i<listaFichajes.size(); i++) {
//				fich = listaFichajes.get(listaFichajes.size()-1);
//			}
//			estado = fich.get("estado").toString();
//		}
//		model.addAttribute("email", empleado.getEmail());
//		model.addAttribute("estado", estado);
//		if (empleado.getRol().equals("usuario"))
//			return new ModelAndView("home");
//		else if(empleado.getRol().equals("gestor"))return new ModelAndView("gestor");//unica línea añadida
//		else return new ModelAndView("admin");
//	}

	@RequestMapping(value = "home.htm", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, ModelMap model)throws Exception{
		String email, contrasena, estado = null;
		Document fich = null;
		email = request.getParameter("inputEmail");
		contrasena = DigestUtils.md5Hex(request.getParameter("inputPassword"));	
		List<Document> listaFichajes = new ArrayList<Document>();

		if(empleado.credencialesCorrectas(email, contrasena)) {
			empleado = new Empleado(email, contrasena);
			listaFichajes = fichaje.fichajesEmpleado(empleado.getDni());
			if(!listaFichajes.isEmpty()) {
				for (int i=0; i<listaFichajes.size(); i++) {
					fich = listaFichajes.get(listaFichajes.size()-1);
				}
				estado = fich.get("estado").toString();
				
			}
			model.addAttribute("email", empleado.getEmail());
			model.addAttribute("estado", estado);
			if (empleado.getRol().equals("usuario"))
				return new ModelAndView("home");
			else if(empleado.getRol().equals("gestor"))return new ModelAndView("gestor");//unica línea añadida
			else return new ModelAndView("admin");

		}else {

			return new ModelAndView("login","error","Usuario o contraseña incorrectos");
		} 	
	}

	@RequestMapping(value = "recuperarContrasena.htm", method = RequestMethod.POST)
	public ModelAndView recuperarContrasena() {
		return new ModelAndView("recuperarContrasena");
	}

	@RequestMapping(method = RequestMethod.POST, value = "enviarPeticionContrasena.htm")
	public ModelAndView enviarPeticionContrasena(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {		
		String mensaje, email;
		email = request.getParameter("inputEmail");
		if(empleado.recuperarContrasena(email))
			mensaje = "Te hemos enviado una nueva contraseña al correo introducido";
		else
			mensaje = "No existe un usuario con el correo introducido";

		return new ModelAndView("recuperarContrasena", "mensaje", mensaje);
	}

	@RequestMapping(method = RequestMethod.POST, value = "abrirFichaje.htm")
	public ModelAndView abrirFichaje(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		String mensaje;
		DateFormat hora = new SimpleDateFormat("HH:mm:ss");
		DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
		model.addAttribute("email", empleado.getEmail());
		fichaje = new Fichaje(empleado.getDni(), fecha.format(new Date()), hora.format(new Date()));
		model.addAttribute("estado", fichaje.getEstado());
		mensaje = "Fichaje Abierto";
		if(empleado.getRol().equals("usuario"))
			return new ModelAndView("home", "mensaje", mensaje);
		else 
			return new ModelAndView("admin", "mensaje", mensaje);
	} 

	@RequestMapping(method = RequestMethod.POST, value = "cerrarFichaje.htm")
	public ModelAndView cerrarFichaje(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		String mensaje;
		DateFormat hora = new SimpleDateFormat("HH:mm:ss");
		model.addAttribute("email", empleado.getEmail());		
		mensaje = "Fichaje Cerrado";
		fichaje.cerrarFichaje(hora.format(new Date()), empleado);
		model.addAttribute("estado", fichaje.getEstado());
		if(empleado.getRol().equals("usuario"))
			return new ModelAndView("home", "mensaje", mensaje);
		else 
			return new ModelAndView("admin", "mensaje", mensaje);
	} 

	@RequestMapping(value = "vistaCambiarContrasena.htm", method = RequestMethod.POST)
	public ModelAndView redireccionarContrasena() {
		return new ModelAndView("contrasena");
	}

	@RequestMapping(method = RequestMethod.POST, value = "cambiarContrasena.htm")
	public ModelAndView cambiarContrasena(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		String mensaje, email, contrasena, contrasenaNueva1, contrasenaNueva2;
		email = empleado.getEmail();
		contrasena = DigestUtils.md5Hex(request.getParameter("inputContrasena"));	
		contrasenaNueva1 = request.getParameter("inputContrasenaNueva1");
		contrasenaNueva2 = request.getParameter("inputContrasenaNueva2");
		if(!empleado.credencialesCorrectas(email, contrasena))
			mensaje = "Error al introducir tu contraseña actual";
		else if(!empleado.requisitosContrasena(contrasenaNueva1))
			mensaje = "La nueva contraseña introducida debe contener al menos una mayúscula, una minúscula "
					+ "un número y 8 caracteres";
		else if(!empleado.contrasenaCoincide(contrasenaNueva1, contrasenaNueva2))
			mensaje = "Las contraseñas no coinciden";
		else {
			empleado.cambiarContrasena("cambiar contraseña", email, contrasenaNueva1);
			mensaje = "Contraseña cambiada con éxito";
		}
		return new ModelAndView("contrasena", "mensaje", mensaje);
	} 

	@RequestMapping(method = RequestMethod.POST, value = "consulta.htm")
	public ModelAndView consulta(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {		
		String idEmpleado = empleado.getDni();
		List<Document> listaFichajes = new ArrayList<Document>();
		listaFichajes = fichaje.fichajesEmpleado(idEmpleado);
		model.addAttribute("fichajes", listaFichajes);

		return new ModelAndView("consulta", "fichajes", listaFichajes);
	} 

	@RequestMapping(method = RequestMethod.POST, value = "crearIncidencia.htm")
	public ModelAndView crearIncidencia(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {		
		return new ModelAndView("crearIncidencia");
	}

	@RequestMapping(method = RequestMethod.POST, value = "registrarIncidencia.htm")
	public ModelAndView registrarIncidencia(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {		
		String idEmpleado, tipo, fechaInicio, fechaFin, comentario, mensajeEstado, mensaje;
		idEmpleado = empleado.getDni();
		tipo = request.getParameter("tipo");	
		fechaInicio = request.getParameter("fechaInicio");
		fechaFin = request.getParameter("fechaFin");	
		comentario = request.getParameter("comentario");
		mensajeEstado = "En espera";
		
		model.addAttribute("email", empleado.getEmail());

		if(!incidencia.IncidenciaCorrecta(idEmpleado, tipo, mensajeEstado))
			mensaje = "Ya tienes una incidencia de este tipo creada en espera";
		else {
			mensaje = "Incidencia creada correctamente";
			//incidencia = new Incidencia(idEmpleado,tipo,mensaje,fechaInicio.format(new Date()), fechaFin.format(new Date()),comentario);
			incidencia = new Incidencia(idEmpleado, tipo, mensajeEstado, fechaInicio, fechaFin, comentario);
		}
		return new ModelAndView("home","mensaje",mensaje);
	}
	
	@RequestMapping(value = "incidenciasGestorUsuario.htm", method = RequestMethod.POST)
	public ModelAndView incidenciasGestorUsuario(HttpServletRequest request, ModelMap model)throws Exception{
		String idEmpleado = request.getParameter("idEmpleado");
		String mensaje = request.getParameter("mensaje");
		String comentario = request.getParameter("comentario");
		incidencia.cambiarMensaje(idEmpleado, comentario, mensaje);
		
		List<Incidencia> listaIncidenciasUsuario=new ArrayList<Incidencia>();
		List<Incidencia> listaIncidenciasGestor=new ArrayList<Incidencia>();
		listaIncidenciasUsuario =  incidencia.consultarIncidenciasPropias(empleado.getDni());
		listaIncidenciasGestor =  incidencia.consultarIncidenciasGestor();
		model.addAttribute("incidenciasUsuario", listaIncidenciasUsuario);
		model.addAttribute("incidenciasGestor", listaIncidenciasGestor);
		if(empleado.getRol().equals("gestor"))
			return new ModelAndView("consultaIncidenciasGestor", "incidencias", listaIncidenciasGestor);
		else
			return new ModelAndView("consultaIncidenciasUsuario", "incidencias", listaIncidenciasUsuario);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "Incidencias.htm")
	public ModelAndView consulIncidencia(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {		
	    String id = request.getParameter("idEmpleado");
		String tipo = request.getParameter("tipo");
		String men = request.getParameter("mensaje");
		String fechaIn = request.getParameter("fechaInicio");
		String fechaFin = request.getParameter("fechaFin");
		String comentario = request.getParameter("comentario");
		
		model.addAttribute("id", id);
		model.addAttribute("tipo", tipo);
		model.addAttribute("men", men);
		model.addAttribute("fechaIn", fechaIn);
		model.addAttribute("fechaFin", fechaFin);
		model.addAttribute("comentario", comentario);
		return new ModelAndView("Incidencias");
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "resolucionIncidencias.htm")
	public ModelAndView resolverIncidencia(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {		
	    String mensaje = "";
	    String id = request.getParameter("idEmpleado");
	    String comentario = request.getParameter("comentario");
	    model.addAttribute("mensaje", mensaje);
	    model.addAttribute("id", id);
	    model.addAttribute("comentario", comentario);
		return new ModelAndView("resolucionIncidencias");
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "direccionAltaEmpleado.htm")
	public ModelAndView direccionAltaEmpleado(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {		
		return new ModelAndView("darAltaEmpleado");
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "darAltaEmpleado.htm")
	public ModelAndView darAltaEmpleado(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {		
		String id = request.getParameter("dni");
		String email = request.getParameter("email");
		String nombre = request.getParameter("nombre");
		String rol = request.getParameter("rol");
		Empleado altaEmpleado = new Empleado(id, email, nombre, rol);	
		return new ModelAndView("darAltaEmpleado");
	}
	
}
