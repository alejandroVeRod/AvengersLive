package com.avengers.proyecto;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import modelo.mongodb.Empleado;
import modelo.mongodb.Fichaje;

@Controller
public class HomeController {
	Empleado empleado = new Empleado();
	Calendar calendario = new GregorianCalendar();
	Fichaje fichaje;

//	@RequestMapping("home.htm")
//	public ModelAndView redireccion() {
//		ModelAndView MV= new ModelAndView();
//		MV.setViewName("home");
//		return MV;
//	}

	
	public String abrirFichaje(){
		String dia, mes, año, hora, mensaje;
		String fecha;		
		dia = Integer.toString(calendario.get(Calendar.DATE));
		mes = Integer.toString(calendario.get(Calendar.MONTH));
		año = Integer.toString(calendario.get(Calendar.YEAR));
		hora = Integer.toString(calendario.get(Calendar.HOUR_OF_DAY));
		fecha = dia + "/" + mes + "/" + año;	
		mensaje = "Fichaje Abierto";
		fichaje = new Fichaje(empleado.getDni(), fecha, hora);
		return mensaje;
	} 
}
