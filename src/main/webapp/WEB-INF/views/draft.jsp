<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.personal.basket.ctes.*" %>
<%@ page import="java.util.ArrayList" language="java" %>

	
<%
HttpSession objSesion = request.getSession(false);
String identificado = (String)objSesion.getAttribute(ConstantesSesion.IDENTIFICADO);
if (identificado == null || identificado.length()<1){
	identificado = Ctes.NO;
}

String loginUsuario = (String)objSesion.getAttribute(ConstantesSesion.LOGIN_USUARIO);
if (loginUsuario == null || loginUsuario.length()<1){
	loginUsuario = Ctes.NO_ASIGNADO_LOGIN;
}

String codLiga = (String)objSesion.getAttribute(ConstantesSesion.MI_LIGA);
if (codLiga == null || codLiga.length()<1){
	codLiga = Ctes.NO;
}

String codEquipo = (String)objSesion.getAttribute(ConstantesSesion.MI_EQUIPO);
if (codEquipo == null || codEquipo.length()<1){
	codEquipo = Ctes.NO;
}

String nbLiga = (String)request.getAttribute(Ctes.DRAFT_NB_LIGA);
if (nbLiga == null || nbLiga.length()<1){
	nbLiga = "";
}

String nbEquipo = (String)request.getAttribute(Ctes.DRAFT_NB_EQUIPO);
if (nbEquipo == null || nbEquipo.length()<1){
	nbEquipo = "";
}

