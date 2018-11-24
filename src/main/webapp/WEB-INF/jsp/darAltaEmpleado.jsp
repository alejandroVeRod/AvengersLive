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
	background: #59ABE3;
	margin: 0
}

.form {
	width: 340px;
	height: 440px;
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
	color: #78788c;
	border-bottom: 3px solid #78788c
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
	border-bottom: 2px solid #bebed2
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
	float: right;
	padding: 8px 12px;
	margin: 8px 0 0;
	font-family: 'Montserrat', sans-serif;
	border: 2px solid #78788c;
	background: 0;
	color: #5a5a6e;
	cursor: pointer;
	transition: all .3s
}

button:hover {
	background: #78788c;
	color: #fff
}
</style>
<body>

</body>
<form action="darAltaEmpleado.htm" class="form" method="post">
	<h2>Dar de alta</h2>
	<p type="DNI:">
		<input id="dni" name="dni" placeholder="DNI del empleado"></input>
	</p>
	<p type="Email:">
		<input id="email" name="email" placeholder="Email del empleado"></input>
	</p>
	<p type="Nombre y apellidos:">
		<input id="nombre" name="nombre"
			placeholder="Nombre y apellidos del empleado"></input>
	</p>
	<p type="Rol:">
		<input id="rol" type="radio" name="rol" value="usuario">Usuario 
		<input id="rol" type="radio" name="rol" value="administrador">Administrador 
		<input id="rol" type="radio" name="rol" value="gestor">Gestor
	</p>
	<button class="btn"	type="submit">Dar de alta</button>

</form>
<script>

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



</script>
</html>