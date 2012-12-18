<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.personal.basket.ctes.*" %>

<%
HttpSession objSesion = request.getSession(false);
String identificado = (String)objSesion.getAttribute(ConstantesSesion.IDENTIFICADO);
if (identificado == null || identificado.length()<1){
	identificado = Ctes.NO;
}
%>

<html>
<head>
	<title>Home</title>
</head>

<script>

function xxxxxxxxxx(){
	alert("xyz");
}

</script>

<body>
  	
  	${OpcionEquipo.codigo}
  	${OpcionEquipo.nombre}
  	
  	<!-- 
 	<ul class="lista">
		<c:forEach var="fila" items="${OpcionJugadores}" varStatus="status">
			<li><a onclick="detalleJugador('${fila.codigo}');" href="#">${fila.apellido}, ${fila.nombre} -- ${fila.puesto}, ${fila.altura}, ${fila.peso}, ${fila.nacionalidad}</a></li>
		</c:forEach>
	</ul>  
	 -->


</body>
</html>