String dineroDisponible =String.valueOf(request.getAttribute(Ctes.DRAFT_ECONOMIA));
if (dineroDisponible == null || dineroDisponible.length()<1){
	dineroDisponible = "";
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
<title>Draft</title>

	<link media="screen" href="css/estilos.css" type="text/css" rel="stylesheet"/>
	<link media="screen" href="css/gmail.css" type="text/css" rel="stylesheet"/>
	<link href="css/redmond/jquery-ui-1.9.0.custom.css" type="text/css" rel="stylesheet">
	<script src="js/jquery-1.8.2.js"></script>
	<script src="js/jquery-ui-1.9.0.custom.js"></script>
	<script type="text/javascript" src="js/jquery.tablednd.0.7.min.js"></script>
	<script type="text/javascript" src="js/error.js"></script>
	
<script language="JavaScript">


	$.ajaxSetup({
	    cache: false
	});
	
	function doConfirmarDraft(codliga, codequipo, sel1,sel2,sel3,sel4,sel5,sel6,sel7,sel8,sel9,sel10){
	
        $.ajax({
	          url: 'confirmarDraft',
	          data: ({codliga : codliga, 
		        	  codequipo : codequipo, 
		        	  draft1 : sel1, 
		        	  draft2 : sel2, 
		        	  draft3 : sel3, 
		        	  draft4 : sel4, 
		        	  draft5 : sel5, 
		        	  draft6 : sel6, 
		        	  draft7 : sel7, 
		        	  draft8 : sel8, 
		        	  draft9 : sel9, 
		        	  draft10 : sel10}),
	          success: function(data) {
	        	  alert(data);
	            	//$("textarea#mensaje_entrada").val(data);//send response to textarea
				 	//$("div#entrada").val(data);//send response to textarea
	          }
		});

	}
/*
	function doRefrescarDraft(){
        $.ajax({
	          url: 'refrescarDraft',
	          data: ('sinvalor'),
	          success: function(data) {
	        	  alert(data);
	            	//$("textarea#mensaje_entrada").val(data);//send response to textarea
				 	//$("div#entrada").val(data);//send response to textarea
					
					cargarJugadoresDraft();
				
	          }
		});
	}
*/
	function cargarJugadoresDraft(){

		var contador = 1;
		<c:forEach var="fila" items="${draft_jugadores_draft}" varStatus="status">
			
			switch(contador){
				case 1: selJugador1.value = '${fila.codigoJugador}'; 
				break;
				case 2: selJugador2.value = '${fila.codigoJugador}'; 
				break;
				case 3: selJugador3.value = '${fila.codigoJugador}'; 
				break;
				case 4: selJugador4.value = '${fila.codigoJugador}'; 
				break;
				case 5: selJugador5.value = '${fila.codigoJugador}'; 
				break;
				case 6: selJugador6.value = '${fila.codigoJugador}'; 
				break;
				case 7: selJugador7.value = '${fila.codigoJugador}'; 
				break;
				case 8: selJugador8.value = '${fila.codigoJugador}'; 
				break;
				case 9: selJugador9.value = '${fila.codigoJugador}'; 
				break;
				case 10: selJugador10.value = '${fila.codigoJugador}'; 
				break;

			}

			contador++;
			
		</c:forEach>
		/*
		<c:forEach var="fila" items="${draft_jugadores_draft}" varStatus="status">

		<select name="selJugador1" id="selJugador1">
				
					<option value=${fila.codigoJugador}>${fila.codigoJugador}</option>
				
		</select> 	
		<c:out value="${fila.codigoJugador}" />
		
		</c:forEach>
	*/
//alert("ivan1");
//selJugador1.value="A0R";
	}


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
	
	<input type="hidden" name="loginUsuario" value="<%=loginUsuario%>" />
	<input type="hidden" name="codLiga" value="<%=codLiga%>" />
	<input type="hidden" name="codEquipo" value="<%=codEquipo%>" />
	<input type="hidden" name="dineroDisponible" value="<%=dineroDisponible%>" />
	
	<div id="cont">
	

 		<jsp:include page="PlantillaCabecera.jsp" />
	
	

		<div id="contenido">
		
			<% if (identificado.equalsIgnoreCase(Ctes.SI)){ %>

				<% if (!loginUsuario.equalsIgnoreCase(ConstantesSesion.LOGIN_USUARIO)){ %>

					<div id="menuPeticion">
					
<%-- 						<h3><b><%=nbLiga%></b></h3> --%>
<%-- 						<h3><b><%=nbEquipo%>, dinero disponible <%=dineroDisponible%></b></h3> --%>



						<!-- SE CARGAN LOS COMBOS PARA LA SELECCION -->
						<!-- SE CARGAN LOS COMBOS PARA LA SELECCION -->
						<!-- SE CARGAN LOS COMBOS PARA LA SELECCION -->
						<!-- SE CARGAN LOS COMBOS PARA LA SELECCION -->
						<!-- SE CARGAN LOS COMBOS PARA LA SELECCION -->
						<!-- SE CARGAN LOS COMBOS PARA LA SELECCION -->
						
						<h3>Preseleccion de jugadores</h3>
						
						<table>
							

					
							<tr>
								<th>Jugador 1</th>
								<th>
									<select name="selJugador1" id="selJugador1">
										<option value="-1">Selecciona jugador</option>
										<c:forEach var="fila" items="${draft_jugadores_recuperados_sin_equipo}" varStatus="status">
											<option value=${fila.codigoJugador}>${fila.apellidos}, ${fila.nombre}  (${fila.puesto}) - ${fila.dinero} &#8364;</option>
										</c:forEach>
									</select> 		
								</th>		
								<th>Jugador 2</th>
								<th>
									<select name="selJugador2" id="selJugador2">
										<option value="-1">Selecciona jugador</option>
										<c:forEach var="fila" items="${draft_jugadores_recuperados_sin_equipo}" varStatus="status">
											<option value=${fila.codigoJugador}>${fila.apellidos}, ${fila.nombre}  (${fila.puesto}) - ${fila.dinero} &#8364;</option>
										</c:forEach>

									</select> 		
								</th>
							</tr>
	
							<tr>
								<th>Jugador 3</th>
								<th>
									<select name="selJugador3" id="selJugador3">
										<option value="-1">Selecciona jugador</option>
										<c:forEach var="fila" items="${draft_jugadores_recuperados_sin_equipo}" varStatus="status">
											<option value=${fila.codigoJugador}>${fila.apellidos}, ${fila.nombre}  (${fila.puesto}) - ${fila.dinero} &#8364;</option>
										</c:forEach>

									</select> 		
								</th>	
								<th>Jugador 4</th>
								<th>
									<select name="selJugador4" id="selJugador4">
										<option value="-1">Selecciona jugador</option>
										<c:forEach var="fila" items="${draft_jugadores_recuperados_sin_equipo}" varStatus="status">
											<option value=${fila.codigoJugador}>${fila.apellidos}, ${fila.nombre}  (${fila.puesto}) - ${fila.dinero} &#8364;</option>
										</c:forEach>

									</select> 		
								</th>
							</tr>	
		

							
							<tr>
								<th>Jugador 5</th>
								<th>
									<select name="selJugador5" id="selJugador5">
										<option value="-1">Selecciona jugador</option>
										<c:forEach var="fila" items="${draft_jugadores_recuperados_sin_equipo}" varStatus="status">
											<option value=${fila.codigoJugador}>${fila.apellidos}, ${fila.nombre}  (${fila.puesto}) - ${fila.dinero} &#8364;</option>
										</c:forEach>

									</select> 		
								</th>	
								<th>Jugador 6</th>
								<th>
									<select name="selJugador6" id="selJugador6">
										<option value="-1">Selecciona jugador</option>
										<c:forEach var="fila" items="${draft_jugadores_recuperados_sin_equipo}" varStatus="status">
											<option value=${fila.codigoJugador}>${fila.apellidos}, ${fila.nombre}  (${fila.puesto}) - ${fila.dinero} &#8364;</option>
										</c:forEach>

									</select> 		
								</th>	
							</tr>	
							

							<tr>
								<th>Jugador 7</th>
								<th>
									<select name="selJugador7" id="selJugador7">
										<option value="-1">Selecciona jugador</option>
										<c:forEach var="fila" items="${draft_jugadores_recuperados_sin_equipo}" varStatus="status">
											<option value=${fila.codigoJugador}>${fila.apellidos}, ${fila.nombre}  (${fila.puesto}) - ${fila.dinero} &#8364;</option>
										</c:forEach>

									</select> 		
								</th>
								<th>Jugador 8</th>
								<th>
									<select name="selJugador8" id="selJugador8">
										<option value="-1">Selecciona jugador</option>
										<c:forEach var="fila" items="${draft_jugadores_recuperados_sin_equipo}" varStatus="status">
											<option value=${fila.codigoJugador}>${fila.apellidos}, ${fila.nombre}  (${fila.puesto}) - ${fila.dinero} &#8364;</option>
										</c:forEach>

									</select> 		
								</th>	
							</tr>	
							<tr>
								<th>Jugador 9</th>
								<th>
									<select name="selJugador9" id="selJugador9">
										<option value="-1">Selecciona jugador</option>
										<c:forEach var="fila" items="${draft_jugadores_recuperados_sin_equipo}" varStatus="status">
											<option value=${fila.codigoJugador}>${fila.apellidos}, ${fila.nombre}  (${fila.puesto}) - ${fila.dinero} &#8364;</option>
										</c:forEach>

									</select> 		
								</th>
								<th>Jugador 10</th>
								<th>
									<select name="selJugador10" id="selJugador10">
										<option value="-1">Selecciona jugador</option>
										<c:forEach var="fila" items="${draft_jugadores_recuperados_sin_equipo}" varStatus="status">
											<option value=${fila.codigoJugador}>${fila.apellidos}, ${fila.nombre}  (${fila.puesto}) - ${fila.dinero} &#8364;</option>
										</c:forEach>

									</select> 		
								</th>	
							</tr>	
							
							<tr>
								<th colspan="4">									
									<input align="right" type="button" name="botonAceptarJug" value="Confirmar Selecci&oacute;n de Draft" 
										maxlength="10" size="10" class="input" 
										onclick="doConfirmarDraft('<%=codLiga%>','<%=codEquipo%>',selJugador1.value,selJugador2.value,selJugador3.value,selJugador4.value,selJugador5.value,selJugador6.value,selJugador7.value,selJugador8.value,selJugador9.value,selJugador10.value);" />
								</th>
<!-- 								<th> -->
<!-- 				  					<ul id="icons" class="ui-widget ui-helper-clearfix"> -->
<!-- 			  							<li class="ui-state-default ui-corner-all" title="refrescar" onclick="doRefrescarDraft();"><span class="ui-icon ui-icon-refresh"></span></li> -->
<!-- 				  					</ul> -->
<!-- 				  				</th>								 -->
							</tr>
							
						</table>
	
						
						<!-- VISUALIZACION DE LAS ELECCIONES DEL DRAFT -->
						<!-- VISUALIZACION DE LAS ELECCIONES DEL DRAFT -->
						<!-- VISUALIZACION DE LAS ELECCIONES DEL DRAFT -->
						<!-- VISUALIZACION DE LAS ELECCIONES DEL DRAFT -->
						<!-- HACERLOS CON HORARIOS -->
						
						
						
						<!-- TODAS LAS ELECCIONES DEL DRAFT -->
						<!-- TODAS LAS ELECCIONES DEL DRAFT -->
						<!-- TODAS LAS ELECCIONES DEL DRAFT -->
						<!-- TODAS LAS ELECCIONES DEL DRAFT -->
						<!-- TODAS LAS ELECCIONES DEL DRAFT -->
						<!-- TODAS LAS ELECCIONES DEL DRAFT -->
						<!-- HACERLO -->
						
						
						
						<!-- Se cargan los jugadores guardados para seleccion de draft -->
						<!-- OJO, esto va al final -->
						<script>
							cargarJugadoresDraft();
						</script>


					
					</div>
				<% }else{ %>

					<div id="menuPeticion">
						<!-- Se redigira a error... -->
						<%
							objSesion.setAttribute(ConstantesSesion.OPERACION_ERROR,"Draft.");
							objSesion.setAttribute(ConstantesSesion.DETALLE_ERROR," No se puede acceder al Draft sin estar logeado.");
						%>		
								
						<h3>Error producido en el Draft</h3>
						
						<table>
							<tr>
								<td>
									<input type="button" name="botonError" value="Ver Detalle" 
											maxlength="10" size="10" class="input" 
											onclick="javascript:redireccionarAError('<%=objSesion.getAttribute(ConstantesSesion.OPERACION_ERROR)%>',
																					'<%=objSesion.getAttribute(ConstantesSesion.DETALLE_ERROR)%>');" />
								
								</td>
							</tr>						
						</table>
					</div>	
				
				<% } %>
				
			<% }else{ %>
			
				<div id="menuPeticion">
					<!-- Se redigira a error... -->
					<%
						objSesion.setAttribute(ConstantesSesion.OPERACION_ERROR,"Draft.");
						objSesion.setAttribute(ConstantesSesion.DETALLE_ERROR," No se puede acceder a la opcion de menu sin estar identificado.");
					%>		
							
					<h3>Error producido en el Draft</h3>
					
					<table>
						<tr>
							<td>
								<input type="button" name="botonError" value="Ver Detalle" 
										maxlength="10" size="10" class="input" 
										onclick="javascript:redireccionarAError('<%=objSesion.getAttribute(ConstantesSesion.OPERACION_ERROR)%>',
																				'<%=objSesion.getAttribute(ConstantesSesion.DETALLE_ERROR)%>');" />
							
							</td>
						</tr>						
					</table>	
				</div>
			<% } %>
		

		</div>

	
	</div>
	
	
	
<%-- 	<jsp:include page="PlantillaPiePagina.jsp" /> --%>
	


<!-- <form name="formConfirmarLiga" action="crearLiga" method="POST"> -->
<!--  	<input type="hidden" name="operacion" value="CREAR_LIGA" /> -->

<!-- 	<input type="hidden" name="nbLiga" value="" /> -->
<!-- 	<input type="hidden" name="pwdLiga" value="" /> -->
<!-- 	<input type="hidden" name="pwd2Liga" value="" /> -->
<!-- 	<input type="hidden" name="chPublica" value="" /> -->
	
<!-- </form> -->
  	

</body>
</html>
