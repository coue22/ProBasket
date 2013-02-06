<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.personal.basket.ctes.*" %>

	
<%
HttpSession objSesion = request.getSession(false);
String identificadoAdministrador = (String)objSesion.getAttribute(ConstantesSesion.ADMINISTRADOR);
if (identificadoAdministrador == null || identificadoAdministrador.length()<1){
	identificadoAdministrador = Ctes.NO;
}

/*
String debeModificaEquipo = (String)request.getAttribute(Ctes.EQUIPO_ADMIN_MOSTRAR_MODIFICAR);
if (debeModificaEquipo == null || debeModificaEquipo.length()<1){
	debeModificaEquipo = Ctes.NO;
}


String ModificaEquipoID = (String)request.getAttribute(Ctes.EQUIPO_ADMIN_MOSTRAR_MODIFICAR_ID);
String ModificaEquipoNB = (String)request.getAttribute(Ctes.EQUIPO_ADMIN_MOSTRAR_MODIFICAR_NB);
String ModificaEquipoSG = (String)request.getAttribute(Ctes.EQUIPO_ADMIN_MOSTRAR_MODIFICAR_SG);


String debeInsertarEquipo = (String)request.getAttribute(Ctes.EQUIPO_ADMIN_MOSTRAR_INSERTAR);
if (debeInsertarEquipo == null || debeInsertarEquipo.length()<1){
	debeInsertarEquipo = Ctes.NO;
}
*/


%>



<html>
   	
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Administrador de Operaciones</title>

	<link media="screen" href="css/estilos.css" type="text/css" rel="stylesheet"/>
	<link href="css/redmond/jquery-ui-1.9.0.custom.css" type="text/css" rel="stylesheet">
	<script src="js/jquery-1.8.2.js"></script>
	<script src="js/jquery-ui-1.9.0.custom.js"></script>
	<script type="text/javascript" src="js/jquery.tablednd.0.7.min.js"></script>
	
<script language="JavaScript">

		
	function modificarOperacion(codOpera){

		var Modifica=document.getElementById("ModificaOperaDesc"+codOpera);
		
		//alert("texto nuevo:" + Modifica.value);
		
		if (Modifica.value == ''){
			alert("El campo del valor a modificar debe ir relleno");
			Modifica.focus();
			return;
		}	
		
		document.formModificarOperacion.codOpera.value=codOpera;
		document.formModificarOperacion.descOpera.value=Modifica.value;
		
		document.formModificarOperacion.submit();			

	}
	
	function insertarOperacion(){
		

		if (InsertaDescOperacion.value == ''){
			alert("El campo de la descripcion es obligatorio.");
			InsertaDescOperacion.focus();
			return;
		}		
			
		document.formInsertarOperacion.descOpera.value=InsertaDescOperacion.value;
		
		
		document.formInsertarOperacion.submit();			
		
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
				
					<h3>Modificacion de Operaciones</h3>
					
					<table>
					
						<tr>
							<th>Codigo</th>
							<th>Descripcion</th>
						</tr>
						
						<c:forEach var="fila" items="${OpcionAdminOperacion}" varStatus="status">

							<tr>
								<td>${fila.value.codigoOpera}</td>
								
								<td>
									<input type="text" name="ModificaOperaDesc${fila.value.codigoOpera}" id="ModificaOperaDesc${fila.value.codigoOpera}" value="${fila.value.descripcion}" maxlength="100" size="30" class="input" />
								</td>
					  			<td>
					  				<ul id="icons" class="ui-widget ui-helper-clearfix">
				  					<li class="ui-state-default ui-corner-all" title="modificar" onclick="modificarOperacion('${fila.value.codigoOpera}');"><span class="ui-icon ui-icon-circle-check"></span></li>
					  				</ul>
					  			</td>
								
							</tr>
						
						</c:forEach>
													
					</table>
					
					<h3>Insertar una operacion</h3>
					<table>
						<tr>
							<th>Desc. Operacion</th>
						</tr>					
						<tr>

							
							<td>
								<input type="text" name="InsertaDescOperacion" id="InsertaDescOperacion" value="" maxlength="100" size="30" class="input" />
							</td>
				  			<td>
				  				<ul id="icons" class="ui-widget ui-helper-clearfix">
			  					<li class="ui-state-default ui-corner-all" title="insertar" onclick="insertarOperacion();"><span class="ui-icon ui-icon-circle-check"></span></li>
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

	
<form name="formModificarOperacion" action="adminOperacion" method="POST">
	<input type="hidden" name="operacion" value="CONFIG_OPERA_CONFIRMAR_MODIFICAR" />
	
	<input type="hidden" name="codOpera" value="" />
	<input type="hidden" name="descOpera" value="" />
	
</form>

<form name="formInsertarOperacion" action="adminOperacion" method="POST">
	<input type="hidden" name="operacion" value="CONFIG_OPERA_CONFIRMAR_INSERTAR" />
	
	<input type="hidden" name="descOpera" value="" />
	
</form>




</body>
</html>
