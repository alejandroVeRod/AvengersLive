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
	List<Document> listaFichajes;
	Fichaje fichaj;
	String dniEmpl;
	String mail;
	Incidencia inc;
	String mensaje;

	@RequestMapping("login.htm")
	public ModelAndView redireccion() {
		ModelAndView MV = new ModelAndView();
		MV.setViewName("login");
		return MV;
	}

	// @RequestMapping(value = "atras.htm", method = RequestMethod.POST)
	// public ModelAndView atras(HttpServletRequest request, ModelMap model) throws
	// Exception {
	// String estado = null;
	// List<Document> listaFichajes = new ArrayList<Document>();
	// Document fich = null;
	// if(!listaFichajes.isEmpty()) {
	// for (int i=0; i<listaFichajes.size(); i++) {
	// fich = listaFichajes.get(listaFichajes.size()-1);
	// }
	// estado = fich.get("estado").toString();
	// }
	// model.addAttribute("email", empleado.getEmail());
	// model.addAttribute("estado", estado);
	// if (empleado.getRol().equals("usuario"))
	// return new ModelAndView("home");
	// else if(empleado.getRol().equals("gestor"))return new
	// ModelAndView("gestor");//unica línea añadida
	// else return new ModelAndView("admin");
	// }

	@RequestMapping(value = "home.htm", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, ModelMap model) throws Exception {
		String email, contrasena, estado = null;
		Document fich = null;
		email = request.getParameter("inputEmail");
		contrasena = DigestUtils.md5Hex(request.getParameter("inputPassword"));
		List<Document> listaFichajes = new ArrayList<Document>();
		System.out.println(contrasena);
		if (empleado.credencialesCorrectas(email, contrasena)) {
			empleado = new Empleado(email, contrasena);
			listaFichajes = fichaje.fichajesEmpleado(empleado.getDni());
			if (!listaFichajes.isEmpty()) {
				for (int i = 0; i < listaFichajes.size(); i++) {
					fich = listaFichajes.get(listaFichajes.size() - 1);
				}
				estado = fich.get("estado").toString();
			}
			model.addAttribute("email", empleado.getEmail());
			model.addAttribute("estado", estado);
			boolean est = fichaje.fichajesAbiertos(empleado.getDni());
			model.addAttribute("est", est);
			mensaje = "Fichaje Cerrado";
			if (est)
				mensaje = "Fichaje Abierto";
			model.addAttribute("mensaje", mensaje);
			if (empleado.getRol().equals("usuario"))
				return new ModelAndView("home");
			else if (empleado.getRol().equals("gestor"))
				return new ModelAndView("gestor");// unica línea añadida
			else
				return new ModelAndView("admin");

		} else {

			return new ModelAndView("login", "error", "Usuario o contraseña incorrectos");
		}
	}

	@RequestMapping(value = "recuperarContrasena.htm", method = RequestMethod.POST)
	public ModelAndView recuperarContrasena() {
		return new ModelAndView("recuperarContrasena");
	}

	@RequestMapping(method = RequestMethod.POST, value = "enviarPeticionContrasena.htm")
	public ModelAndView enviarPeticionContrasena(HttpServletRequest request, HttpServletResponse response,
			ModelMap model) throws Exception {
		String mensaje, emailDni;
		emailDni = request.getParameter("inputEmail");
		if (empleado.recuperarContrasena(emailDni))
			mensaje = "Te hemos enviado una nueva contraseña al correo introducido";
		else
			mensaje = "No existe un usuario con el correo introducido";

		return new ModelAndView("recuperarContrasena", "mensaje", mensaje);
	}

	@RequestMapping(method = RequestMethod.POST, value = "abrirFichaje.htm")
	public ModelAndView abrirFichaje(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {

		DateFormat hora = new SimpleDateFormat("HH:mm:ss");
		DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
		model.addAttribute("email", empleado.getEmail());
		fichaje = new Fichaje(empleado.getDni(), fecha.format(new Date()), hora.format(new Date()));
		model.addAttribute("estado", fichaje.getEstado());
		mensaje = "Fichaje Abierto";

		boolean est = fichaje.fichajesAbiertos(empleado.getDni());

		model.addAttribute("est", est);
		if (empleado.getRol().equals("usuario"))
			return new ModelAndView("home", "mensaje", mensaje);
		else if (empleado.getRol().equals("gestor"))
			return new ModelAndView("gestor", "mensaje", mensaje);
		return new ModelAndView("admin", "mensaje", mensaje);
	}

	@RequestMapping(method = RequestMethod.POST, value = "cerrarFichaje.htm")
	public ModelAndView cerrarFichaje(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {

		DateFormat hora = new SimpleDateFormat("HH:mm:ss");
		model.addAttribute("email", empleado.getEmail());
		mensaje = "Fichaje Cerrado";
		fichaje.cerrarFichaje(hora.format(new Date()), empleado);
		model.addAttribute("estado", fichaje.getEstado());
		if (empleado.getRol().equals("usuario"))
			return new ModelAndView("home", "mensaje", mensaje);
		else if (empleado.getRol().equals("administrador")) {
			return new ModelAndView("admin", "mensaje", mensaje);
		} else {
			return new ModelAndView("gestor", "mensaje", mensaje);
		}
	}

	@RequestMapping(value = "vistaCambiarContrasena.htm", method = RequestMethod.POST)
	public ModelAndView redireccionarContrasena() {
		return new ModelAndView("contrasena");
	}

	@RequestMapping(method = RequestMethod.POST, value = "cambiarContrasena.htm")
	public ModelAndView cambiarContrasena(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		String mensaje, email, contrasena, contrasenaNueva1, contrasenaNueva2;
		email = empleado.getEmail();
		contrasena = DigestUtils.md5Hex(request.getParameter("inputContrasena"));
		contrasenaNueva1 = request.getParameter("inputContrasenaNueva1");
		contrasenaNueva2 = request.getParameter("inputContrasenaNueva2");
		if (!empleado.credencialesCorrectas(email, contrasena))
			mensaje = "Error al introducir tu contraseña actual";
		else if (!empleado.requisitosContrasena(contrasenaNueva1))
			mensaje = "La nueva contraseña introducida debe contener al menos una mayúscula, una minúscula "
					+ "un número y 8 caracteres";
		else if (!empleado.contrasenaCoincide(contrasenaNueva1, contrasenaNueva2))
			mensaje = "Las contraseñas no coinciden";
		else {
			empleado.cambiarContrasena("cambiar contraseña", email, contrasenaNueva1);
			mensaje = "Contraseña cambiada con éxito";
		}
		return new ModelAndView("contrasena", "mensaje", mensaje);
	}

	@RequestMapping(method = RequestMethod.POST, value = "consulta.htm")
	public ModelAndView consulta(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		String idEmpleado = empleado.getDni();
		List<Document> listaFichajes = new ArrayList<Document>();
		listaFichajes = fichaje.fichajesEmpleado(idEmpleado);
		model.addAttribute("fichajes", listaFichajes);

		return new ModelAndView("consulta", "fichajes", listaFichajes);
	}

	@RequestMapping(method = RequestMethod.POST, value = "crearIncidencia.htm")
	public ModelAndView crearIncidencia(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		return new ModelAndView("crearIncidencia");
	}

	@RequestMapping(method = RequestMethod.POST, value = "registrarIncidencia.htm")
	public ModelAndView registrarIncidencia(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		boolean est = fichaje.fichajesAbiertos(empleado.getDni());
		model.addAttribute("est", est);
		String idEmpleado, tipo, fechaInicio, fechaFin, comentario, mensajeEstado, mensaje;
		idEmpleado = empleado.getDni();
		tipo = request.getParameter("tipo");
		fechaInicio = request.getParameter("fechaInicio");
		fechaFin = request.getParameter("fechaFin");
		comentario = request.getParameter("comentario");
		mensajeEstado = "En espera";

		model.addAttribute("email", empleado.getEmail());

		if (!incidencia.incidenciaMismoTipo(idEmpleado, tipo, mensajeEstado))
			mensaje = "Ya tienes una incidencia de este tipo creada en espera";
		else {
			mensaje = "Incidencia creada correctamente";
			incidencia = new Incidencia(idEmpleado, tipo, mensajeEstado, fechaInicio, fechaFin, comentario);
		}
		return new ModelAndView("home", "mensaje", mensaje);
	}

	@RequestMapping(value = "incidenciasGestorUsuario.htm", method = RequestMethod.POST)
	public ModelAndView incidenciasGestorUsuario(HttpServletRequest request, ModelMap model) throws Exception {
		String idEmpleado = request.getParameter("idEmpleado");
		String mensaje = request.getParameter("mensaje");
		String comentario = request.getParameter("comentario");
		Incidencia auxIncidencia = new Incidencia();
		incidencia.cambiarMensaje(idEmpleado, comentario, mensaje);

		List<Incidencia> listaIncidencias = new ArrayList<Incidencia>();
		listaIncidencias = auxIncidencia.incidenciasFiltradas(null, null, empleado.getRol(), empleado.getDni());
		model.addAttribute("incidenciasUsuario", listaIncidencias);
		model.addAttribute("incidenciasGestor", listaIncidencias);
		if (empleado.getRol().equals("gestor"))
			return new ModelAndView("consultaIncidenciasGestor", "incidencias", listaIncidencias);
		else
			return new ModelAndView("consultaIncidenciasUsuario", "incidencias", listaIncidencias);
	}

	@RequestMapping(method = RequestMethod.POST, value = "Incidencias.htm")
	public ModelAndView consulIncidencia(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		String id = request.getParameter("idEmpleado");
		String tip = request.getParameter("tipo");
		String email = request.getParameter("emailEmpleado");
		String men = request.getParameter("mensaje");
		String fechaIn = request.getParameter("fechaInicio");
		String fechaFin = request.getParameter("fechaFin");
		String comentario = request.getParameter("comentario");
		inc = new Incidencia(id, tip, email, men, fechaIn, fechaFin, comentario);

		model.addAttribute("id", id);
		model.addAttribute("tip", tip);
		model.addAttribute("email", email);
		model.addAttribute("men", men);
		model.addAttribute("fechaIn", fechaIn);
		model.addAttribute("fechaFin", fechaFin);
		model.addAttribute("comentario", comentario);
		return new ModelAndView("Incidencias");
	}

	@RequestMapping(method = RequestMethod.POST, value = "resolucionIncidencias.htm")
	public ModelAndView resolverIncidencia(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		String mensaje = "";
		String id = request.getParameter("idEmpleado");
		String comentario = request.getParameter("comentario");
		model.addAttribute("mensaje", mensaje);
		model.addAttribute("id", id);
		model.addAttribute("comentario", comentario);
		return new ModelAndView("resolucionIncidencias");
	}

	@RequestMapping(method = RequestMethod.POST, value = "GestionarUsuarios.htm")
	public ModelAndView GestionarUsuarios(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {

		return new ModelAndView("GestionarUsuarios");
	}

	@RequestMapping(method = RequestMethod.POST, value = "direccionAltaEmpleado.htm")
	public ModelAndView direccionAltaEmpleado(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		return new ModelAndView("darAltaEmpleado");
	}

	@RequestMapping(method = RequestMethod.POST, value = "darAltaEmpleado.htm")
	public ModelAndView darAltaEmpleado(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		String id = request.getParameter("dni");
		String email = request.getParameter("email");
		String nombre = request.getParameter("nombre");
		String rol = request.getParameter("rol");
		Empleado altaEmpleado = new Empleado(id, email, nombre, rol);
		List<Empleado> listaEmpleados = new ArrayList<Empleado>();
		listaEmpleados = empleado.consultarEmpleados();
		model.addAttribute("Empleados", listaEmpleados);

		return new ModelAndView("consultaEmpleadosModuloAdministrador");
	}

	@RequestMapping(method = RequestMethod.POST, value = "filtro.htm")
	public ModelAndView filtros(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		int i = 0;
		String[] arrayValores = new String[6];
		String[] arrayTipos = new String[6];
		List<Incidencia> listaIncidenciasFiltradas = new ArrayList<Incidencia>();
		String idEmpleado = request.getParameter("filtroDni");
		String email = request.getParameter("filtroEmail");
		String fechaInicio = request.getParameter("filtroFechaInicio");
		String fechaFin = request.getParameter("filtroFechaFin");
		String tipoIncidencia = request.getParameter("tipoIncidencia");
		String estado = request.getParameter("filtroEstado");

		if (!idEmpleado.equals("")) {
			arrayValores[i] = idEmpleado;
			arrayTipos[i] = "idEmpleado";
			i++;
		}
		if (!email.equals("")) {
			arrayValores[i] = email;
			arrayTipos[i] = "email";
			i++;
		}
		if (!fechaInicio.equals("")) {
			arrayValores[i] = fechaInicio;
			arrayTipos[i] = "fechaInicio";
			i++;
		}
		if (!fechaFin.equals("")) {
			arrayValores[i] = fechaFin;
			arrayTipos[i] = "fechaFin";
			i++;
		}
		if (!tipoIncidencia.equals("")) {
			arrayValores[i] = tipoIncidencia;
			arrayTipos[i] = "tipo";
			i++;
		}
		if (!estado.equals("")) {
			arrayValores[i] = estado;
			arrayTipos[i] = "mensaje";
			i++;
		}

		listaIncidenciasFiltradas = incidencia.incidenciasFiltradas(arrayTipos, arrayValores, empleado.getRol(),
				empleado.getDni());

		if (empleado.getRol().equals("gestor"))
			return new ModelAndView("consultaIncidenciasGestor", "incidencias", listaIncidenciasFiltradas);
		else
			return new ModelAndView("consultaIncidenciasUsuario", "incidencias", listaIncidenciasFiltradas);
	}

	@RequestMapping(value = "IrHome.htm")
	public ModelAndView IrHome(HttpServletRequest request, ModelMap model) throws Exception {

		model.addAttribute("email", empleado.getEmail());
		boolean est = fichaje.fichajesAbiertos(empleado.getDni());
		model.addAttribute("est", est);
		model.addAttribute("mensaje", mensaje);
		if (empleado.getRol().equals("usuario"))
			return new ModelAndView("home");
		else if (empleado.getRol().equals("gestor"))
			return new ModelAndView("gestor");// unica línea añadida
		else
			return new ModelAndView("admin");

	}

	@RequestMapping(method = RequestMethod.POST, value = "incis.htm")
	public ModelAndView incis(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		String id = request.getParameter("id");
		model.addAttribute("id", id);
		listaFichajes = fichaje.fichajesEmpleado(id);
		dniEmpl = id;
		model.addAttribute("mail", mail);
		model.addAttribute("fichajes", listaFichajes);
		return new ModelAndView("incs", "fichajes", listaFichajes);
	}

	@RequestMapping(value = "formFich.htm")
	public ModelAndView formFich(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		model.addAttribute("dni", dniEmpl);
		model.addAttribute("emailEmpleado", mail);
		String email = request.getParameter("emailEmpleado");
		String fechaFich = request.getParameter("fechaFichaje");
		String horaEntrada = request.getParameter("horaEntrada");
		String horaCierre = request.getParameter("horaCierre");
		String estad = request.getParameter("estado");

		if (horaEntrada != "") {
			model.addAttribute("horAh", horaEntrada.substring(0, 2));
			model.addAttribute("horAm", horaEntrada.substring(3, 5));
			model.addAttribute("horAs", horaEntrada.substring(6, 8));
		}
		if (horaCierre != "") {
			model.addAttribute("horCh", horaCierre.substring(0, 2));
			model.addAttribute("horCm", horaCierre.substring(3, 5));
			model.addAttribute("horCs", horaCierre.substring(6, 8));
		}
		model.addAttribute("dia", fechaFich.substring(0, 2));
		model.addAttribute("mes", fechaFich.substring(3, 5));
		model.addAttribute("ano", fechaFich.substring(6, 10));
		model.addAttribute("estado", estad);
		if (email == null) {
			System.out.println("hola");
			fichaj = new Fichaje("", fechaFich, horaEntrada, horaCierre, estad);
		}

		return new ModelAndView("formFich");
	}

	@RequestMapping(value = "resolverInc.htm", method = RequestMethod.POST)
	public ModelAndView Resolverincidencias(HttpServletRequest request, ModelMap model) throws Exception {
		String id = request.getParameter("idEmpleado");
		String fechaIn = request.getParameter("fechaInicio");
		String fechaFin = request.getParameter("fechaFin");
		String comen = request.getParameter("comentario");
		String tip = request.getParameter("tipo");
		String email = request.getParameter("emailEmpleado");
		incidencia.resolver(id, fechaIn, fechaFin, comen);
		model.addAttribute("id", id);
		model.addAttribute("tip", tip);
		model.addAttribute("email", email);
		model.addAttribute("men", "resuelta");
		model.addAttribute("fechaIn", fechaIn);
		model.addAttribute("fechaFin", fechaFin);
		model.addAttribute("comentario", comen);
		return new ModelAndView("Incidencias");
	}

	@RequestMapping(method = RequestMethod.POST, value = "incs.htm")
	public ModelAndView inc(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws Exception {
		String id = request.getParameter("idEmpleado");
		model.addAttribute("id", id);
		listaFichajes = fichaje.fichajesEmpleado(id);
		dniEmpl = id;
		mail = request.getParameter("emailEmpleado");
		model.addAttribute("fichajes", listaFichajes);
		return new ModelAndView("incs", "fichajes", listaFichajes);
	}

	@RequestMapping(method = RequestMethod.POST, value = "RellenarIncidencia.htm")
	public ModelAndView RellenarIncidencia(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		model.addAttribute("id", inc.getIdEmpleado());
		model.addAttribute("tip", inc.getTipo());
		model.addAttribute("email", inc.getEmailEmpleado());
		model.addAttribute("men", inc.getMensaje());
		model.addAttribute("fechaIn", inc.getFechaInicio());
		model.addAttribute("fechaFin", inc.getFechaFin());
		model.addAttribute("comentario", inc.getComentario());
		return new ModelAndView("Incidencias");
	}

	@RequestMapping(value = "retroceder.htm")
	public ModelAndView accesoModulo(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		List<Empleado> listaEmpleados = new ArrayList<Empleado>();
		listaEmpleados = empleado.consultarEmpleados();
		model.addAttribute("Empleados", listaEmpleados);
		return new ModelAndView("consultaEmpleadosModuloAdministrador");
	}

	@RequestMapping(method = RequestMethod.POST, value = "eliminarEmpleado.htm")
	public ModelAndView eliminarEmpleado(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		String email = request.getParameter("emailEmpleado");
		String nombre = request.getParameter("nombre");
		String dni = request.getParameter("dni");
		String rol = request.getParameter("rol");
		empleado.eliminarEmpleado(email);
		List<Empleado> listaEmpleados = new ArrayList<Empleado>();
		listaEmpleados = empleado.consultarEmpleados();
		model.addAttribute("Empleados", listaEmpleados);
		return new ModelAndView("consultaEmpleadosModuloAdministrador");
	}

	@RequestMapping(method = RequestMethod.POST, value = "CrearIncYCerrarFich.htm")
	public ModelAndView CrearIncYCerrarFich(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		boolean est = fichaje.fichajesAbiertos(empleado.getDni());
		model.addAttribute("est", est);

		DateFormat hora1 = new SimpleDateFormat("HH:mm:ss");
		model.addAttribute("email", empleado.getEmail());

		fichaje.cerrarFichaje(hora1.format(new Date()), empleado);
		model.addAttribute("estado", fichaje.getEstado());

		Incidencia inc = new Incidencia();
		inc.generarIncidenciaFichajeSinCerrar(empleado);

		String mensaje;
		DateFormat hora = new SimpleDateFormat("HH:mm:ss");
		DateFormat fecha = new SimpleDateFormat("dd/MM/yyyy");
		model.addAttribute("email", empleado.getEmail());
		fichaje = new Fichaje(empleado.getDni(), fecha.format(new Date()), hora.format(new Date()));
		model.addAttribute("estado", fichaje.getEstado());
		mensaje = "Fichaje Abierto";
		model.addAttribute("mensaje", mensaje);
		if (empleado.getRol().equals("Usuario"))
			return new ModelAndView("home");
		else if (empleado.getRol().equals("gestor"))
			return new ModelAndView("gestor");// unica línea añadida
		else
			return new ModelAndView("admin");
	}

	@RequestMapping(value = "modificarEmpleado.htm")
	public ModelAndView modificarEmpleado(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		String email = request.getParameter("emailEmpleado");
		String dni = request.getParameter("dni");
		String nombre = request.getParameter("nombre");
		String rol = request.getParameter("rol");
		model.addAttribute("dni", dni);
		model.addAttribute("emailEmpleado", email);
		model.addAttribute("nombre", nombre);
		model.addAttribute("rol", rol);

		String mail = request.getParameter("emailEmpleado");
		String nombree = request.getParameter("nombre");
		System.out.println(nombree);

		if (mail != null && nombre != null) {
			Empleado empl = new Empleado();
			String[] tipos = { "email", "nombre" };
			String[] valores = { mail, nombree };
			empl.modificarEmpleado(tipos, valores, dni);

		}

		return new ModelAndView("modificarEmpleado");
	}

	@RequestMapping(value = "modEmpleado.htm")
	public ModelAndView modEmpleado(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {

		String dni = request.getParameter("dni");

		System.out.println("hola");

		String mail = request.getParameter("emailEmpleado");
		String nombree = request.getParameter("nombre");
		System.out.println(nombree);

		Empleado empl = new Empleado();
		String[] tipos = { "email", "nombre" };
		String[] valores = { mail, nombree };
		empl.modificarEmpleado(tipos, valores, dni);

		List<Empleado> listaEmpleados = new ArrayList<Empleado>();
		listaEmpleados = empleado.consultarEmpleados();
		model.addAttribute("Empleados", listaEmpleados);

		return new ModelAndView("consultaEmpleadosModuloAdministrador");
	}

	@RequestMapping(method = RequestMethod.POST, value = "promocionarEmpleado.htm")
	public ModelAndView promocionarEmpleado(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		String email = request.getParameter("emailEmpleado");
		String rol = request.getParameter("rol");
		empleado.cambiarRol(email, rol);
		List<Empleado> listaEmpleados = new ArrayList<Empleado>();
		listaEmpleados = empleado.consultarEmpleados();
		model.addAttribute("Empleados", listaEmpleados);
		return new ModelAndView("consultaEmpleadosModuloAdministrador");
	}

	////////////// METODOS GET PARA NAVEGACION DEL MENU////////////////7
	@RequestMapping(method = RequestMethod.GET, value = "home.htm")
	public ModelAndView home(HttpServletRequest request, ModelMap model) throws Exception {
		String estado = null;
		model.addAttribute("email", empleado.getEmail());
		model.addAttribute("estado", estado);
		if (empleado.getRol().equals("usuario"))
			return new ModelAndView("home");
		else if (empleado.getRol().equals("gestor"))
			return new ModelAndView("gestor");// unica línea añadida
		else
			return new ModelAndView("admin");

	}

	@RequestMapping(method = RequestMethod.GET, value = "consulta.htm")
	public ModelAndView consultaFichajes(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		String idEmpleado = empleado.getDni();
		model.addAttribute("email", empleado.getEmail());
		List<Document> listaFichajes = new ArrayList<Document>();
		listaFichajes = fichaje.fichajesEmpleado(idEmpleado);
		model.addAttribute("fichajes", listaFichajes);

		return new ModelAndView("consulta", "fichajes", listaFichajes);
	}

	@RequestMapping(method = RequestMethod.GET, value = "crearIncidencia.htm")
	public ModelAndView crearIncidencias(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		model.addAttribute("id", empleado.getDni());
		model.addAttribute("email", empleado.getEmail());
		return new ModelAndView("crearIncidencia");
	}

	@RequestMapping(method = RequestMethod.GET, value = "incidenciasGestorUsuario.htm")
	public ModelAndView consultarIncidencias(HttpServletRequest request, ModelMap model) throws Exception {
		model.addAttribute("id", empleado.getDni());
		model.addAttribute("email", empleado.getEmail());
		List<Incidencia> listaIncidencias = new ArrayList<Incidencia>();
		Incidencia auxIncidencia = new Incidencia();
		listaIncidencias = auxIncidencia.incidenciasFiltradas(null, null, empleado.getRol(), empleado.getDni());
		model.addAttribute("incidenciasUsuario", listaIncidencias);
		model.addAttribute("incidenciasGestor", listaIncidencias);
		if (empleado.getRol().equals("gestor"))
			return new ModelAndView("consultaIncidenciasGestor", "incidencias", listaIncidencias);
		else
			return new ModelAndView("consultaIncidenciasUsuario", "incidencias", listaIncidencias);
	}

	@RequestMapping(method = RequestMethod.GET, value = "vistaCambiarContrasena.htm")
	public ModelAndView recuperarPassword(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		model.addAttribute("id", empleado.getDni());
		model.addAttribute("email", empleado.getEmail());
		return new ModelAndView("contrasena");
	}

	@RequestMapping(method = RequestMethod.GET, value = "recuperarContrasena.htm")
	public ModelAndView passwordOlvidada(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
		model.addAttribute("id", empleado.getDni());
		model.addAttribute("email", empleado.getEmail());

		return new ModelAndView("recuperarContrasena");
	}

	@RequestMapping(method = RequestMethod.GET, value = "accesoModulo.htm")
	public ModelAndView gestionEmpleados(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		model.addAttribute("id", empleado.getDni());
		model.addAttribute("email", empleado.getEmail());
		List<Empleado> listaEmpleados = new ArrayList<Empleado>();
		listaEmpleados = empleado.consultarEmpleados();
		model.addAttribute("Empleados", listaEmpleados);
		if (empleado.getRol().equals("administrador")) {
			return new ModelAndView("consultaEmpleadosModuloAdministrador");
		} else
			mensaje = "administrador";
		return new ModelAndView("permisoDenegado", "mensaje", mensaje);

	}

	///////////////////////////////////////////////////

	@RequestMapping(value = "AlmFich.htm")
	public ModelAndView AlmFich(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {

		String dni = request.getParameter("idEmpleado");
		String horAh = request.getParameter("horAh");
		String horAm = request.getParameter("horAm");
		String horAs = request.getParameter("horAs");
		String horCh = request.getParameter("horCh");

		String horCm = request.getParameter("horCm");
		String horCs = request.getParameter("horCs");

		String dia = request.getParameter("dia");
		String mes = request.getParameter("mes");
		String ano = request.getParameter("ano");
		String estado = request.getParameter("estado");
		if (horAs == "")
			horAs = "00";
		if (horCs == "")
			horCs = "00";
		fichaj.setIdEmpleado(dni);
		fichaj.editarFichaje(dni, horAh, horAm, horAs, horCh, horCm, horCs, dia, mes, ano, estado);
		model.addAttribute("horAh", horAh);
		model.addAttribute("horAm", horAm);
		model.addAttribute("horAs", horAs);
		model.addAttribute("horCh", horCh);
		model.addAttribute("horCm", horCm);
		model.addAttribute("horCs", horCs);
		model.addAttribute("dia", dia);
		model.addAttribute("mes", mes);
		model.addAttribute("ano", ano);
		model.addAttribute("estado", estado);
		model.addAttribute("dni", dniEmpl);
		model.addAttribute("emailEmpleado", mail);

		return new ModelAndView("formFich");
	}

	@RequestMapping(method = RequestMethod.POST, value = "IncidenciasUsers.htm")
	public ModelAndView consulIncidenciasUsers(HttpServletRequest request, HttpServletResponse response, ModelMap model)
			throws Exception {
		String id = request.getParameter("idEmpleado");
		String tip = request.getParameter("tipo");
		String email = request.getParameter("emailEmpleado");
		String men = request.getParameter("mensaje");
		String fechaIn = request.getParameter("fechaInicio");
		String fechaFin = request.getParameter("fechaFin");
		String comentario = request.getParameter("comentario");
		inc = new Incidencia(id, tip, email, men, fechaIn, fechaFin, comentario);

		model.addAttribute("id", id);
		model.addAttribute("tip", tip);
		model.addAttribute("email", email);
		model.addAttribute("men", men);
		model.addAttribute("fechaIn", fechaIn);
		model.addAttribute("fechaFin", fechaFin);
		model.addAttribute("comentario", comentario);
		return new ModelAndView("IncidenciasUsers");
	}
}
