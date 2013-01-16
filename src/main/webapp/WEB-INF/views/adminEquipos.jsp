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


String ModificaEquipoID = (String)request.getAttribute(Ctes.EQUIPO_ADMIN_MOSTRAR_MODIFICAR_ID);
String ModificaEquipoNB = (String)request.getAttribute(Ctes.EQUIPO_ADMIN_MOSTRAR_MODIFICAR_NB);
String ModificaEquipoSG = (String)request.getAttribute(Ctes.EQUIPO_ADMIN_MOSTRAR_MODIFICAR_SG);


String debeInsertarEquipo = (String)request.getAttribute(Ctes.EQUIPO_ADMIN_MOSTRAR_INSERTAR);
if (debeInsertarEquipo == null || debeInsertarEquipo.length()<1){
	debeInsertarEquipo = Ctes.NO;
}



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
				
					<caption> EQUIPOS </caption>
					
					<thead>
						<tr>
						<th></th>
						<th>Codigo</th>
						<th>Nombre</th>
						<th>Siglas</th>
						</tr>
					</thead>
					<tbody>
					
					
					<c:forEach var="fila" items="${OpcionAdminEquipo}" varStatus="status">
						
						
						
						<tr>
							<td>
								<ul id="icons" class="ui-widget ui-helper-clearfix">
				  					<li class="ui-state-default ui-corner-all" title="jug"><span class="ui-icon ui-icon-person"></span></li>
				  				</ul>
				  			</td>
				  
				  			<td>${fila.codigo}</td>
				  			<td>${fila.nombre}</td>
				  			<td>${fila.siglas}</td>

				  			<td>
				  				<ul id="icons" class="ui-widget ui-helper-clearfix">
			  					<li class="ui-state-default ui-corner-all" title="modificar" onclick="modificarEquipo('${fila.codigo}','${fila.nombre}','${fila.siglas}');"><span class="ui-icon ui-icon-circle-close"></span></li>
				  				</ul>
				  			</td>
				  			
				  
				  		</tr>
	

													
					</c:forEach>
					

						
					</tbody>
					
				</table>
				</div>		
				
					<div id="menuPeticion">
					<tr>
						<td>
							<input type="button" name="botonInsertar" value="Insertar" alt="Inserta un nuevo equipo" maxlength="10" size="10" class="input" onclick="javascript:insertarEquipo()" />
						</td>
					</tr>
					</div>				

				<!-- Se muestra para que pueda modificar un equipo. -->
				<% if (debeModificaEquipo.equals(Ctes.SI)) { %>
					<div id="menuPeticion">

						<h3>Modificacion Equipo</h3>
						
						<p>Codigo
						<input type="text" name="ModificaEquipoIDConfirmar" id="ModificaEquipoIDConfirmar" value="<%=ModificaEquipoID%>" maxlength="5" size="5" class="input" readonly />
						</p>						
						<p>Nombre
						<input type="text" name="ModificaEquipoNBConfirmar" id="ModificaEquipoNBConfirmar" value="<%=ModificaEquipoNB%>" maxlength="40" size="40" class="input" />
						</p>
						<p>Siglas
						<input type="text" name="ModificaEquipoSGConfirmar" id="ModificaEquipoSGConfirmar" value="<%=ModificaEquipoSG%>" maxlength="5" size="5" class="input" />
						</p>
						<p>
						<input type="button" name="botonModificar" value="Modificar" maxlength="10" size="10" class="input" onclick="javascript:confirmarModificarEquipo('<%=ModificaEquipoID%>')" />
						</p>

					</div>		
				<%}else if (debeInsertarEquipo.equals(Ctes.SI)) { %> <!-- Se muestra para que pueda insertar un equipo. -->

					<div id="menuPeticion">

						<h3>Insertar Equipo</h3>
					
						<p>Nombre
						<input type="text" name="InsertaEquipoNBConfirmar" id="InsertaEquipoNBConfirmar" value="" maxlength="40" size="40" class="input" />
						</p>
						<p>Siglas
						<input type="text" name="InsertaEquipoSGConfirmar" id="InsertaEquipoSGConfirmar" value="" maxlength="5" size="5" class="input" />
						</p>
						<p>
						<input type="button" name="botonInsertar" value="Insertar" maxlength="10" size="10" class="input" onclick="javascript:confirmarInsertarEquipo();" />
						</p>

					</div>		
				<%}%>				
				

				
							
				
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
	
	
<form name="formModificarEquipo" action="adminEquipos" method="POST">
	<input type="hidden" name="operacion" value="EQUIPO_ADMIN_MODIFICAR" />
	
	<input type="hidden" name="idEquipoModificar" value="" />
	<input type="hidden" name="equipoNombre" value="" />
	<input type="hidden" name="equipoSiglas" value="" />
	
</form>

<form name="formConfirmarModificarEquipo" action="adminEquipos" method="POST">
	<input type="hidden" name="operacion" value="EQUIPO_ADMIN_CONFIRMAR_MODIFICAR" />
	
	<input type="hidden" name="idEquipoModificar" value="" />
	<input type="hidden" name="equipoNombre" value="" />
	<input type="hidden" name="equipoSiglas" value="" />
	
</form>

<form name="formInsertarEquipo" action="adminEquipos" method="POST">
	<input type="hidden" name="operacion" value="EQUIPO_ADMIN_INSERTAR" />
</form>

<form name="formConfirmarInsertarEquipo" action="adminEquipos" method="POST">
	<input type="hidden" name="operacion" value="EQUIPO_ADMIN_CONFIRMAR_INSERTAR" />
	
	<input type="hidden" name="equipoNombre" value="" />
	<input type="hidden" name="equipoSiglas" value="" />
</form>
  	

</body>
</html>
