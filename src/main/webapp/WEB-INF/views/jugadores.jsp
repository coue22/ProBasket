<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.personal.basket.ctes.*" %>

	
<%
HttpSession objSesion = request.getSession(false);
String identificado = (String)objSesion.getAttribute(ConstantesSesion.IDENTIFICADO);
if (identificado == null || identificado.length()<1){
	identificado = Ctes.NO;
}
%>

<html>
   	
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Homeeee</title>

	<link media="screen" href="css/estilos.css" type="text/css" rel="stylesheet"/>
	<link href="css/redmond/jquery-ui-1.9.0.custom.css" type="text/css" rel="stylesheet">
	<script src="js/jquery-1.8.2.js"></script>
	<script src="js/jquery-ui-1.9.0.custom.js"></script>
	<script type="text/javascript" src="js/jquery.tablednd.0.7.min.js"></script>
	
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
				
					<caption> Jugadores </caption>
					
					<thead>
						<tr>
						<th></th>
						<th>Jugador</th>
						<th>Posicion</th>
						<th>Nacion.</th>
						<th>Altura</th>
						<th>Peso</th>
						<th>Acciones</th>
						</tr>
					</thead>
					<tbody>
					
					
					<c:forEach var="fila" items="${OpcionJugadores}" varStatus="status">
						
						
						
						<tr>
							<td>
								<ul id="icons" class="ui-widget ui-helper-clearfix">
				  					<li class="ui-state-default ui-corner-all" title="jug"><span class="ui-icon ui-icon-person"></span></li>
				  				</ul>
				  			</td>
				  
				  			<td>${fila.apellido}, ${fila.nombre}</td>
				  			<td>${fila.puesto}</td>
				  			<td>${fila.nacionalidad}</td>
				  			<td>${fila.altura} Cm. </td>
				  			<td>${fila.peso} Kg. </td>
				  			
				  			<td><ul id="icons" class="ui-widget ui-helper-clearfix">
				  			<li class="ui-state-default ui-corner-all" title="despedir" onclick=alert(${fila.codigo});><span class="ui-icon ui-icon-circle-close"></span></li>
				  			<c:if test="${status.count == 1}">
					  			<li class="ui-state-default ui-corner-all" title="Capitán: Puntuación doble" onclick=alert(${fila.codigo});><span class="ui-icon ui-icon-battery-3"></span></li>
					  		</c:if>
				  			</ul></td>
				  			
				  
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
  	

</body>
</html>
