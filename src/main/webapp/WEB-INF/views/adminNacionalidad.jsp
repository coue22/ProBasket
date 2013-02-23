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
<title>Administrador de Nacionalidades</title>

	<link media="screen" href="css/estilos.css" type="text/css" rel="stylesheet"/>
	<link href="css/redmond/jquery-ui-1.9.0.custom.css" type="text/css" rel="stylesheet">
	<script src="js/jquery-1.8.2.js"></script>
	<script src="js/jquery-ui-1.9.0.custom.js"></script>
	<script type="text/javascript" src="js/jquery.tablednd.0.7.min.js"></script>
	<script type="text/javascript" src="js/error.js"></script>
	
<script language="JavaScript">


	function eliminarConfiguracion(){
		
		var selNacion=document.getElementById('selNacion');
		
		document.formEliminarNacionalidad.codigoNacion.value=selNacion.value;
		
		document.formEliminarNacionalidad.submit();			
		
	}

	function insertarNacionalidad(){
		
		if (InsertaNacionalidadCod.value == ''){
			alert("El campo codigo debe ir relleno");
			InsertaNacionalidadCod.focus();
			return;
		}	
		if (InsertaNacionalidadDesc.value == ''){
			alert("El campo descripcion debe ir relleno");
			InsertaNacionalidadDesc.focus();
			return;
		}		
			
		document.formInsertarNacionalidad.codigoNacion.value=InsertaNacionalidadCod.value;
		document.formInsertarNacionalidad.descripcion.value=InsertaNacionalidadDesc.value;
		
		
		document.formInsertarNacionalidad.submit();			
		
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
				
					<h3>Nacionalidades</h3>
					
					<table>
					
						<tr>
							<th></th>
							<th></th>
						</tr>
						
						
						<tr>
							<td>
								<select name="selNacion" id="selNacion">
									<option value="-1">Seleccione nacionalidad</option>
									<c:forEach var="fila" items="${OpcionAdminNacionalidad}" varStatus="status">
										<option value=${fila.value.codigoNacionalidad}>${fila.value.descripcion}</option>
									</c:forEach>
								</select> 							
							</td>
				  			<td>
				  				<ul id="icons" class="ui-widget ui-helper-clearfix">
			  					<li class="ui-state-default ui-corner-all" title="Eliminar" onclick="eliminarConfiguracion('${fila.value.codigoNacionalidad}');"><span class="ui-icon ui-icon-circle-close"></span></li>
				  				</ul>
				  			</td>

						</tr>
						
					</table>
					
					<h3>Insertar una nacionalidad</h3>
					<table>
						<tr>
							<th>Nacionalidad</th>
						</tr>					
						<tr>
							<td>
								<input type="text" name="InsertaNacionalidadCod" id="InsertaNacionalidadCod" value="" maxlength="4" size="7" class="input" />
							</td>							
							<td>
								<input type="text" name="InsertaNacionalidadDesc" id="InsertaNacionalidadDesc" value="" maxlength="100" size="15" class="input" />
							</td>
				  			<td>
				  				<ul id="icons" class="ui-widget ui-helper-clearfix">
			  					<li class="ui-state-default ui-corner-all" title="insertar" onclick="insertarNacionalidad();"><span class="ui-icon ui-icon-circle-check"></span></li>
				  				</ul>
				  			</td>
								
							</tr>
					</table>			
													
				</div>
				
			<% }else{ %>
			
				<div id="menuPeticion">
					<!-- Se redigira a error... -->
					<%
						objSesion.setAttribute(ConstantesSesion.OPERACION_ERROR,"Administracion de Nacionalidades.");
						objSesion.setAttribute(ConstantesSesion.DETALLE_ERROR," No se puede acceder a la opcion de menu sin estar identificado como Administrador.");
					%>		
							
					<h3>Error producido en administracion de nacionalidades</h3>
					
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
	
	
<form name="formEliminarNacionalidad" action="adminNacionalidad" method="POST">
	<input type="hidden" name="operacion" value="CONFIG_NACIO_CONFIRMAR_ELIMINAR" />
	<input type="hidden" name="codigoNacion" value="" />
</form>
	
<form name="formInsertarNacionalidad" action="adminNacionalidad" method="POST">
	<input type="hidden" name="operacion" value="CONFIG_NACIO_CONFIRMAR_INSERTAR" />
	<input type="hidden" name="codigoNacion" value="" />
	<input type="hidden" name="descripcion" value="" />
</form>	



		



</body>
</html>
