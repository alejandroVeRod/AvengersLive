<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=utf-8"
	import="com.avengers.proyecto.loginController"%>
<!DOCTYPE html>
<html>
<head>
<script src=" https://code.jquery.com/jquery.js ">
	
</script>
<script type="text/javascript"
	src=" https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js">
	
</script>
<link rel="stylesheet" href="css/estilos.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<title>Home</title>
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

.boton-contrasena {
	text-decoration: none;
	font-weight: 600;
	font-size: 20px;
	color: white;
	padding-top: 15px;
	padding-bottom: 15px;
	padding-left: 40px;
	padding-right: 40px;
	background-color: #5DE7DA;
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

	<h1 align="center">Bienvenido a AvengersLive Web</h1>
	<h3 style="padding-right: 20px;" align="right">
		Administrador:
		<c:out value="${email}" />
	</h3>
	</br>

	<div>
		<p>
		<form style="padding-left: 20px;" action="vistaCambiarContrasena.htm"
			method="post" align="left">
			<input class="btn boton-contrasena" type="submit"
				id="cambiarContraseña" value="Cambiar Contraseña" />
		</form>
		</p>

		<p>
		<form action="abrirFichaje.htm" method="post" align="center">
			<input class="btn boton-personalizado" type="submit"
				id="abrirFichaje" value="Abrir Fichaje" />
		</form>
		</p>

		<p>
		<form action="cerrarFichaje.htm" method="post" align="center">
			<input class="btn boton-personalizado" type="submit"
				id="cerrarFichaje" value="Cerrar Fichaje" align="center" />
		</form>
		</p>

		<script language="JavaScript" type="text/javascript">
			if ("${estado}" == "Abierto") {
				abrirFichaje.disabled = true;
				cerrarFichaje.disabled = false;
			} else {
				abrirFichaje.disabled = false;
				cerrarFichaje.disabled = true;
			}
		</script>

		<p>
		<form action="consulta.htm" method="post" align="center">
			<input class="btn boton-personalizado" type="submit" name="consulta"
				value="Consulta de Fichajes" />
		</form>
		</p>

		<p>
		<form action="crearIncidencia.htm" method="post" align="center">
			<input class="btn boton-personalizado" type="submit"
				name="crearIncidencia" value="Crear incidencia" />
		</form>
		</p>

		<p>
		<form action="incidenciasGestorUsuario.htm" method="post"
			align="center">
			<input class="btn boton-personalizado" type="submit"
				name="ConsultarIncidencia" value="Consultar incidencias" />
		</form>
		</p>

		<p>
		<form action="direccionAltaEmpleado.htm" method="post"
			align="center">
			<input class="btn boton-personalizado" type="submit"
				name="darAltaEmpleado" value="Dar de alta" />
		</form>
		</p>


		<br></br>
		<p>
		<h4 align="center">
			Estado:
			<c:out value="${mensaje}" />
		</h4>
		</p>
		<p>
		<form style="padding-left: 20px;" action="login.htm" method="post">
			<input class="btn boton-salir" value="Logout" type="submit"
				name="BtnSalir">
		</form>
		</p>
	</div>

</body>
</html>
