<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.personal.basket.ctes.*" %>

	
<%
HttpSession objSesion = request.getSession(false);
String identificado = (String)objSesion.getAttribute(ConstantesSesion.IDENTIFICADO);
if (identificado == null || identificado.length()<1){
	identificado = Ctes.NO;
}


String nbLiga = (String)request.getAttribute("DRAFT_NB_LIGA");
if (nbLiga == null || nbLiga.length()<1){
	nbLiga = "";
}
String nbEquipo = (String)request.getAttribute("DRAFT_NB_EQUIPO");
if (nbEquipo == null || nbEquipo.length()<1){
	nbEquipo = "";
}
String economia = (String)request.getAttribute("DRAFT_ECONOMIA");
if (economia == null || economia.length()<1){
	economia = "";
}


/*
String errorCrearLiga = (String)request.getAttribute("errorCrearLiga");
if (errorCrearLiga == null || errorCrearLiga.length()<1){
	errorCrearLiga = "";
}
*/


%>


<html>
   	
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Draft</title>

	<link media="screen" href="css/estilos.css" type="text/css" rel="stylesheet"/>
	<link media="screen" href="css/gmail.css" type="text/css" rel="stylesheet"/>
	<link href="css/redmond/jquery-ui-1.9.0.custom.css" type="text/css" rel="stylesheet">
	<script src="js/jquery-1.8.2.js"></script>
	<script src="js/jquery-ui-1.9.0.custom.js"></script>
	<script type="text/javascript" src="js/jquery.tablednd.0.7.min.js"></script>
	
<script language="JavaScript">



/*
	function inicializar(){
	
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
		
			<% if (identificado.equalsIgnoreCase(Ctes.SI)){ %>

					<div id="menuPeticion">
					
						<h3><b>Draft</b></h3>
						
							<!-- Tabla de resumen -->
							


<!-- 					<br /> -->
<!-- 					<br />		 -->
					
<table border="2">
<caption>Nombre Liga: <%=nbLiga%><br />Nombre Equipo: <%=nbEquipo%><br />Dinero disponible: <%=economia%> &#8364;</caption>
<thead>
	<tr>
		<th width="65%" scope="col">Nombre</th>
		<th width="10%" scope="col">Posicion</th>
		<th width="20%" scope="col">Precio</th>
		<th width="5%" scope="col"></th>
	</tr>
</thead>

<tfoot>
	<tr>
		<th scope="row" colspan="4" align="right">Total: 2 jugadores</th>
	</tr>
</tfoot>

<tbody>
<tr>
	<td scope="row">Kurz, Robert</td>
	<td>Pivot</td>
	<td>1.265.698 &#8364;</td>
	<td><a href="http://www.adobati.it/labs/CSSTable/0m4r.table.css" title="Download the rows table template CSS file">Download</a></td>
</tr>

<tr class="odd">
	<td scope="row">Mainoldi, Leo</td>
	<td>Alero</td>
	<td>663.258 &#8364;</td>
	<td><a href="http://www.admixweb.com/downloads/csstablegallery/bluedream.css" title="Download the Blue Dream CSS file">Download</a></td>
</tr>

</tbody>
</table>
	

							
<!-- 							<span class="resaltarTorcido"> -->
<%-- 								<%=errorCrearLiga%> --%>
<!-- 							</span> -->
							
						</div>
					
				
			<% }else{ %>
			
				<!-- Se redigira a error... -->
				<%
					objSesion.setAttribute(ConstantesSesion.DETALLE_ERROR," No se puede acceder a la opcion de menu sin estar identificado.");
				
					String redirectURL = "error";
					response.sendRedirect(redirectURL);
				%>
				
			<% } %>
		

		</div>

	
	</div>
	
	
	
<%-- 	<jsp:include page="PlantillaPiePagina.jsp" /> --%>
	


<!-- <form name="formConfirmarLiga" action="crearLiga" method="POST"> -->
<!--  	<input type="hidden" name="operacion" value="CREAR_LIGA" /> -->

<!-- 	<input type="hidden" name="nbLiga" value="" /> -->
<!-- 	<input type="hidden" name="pwdLiga" value="" /> -->
<!-- 	<input type="hidden" name="pwd2Liga" value="" /> -->
<!-- 	<input type="hidden" name="chPublica" value="" /> -->
	
<!-- </form> -->
  	

</body>
</html>
