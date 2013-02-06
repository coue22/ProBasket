<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.personal.basket.ctes.*" %>

<%
HttpSession objSesion = request.getSession(false);
String tipo_menu = (String)objSesion.getAttribute(ConstantesSesion.TIPOMENU);
if (tipo_menu == null || tipo_menu.length()<1){
	tipo_menu = "0";
}
%>

<html>
   	
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Homeeee</title>

	<link media="screen" href="css/estilos.css" type="text/css" rel="stylesheet"/>
	<link href="css/redmond/jquery-ui-1.9.0.custom.css" type="text/css" rel="stylesheet">
	<script src="js/jquery-1.8.2.js"></script>
	<script src="js/jquery-ui-1.9.0.custom.js"></script>
	<script type="text/javascript" src="js/jquery.tablednd.0.7.min.js"></script>
	
</head>

<body>
	
	<div id="cont">
	

 		<jsp:include page="PlantillaCabecera.jsp" />

		<div id="contenido">
			<% if (tipo_menu.equals("1")) { %>
				Aqui puedo poner lo referente al menu de administracion.
			<%}else if (tipo_menu.equals("2")) {%>
				Aqui puedo poner lo referente a crear e inscribirse en liga
			<%}else if (tipo_menu.equals("3")) {%>
				Aqui puedo poner lo referente al draft
			<%}else if (tipo_menu.equals("4")) {%>
				Aqui puedo poner lo referente al menu normal
			<%}else if (tipo_menu.equals("0")) {%>
				El inicio....
			<%}%>

		</div>
		

		
		
	</div>
<%-- 	<jsp:include page="PlantillaPiePagina.jsp" /> --%>
	
  	 



</body>
</html>
