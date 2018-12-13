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
<title>Resolución Incidencias Gestor</title>
</head>
<style>

body {
  /* Aquí el origen de la imagen */
	background-color:
	#F7F8E0;
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


.boton-personalizado {
	text-decoration: none;
	font-weight: 600;
	font-size: 20px;
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
</style>
<body>

	<h2 align="center">Resolución de incidencias</h2>
	<p align="center">
		<b>*Acepte o deniegue la incidencia seleccionada </b>
	</p>
	<input name="idEmpleado" id="dni" value="" style="display: none">
	<input name="mensaje" id="mens" value="" style="display: none">
	<input name="comentario" id="com" value="" style="display: none">

	<br>
	<br>
	<div class="col-sm-12 col-xs-12" align="center">

		<form action="incidenciasGestorUsuario.htm" method="post">
			<p>
				<input type="submit" value="Aceptar" id="aceptar"
					class="btn boton boton-personalizado"></input>
			</p>
			<input name="mensaje" id="mens" value="Aceptada"
				style="display: none"> <input name="idEmpleado" id="dni"
				value="${id}" style="display: none"> <input
				name="comentario" id="com" value="${comentario}"
				style="display: none">
		</form>

		<form action="incidenciasGestorUsuario.htm" method="post">
			<p>
				<input type="submit" value="Denegar" id="denegar"
					class="btn boton boton-personalizado"></input>
			</p>
			<input name="mensaje" id="mens" value="Denegada"
				style="display: none"> <input name="idEmpleado" id="dni"
				value="${id}" style="display: none"> <input
				name="comentario" id="com" value="${comentario}"
				style="display: none">
		</form>
	</div>
</body>

</html>