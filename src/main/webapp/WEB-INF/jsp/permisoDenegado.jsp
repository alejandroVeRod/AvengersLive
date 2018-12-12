<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=utf-8"
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
<script
	src=" https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js">
	
</script>

<title>Permiso denegado</title>
</head>
<style>
body {
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
</style>

<jsp:include page="menu.jsp" />
<body>
<div > 
<h4 class="alert alert-danger align="center">
					Módulo solo disponible para el 
					<c:out value="${mensaje}" />
				</h4>
</div>
</body>
</html>