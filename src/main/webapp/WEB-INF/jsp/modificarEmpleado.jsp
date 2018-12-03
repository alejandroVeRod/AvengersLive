<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html; charset=utf-8"
	import="com.avengers.proyecto.loginController"%>
<!DOCTYPE html>
<html>
<head>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
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
</style>


<body>

<form class="form-horizontal">
<fieldset>

         
<legend></legend>

<div class="form-group">
  <label class="col-md-4 control-label" for="nombre">DNI</label>  
  <div class="col-md-5">
      <input type="text" style="height:30px" name="idEmpleado" readonly=”readonly” value= "${emailEmpleado}"  class="form-control" id="email" >
  </div>
</div>
<div class="form-group">
  <label class="col-md-4 control-label" for="nombre">Nombre</label>  
  <div class="col-md-5">
  <input id="nombree" name="nombree" type="text" placeholder="Nombre" class="form-control input-md"  value ="${nombree}" pattern="[A-Za-z ]{1,20}" title="Ej:Jose Luis" required >
    
  </div>
</div>


<div class="form-group">
  <label class="col-md-4 control-label" for="apellido">Apellido</label>  
  <div class="col-md-5">
  <input id="apellido" name="apellido" type="text" placeholder="apellidos" class="form-control input-md" value ="${apellido}" pattern="[A-Za-z]{1,15} [A-Za-z]{1,15}" title="Ej: Rodriguez Esi" required >
    
  </div>
</div>


<div class="form-group">
  <label class="col-md-4 control-label" for="email">Email</label>  
  <div class="col-md-5">
  <input id="email" name="email" type="text" placeholder="email@esi.uclm.es" class="form-control input-md" value ="${email}" required>
    
  </div>
</div>


<div class="form-group">
  <label class="col-md-4 control-label" for="enviar"></label>
  <div class="col-md-4">
     <button type="submit" class="btn boton-personalizado">Editar</button>
  </div>
</div>

</fieldset>

	<h2 align="center">Modificación de rol del empleado</h2>
	<p align="center">
		<b>*Promocione o deniegue al empleado </b>
	</p>
	<input name="idEmpleado" id="dni" value="" style="display: none">
	<input name="mensaje" id="mens" value="" style="display: none">
	<input name="comentario" id="com" value="" style="display: none">

	<br>
	<br>
	<input name="emailEmpleado" id="email" value="${emailEmpleado}" style="display: none"> 
    <input name="dni" id="email" value="${dni}" style="display: none"> 
	</form>
	<div  align="center">

		<form action="promocionarEmpleado.htm" method="post">
			
		<p>
				
			<input name="emailEmpleado" id="email" value="${emailEmpleado}" style="display: none"> 
			<input name="rol" id="rol" value="Administrador" style="display: none">
			<input class="btn boton-personalizado" type="submit" name="consulta"
			value="Promocionar a administrador" />
		</p>
		</form>

		<form action="promocionarEmpleado.htm" method="post">
			<input name="emailEmpleado" id="email" value="${emailEmpleado}" style="display: none"> 
			<input name="rol" id="rol" value="Usuario" style="display: none">
			<p>
			<input class="btn boton-personalizado" type="submit" name="consulta"
			value="Degradar a usuario"  />
				
			</p>
			
		</form>
		
		<form action="promocionarEmpleado.htm" method="post">
			<input name="emailEmpleado" id="email" value="${emailEmpleado}" style="display: none"> 
			<input name="rol" id="rol" value="Gestor" style="display: none">
			<p>
				<input class="btn boton-personalizado" type="submit" name="consulta"
			value="Promocionar a gestor"  />
			</p>
			
		</form>
	</div>
	
</body>

</html>