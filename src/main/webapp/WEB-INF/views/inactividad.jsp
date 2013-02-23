<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.personal.basket.ctes.*" %>

<html>
   	
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Error</title>

	<link media="screen" href="css/estilos.css" type="text/css" rel="stylesheet"/>
	<link href="css/redmond/jquery-ui-1.9.0.custom.css" type="text/css" rel="stylesheet">
	<script src="js/jquery-1.8.2.js"></script>
	<script src="js/jquery-ui-1.9.0.custom.js"></script>
	<script type="text/javascript" src="js/jquery.tablednd.0.7.min.js"></script>
	
	<script>
		function volverPrincipal(){
			document.formInactividad.submit();
		}	
	</script>
	
</head>

<body>
	
	<div id="cont">
	

<%--  		<jsp:include page="PlantillaCabecera.jsp" /> --%>
			
			<div id="contenido">
			
			 	<div id="menuPeticion">
			 	
			 		<h3>Error</h3>
					<table><tr><td>Ha caducado la sesion</td></tr></table>
					
					<h3>Detalle</h3>
					<table><tr><td>Se ha superado el tiempo maximo de sesion. Debe logarse otra vez.</td></tr></table>
					
					<table><tr><td>
						<input type="button" name="botonInactividad" value="Volver" maxlength="10" size="10" class="input" onclick="volverPrincipal();"/>
					</td></tr></table>
					
					
				</div>
				
			</div>
	

	
	</div>

<!-- Se redirecciona a la primera pantalla. -->
<form name="formInactividad" action="/ProBasket" method="GET">
<!-- <form name="formInactividad" action="/basket" method="GET"> -->
</form>

</body>
</html>
