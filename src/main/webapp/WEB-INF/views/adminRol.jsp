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
<title>Administrador de Roles</title>

	<link media="screen" href="css/estilos.css" type="text/css" rel="stylesheet"/>
	<link href="css/redmond/jquery-ui-1.9.0.custom.css" type="text/css" rel="stylesheet">
	<script src="js/jquery-1.8.2.js"></script>
	<script src="js/jquery-ui-1.9.0.custom.js"></script>
	<script type="text/javascript" src="js/jquery.tablednd.0.7.min.js"></script>
	<script type="text/javascript" src="js/error.js"></script>
	
<script language="JavaScript">

		
	function modificarRol(codRol){

		var Modifica=document.getElementById("ModificaRolDesc"+codRol);
		
		//alert("texto nuevo:" + Modifica.value);
		
		if (Modifica.value == ''){
			alert("El campo del valor a modificar debe ir relleno");
			Modifica.focus();
			return;
		}	
		
		document.formModificarRol.codRol.value=codRol;
		document.formModificarRol.descRol.value=Modifica.value;
		
		document.formModificarRol.submit();			

	}
	
	function insertarRol(){
		

		if (InsertaCodRol.value == ''){
			alert("El campo del codigo es obligatorio.");
			InsertaCodRol.focus();
			return;
		}		
		if (InsertaDescRol.value == ''){
			alert("El campo de la descripcion es obligatorio.");
			InsertaDescRol.focus();
			return;
		}				
			
		document.formInsertarRol.codRol.value=InsertaCodRol.value;
		document.formInsertarRol.descRol.value=InsertaDescRol.value;

		document.formInsertarRol.submit();			
		
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
				
					<h3>Modificacion de Roles</h3>
					
					<table>
					
						<tr>
							<th>Codigo</th>
							<th>Descripcion</th>
						</tr>
						
						<c:forEach var="fila" items="${OpcionAdminRoles}" varStatus="status">

							<tr>
								<td>${fila.value.codigoRole}</td>
								
								<td>
									<input type="text" name="ModificaRolDesc${fila.value.codigoRole}" id="ModificaRolDesc${fila.value.codigoRole}" value="${fila.value.descripcion}" maxlength="100" size="30" class="input" />
								</td>
					  			<td>
					  				<ul id="icons" class="ui-widget ui-helper-clearfix">
				  					<li class="ui-state-default ui-corner-all" title="modificar" onclick="modificarRol('${fila.value.codigoRole}');"><span class="ui-icon ui-icon-circle-check"></span></li>
					  				</ul>
					  			</td>
								
							</tr>
						
						</c:forEach>
													
					</table>
					
					<h3>Insertar un Role</h3>
					<table>
						<tr>
							<th>Codigo</th>
							<th>Descripcion</th>
						</tr>					
						<tr>

							<td>
								<input type="text" name="InsertaCodRol" id="InsertaCodRol" value="" maxlength="3" size="6" class="input" />
							</td>							
							<td>
								<input type="text" name="InsertaDescRol" id="InsertaDescRol" value="" maxlength="100" size="30" class="input" />
							</td>
				  			<td>
				  				<ul id="icons" class="ui-widget ui-helper-clearfix">
			  					<li class="ui-state-default ui-corner-all" title="insertar" onclick="insertarRol();"><span class="ui-icon ui-icon-circle-check"></span></li>
				  				</ul>
				  			</td>
								
							</tr>
					</table>			
													
				</div>
				
			<% }else{ %>
			
				<div id="menuPeticion">
					<!-- Se redigira a error... -->
					<%
						objSesion.setAttribute(ConstantesSesion.OPERACION_ERROR,"Administracion de Roles.");
						objSesion.setAttribute(ConstantesSesion.DETALLE_ERROR," No se puede acceder a la opcion de menu sin estar identificado como Administrador.");
					%>		
							
					<h3>Error producido en administracion de Roles</h3>
					
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

	
<form name="formModificarRol" action="adminRol" method="POST">
	<input type="hidden" name="operacion" value="CONFIG_ROL_CONFIRMAR_MODIFICAR" />
	
	<input type="hidden" name="codRol" value="" />
	<input type="hidden" name="descRol" value="" />
	
</form>

<form name="formInsertarRol" action="adminRol" method="POST">
	<input type="hidden" name="operacion" value="CONFIG_ROL_CONFIRMAR_INSERTAR" />
	
	<input type="hidden" name="codRol" value="" />
	<input type="hidden" name="descRol" value="" />
	
</form>




</body>
</html>
