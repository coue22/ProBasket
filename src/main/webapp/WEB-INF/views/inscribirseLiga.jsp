<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.personal.basket.ctes.*" %>

	
<%
HttpSession objSesion = request.getSession(false);
String identificado = (String)objSesion.getAttribute(ConstantesSesion.IDENTIFICADO);
if (identificado == null || identificado.length()<1){
	identificado = Ctes.NO;
}

/*
String errorCrearLiga = (String)request.getAttribute("errorCrearLiga");
if (errorCrearLiga == null || errorCrearLiga.length()<1){
	errorCrearLiga = "";
}
*/
%>


<html>
   	
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Crear Liga</title>

	<link media="screen" href="css/estilos.css" type="text/css" rel="stylesheet"/>
	<link href="css/redmond/jquery-ui-1.9.0.custom.css" type="text/css" rel="stylesheet">
	<script src="js/jquery-1.8.2.js"></script>
	<script src="js/jquery-ui-1.9.0.custom.js"></script>
	<script type="text/javascript" src="js/jquery.tablednd.0.7.min.js"></script>
	
<script language="JavaScript">

/*
	function crearLiga()
	{
		var nbliga=document.getElementById('InsertaNBLiga');
		
		//Se valida que el nombre de la liga
		if (nbliga.value=='' )
		{
			alert("El nombre de la liga debe ir relleno.");
			
		}else{
		
			document.formConfirmarLiga.nbLiga.value=InsertaNBLiga.value;
			document.formConfirmarLiga.pwdLiga.value=InsertaPWDLiga.value;
			document.formConfirmarLiga.submit();
		}
	}
*/

</script>

<style>
	#icons {
		margin: 0;
		padding: 0;
	}
	#icons li {
		/*margin: 2px;*/
		position: relative;
		/*padding: 4px 0;*/
		cursor: pointer;
		float: left;
		list-style: none;
	}
	#icons span.ui-icon {
		float: left;
		margin: 0 1px;
	}
</style>
	

</head>

<body>
	
	<div id="cont">
	

 		<jsp:include page="PlantillaCabecera.jsp" />
	
	

		<div id="contenido">
		
			<% if (identificado.equalsIgnoreCase(Ctes.SI)){ %>

				<div class="tableDemo">
				<!-- <div id="debugArea" style="float: right">&nbsp;</div>"; -->
				<table id="table-2">
				
					<caption> LIGAS </caption>
					
					<thead>
						<tr>
						<th></th>
						<th>Codigo</th>
						<th>Nombre</th>
						<th>¿Es publica?</th>
						</tr>
					</thead>
					<tbody>
					
					
					<c:forEach var="fila" items="${OpcionInscribirLiga_RecuperarLigas}" varStatus="status">
						
						
						
						<tr>
							<td>
								<ul id="icons" class="ui-widget ui-helper-clearfix">
				  					<li class="ui-state-default ui-corner-all" title="jug"><span class="ui-icon ui-icon-person"></span></li>
				  				</ul>
				  			</td>
				  
				  			<td>${fila.codigo}</td>
				  			<td>${fila.nombre}</td>
			  			
				  			<td>${fila.ligaPublica}</td>

<!-- 				  			<td> -->
<!-- 				  				<ul id="icons" class="ui-widget ui-helper-clearfix"> -->
<%-- 			  					<li class="ui-state-default ui-corner-all" title="modificar" onclick="modificarEquipo('${fila.codigo}','${fila.nombre}','${fila.siglas}');"><span class="ui-icon ui-icon-circle-close"></span></li> --%>
<!-- 				  				</ul> -->
<!-- 				  			</td> -->
				  			
				  
				  		</tr>
	

													
					</c:forEach>
					

						
					</tbody>
					
				</table>
				</div>	
				
				
			<% }else{ %>
			
				<!-- Se redigira a error... -->
				<%
					objSesion.setAttribute(ConstantesSesion.DETALLE_ERROR," No se puede acceder a la opcion de menu sin estar identificado.");
				
					String redirectURL = "error";
					response.sendRedirect(redirectURL);
				%>
				
			<% } %>
		

		</div>

	
	</div>
	
	
	<jsp:include page="PlantillaPiePagina.jsp" />
	


<!-- <form name="formConfirmarLiga" action="crearLiga" method="POST"> -->
<!--  	<input type="hidden" name="operacion" value="CREAR_LIGA" /> -->

<!-- 	<input type="hidden" name="nbLiga" value="" /> -->
<!-- 	<input type="hidden" name="pwdLiga" value="" /> -->
<!-- </form> -->
  	

</body>
</html>
