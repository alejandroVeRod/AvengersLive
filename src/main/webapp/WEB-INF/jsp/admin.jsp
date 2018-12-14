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


<title>Home</title>
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



.button {
	font-family: Arial;
  padding: 19px 39px 18px 39px;
  color: #FFF;
	background-color: #1161ee;
	font-size: 18px;
	text-align: center;
	font-style: normal;
	border-radius: 5px;
	width: 50%;
	border: 2px solid #08088A;
	border-width: 1px 1px 3px;
	box-shadow: 0 -1px 0 rgba(255, 255, 255, 0.1) inset;
	margin-bottom: 10px;

}

.button:hover {
	background: white;
}



.divFichajes {
	background-color: rgba(0, 0, 0, 0.8);;
	font-size: 14px;
	border: outset;
	margin: 20px auto 40px auto;
	height: 350px;
	width: 500px;
	overflow: hidden;
	position: relative;
	color: white;
	text-align: center;
	padding: 20px;
}

.cabecera {
	border-radius: 0px 0px 0px 0px;
	-moz-border-radius: 0px 0px 0px 0px;
	-webkit-border-radius: 0px 0px 0px 0px;
	height: 120px;

		width: 100%;

	position: relative;
	top: 0;
	transition: all 0.5s ease;
	box-shadow: 0px 2px 10px #fff inset;
}



.centerTable {
	text-align: center;
}

.centerTable table {
	margin: 0 auto;
	text-align: left;
}
</style>
<jsp:include page="menu.jsp" />
<body>

	<div>

		<div class="cabecera">
			<h1 align="center">Gestor de Fichajes</h1>
		</div>

		</br>
		<div class="divFichajes">

			<br></br>

			<div class="centerTable">
				<tr>
					<td colspan="2" style="text-align: center;">

						<form name="abr" method="post">

							<input name="est" id="est" value="${est}" style="display: none">
							<input type="submit" value="ABRIR FICHAJE" id="consulta"
								onClick="verEstado()" class="button"></input>

						</form> </br>

						<form action="cerrarFichaje.htm" method="post" align="center">
							<input class="button" type="submit"
								id="cerrarFichaje" value="CERRAR FICHAJE" align="center" />
						</form>

					</td>
				</tr>
			</div>

			<div>
				<br>
				<h4 style="color: white" align="center">
					Estado:
					<c:out value="${mensaje}" />
				</h4>

			</div>

		</div>


		<script language="JavaScript" type="text/javascript">
			if ("${estado}" == "Abierto") {
				abrirFichaje.disabled = true;
				cerrarFichaje.disabled = false;
			} else {
				abrirFichaje.disabled = false;
				cerrarFichaje.disabled = true;
			}
		</script>
		<br></br>



		<p></p>

	</div>
</body>

<script type="text/javascript">
	function verEstado() {
		if (document.getElementById("est").value == "true") {

			var mensaje;
			var opcion = confirm("Tiene otro fichaje abierto, ¿desea continuar?");
			if (opcion == true) {
				document.abr.action = "CrearIncYCerrarFich.htm";
			} else {

				document.abr.action = "IrHome.htm";
			}
		} else {
			document.abr.action = "abrirFichaje.htm";
		}

	}
</script>


</html>

