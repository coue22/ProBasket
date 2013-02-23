<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.personal.basket.ctes.*" %>

	
<%
HttpSession objSesion = request.getSession(false);
String identificado = (String)objSesion.getAttribute(ConstantesSesion.IDENTIFICADO);
if (identificado == null || identificado.length()<1){
	identificado = Ctes.NO;
}


String errorRegistro = (String)request.getAttribute("errorRegistro");
if (errorRegistro == null || errorRegistro.length()<1){
	errorRegistro = "";
}

String okRegistro = (String)request.getAttribute("okRegistro");
if (okRegistro == null || okRegistro.length()<1){
	okRegistro = "";
}

String tiempoDraft = (String)request.getAttribute("tiempoDraft");
if (tiempoDraft == null || tiempoDraft.length()<1){
	tiempoDraft = Ctes.NO;
}




%>


<html>
   	
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Registro</title>

	<link media="screen" href="css/estilos.css" type="text/css" rel="stylesheet"/>
	<link href="css/redmond/jquery-ui-1.9.0.custom.css" type="text/css" rel="stylesheet">
	<script src="js/jquery-1.8.2.js"></script>
	<script src="js/jquery-ui-1.9.0.custom.js"></script>
	<script type="text/javascript" src="js/jquery.tablednd.0.7.min.js"></script>
	<script type="text/javascript" src="js/error.js"></script>
	
