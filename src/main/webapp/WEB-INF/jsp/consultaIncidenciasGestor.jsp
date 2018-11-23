<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
<title>Consulta Incidencias Gestor</title>
</head>
<style>
html, body {
	height: auto;
	width: 100%;
	margin: 0;
	display: flex;
	flex-direction: column;
	flex-wrap: wrap;
}

.boton-salir {
	text-decoration: none;
	font-weight: 600;
	font-size: 20px;
	color: white;
	padding-top: 15px;
	padding-bottom: 15px;
	padding-left: 40px;
	padding-right: 40px;
	background-color: red;
	border-color: #d8d8d8;
	border-width: 3px;
	border-style: solid;
	border-radius: 35px;
}

tr:hover {
	background-color: #D5D5D5;
}

table {
	font-size: 15px;
	width: 50%;
	height: 150px;
	text-align: center;
}

.boton-personalizado {
	text-decoration: none;
	font-weight: 600;
	font-size: 20px;
	color: white;
	padding-top: 15px;
	padding-bottom: 15px;
	padding-left: 40px;
	padding-right: 40px;
	background-color: #FFD27D;
	border-color: #d8d8d8;
	border-width: 3px;
	border-style: solid;
	border-radius: 35px;
}

.boton-resolver {
	text-decoration: none;
	font-weight: 600;
	font-size: 20px;
	color: white;
	padding-top: 15px;
	padding-bottom: 15px;
	padding-left: 40px;
	padding-right: 40px;
	background-color: green;
	border-color: #d8d8d8;
	border-width: 3px;
	border-style: solid;
	border-radius: 35px;
}

.boton-salir {
	text-decoration: none;
	font-weight: 600;
	font-size: 20px;
	color: white;
	padding-top: 15px;
	padding-bottom: 15px;
	padding-left: 40px;
	padding-right: 40px;
	background-color: red;
	border-color: #d8d8d8;
	border-width: 3px;
	border-style: solid;
	border-radius: 35px;
}
</style>
<body>
	<p>
	<h2 align="center">Listado de incidencias</h2>
	</p>

	<p align="center">
		<b>*Seleccione una fila haciendo click sobre ella y después, pulse
			el botón "Consultar" para mostrar la información más detallada </b>
	</p>

	<form name="normal" method="post">
		<table align="center" border="1" width: 100% id="tab">
			<thead bgcolor="#FFFD86">
				<tr>
					<th width="30">DNI</th>
					<th width="30">Email</th>
					<th width="30">Estado</th>
					<th width="30">Comentario</th>
					<th width="30">Fecha Inicio</th>
					<th width="30">Fecha Fin</th>
				</tr>
			</thead>
			<tbody bgcolor="#FFD27D">

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


		<p>
			<input type="submit" value="Consultar" id="consulta"
				onClick="verIncidencia()" class="btn boton-personalizado"></input>
		</p>

		<p>
			<input type="submit" value="Resolver" id="resolver"
				onClick="resolverIncidencia()" class="btn boton-resolver"></input>
		</p>



	</form>
	<form action="home.htm" method="post">
		<p>
			<input class="btn boton-salir" type="submit" value="Atrás">
		</p>
	</form>
</body>


<script type="text/javascript">
	var seleccionado = null; //tendremos la fila necesaria

	function onclickHandler() {
		if (seleccionado == this) {
			this.style.backgroundColor = "transparent";
			seleccionado = null;
		} else {
			if (seleccionado != null)
				seleccionado.style.backgroundColor = "transparent";
			this.style.backgroundColor = "#e1b";
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
	
	
</script>
</html>
