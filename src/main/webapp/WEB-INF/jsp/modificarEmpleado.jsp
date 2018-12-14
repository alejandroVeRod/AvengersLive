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


<title>Resolucion Incidencias Gestor</title>
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
	background-color: #E6E6E6;
}


.boton-personalizadodivarriba{
  
    padding: 19px 39px 18px 39px;
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
	margin-bottom: 10px;
 

}

.boton-personalizado {

  padding: 19px 39px 18px 39px;
  color: #FFF;
	background-color: 1161ee;
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

.divCentro1 {
	background-color: rgba(0, 0, 0, 0.8);;
	font-size: 14px;
	border: outset;
	margin: 20px auto 40px auto;
	height: 500px;
	width: 780px;
	overflow: hidden;
	position: relative;
	color: white;
	text-align: left;
	padding: 20px;
  
}

.divCentro2 {
	background-color: rgba(0, 0, 0, 0.8);;
	font-size: 14px;
	border: outset;
	margin: 20px auto 100px auto;
	height: 430px;
	width: 780px;
	overflow: hidden;
	position: relative;
	color: white;
	text-align: center;
	padding: 40px;
}


</style>

<jsp:include page="menu.jsp" />

<body>
<div class="divCentro1">
<form  name="form" class="form-horizontal">
<fieldset>

         
<legend></legend>


<h2 align="center">Modificacion de datos del empleado</h2>
<br></br>
<div class="form-group">
  <label class="col-md-4 control-label" for="nombre">DNI</label>  
  <div class="col-md-5">
      <input type="text" style="height:30px; width: 700px; align:center;" name="idEmpleado" readonly=”readonly” value= "${dni}"  class="form-control" id="email" >
  </div>
</div>
<div class="form-group">
  <label class="col-md-4 control-label" for="nombre">Nombre y Apellidos</label>  
  <div class="col-md-5">
  <input id="nombree" name="nombre" type="text" style="height:30px; width: 700px;" placeholder="Nombre" class="form-control input-md"  value ="${nombre}" pattern="[A-Za-z ]{1,20}" title="Ej:Jose Luis" required >
    
  </div>
</div>





<div class="form-group" >
  <label class="col-md-4 control-label" for="email">Email</label>  
  <div class="col-md-5">
  <input id="email" name="emailEmpleado" type="text" style="height:30px; width: 700px;" placeholder="email@esi.uclm.es" class="form-control input-md" value ="${emailEmpleado}" required>
    
  </div>
</div>


<div class="form-group" align="center">
  <label class="col-md-4 control-label" for="enviar"></label>
  <div class="col-md-4">
     <button type="submit" onClick="modificar()"   style="width: 320px;"  class="btn boton-personalizadodivarriba">Editar</button>
  </div>
</div>

</div>


<div class="divCentro2">

</fieldset>

	<h2 align="center">Modificacion de rol del empleado</h2>
	<p align="center">
	</p>
	<input name="idEmpleado" id="dni" value="" style="display: none">
	<input name="mensaje" id="mens" value="" style="display: none">
	<input name="comentario" id="com" value="" style="display: none">

	<br>
	<br>
	<input name="email" id="email" value="${emailEmpleado}" style="display: none"> 
    <input name="dni" id="email" value="${dni}" style="display: none"> 
	<input name="nombree" id="email" value="${nombr}" style="display: none"> 
    <input name="rol" id="email" value="${rol}" style="display: none"> 
	</form>
	<div  align="center">

		<form action="promocionarEmpleado.htm" method="post">
			
		<p>
				
			<input name="emailEmpleado" id="email" value="${emailEmpleado}" style="display: none"> 
			<input name="rol" id="rol" value="administrador" style="display: none">
			<input class="btn boton-personalizado" type="submit" name="consulta"
			value="Promocionar a administrador" />
		</p>
		</form>

		<form action="promocionarEmpleado.htm" method="post">
			<input name="emailEmpleado" id="email" value="${emailEmpleado}" style="display: none"> 
			<input name="rol" id="rol" value="usuario" style="display: none">
			<p>
			<input class="btn boton-personalizado" type="submit" name="consulta"
			value="Degradar a usuario"  />
				
			</p>
			
		</form>

		<form action="promocionarEmpleado.htm" method="post">
			<input name="emailEmpleado" id="email" value="${emailEmpleado}" style="display: none"> 
			<input name="rol" id="rol" value="gestor" style="display: none">
			<p>
				<input class="btn boton-personalizado" type="submit" name="consulta"
			value="Promocionar a gestor"  />
			</p>
			
		</form>
	</div>
	</div>
</body>
<script>
function modificar(){
	
	document.form.action = "modEmpleado.htm";
}
</script>
</html>