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
<title>Insertar Jugador</title>

	<link media="screen" href="css/estilos.css" type="text/css" rel="stylesheet"/>
	<link href="css/redmond/jquery-ui-1.9.0.custom.css" type="text/css" rel="stylesheet">
	<script src="js/jquery-1.8.2.js"></script>
	<script src="js/jquery-ui-1.9.0.custom.js"></script>
	<script type="text/javascript" src="js/jquery.tablednd.0.7.min.js"></script>
	<script type="text/javascript" src="js/error.js"></script>
	
<script language="JavaScript">

	function insertarJugador(){
		
 		if (CodigoJugador.value == ''){
			alert("El codigo debe ir relleno.");
			CodigoJugador.focus();
			return;
		}

 		if (NombreJugador.value == ''){
			alert("El nombre debe ir relleno.");
			NombreJugador.focus();
			return;
		} 		
 		
 		if (ApellidoJugador.value == ''){
			alert("El Apellido debe ir relleno.");
			ApellidoJugador.focus();
			return;
		} 
 		
 		var PosicionJugador=document.getElementById('selPosicionJugador');
		if (PosicionJugador.value == "X"){
			alert("Debe seleccionar una posicion del jugador.");
			return;
		}

 		if (AlturaJugador.value == ''){
			alert("La altura debe ir relleno.");
			AlturaJugador.focus();
			return;
		} 
 		
 		var NacionalidadJugador=document.getElementById('selNacion');
 		if (NacionalidadJugador.value == '-1'){
			alert("La nacionalidad debe ir relleno.");
			NacionalidadJugador.focus();
			return;
		}  		
 		
 		if (PrecioJugadorEntero.value == ''){
			alert("El precio debe ir relleno.");
			PrecioJugadorEntero.focus();
			return;
		}  	
 		
 		if (PrecioJugadorDecimal.value == ''){
			alert("El precio debe ir relleno.");
			PrecioJugadorDecimal.focus();
			return;
		}  	
 		
 		var CodigoEquipoRealJugador=document.getElementById('selEquipoReal');
 		if (CodigoEquipoRealJugador.value == '-1'){
			alert("El equipo real debe ir relleno.");
			CodigoEquipoRealJugador.focus();
			return;
		}  		
 		
 		
		document.formInsertarJugador.CodigoJugador.value=CodigoJugador.value;
		document.formInsertarJugador.NombreJugador.value=NombreJugador.value;
		document.formInsertarJugador.ApellidoJugador.value=ApellidoJugador.value;
		document.formInsertarJugador.PosicionJugador.value=PosicionJugador.value;
		document.formInsertarJugador.AlturaJugador.value=AlturaJugador.value;
		document.formInsertarJugador.NacionalidadJugador.value=NacionalidadJugador.value;
		document.formInsertarJugador.PrecioJugadorEntero.value=PrecioJugadorEntero.value;
		document.formInsertarJugador.PrecioJugadorDecimal.value=PrecioJugadorDecimal.value;
		document.formInsertarJugador.CodigoEquipoRealJugador.value=CodigoEquipoRealJugador.value;

		
		document.formInsertarJugador.submit();			
		
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
					
					<h3>Insertar jugador</h3>

						
					<table>
							<tr>
								<td>Codigo</td>
								<td><input type="text" name="CodigoJugador" id="CodigoJugador" value="" maxlength="3" size="2" class="input" /></td>
							</tr>
							
							<tr>
								<td>Nombre</td>
								<td><input type="text" name="NombreJugador" id="NombreJugador" value="" maxlength="20" size="12" class="input" /></td>
							</tr>
							
							<tr>
								<td>Apellido</td>
								<td><input type="text" name="ApellidoJugador" id="ApellidoJugador" value="" maxlength="30" size="15" class="input" /></td>
							</tr>
							
							<tr>
								<td>Posicion</td>
<!-- 								<td><input type="text" name="PosicionJugador" id="PosicionJugador" value="" maxlength="1" size="1" class="input" /></td> -->
								<td>
									<select name="selPosicionJugador" id="selPosicionJugador" onchange="if (this.selectedIndex){ establecerPase(this.value); }">
										<option value="X">Selecciona Posicion</option>
										<option value="B">Base</option>
										<option value="A">Alero</option>
										<option value="P">Pivot</option>
									</select>
								</td> 
							</tr>
							
							<tr>
								<td>Altura</td>
								<td><input type="text" name="AlturaJugador" id="AlturaJugador" value="" maxlength="4" size="4" class="input" /> (Ejemplo: 1.98)	</td> 							
							</tr>
							
							<tr>
								<td>Nacionalidad</td>
<!-- 								<td><input type="text" name="NacionalidadJugador" id="NacionalidadJugador" value="" maxlength="3" size="4" class="input" /></td> -->
								<td>
									<select name="selNacion" id="selNacion">
										<option value="-1">Seleccione nacionalidad</option>
										<c:forEach var="fila" items="${OpcionAdminNacionalidad}" varStatus="status">
											<option value=${fila.value.codigoNacionalidad}>${fila.value.descripcion}</option>
										</c:forEach>
									</select> 							
								</td>								
							</tr>
							
							<tr>
								<td>Precio</td>
								<td>
									<input type="text" name="PrecioJugadorEntero" id="PrecioJugadorEntero" value="" maxlength="7" size="8" class="input" />
									,
									<input type="text" name="PrecioJugadorDecimal" id="PrecioJugadorDecimal" value="" maxlength="2" size="2" class="input" />
									&#8364; (No usar puntos para los miles)
								</td>
							</tr>
							
							<tr>
								<td>Equipo Real</td>
								<td>
									<select name="selEquipoReal" id="selEquipoReal">
										<option value="-1">Seleccione Equipo Real</option>
										<c:forEach var="fila" items="${OpcionAdminEquipo}" varStatus="status">
											<option value=${fila.value.codigoEquipoReal}>${fila.value.nombre}</option>
										</c:forEach>
									</select> 							
								</td>								
							</tr>
<!-- private String codigoEquipoReal; -->
<!-- 	private String nombre; -->
<!-- 	private String siglas; -->
<!-- 	private String activo; -->
<!-- 	private String patrocinio; -->
							
							<tr>
					  			<td>
									<input align="right" type="button" name="botonInsertarJug" value="Insertar" 
										maxlength="10" size="10" class="input" onclick="insertarJugador();" />
					  			
					  			</td>
					  		</tr>						
														
						</table>
							
							
				</div>	
				
				
			<% }else{ %>
			
				<div id="menuPeticion">
					<!-- Se redigira a error... -->
					<%
						objSesion.setAttribute(ConstantesSesion.OPERACION_ERROR,"Administracion de insertar un Jugador.");
						objSesion.setAttribute(ConstantesSesion.DETALLE_ERROR," No se puede acceder a la opcion de menu sin estar identificado como Administrador.");
					%>		
							
					<h3>Error producido en administracion de insertar un jugador</h3>
					
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
	
	
<form name="formInsertarJugador" action="adminInsertarJugador" method="POST">
 	
 	<input type="hidden" name="operacion" value="JUGADOR_ADMIN_INSERTAR_JUGADOR" />
	<input type="hidden" name="CodigoJugador" value="" />
	<input type="hidden" name="NombreJugador" value="" />
	<input type="hidden" name="ApellidoJugador" value="" />
	<input type="hidden" name="PosicionJugador" value="" />
	<input type="hidden" name="AlturaJugador" value="" />
	<input type="hidden" name="NacionalidadJugador" value="" />
	<input type="hidden" name="PrecioJugadorEntero" value="" />
	<input type="hidden" name="PrecioJugadorDecimal" value="" />
	<input type="hidden" name="CodigoEquipoRealJugador" value="" />
	

</form>	


  	

</body>
</html>
