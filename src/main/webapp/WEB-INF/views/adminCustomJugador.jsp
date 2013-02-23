<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.personal.basket.ctes.*" %>

	
<%
HttpSession objSesion = request.getSession(false);
String identificadoAdministrador = (String)objSesion.getAttribute(ConstantesSesion.ADMINISTRADOR);
if (identificadoAdministrador == null || identificadoAdministrador.length()<1){
	identificadoAdministrador = Ctes.NO;
}

%>



<html>
   	
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Gestion de Jugadores</title>

	<link media="screen" href="css/estilos.css" type="text/css" rel="stylesheet"/>
	<link media="screen" href="css/gmail.css" type="text/css" rel="stylesheet"/>
	<link href="css/redmond/jquery-ui-1.9.0.custom.css" type="text/css" rel="stylesheet">
	<script src="js/jquery-1.8.2.js"></script>
	<script src="js/jquery-ui-1.9.0.custom.js"></script>
	<script type="text/javascript" src="js/jquery.tablednd.0.7.min.js"></script>
	<script type="text/javascript" src="js/error.js"></script>
	
<script language="JavaScript">

	function CambiarEstadoJugador(codigoJugador, estado){
		
		if (estado == 'S')
			alert("HACERLO: Al realizar el cambio de estado sobre el CodigoJugador:" + codigoJugador + " y el estado:" + estado + " el jugador sera dado de baja y los players que lo tengan podran venderlo sin penalizacion.");
		else
			alert("HACERLO: Al realizar el cambio de estado sobre el CodigoJugador:" + codigoJugador + " y el estado:" + estado + " el jugador sera dado de alta y se establecera un precio para el. Quien lo tuviera tendra penalizacion en la venta otra vez.");
		
		document.formCambiarEstadoJugadores.codigoJugador.value=codigoJugador;
		document.formCambiarEstadoJugadores.estado.value=estado;
		document.formCambiarEstadoJugadores.submit();

	}
	
	/*
	function filtrar(selValor){
		
		//var selEstado=document.getElementById('selEstado');
		//alert(selEstado);
		alert("asdf:" + selValor);
		
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
		
			<% if (identificadoAdministrador.equalsIgnoreCase(Ctes.SI)){ %>
			
				<div id="menuPeticion">
					
				<h3><b>Jugadores</b></h3>
				
				
<!-- 				<table> -->
						
<!-- 					<tr><td>Filtro Estado</td></tr> -->
											
<!-- 					<tr> -->
<!-- 						<td> -->
<!-- 							<select name="selEstado" id="selEstado" onchange="filtrar(this.value);"> -->
<!-- 								<option value="T">Todos</option> -->
<!-- 								<option value="S">Activos</option> -->
<!-- 								<option value="N">Desactivados</option> -->
<!-- 							</select>  -->
<!-- 						</td> -->
<!-- 					</tr> -->
					
<!-- 				</table> -->
				
				
				<table border="2">
					<thead>
						<tr>
							<th width="5%" scope="col">Cod</th>
							<th width="15%" scope="col">Nombre</th>
							<th width="30%" scope="col">Apellidos</th>
							<th width="6%" scope="col">Pos</th>
							<th width="6%" scope="col">Alt.</th>
							<th width="6%" scope="col">Nac.</th>
							<th width="5%" scope="col">F.Alta</th>
							<th width="19%" scope="col">Precio</th>
							<th width="4%" scope="col">Act</th>
							<th width="4%" scope="col">Estado</th>
						</tr>
					</thead>
				
					<tbody>
					
						
						<c:forEach var="fila" items="${OpcionAdminJugador}" varStatus="status">
						
							<c:set var="nContador" value="${status.count}"></c:set>
							
						
							<c:if test="${nContador % 2 == 0}">
							  <tr>
							</c:if>			
							<c:if test="${nContador % 2 == 1}">
							  <tr class="odd">
							</c:if>					
								<td align="center" scope="row">${fila.value.codigoJugador}</td>							
								<td>${fila.value.nombre}</td>
								<td>${fila.value.apellidos}</td>
								<td>${fila.value.puesto}</td>
								<td>${fila.value.altura}</td>
								<td>${fila.value.codigoNacionalidad}</td>
								<td>${fila.value.fecaltaMostrar}</td>
<!-- 								<td><a href="http://www.adobati.it/labs/CSSTable/0m4r.table.css" title="Download the rows table template CSS file">Download</a></td> -->

								<td align="center">${fila.value.precio} &#8364;</td>
								<td align="center">${fila.value.activo}</td>
	
					  			<td>
					  				<ul id="icons" class="ui-widget ui-helper-clearfix">
				  					<li class="ui-state-default ui-corner-all" title="DesActivar/Activar Jugador" onclick="CambiarEstadoJugador('${fila.value.codigoJugador}','${fila.value.activo}');"><span class="ui-icon ui-icon-circle-close"></span></li>
					  				</ul>
					  			</td>					  			
							</tr>

						</c:forEach>
					</tbody>
					
									
				
					<tfoot>
						<tr>
							<th scope="row" colspan="10" align="right">Total: <c:out value="${nContador} jugadores"/><p></th>
						</tr>
					</tfoot>
				

					
				</table>
				</div>
			

				
				
			<% }else{ %>
			
				<div id="menuPeticion">
					<!-- Se redigira a error... -->
					<%
						objSesion.setAttribute(ConstantesSesion.OPERACION_ERROR,"Administracion de Custom Jugador.");
						objSesion.setAttribute(ConstantesSesion.DETALLE_ERROR," No se puede acceder a la opcion de menu sin estar identificado como Administrador.");
					%>		
							
					<h3>Error producido en la administracion Custom Jugador</h3>
					
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
	
			
<form name="formCambiarEstadoJugadores" action="adminCustomJugador" method="POST">
 	<input type="hidden" name="operacion" value="CUSTOMJUGADOR_ADMIN_CAMBIO_ESTADO" />
 	<input type="hidden" name="codigoJugador" value="" />
	<input type="hidden" name="estado" value="" />
</form>	


  	

</body>
</html>
