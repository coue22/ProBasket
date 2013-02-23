
	
<%
HttpSession objSesion = request.getSession(false);
String opError = (String)objSesion.getAttribute(ConstantesSesion.OPERACION_ERROR);
String detError = (String)objSesion.getAttribute(ConstantesSesion.DETALLE_ERROR);

%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.personal.basket.ctes.*" %>

<html>
   	
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Error</title>

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
			
			 	<div id="menuPeticion">
			 		<h3>Error</h3>
					<table><tr><td><%=opError%></td></tr></table>
					<h3>Detalle</h3>
					<table><tr><td><%=detError%></td></tr></table>
				</div>
				
			</div>
	
<!-- 		<div id="contenido"> -->
<!-- 			<h3>Operacion</h3> -->
<%-- 			<P> <%=opError%> </P> --%>
<!-- 		</div> -->
		
<!-- 		<div id="contenido"> -->
<!-- 			<h3>Error</h3> -->
<%-- 			<P> <%=detError%> </P> --%>
<!-- 		</div> -->
	
	</div>
	
<%-- 	<jsp:include page="PlantillaPiePagina.jsp" /> --%>
  	 



</body>
</html>
