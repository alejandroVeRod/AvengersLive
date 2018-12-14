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
<title>Dar alta empleado</title>
</head>
<style>


body {
	background: #F7F8E0;
	margin: 0
}



.form {
	width: 340px;
	height: 500px;
	background: #ffffff;
	border-radius: 8px;
	box-shadow: 0 0 40px -10px #000;
	margin: calc(50vh - 220px) auto;
	padding: 20px 30px;
	max-width: calc(100vw - 40px);
	box-sizing: border-box;
	font-family: 'Montserrat', sans-serif;
	position: relative
}

h2 {
	margin: 10px 0;
	padding-bottom: 10px;
	width: 180px;
	color: #000000;
	border-bottom: 3px solid #428bca
}

input {
	width: auto;
	padding: 10px;
	box-sizing: border-box;
	background: none;
	outline: none;
	resize: none;
	border: 0;
	font-family: 'Montserrat', sans-serif;
	transition: all .3s;
	border-bottom: 2px solid #428bca
}

input:focus {
	border-bottom: 2px solid #78788c
}

p:before {
	content: attr(type);
	display: block;
	margin: 28px 0 0;
	font-size: 14px;
	color: #5a5a5a
}

button {
  padding: 19px 39px 20px 50px;
  color: #FFF;
	background-color: #1161ee;
	font-size: 18px;
	text-align: center;
	font-style: normal;
	border-radius: 5px;
	width: 40%;
	border: 2px solid #08088A;
	border-width: 1px 1px 3px;
	box-shadow: 0 -1px 0 rgba(255, 255, 255, 0.1) inset;
	margin-bottom: 25px;
  margin-top: 25px;
  margin-left: 200px;
  position: relative;

}

</style>

<body>

<div class="divCentro">
<form name="form" action="darAltaEmpleado.htm" class="form" method="post">
	<h2>Dar de alta</h2>
	<p type="DNI:">
		<input id="dni" name="dni" placeholder="DNI del empleado" required></input>
	</p>
	<p type="Email:">
		<input id="email" name="email" placeholder="Email del empleado" required></input>
	</p>
	<p type="Nombre y apellidos:" required>
		<input id="nombre" name="nombre"
			placeholder="Nombre y apellidos del empleado" required></input>
	</p>
	<p type="Rol:">
		<input id="rol" type="radio" name="rol" value="usuario">Usuario <br/>
		<input id="rol" type="radio" name="rol" value="administrador">Administrador <br/>
		<input id="rol" type="radio" name="rol" value="gestor">Gestor <br/>
	</p>
	
	<br>
	

	
	<button class="btn"	OnClick="atras()" type="submit">Dar de alta</button>
		<div class="foot-lnk">
							<a href="retroceder.htm">Volver</a>
				</div>

</form>

</div>
</body>
<script type="text/javascript">

	function mandarInfoAlta(){
		var dni;
		var email;
		var nombre;
		var rol;
		document.getElementById("dni").value = dni;
		document.getElementById("email").value = email;
		document.getElementById("nombre").value = nombre;
		document.getElementById("rol").value = rol;
		
	}
	function atras(){
		
		
	}

</script>
</html>