<script language="JavaScript">

	function inicializar(){
		document.getElementById('CHPolitica').checked = false;
		document.getElementById('CHPolitica').value = "N";
	}


	function registrar()
	{
		
		var loginRegistro=document.getElementById('RegistraLogin');
		var pwdRegistro=document.getElementById('RegistraPWD');
		var pwd2Registro=document.getElementById('RegistraPWD2');
		var emailRegistro=document.getElementById('RegistraEmail');
		var email2Registro=document.getElementById('RegistraEmail2');
		var selAnoNacRegistro=document.getElementById('selAnoNac');
		var selSexRegistro=document.getElementById('selSexo');
		var chPolitica=document.getElementById('CHPolitica');
		
		if (loginRegistro.value=='' )
		{
			alert("El login debe ir relleno.");
			return;
		}		
		
		if (pwdRegistro.value == ''){
			alert("La contraseña debe ir rellena.");
			return;
		}
			
		if (pwd2Registro.value == ''){
			alert("La re-contraseña debe ir rellena.");
			return;
		}
			
		if (pwdRegistro.value != pwd2Registro.value){
			alert("Las contraseñas deben ser iguales.");	
			pwdRegistro.value='';
			pwd2Registro.value='';
			return;
		}
		
		if (emailRegistro.value == ''){
			alert("La e-mail debe ir relleno.");
			return;
		}
			
		if (email2Registro.value == ''){
			alert("La re-email debe ir relleno.");
			return;
		}
			
		if (emailRegistro.value != email2Registro.value){
			alert("Los e-mails deben ser iguales.");	
			emailRegistro.value='';
			email2Registro.value='';
			return;
		}		
		
		if (selAnoNacRegistro.value == '---'){
			alert("Debe seleccionar un año de nacimiento.");
			return;
		}
		
		if (selSexRegistro.value == '---'){
			alert("Debe seleccionar si es hombre o mujer.");
			return;
		}
		
		chPolitica.value = "N";
		if (chPolitica.checked == true){
			chPolitica.value = "S";
		}else{
			alert("Debe confirmar la politica de privacidad.");
			return;
		}
		
		
		document.formConfirmarRegistro.loginRegistro.value=loginRegistro.value;
		document.formConfirmarRegistro.pwdRegistro.value=pwdRegistro.value;
		document.formConfirmarRegistro.pwd2Registro.value=pwd2Registro.value;
		document.formConfirmarRegistro.emailRegistro.value=emailRegistro.value;
		document.formConfirmarRegistro.email2Registro.value=email2Registro.value;
		document.formConfirmarRegistro.selAnoNacRegistro.value=selAnoNacRegistro.value;
		document.formConfirmarRegistro.selSexRegistro.value=selSexRegistro.value;
		document.formConfirmarRegistro.chPolitica.value=chPolitica.value;
		
		document.formConfirmarRegistro.submit();

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
		
			<% if (identificado.equalsIgnoreCase(Ctes.NO)){ %>

					<div id="menuPeticion">
					
						<% if (tiempoDraft.equalsIgnoreCase(Ctes.NO)){ %>
						
						<h3>Registro</h3>
								
					
							<table>
								
								<tr>
									<td>Login</td>
									<td><input type="text" name="RegistraLogin" id="RegistraLogin" value="" maxlength="20" size="20" class="input" /></td>
								</tr>
								
								<tr>
									<td>Password</td>
									<td><input type="password" name="RegistraPWD" id="RegistraPWD" value="" maxlength="20" size="20" class="input" /></td>
								</tr>
								
								<tr>
									<td>Re-Password</td>
									<td><input type="password" name="RegistraPWD2" id="RegistraPWD2" value="" maxlength="20" size="20" class="input" /></td>
								</tr>
								
								<tr>
									<td>e-mail</td>
									<td><input type="text" name="RegistraEmail" id="RegistraEmail" value="" maxlength="50" size="50" class="input" /></td>
								</tr>
								
								<tr>
									<td>repita e-mail</td>
									<td><input type="text" name="RegistraEmail2" id="RegistraEmail2" value="" maxlength="50" size="50" class="input" /></td>
								</tr>
									
								<tr>
									<td>Año nacimiento</td>
									<td>								
										<select name="selAnoNac" id="selAnoNac">
											<option value="---">---</option>
											<option value="1930">1930</option>
											<option value="1931">1931</option>
											<option value="1932">1932</option>
											<option value="1933">1933</option>
											<option value="1934">1934</option>
											<option value="1935">1935</option>
											<option value="1936">1936</option>
											<option value="1937">1937</option>
											<option value="1938">1938</option>
											<option value="1939">1939</option>
											<option value="1940">1940</option>
											<option value="1941">1941</option>
											<option value="1942">1942</option>
											<option value="1943">1943</option>
											<option value="1944">1944</option>
											<option value="1945">1945</option>
											<option value="1946">1946</option>
											<option value="1947">1947</option>
											<option value="1948">1948</option>
											<option value="1949">1949</option>
											<option value="1950">1950</option>
											<option value="1951">1951</option>
											<option value="1952">1952</option>
											<option value="1953">1953</option>
											<option value="1954">1954</option>
											<option value="1955">1955</option>
											<option value="1956">1956</option>
											<option value="1957">1957</option>
											<option value="1958">1958</option>
											<option value="1959">1959</option>
											<option value="1960">1960</option>
											<option value="1961">1961</option>
											<option value="1962">1962</option>
											<option value="1963">1963</option>
											<option value="1964">1964</option>
											<option value="1965">1965</option>
											<option value="1966">1966</option>
											<option value="1967">1967</option>
											<option value="1968">1968</option>
											<option value="1969">1969</option>
											<option value="1970">1970</option>
											<option value="1971">1971</option>
											<option value="1972">1972</option>
											<option value="1973">1973</option>
											<option value="1974">1974</option>
											<option value="1975">1975</option>
											<option value="1976">1976</option>
											<option value="1977">1977</option>
											<option value="1978">1978</option>
											<option value="1979">1979</option>
											<option value="1980">1980</option>
											<option value="1981">1981</option>
											<option value="1982">1982</option>
											<option value="1983">1983</option>
											<option value="1984">1984</option>
											<option value="1985">1985</option>
											<option value="1986">1986</option>
											<option value="1987">1987</option>
											<option value="1988">1988</option>
											<option value="1989">1989</option>
											<option value="1990">1990</option>
											<option value="1991">1991</option>
											<option value="1992">1992</option>
											<option value="1993">1993</option>
											<option value="1994">1994</option>
											<option value="1995">1995</option>
											<option value="1996">1996</option>
											<option value="1997">1997</option>
											<option value="1998">1998</option>
											<option value="1999">1999</option>
											<option value="2000">2000</option>
											<option value="2001">2001</option>
											<option value="2002">2002</option>
											<option value="2003">2003</option>
											<option value="2004">2004</option>
											<option value="2005">2005</option>
											<option value="2006">2006</option>
											<option value="2007">2007</option>
											<option value="2008">2008</option>
										</select>
									</td> 								
								</tr>
								
								<tr>
									<td>Sexo</td>
									<td>								
										<select name="selSexo" id="selSexo">
											<option value="---">---</option>
											<option value="H">Hombre</option>
											<option value="M">Mujer</option>
										</select>
									</td> 								
								</tr>
								
								<tr>
									<td colspan="2" align="center">
									<input type="checkbox" name="CHPolitica" id="CHPolitica">Acepto la politica de privacidad</td>
								</tr>	
								


								<tr>
									<td colspan="2" align="right">
										<input type="button" name="botonRegistrar" value="Registrar" maxlength="10" size="10" class="input" onclick="javascript:registrar();" />
									</td>
								</tr>
																
							</table>
						<%}%> <!-- Lo del tiempo de draft -->
							
						<span class="resaltarTorcido">
							<%=errorRegistro%>
						</span>
						
						<span class="resaltarTorcidoExito">
							<%=okRegistro%>
						</span>
							
							
							
						</div>
					
				
			<% }else{ %>
				<div id="menuPeticion">
					<!-- Se redigira a error... -->
					<%
						objSesion.setAttribute(ConstantesSesion.OPERACION_ERROR,"Registro.");
						objSesion.setAttribute(ConstantesSesion.DETALLE_ERROR," No se puede acceder al regristro estando identificado.");
					%>		
							
					<h3>Error producido en el registro</h3>
					
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
	


<form name="formConfirmarRegistro" action="registrarse" method="POST">
 	<input type="hidden" name="operacion" value="CONFIRMAR_REGISTRO" />

	<input type="hidden" name="loginRegistro" value="" />
	<input type="hidden" name="pwdRegistro" value="" />
	<input type="hidden" name="pwd2Registro" value="" />
	<input type="hidden" name="emailRegistro" value="" />
	<input type="hidden" name="email2Registro" value="" />
	<input type="hidden" name="selAnoNacRegistro" value="" />
	<input type="hidden" name="selSexRegistro" value="" />
	<input type="hidden" name="chPolitica" value="" />	
	
</form>
  	

</body>
</html>
