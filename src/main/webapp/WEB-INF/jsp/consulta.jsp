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
<title>Consulta Fichajes</title>
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
	background-color: F7F8E0;
}


tr:hover {
	background-color: #D5D5D5;
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


.centerTable{
        text-align: center;
}

 

.centerTable table {
       margin: 0 auto;
       text-align: left;
}

</style>
<jsp:include page="menu.jsp" />
<body>
	<p>
	<h2 align="center">Listado de fichajes</h2>
	</p>
	<p>
	
	<div class="centerTable"   bgcolor="#F7F8E0">
	<form action="consulta.htm" method="post">
  <br>
		<table>
			<thead bgcolor="#428bca">
				<tr>
					<th>Fecha</th>
					<th>Apertura</th>
					<th>Cierre</th>
					<th>Estado</th>
				</tr>
			</thead>
			<tbody bgcolor="#f5f5f5">
				<c:forEach var="fich" items="${fichajes}">
					<tr>
						<td>${fich.fechaFichaje}</td>
						<td>${fich.horaEntrada}</td>
						<td>${fich.horaCierre}</td>
						<td>${fich.estado}</td>
				</c:forEach>
			</tbody>
		</table>
	</form>
	</p>
	</div>

</body>

</html>
