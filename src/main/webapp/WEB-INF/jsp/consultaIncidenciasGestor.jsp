<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<script src=" https://code.jquery.com/jquery.js">
  
</script>
<script
  src=" https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js">
	
</script>
<link rel="stylesheet"
	href=" https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<meta http-equiv="content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="css/estilos.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script>
  $( function() {
    $( "#datepickerInicio" ).datepicker({
    	dateFormat: 'dd/mm/yy'
    });
    $( "#datepickerFin" ).datepicker({
    	dateFormat: 'dd/mm/yy'
    });
  } );
  </script>

<title>Consulta Incidencias Usuario</title>
</head>
<style>
html, body {
	/* Aquí el origen de la imagen */
	/* Fijar la imagen de fondo este vertical y
    horizontalmente y centrado */
	background-position: center center;
	/* Esta imagen no debe de repetirse */
	background-repeat: no-repeat;
	/* COn esta regla fijamos la imagen en la pantalla. */
	background-attachment: fixed;
	/* La imagen ocupa el 100% y se reescala */
	background-size: cover;
	/* Damos un color de fondo mientras la imagen está cargando  */
	background-color: #F7F8E0;
}


tr:hover {
	background-color: #D5D5D5;
}

table {
	font-size: 15px;
	width: 100%;
	padding: 20px;
	height: 150px;
	text-align: center;
	color:black;
}



.boton {
  padding: 19px 39px 18px 39px;
	color: #FFF;
	background-color: #1161ee;
	font-size: 18px;
	text-align: center;
	font-style: normal;
	border-radius: 5px;
	width: 25%;
	border: 2px solid #08088A;
	border-width: 1px 1px 3px;
	box-shadow: 0 -1px 0 rgba(255, 255, 255, 0.1) inset;
	margin-bottom: 10px;
	
}

.boton:hover {
	cursor: pointer;
}

.plegable{
   height:0;
   overflow:hidden;
}


.filtros{
border-color: #d8d8d8;
	border-width: 3px;
	border-style: solid;
	border-radius: 35px;
background-color: #c0d6e4;
}

.h4{
	cursor: pointer;
}


.divCentrado {
	font-size: 14px;
	margin: 20px auto 40px auto;
	height: 300px auto;
	width: 900px;
	overflow: hidden;
	position: relative;
	color: white;
	text-align: center;
	padding: 20px;
}

table, td, th {  
  border: 2px solid;
  text-align: left;

}

table {
  border-collapse: collapse;
  width: 40%;
}

th{
  text-align: center  
}
th, td {
  padding: 15px;
}

</style>

<jsp:include page="menu.jsp" />

<body>
	<p>
	<h2 align="center">Listado de incidencias</h2>
	</p>
	
	<p align="center">
		<b>*Seleccione una fila haciendo click sobre ella y después, pulse el botón "Consultar" 
		para mostrar la información más detallada </b>
	</p>
	
	<div class="divCentrado">
	<div class="plegable">
	<div class="filtros">
	<form action="filtro.htm" method="post">
		<p align="center" >
			<br>
			DNI 
			<input type="text" name="filtroDni">
			Email 
			<input type="text" name="filtroEmail"> 
			<br><br> 
			Fecha Inicio 
			<input type="text" id="datepickerInicio" name="filtroFechaInicio" onChange="copiar()"> 
			Fecha Fin 
			<input type="text" name="filtroFechaFin" id="datepickerFin"> 
			<br><br> 
			Tipo de Incidencia
			
			<select name="tipoIncidencia" class="select select:focus">
				<option></option>
				<option value="Vacaciones">Solicitar vacaciones</option>
				<option value="Bajas Medicas">Comunicar bajas médicas</option>
				<option value="Ausencias">Justificar ausencias</option>
				<option value="Errores">Comunicar errores en el marcaje</option>
			</select>
			
			Estado
			
				<select name="filtroEstado" class="select select:focus">
					<option></option>
					<option value="En espera">En espera</option>
					<option value="Resuelta">Resuelta</option>
				</select>
		<br>
		<br> <input class="btn" type="submit" value="Filtrar">
		</p>
	</form>
	</div>
</div>



