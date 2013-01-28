<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.personal.basket.ctes.*" %>

	
<%
HttpSession objSesion = request.getSession(false);
String identificado = (String)objSesion.getAttribute(ConstantesSesion.IDENTIFICADO);
if (identificado == null || identificado.length()<1){
	identificado = Ctes.NO;
}

String hay_ligas_recuperadas = (String)request.getAttribute("hay_ligas_recuperadas");
if (hay_ligas_recuperadas == null || hay_ligas_recuperadas.length()<1){
	hay_ligas_recuperadas = "";
}


%>


<html>
   	
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Inscribirse en la liga</title>

	<link media="screen" href="css/estilos.css" type="text/css" rel="stylesheet"/>
	<link href="css/redmond/jquery-ui-1.9.0.custom.css" type="text/css" rel="stylesheet">
	<script src="js/jquery-1.8.2.js"></script>
	<script src="js/jquery-ui-1.9.0.custom.js"></script>
	<script type="text/javascript" src="js/jquery.tablednd.0.7.min.js"></script>
	
<script language="JavaScript">

	function buscarLiga(){
		
		var nbLigaBuscar=document.getElementById('NBLigaBuscar');
		if (nbLigaBuscar.value == ''){
			alert("Para buscar una liga debe insertar al menos un caracter.");
			return;
		}
		
		document.formBuscarLiga.nbLigaBuscar.value=NBLigaBuscar.value;
		document.formBuscarLiga.submit();
	}
	
	function establecerPase(selValor){
		
		//alert(selValor);
		
		var temp=selValor.split("#");

		//alert(temp[0]);	
		//alert(temp[1]);
		
		var passLiga=document.getElementById('passLiga');
		
		// Si es liga publica
		if (temp[1] == "true"){
			passLiga.value="publica";
			document.getElementById('passLiga').setAttribute('readOnly','readonly');
		}else{
			passLiga.value="";
			document.getElementById('passLiga').removeAttribute('readOnly');
		}
	
	}
	
	function inscribirLiga(){
		
		var passLiga=document.getElementById('passLiga');
		var selLiga=document.getElementById('selLiga');
		
		var temp=selLiga.value.split("#");
		if (temp[0] == "-1"){
			alert("Debe seleccionar una liga.");
		}
		else{
			document.formInscribirLiga.passLiga.value=passLiga.value;
			document.formInscribirLiga.selLiga.value=selLiga.value;
			
			document.formInscribirLiga.submit();		
			
		}
		
		//alert(selLiga.value + "--" + passLiga.value);
		
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
		
			<% if (identificado.equalsIgnoreCase(Ctes.SI)){ %>
				
				<div id="menuPeticion">
				
					<h3>Inscribirse a una liga</h3>
					
						<table>
							<tr>
								<td>Nombre de la liga</td>
								<td><input type="text" name="NBLigaBuscar" id="NBLigaBuscar" value="" maxlength="25" size="25" class="input" /></td>
							</tr>
							
							<tr>
								<td colspan="2" align="right">
									<input type="button" name="botonBuscar" value="Buscar Liga" maxlength="10" size="10" class="input" onclick="javascript:buscarLiga();" />
								</td>
							</tr>
							
							
						</table>
						
		
						
						<% if (!hay_ligas_recuperadas.equalsIgnoreCase("")){ %>
						
							<% if (hay_ligas_recuperadas.equals(Ctes.SI)){ %>
							
								<h3>Ligas</h3>	
								
								<table>
									<tr>
										<td>
											<select name="selLiga" id="selLiga" onchange="if (this.selectedIndex){ establecerPase(this.value); }">
												<option value="-1#-1">Selecciona liga</option>
												<c:forEach var="fila" items="${ligas_recuperadas}" varStatus="status">
													<option value=${fila.codigo}#${fila.ligaPublica}>${fila.nombre}</option>
												</c:forEach>
											</select> 
										</td>

										<td><input type="text" name="passLiga" id="passLiga" value="" maxlength="25" size="25" class="input" /></td>
									
									</tr>
									
									<tr>
										<td colspan="2" align="right">
											<input type="button" name="botonInscribir" value="Inscribirse" maxlength="10" size="10" class="input" onclick="javascript:inscribirLiga();" />
										</td>
									</tr>	
																
								</table>
								
							<% } else {%>
								<span class="resaltarTorcido">
									No existen ligas con el criterio de busqueda.
								</span>							
							<% } %>
							
						<% } %>		
											
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
	
	
<form name="formBuscarLiga" action="inscribirseLiga" method="POST">
 	<input type="hidden" name="operacion" value="BUSCAR_LIGA" />
	<input type="hidden" name="nbLigaBuscar" value="" />
</form>
  	
  	

<form name="formInscribirLiga" action="inscribirseLiga" method="POST">
 	<input type="hidden" name="operacion" value="INSCRIBIR_LIGA" />
	<input type="hidden" name="passLiga" value="" />
	<input type="hidden" name="selLiga" value="" />
</form>
  	  	

  		
	
<%-- 	<jsp:include page="PlantillaPiePagina.jsp" /> --%>
	


<!-- <form name="formConfirmarLiga" action="crearLiga" method="POST"> -->
<!--  	<input type="hidden" name="operacion" value="CREAR_LIGA" /> -->

<!-- 	<input type="hidden" name="nbLiga" value="" /> -->
<!-- 	<input type="hidden" name="pwdLiga" value="" /> -->
<!-- </form> -->
  	

</body>
</html>
