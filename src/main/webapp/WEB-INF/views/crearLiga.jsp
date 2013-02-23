<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.personal.basket.ctes.*" %>

	
<%
HttpSession objSesion = request.getSession(false);
String identificado = (String)objSesion.getAttribute(ConstantesSesion.IDENTIFICADO);
if (identificado == null || identificado.length()<1){
	identificado = Ctes.NO;
}

String errorCrearLiga = (String)request.getAttribute("errorCrearLiga");
if (errorCrearLiga == null || errorCrearLiga.length()<1){
	errorCrearLiga = "";
}



%>


<html>
   	
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Crear Liga</title>

	<link media="screen" href="css/estilos.css" type="text/css" rel="stylesheet"/>
	<link href="css/redmond/jquery-ui-1.9.0.custom.css" type="text/css" rel="stylesheet">
	<script src="js/jquery-1.8.2.js"></script>
	<script src="js/jquery-ui-1.9.0.custom.js"></script>
	<script type="text/javascript" src="js/jquery.tablednd.0.7.min.js"></script>
	<script type="text/javascript" src="js/error.js"></script>
	
<script language="JavaScript">

	function inicializar(){
		//alert("ini2");
		
		document.getElementById('CHPublica').checked = false;
		document.getElementById('CHPublica').value = "N";

	}

	function crearLiga()
	{
		var nbliga=document.getElementById('InsertaNBLiga');
		var pwdLiga=document.getElementById('InsertaPWDLiga');
		var pwd2Liga=document.getElementById('InsertaPWD2Liga');
		var chPublica=document.getElementById('CHPublica');
		
		chPublica.value = "N";
		if (chPublica.checked == true)
			chPublica.value = "S";
		
		//alert(document.getElementById('CHPublica').value);
		//alert(document.getElementById('CHPublica').checked);
		
		//Se valida que el nombre de la liga
		if (nbliga.value=='' )
		{
			alert("El nombre de la liga debe ir relleno.");
			
		}else{
			
			if (chPublica.checked == true){
				document.formConfirmarLiga.nbLiga.value=InsertaNBLiga.value;
				document.formConfirmarLiga.pwdLiga.value="";
				document.formConfirmarLiga.pwd2Liga.value="";
				document.formConfirmarLiga.chPublica.value=chPublica.value;
				
				document.formConfirmarLiga.submit();
				
			}else{
				
				if (pwdLiga.value == '')
					alert("La contraseña debe ir rellena.");
				else if (pwd2Liga.value == '')
					alert("La re-contraseña debe ir rellena.");
				else if (pwdLiga.value != pwd2Liga.value){
					alert("Las contraseñas deben ser iguales.");	
					InsertaPWDLiga.value='';
					InsertaPWD2Liga.value='';
				}else{
					document.formConfirmarLiga.nbLiga.value=InsertaNBLiga.value;
					document.formConfirmarLiga.pwdLiga.value=InsertaPWDLiga.value;
					document.formConfirmarLiga.pwd2Liga.value=InsertaPWD2Liga.value;
					document.formConfirmarLiga.chPublica.value=chPublica.value;
					
					document.formConfirmarLiga.submit();
					
				}				
			}

		}
	}
	
	// Cuando se indica que es publica la liga se borran las contraseñas.
	function handleChange(cb){
		if (cb.checked == true){
			document.getElementById('InsertaPWDLiga').value='';
			document.getElementById('InsertaPWD2Liga').value='';			
		}
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

<body onload="inicializar();">
	
	<div id="cont">
	

 		<jsp:include page="PlantillaCabecera.jsp" />
	
	

		<div id="contenido">
		
			<% if (identificado.equalsIgnoreCase(Ctes.SI)){ %>

					<div id="menuPeticion">
						<h3>Crear Liga</h3>
					
					
							<table>
								<tr>
									<td>Nombre</td>
									<td><input type="text" name="InsertaNBLiga" id="InsertaNBLiga" value="" maxlength="20" size="20" class="input" /></td>
								</tr>
								<tr>
									<td>Password</td>
									<td><input type="password" name="InsertaPWDLiga" id="InsertaPWDLiga" value="" maxlength="20" size="20" class="input" /></td>
								</tr>
								<tr>
									<td>Re-Password</td>
									<td><input type="password" name="InsertaPWD2Liga" id="InsertaPWD2Liga" value="" maxlength="20" size="20" class="input" /></td>
								</tr>
								<tr>
									<td colspan="2" align="center">
									<input type="checkbox" name="CHPublica" id="CHPublica" onchange="handleChange(this);">Hacer la liga p&uacute;blica</td>
								</tr>	
								<tr>
									<td colspan="2" align="right">
										<input type="button" name="botonInsertar" value="Insertar" maxlength="10" size="10" class="input" onclick="javascript:crearLiga();" />
									</td>
								</tr>
																
							</table>
							
							<span class="resaltarTorcido">
								<%=errorCrearLiga%>
							</span>
							
						</div>
					
				
			<% }else{ %>
			
				<div id="menuPeticion">
					<!-- Se redigira a error... -->
					<%
						objSesion.setAttribute(ConstantesSesion.OPERACION_ERROR,"Crear Liga.");
						objSesion.setAttribute(ConstantesSesion.DETALLE_ERROR," No se puede acceder a la opcion de menu sin estar identificado.");
					%>		
							
					<h3>Error producido en la creacion de una liga</h3>
					
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
	


<form name="formConfirmarLiga" action="crearLiga" method="POST">
 	<input type="hidden" name="operacion" value="CREAR_LIGA" />

	<input type="hidden" name="nbLiga" value="" />
	<input type="hidden" name="pwdLiga" value="" />
	<input type="hidden" name="pwd2Liga" value="" />
	<input type="hidden" name="chPublica" value="" />
	
</form>
  	

</body>
</html>