<br></br>

	<form name="normal" method="post">
		<table align="center" border="1" id="tab">
			<thead bgcolor="#428bca">
				<tr>
					<th width="30">DNI</th>
					<th width="30">Email</th>
					<th width="30">Estado</th>
					<th width="30">Comentario</th>
					<th width="30">Fecha Inicio</th>
					<th width="30">Fecha Fin</th>
				</tr>
			</thead>
			<tbody bgcolor="#f5f5f5">

				<c:forEach items="${incidencias}" var="proyecto">
					<tr>

						<td>${proyecto.idEmpleado}</td>
						<td style="display: none">${proyecto.tipo}</td>
						<td>${proyecto.emailEmpleado}</td>
						<td>${proyecto.mensaje}</td>
						<td>${proyecto.comentario}</td>
						<td>${proyecto.fechaInicio}</td>
						<td>${proyecto.fechaFin}</td>


					</tr>
				</c:forEach>
			</tbody>

		</table>

		<input name="idEmpleado" id="dni" value="" style="display: none">
		<input name="tipo" id="tip" value="" style="display: none"> 
		<input name="emailEmpleado" id="email" value="" style="display: none">

		<input name="mensaje" id="mens" value="" style="display: none">
		<input name="fechaInicio" id="fechIn" value="" style="display: none">
		<input name="fechaFin" id="fechFin" value="" style="display: none">
		<input name="comentario" id="com" value="" style="display: none">
		<input name="estado" id="est" value="" style="display: none">

<br><br> 
		<p>
			<input type="submit" value="Consultar" id="consulta"
				onClick="verIncidencia()" class="boton"></input>
		</p>

	

</div>
	</form>
	
</body>

<script>
jQuery.fn.animateAuto = function(prop, speed, callback){
	   var elem, height, width;
	   return this.each(function(i, el){
	      el = jQuery(el), elem = el.clone().css({"height":"auto","width":"auto"}).appendTo("body");
	      height = elem.css("height"),
	      width = elem.css("width"),
	      elem.remove();
	      if(prop === "height")
	         el.animate({"height":height}, speed, callback);
	      else if(prop === "width")
	         el.animate({"width":width}, speed, callback);  
	      else if(prop === "both")
	         el.animate({"width":width,"height":height}, speed, callback);
	   });  
	}
	$(window).ready(function(){
	   $('h4').click(function(){
	      if($(this).next().hasClass('desplegado')){
	         $(this).next().removeClass('desplegado').animate({height:0},500);
	      }else{
	         $(this).next().addClass('desplegado').animateAuto("height",500);
	      }
	   })
	})
</script>


<script type="text/javascript">
	var seleccionado = null; //tendremos la fila necesaria
	
	function copiar()
    {
        document.getElementById("datepickerFin").value=document.getElementById("datepickerInicio").value;
    }

	function onclickHandler() {
		if (seleccionado == this) {
			this.style.backgroundColor = "transparent";
			seleccionado = null;
		} else {
			if (seleccionado != null)
				seleccionado.style.backgroundColor = "transparent";
			this.style.backgroundColor = "#D5D5D5";
			seleccionado = this;
		}

	}

	var fil = document.getElementById("tab").getElementsByTagName("tr");
	for (var i = 0; i < fil.length; i++) {
		fil[i].onclick = onclickHandler;

	}

	function verIncidencia() {
		if (seleccionado == null) {
			alert("Seleccione una fila haciendo click sobre ella");
			document.normal.action = "incidenciasGestorUsuario.htm";

		}
		var f = seleccionado.getElementsByTagName('td');
		var dni = f[0].textContent;
		var tipo = f[1].textContent;
		var correo= f[2].textContent;
		var mensaje = f[3].textContent;
		var comentario = f[4].textContent;
		var fechaInicio = f[5].textContent;
		var fechaFin = f[6].textContent;
		document.getElementById("dni").value = dni;
		document.getElementById("tip").value = tipo;
		document.getElementById("email").value = correo;
		document.getElementById("mens").value = mensaje;
		document.getElementById("com").value = comentario;
		document.getElementById("fechIn").value = fechaInicio;
		document.getElementById("fechFin").value = fechaFin;

		document.normal.action = "Incidencias.htm";

	}
	
	function resolverIncidencia(){	
		var f = seleccionado.getElementsByTagName('td');
		var dni = f[0].textContent;
		var comentario = f[3].textContent;
		document.getElementById("dni").value = dni;
		document.getElementById("com").value = comentario;
		document.normal.action = "resolucionIncidencias.htm";
	}
	
	function atras(){
		document.normal.action="IrHome.htm";
	}
</script>
</html>
