<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Fichajes</title>
<link href="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/css/bootstrap-combined.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//netdna.bootstrapcdn.com/twitter-bootstrap/2.3.2/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
 <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" media="screen">
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
	background-color: #F7F8E0;
}
{
  .boton {
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

.boton:hover {
	cursor: pointer;
}

}

}

</style>

<body class="bg-light">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
  
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<div class="container">
  <div class="row">
    <div class="span12">
      <form name="form" class="form-horizontal span6" method="post">
        <fieldset>
          <legend>Fichaje</legend>
       
          <div class="control-group">
            <label class="control-label">Correo</label>
            <div class="controls">
       
            <input type="text" style="height:30px" name="emailEmpleado" readonly=”readonly” value= "${emailEmpleado}"  class="form-control" id="email" >
                
            </div>
          </div>
                           
       <div class="control-group">
            <label class="control-label">dni</label>
            <div class="controls">
       
            <input type="text" style="height:30px" name="idEmpleado" readonly=”readonly” value= "${dni}"  class="form-control" id="email" >
                
            </div>
          </div>
          <div class="control-group">
            <label class="control-label">Apertura</label>
            <div class="controls">
              <div class="row-fluid">
                <div class="span2">
              <input type="number" name="horAh" value= "${horAh}" class="input-block-level" id="horAh" autocomplete="off" min="0" max="23" step="1"   required>
                </div>
                <div class="span2">
                 <input type="number" name="horAm" value= "${horAm}" class="input-block-level" id="horAm" autocomplete="off" min="0" max="59" step="1"  required>
                </div>
               <div class="span2">
                 <input type="number" name="horAs" readonly=”readonly”  value= "${horAs}"  class="input-block-level" autocomplete="off" min="0" max="59" step="1"  required>
                </div>
              </div>
            </div>
          </div>
           <div class="control-group">
            <label class="control-label">Cierre</label>
            <div class="controls">
              <div class="row-fluid">
                <div class="span2">
              <input type="number" name="horCh" value= "${horCh}"  class="input-block-level" id="horCh" autocomplete="off" min="0" max="59" step="1"   required>
                </div>
                <div class="span2">
                 <input type="number" name="horCm" value= "${horCm}"  class="input-block-level" id="horCm" autocomplete="off" min="0" max="59" step="1"  required>
                </div>
               <div class="span2">
                 <input type="number" name="horCs" readonly=”readonly”  value= "${horCs}" class="input-block-level" autocomplete="off" min="0" max="59" step="1"  required>
                </div>
              </div>
            </div>
          </div>
       
          <div class="control-group">
            <label class="control-label">Fecha</label>
            <div class="controls">
              <div class="row-fluid">
              <div class="span2">
                  
                         <input type="number" name="dia" value= "${dia}" class="input-block-level" id="dia" autocomplete="off" min="1" max="31" step="1"   required>
                 
                </div>
                <div class="span2">
                 
                         <input type="number" name="mes" value= "${mes}"  class="input-block-level" id="mes" autocomplete="off" min="1" max="12" step="1"   required>
                
                </div>
                <div class="span3">
                  <input type="number" name="ano" value= "${ano}"  class="input-block-level" id="ano" autocomplete="off" min="2000" max="2030" step="1"   required>
                
                </div>
              </div>
            </div>
          </div>
       
          <div class="control-group">
            <label class="control-label">Estado</label>
            <div class="controls">
              <div class="row-fluid" >
                <select name ="estado" class="input-block-level">
                 
                    <option>Abierto</option>
                    <option>Cerrado</option>                   
                  </select>
              </div>
            </div>
          </div>
       
          <div class="form-actions">
          
            <button type="submit" onClick="modificar()" class="btn btn-primary">Almacenar</button>
            
          
          </div>
          
          
        </fieldset>
        
        <input type="text" name="idEmpleado" style="display: none" value= "${dni}" class="form-control">
  				 <input type="text" name="emailEmpleado" style="display:none"   value= "${emailEmpleado}"  class="form-control" >
					 <input
					type="text" name="idEmpleado" style="display: none" value= "${dni}" class="form-control">
					 <input type="text" name="emailEmpleado" style="display:none"   value= "${emailEmpleado}"  class="form-control" >
      </form>
    </div>
  </div>
</div>


<form action="incs.htm" method="post" class="formFich">
				<input
					type="text" name="idEmpleado" style="display: none" value= "${dni}" class="form-control">
					 <input type="text" name="emailEmpleado" style="display:none"   value= "${emailEmpleado}"  class="form-control" >
				
				
&nbsp;&nbsp;
				<button class="btn  btn-primary  "
				
        
        type="submit">Atras</button>
					
        <style>
          
          

        </style>
			</form>
</body>

<script>
function modificar(){
	if(document.getElementById("horAh").value!=""&&document.getElementById("horAm").value!=""&&document.getElementById("horCh").value!=""
			&&document.getElementById("horCm").value!=""&&document.getElementById("dia").value!=""&&document.getElementById("mes").value!=""
			&&document.getElementById("ano").value!=""){
	alert("Fichaje editado");
	}
	document.form.action = "AlmFich.htm";
}
</script>
</html>
