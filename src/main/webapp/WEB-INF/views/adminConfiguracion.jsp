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
<title>Administrador de Configuracion</title>

	<link media="screen" href="css/estilos.css" type="text/css" rel="stylesheet"/>
	<link href="css/redmond/jquery-ui-1.9.0.custom.css" type="text/css" rel="stylesheet">
	<script src="js/jquery-1.8.2.js"></script>
	<script src="js/jquery-ui-1.9.0.custom.js"></script>
	<script type="text/javascript" src="js/jquery.tablednd.0.7.min.js"></script>
	
<script language="JavaScript">

		
	function modificarConfiguracion(parametro){
		
		//alert("Configuracion a modificar: " + parametro + "-" + ModificaConfigValor.value);

		var Modifica=document.getElementById("ModificaConfigValor"+parametro);
		
		//alert("Configuracion a modificar: " + parametro + "-" + Modifica.value);

		
		if (Modifica.value == ''){
			alert("El campo del valor a modificar debe ir relleno");
			Modifica.focus();
			return;
		}	
		
		document.formModificarConfiguracion.parametro.value=parametro;
		document.formModificarConfiguracion.valor.value=Modifica.value;
		
		document.formModificarConfiguracion.submit();			
		
	}
	
	function insertarConfiguracion(){
		
		//alert("Configuracion a modificar: " + parametro + "-" + ModificaConfigValor.value);
		if (InsertaConfigParametro.value == ''){
			alert("El campo del parametro debe ir relleno");
			InsertaConfigParametro.focus();
			return;
		}
		if (InsertaConfigValor.value == ''){
			alert("El campo del valor debe ir relleno");
			InsertaConfigValor.focus();
			return;
		}		
			
		document.formInsertarConfiguracion.parametro.value=InsertaConfigParametro.value;
		document.formInsertarConfiguracion.valor.value=InsertaConfigValor.value;
		
		document.formInsertarConfiguracion.submit();			
		
	}
	
	
	
/*
	function modificarEquipo(idEquipoModificar, equipoNombre, equipoSiglas){
		
		//alert("Equipo a Modificar:" + idEquipoModificar);
		
		document.formModificarEquipo.idEquipoModificar.value=idEquipoModificar;
		document.formModificarEquipo.equipoNombre.value=equipoNombre;
		document.formModificarEquipo.equipoSiglas.value=equipoSiglas;
		
		document.formModificarEquipo.submit();	
	}
	
	function confirmarModificarEquipo(idEquipoModificar)
	{
		// alert("Datos mofidicados - ID: " + idEquipoModificar);
		// alert("Datos mofidicados - NB: " + ModificaEquipoNBConfirmar.value);
		// alert("Datos mofidicados - SG: " + ModificaEquipoSGConfirmar.value);
		
		document.formConfirmarModificarEquipo.idEquipoModificar.value=idEquipoModificar;
		document.formConfirmarModificarEquipo.equipoNombre.value=ModificaEquipoNBConfirmar.value;
		document.formConfirmarModificarEquipo.equipoSiglas.value=ModificaEquipoSGConfirmar.value;
		document.formConfirmarModificarEquipo.submit();
	}	

	function insertarEquipo(idEquipoModificar)
	{
		document.formInsertarEquipo.submit();	
	}


	function confirmarInsertarEquipo()
	{
		document.formConfirmarInsertarEquipo.equipoNombre.value=InsertaEquipoNBConfirmar.value;
		document.formConfirmarInsertarEquipo.equipoSiglas.value=InsertaEquipoSGConfirmar.value;
		document.formConfirmarInsertarEquipo.submit();
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
				
					<h3>Configuracion de la aplicacion</h3>
					
					<table>
					
						<tr>
							<th>Parametro</th>
							<th>Valor</th>
						</tr>
						
						<c:forEach var="fila" items="${OpcionAdminConfiguracion}" varStatus="status">

							<tr>
								<td>${fila.value.parametro}</td>
								
								<td>
									<input type="text" name="ModificaConfigValor${fila.value.parametro}" id="ModificaConfigValor${fila.value.parametro}" value="${fila.value.valor}" maxlength="50" size="25" class="input" />
								</td>
					  			<td>
					  				<ul id="icons" class="ui-widget ui-helper-clearfix">
				  					<li class="ui-state-default ui-corner-all" title="modificar" onclick="modificarConfiguracion('${fila.value.parametro}');"><span class="ui-icon ui-icon-circle-check"></span></li>
					  				</ul>
					  			</td>
								
							</tr>
						
						</c:forEach>
													
					</table>
					
					<h3>Insertar un valor</h3>
					<table>
						<tr>
							<th>Parametro</th>
							<th>Valor</th>
						</tr>					
						<tr>
							<td>
								<input type="text" name="InsertaConfigParametro" id="InsertaConfigParametro" value="" maxlength="50" size="15" class="input" />
							</td>
							
							<td>
								<input type="text" name="InsertaConfigValor" id="InsertaConfigValor" value="" maxlength="50" size="15" class="input" />
							</td>
				  			<td>
				  				<ul id="icons" class="ui-widget ui-helper-clearfix">
			  					<li class="ui-state-default ui-corner-all" title="insertar" onclick="insertarConfiguracion();"><span class="ui-icon ui-icon-circle-check"></span></li>
				  				</ul>
				  			</td>
								
							</tr>
					</table>			
													
				</div>
				
			<% }else{ %>
			
				<!-- Se redigira a error... -->
				<%
					objSesion.setAttribute(ConstantesSesion.DETALLE_ERROR," No se puede acceder a la opcion de menu sin estar identificado como Administrador.");
				
					String redirectURL = "error";
					response.sendRedirect(redirectURL);
				%>
				
			<% } %>
		

		</div>

	
	</div>
	
	
<%-- 	<jsp:include page="PlantillaPiePagina.jsp" /> --%>

	
<form name="formModificarConfiguracion" action="adminConfiguracion" method="POST">
	<input type="hidden" name="operacion" value="CONFIG_ADMIN_CONFIRMAR_MODIFICAR" />
	
	<input type="hidden" name="parametro" value="" />
	<input type="hidden" name="valor" value="" />
	
</form>

<form name="formInsertarConfiguracion" action="adminConfiguracion" method="POST">
	<input type="hidden" name="operacion" value="CONFIG_ADMIN_CONFIRMAR_INSERTAR" />
	
	<input type="hidden" name="parametro" value="" />
	<input type="hidden" name="valor" value="" />
	
</form>


		



</body>
</html>
