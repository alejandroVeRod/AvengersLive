<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html" pageEncoding="iso-8859-1"
	import="com.avengers.proyecto.loginController"%>
<!DOCTYPE html>
<html>
<head>

<link href="src/main/webapp/WEB-INF/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	rel="stylesheet" media="screen">

<script src=" https://code.jquery.com/jquery.js">
	
</script>

<title>Menu</title>
</head>
<body>

	<!-- Navegation -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="">AVENGERS LIVE</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>


		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> Fichajes</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="IrHome.htm">Abrir/Cerrar Fichajes<span
							class="sr-only">(current)</span></a> <a class="dropdown-item"
							href="consulta.htm">Consultar Fichajes</a>
						<!-- <div class="dropdown-divider"></div> -->
					</div></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> Incidencias </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="crearIncidencia.htm">Crear
							incidencia</a> <a class="dropdown-item"
							href="incidenciasGestorUsuario.htm">Consultar incidencias</a>
					</div></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="" id="navbarDropdown"
					role="button" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false"> Cuenta </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
						<a class="dropdown-item" href="vistaCambiarContrasena.htm">Cambiar
							contraseña</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href=""><c:out value="${email}" /></a>

					</div></li>

				<li class="nav-item"><a class="nav-link disabled"
					href="accesoModulo.htm" >Empleados</a></li>

		

				<li class="nav-item"><a class="nav-link" href="login.htm">Salir</a>
				</li>
			</ul>
		</div>
	</nav>

</body>


</html>
