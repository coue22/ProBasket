<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.personal.basket.ctes.*" %>

	
<%
HttpSession objSesion = request.getSession(false);
String identificadoAdministrador = (String)objSesion.getAttribute(ConstantesSesion.ADMINISTRADOR);
if (identificadoAdministrador == null || identificadoAdministrador.length()<1){
	identificadoAdministrador = Ctes.NO;
}

String debeModificaEquipo = (String)request.getAttribute(Ctes.EQUIPO_ADMIN_MOSTRAR_MODIFICAR);
if (debeModificaEquipo == null || debeModificaEquipo.length()<1){
	debeModificaEquipo = Ctes.NO;
}

/*
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
<title>Administrador de Equipos</title>

	<link media="screen" href="css/estilos.css" type="text/css" rel="stylesheet"/>
	<link href="css/redmond/jquery-ui-1.9.0.custom.css" type="text/css" rel="stylesheet">
	<script src="js/jquery-1.8.2.js"></script>
	<script src="js/jquery-ui-1.9.0.custom.js"></script>
	<script type="text/javascript" src="js/jquery.tablednd.0.7.min.js"></script>
	
<script language="JavaScript">

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
			

				<div class="tableDemo">
				<!-- <div id="debugArea" style="float: right">&nbsp;</div>"; -->
				<table id="table-2">
				
					<caption> JUGADORES </caption>
					
					<thead>
						<tr>
						<th></th>
						<th>Codigo</th>
						<th>Nombre</th>
						<th>Puesto</th>
						<th>Altura</th>
						<th>Peso</th>
						<th>Nacionalidad</th>
						</tr>
					</thead>
					<tbody>
					
					
					<c:forEach var="fila" items="${OpcionAdminJugador}" varStatus="status">
						
						
						
						<tr>
							<td>
								<ul id="icons" class="ui-widget ui-helper-clearfix">
				  					<li class="ui-state-default ui-corner-all" title="jug"><span class="ui-icon ui-icon-person"></span></li>
				  				</ul>
				  			</td>
				  
				  			<td>${fila.codigo}</td>
				  			<td>${fila.apellido}, ${fila.nombre} </td>
				  			<td>${fila.puesto}</td>
				  			<td>${fila.altura}</td>
				  			<td>${fila.peso}</td>
				  			<td>${fila.nacionalidad}</td>


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
					objSesion.setAttribute(ConstantesSesion.DETALLE_ERROR," No se puede acceder a la opcion de menu sin estar identificado como Administrador.");
				
					String redirectURL = "error";
					response.sendRedirect(redirectURL);
				%>
				
			<% } %>
		

		</div>

	
	</div>
	
	
	<jsp:include page="PlantillaPiePagina.jsp" />
	
	
<!-- <form name="formModificarEquipo" action="adminEquipos" method="POST"> -->
<!-- 	<input type="hidden" name="operacion" value="EQUIPO_ADMIN_MODIFICAR" /> -->
	
<!-- 	<input type="hidden" name="idEquipoModificar" value="" /> -->
<!-- 	<input type="hidden" name="equipoNombre" value="" /> -->
<!-- 	<input type="hidden" name="equipoSiglas" value="" /> -->
	
<!-- </form> -->

<!-- <form name="formConfirmarModificarEquipo" action="adminEquipos" method="POST"> -->
<!-- 	<input type="hidden" name="operacion" value="EQUIPO_ADMIN_CONFIRMAR_MODIFICAR" /> -->
	
<!-- 	<input type="hidden" name="idEquipoModificar" value="" /> -->
<!-- 	<input type="hidden" name="equipoNombre" value="" /> -->
<!-- 	<input type="hidden" name="equipoSiglas" value="" /> -->
	
<!-- </form> -->

<!-- <form name="formInsertarEquipo" action="adminEquipos" method="POST"> -->
<!-- 	<input type="hidden" name="operacion" value="EQUIPO_ADMIN_INSERTAR" /> -->
<!-- </form> -->

<!-- <form name="formConfirmarInsertarEquipo" action="adminEquipos" method="POST"> -->
<!-- 	<input type="hidden" name="operacion" value="EQUIPO_ADMIN_CONFIRMAR_INSERTAR" /> -->
	
<!-- 	<input type="hidden" name="equipoNombre" value="" /> -->
<!-- 	<input type="hidden" name="equipoSiglas" value="" /> -->
<!-- </form> -->
  	

</body>
</html>
