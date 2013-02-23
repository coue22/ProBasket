<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.personal.basket.ctes.*" %>

	
<%
HttpSession objSesion = request.getSession(false);
String identificadoAdministrador = (String)objSesion.getAttribute(ConstantesSesion.ADMINISTRADOR);
if (identificadoAdministrador == null || identificadoAdministrador.length()<1){
	identificadoAdministrador = Ctes.NO;
}




String nbFicheroAInsertar = (String)request.getAttribute(Ctes.JUGADOR_ADMIN_JUGADORES_FICHERO);
if (nbFicheroAInsertar == null || nbFicheroAInsertar.length()<1){
	nbFicheroAInsertar = "";
}

String bJugadoresInsertados = (String)request.getAttribute(Ctes.JUGADOR_ADMIN_JUGADORES_INSERTADOS);
if (bJugadoresInsertados == null || bJugadoresInsertados.length()<1){
	bJugadoresInsertados = Ctes.NO;
}


%>



<html>
   	
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Insertar de Jugadores</title>

	<link media="screen" href="css/estilos.css" type="text/css" rel="stylesheet"/>
	<link href="css/redmond/jquery-ui-1.9.0.custom.css" type="text/css" rel="stylesheet">
	<script src="js/jquery-1.8.2.js"></script>
	<script src="js/jquery-ui-1.9.0.custom.js"></script>
	<script type="text/javascript" src="js/jquery.tablednd.0.7.min.js"></script>
	<script type="text/javascript" src="js/error.js"></script>
	
<script language="JavaScript">

	function insertarJugadores(bInsertar, fich){
		

		if (fich == ''){
			alert("El nombre del fichero debe ir relleno.");
			NbFicheroJugadores.focus();
			return;
		}

		document.formInsertarJugadores.nbFicheroJugadores.value=fich;
		document.formInsertarJugadores.bInsertar.value=bInsertar;
		document.formInsertarJugadores.submit();			
		
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
	
	<div id="cont">
	

 		<jsp:include page="PlantillaCabecera.jsp" />
	
	

		<div id="contenido">
		
			<% if (identificadoAdministrador.equalsIgnoreCase(Ctes.SI)){ %>
			

				<div id="menuPeticion">
					
					<h3>Insertar fichero jugadores</h3>
					
					<table>
						<tr>
							<th colspan="2" align="left">Nombre del fichero</th>
						</tr>					
						<tr>
							
							<td>
								<input type="text" name="NbFicheroJugadores" id="NbFicheroJugadores" value="" maxlength="100" size="35" class="input" />
							</td>
				  			<td>
				  				<ul id="icons" class="ui-widget ui-helper-clearfix">
			  					<li class="ui-state-default ui-corner-all" title="insertar jugadores" onclick="insertarJugadores('N', NbFicheroJugadores.value);"><span class="ui-icon ui-icon-circle-check"></span></li>
				  				</ul>
				  			</td>								
						</tr>
						
<!-- http://viralpatel.net/blogs/spring-mvc-multiple-file-upload-example/ -->

<!-- <tr> -->
<!-- 	<td> -->

<!-- 		<form action="adminJugadores" method="POST" enctype="multipart/form-data"> -->
<!-- 		    <input type="file" name="NbFicheroJugadores" id="NbFicheroJugadores" class="input"/> -->
<!-- 		    <input type="hidden" name="bInsertar" value="N" /> -->
<!-- 		    <input type="hidden" name="operacion" value="JUGADOR_ADMIN_INSERTAR" /> -->
<!-- 		    <input type="submit" /> -->
<!-- 		</form>	 -->
<!-- 	</td>				 -->
<!-- </tr>							 -->
					</table>	
					
					
					
					<table>
						<tr>
							<td>
								<span class="resaltarTorcido">
									Un ejemplo de nombre de fichero: 012jugadores.txt o 006_2fich.txt
								</span>	
							</td>
						</tr>
						<tr>
							<td>
								<span class="resaltarTorcido">
									El fichero debe existir en el directorio resources/jugadores y comenzar por un codigo de equipo real
								</span>	
							</td>							
						</tr>						
					</table>		
					
					<!-- Solo si los jugadores no se han insertado -->
					<% if (bJugadoresInsertados.equalsIgnoreCase(Ctes.NO)){ %>
					
						<!-- Solo si esta variable viene rellena se presentan los jugadores a insertar. -->
						<% if (!(nbFicheroAInsertar.equalsIgnoreCase(""))){ %>
							<h3>Jugadores a Insertar</h3>
							<table>
							
								<tr>
									<th>Codigo</th>
									<th>Nombre</th>
									<th>Apellidos</th>
									<th>Posicion</th>
									<th>Altura</th>
									<th>Nacionalidad</th>
									<th>Precio</th>
		
								
								</tr>
								
								<c:forEach var="fila" items="${OpcionAdminJugador_para_insertar}" varStatus="status">
		
									<tr>
										<td>${fila.codigoJugador}</td>
										<td>${fila.nombre}</td>
										<td>${fila.apellidos}</td>
										<td>${fila.puesto}</td>
										<td>${fila.altura}</td>
										<td>${fila.codigoNacionalidad}</td>
										<td>${fila.precio}</td>
		
									</tr>
								
								</c:forEach>
								
								<tr>
						  			<td>
						  				<ul id="icons" class="ui-widget ui-helper-clearfix">
					  					<li class="ui-state-default ui-corner-all" title="insertar jugadores" onclick="insertarJugadores('S','<%=nbFicheroAInsertar%>');"><span class="ui-icon ui-icon-circle-check"></span></li>
						  				</ul>
						  			</td>
						  		</tr>						
															
							</table>
						
						<%}%>	
										
					<% } else {%>
					
						<table>
							<tr>
								<td>
									<span class="resaltarTorcidoExito">
										Se han insertado de forma correcta los datos del fichero <%=nbFicheroAInsertar%>
									</span>	
								</td>							
							</tr>						
						</table>

					<%}%>	
			
													
				</div>	
				
				
			<% }else{ %>
			
				<div id="menuPeticion">
					<!-- Se redigira a error... -->
					<%
						objSesion.setAttribute(ConstantesSesion.OPERACION_ERROR,"Administracion de Jugadores.");
						objSesion.setAttribute(ConstantesSesion.DETALLE_ERROR," No se puede acceder a la opcion de menu sin estar identificado como Administrador.");
					%>		
							
					<h3>Error producido en administracion de jugadores</h3>
					
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
	
<form name="formInsertarJugadores" action="adminJugadores" method="POST">
 	<input type="hidden" name="operacion" value="JUGADOR_ADMIN_INSERTAR" />
 	<input type="hidden" name="bInsertar" value="" />
	<input type="hidden" name="nbFicheroJugadores" value="" />
</form>	


  	

</body>
</html>
