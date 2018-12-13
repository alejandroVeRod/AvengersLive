<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=utf-8"
  import="com.avengers.proyecto.loginController"%>
<!DOCTYPE html>
<html>
<head>
<script src=" https://code.jquery.com/jquery.js ">
  
</script>
<script
	src=" https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js">
	
</script>
<link rel="stylesheet" href="css/estilos.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<title>Cambiar contraseña</title>
</head>


<style>
.body {
	margin: 0;
	color: #6a6f8c;
	background: #F7F8E0;
}

*, :after, :before {
	box-sizing: border-box
}

.clearfix:after, .clearfix:before {
	content: '';
	display: table
}

.clearfix:after {
	clear: both;
	display: block
}

.a {
	color: inherit;
	text-decoration: none
}

.login-wrap {
	width: 100%;
	margin: auto;
	max-width: 525px;
	min-height: 670px;
	position: relative;
		no-repeat center;
	box-shadow: 0 12px 15px 0 rgba(0, 0, 0, .24), 0 17px 50px 0
		rgba(0, 0, 0, .19);
}

.login-html {
	width: 100%;
	height: 110%;
	position: absolute;
	padding: 90px 70px 50px 70px;
	background: #343a40;
}

.login-html .sign-in, .login-html .sign-up, .login-form .group .check {
	display: none;
}

.login-html .tab, .login-form .group .label, .login-form .group .button
	{
	text-transform: uppercase;
}

.login-html .tab {
	font-size: 22px;
	margin-right: 15px;
	padding-bottom: 5px;
	margin: 0 15px 10px 0;
	display: inline-block;
	border-bottom: 2px solid transparent;
}

.login-html .sign-in:checked+.tab, .login-html .sign-up:checked+.tab {
	color: #fff;
	border-color: #1161ee;
}

.login-form {
	min-height: 345px;
	position: relative;
	perspective: 1000px;
	transform-style: preserve-3d;
}

.login-form .group {
	margin-bottom: 15px;
}

.login-form .group .label, .login-form .group .input, .login-form .group .button
	{
	width: 100%;
	color: #fff;
	display: block;
}

.login-form .group .input, .login-form .group .button {
	border: none;
	padding: 15px 20px;
	border-radius: 25px;
	background: #D8D8D8;
}

.login-form .group input[data-type="password"] {
	text-security: circle;
	-webkit-text-security: circle;
}

.login-form .group .label {
	color: #aaa;
	font-size: 12px;
}

.login-form .group .button {
	background: #1161ee;
}

.login-form .group label .icon {
	width: 15px;
	height: 15px;
	border-radius: 2px;
	position: relative;
	display: inline-block;
	background: rgba(255, 255, 255, .1);
}

.login-form .group label .icon:before, .login-form .group label .icon:after
	{
	content: '';
	width: 10px;
	height: 2px;
	background: #fff;
	position: absolute;
	transition: all .2s ease-in-out 0s;
}

.login-form .group label .icon:before {
	left: 3px;
	width: 5px;
	bottom: 6px;
	transform: scale(0) rotate(0);
}

.login-form .group label .icon:after {
	top: 6px;
	right: 0;
	transform: scale(0) rotate(0);
}

.login-form .group .check:checked+label {
	color: #fff;
}

.login-form .group .check:checked+label .icon {
	background: #1161ee;
}

.hr {
	height: 2px;
	margin: 60px 0 50px 0;
	background: rgba(255, 255, 255, .2);
}

.foot-lnk {
	text-align: center;
}
</style>

<body>
<br></br>
  <div style="background: #1161ee; background-color: transparent">
		<p></p>

		<!-- LOGIN -->
		<div class="login-wrap">
			<div class="login-html">
				<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label
					for="tab-1" class="tab">Cambio de contraseña</label>

				<p align="left" >

				</p>

				<form action="cambiarContrasena.htm" method="post" autocomplete="off"
					class="login-form">
					<div class="sign-in-htm">
						<div class="group">
  			<li type="disc" align="center" style="color:white">Introduzca la contraseña actual</li>
              <br>
							<input type="password" class="input" name="inputContrasena" autocomplete="off"
								placeholder="Contraseña actual" required autofocus> 
								<br>
                <li type="disc" align="center" style="color:white">Introduzca la nueva contraseña.
				      	Esta debe contener al menos 8 caracteres con una mayúscula, una
					      minúscula y un número</li>
								<br>
                <input
								type="password" class="input" name="inputContrasenaNueva1" autocomplete="off"
								placeholder="Nueva contraseña" required> 
								<br>
                    
      			<li type="disc" align="center" style="color:white">Vuelva a introducir la nueva
    					contraseña</li>
              <br>
								<input
								type="password" class="input" name="inputContrasenaNueva2" autocomplete="off"
								placeholder="Repetir nueva contraseña" required>
						</div>
	<br>
						<div class="group">
							<input class="button" type="submit" name="cambioContrasena"
								value="Cambiar Contraseña" />
						</div>
					</div>
					<h4 align="center">
			<br>
        <ul style="color:#FF0000";>
				  	<c:out value="${mensaje}" />
				</ul>
        
				
					</h4>
				</form>
				
				<div class="foot-lnk">
        <br>
							<a href="IrHome.htm">Volver al inicio</a>
				</div>
			</div>
		</div>
	</div>
</body>

</html>