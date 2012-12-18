<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.personal.basket.ctes.*" %>
<%@ page import="com.personal.basket.dtos.*" %>
<%@ page import="java.util.ArrayList" %>

		
<%
HttpSession objSesion = request.getSession(false);
String identificado = (String)objSesion.getAttribute(ConstantesSesion.IDENTIFICADO);
if (identificado == null || identificado.length()<1){
	identificado = Ctes.NO;
}
%>

<script>

$(document).ready(function(){	

	$.ajaxSetup({
	    cache: false
	});
	

	// Make a nice striped effect on the table
	$("#table-2 tr:even").addClass("alt");
	// Initialise the second table specifying a dragClass and an onDrop function that will display an alert
	$("#table-2").tableDnD({
	    onDragClass: "myDragClass",
	    onDrop: function(table, row) {
            var rows = table.tBodies[0].rows;
            var debugStr = "Row dropped was "+row.id+". New order: ";
            for (var i=0; i<rows.length; i++) {
                debugStr += rows[i].id+" ";
            }
	        $("#debugArea").html(debugStr);
	    },
		onDragStart: function(table, row) {
			$("#debugArea").html("Started dragging row "+row.id);
		}
	});

    

	
/*
	var availableTags = [
		"ActionScript",
		"AppleScript",
		"Asp",
		"BASIC",
		"C",
		"C++",
		"Clojure",
		"COBOL",
		"ColdFusion",
		"Erlang",
		"Fortran",
		"Groovy",
		"Haskell",
		"Java",
		"JavaScript",
		"Lisp",
		"Perl",
		"PHP",
		"Python",
		"Ruby",
		"Scala",
		"Scheme"
	];
	$( "#autocomplete" ).autocomplete({
		source: availableTags
	});

*/

	
    $("#fLogin").submit(function() {

        if (($("input#nombre").val() != "") &&  ($("input#contra").val() != "")) {
          $("span#error").text("Validando...").show();
          return true;
        }
        
        if ($("input#nombre").val() == ""){
        	$("span#error").text("Rellene el login.").show().fadeOut(3000);
        	return false;
        }
        
        if ($("input#contra").val() == ""){ 
        	$("span#error").text("Rellene la password.").show().fadeOut(3000);
        	return false;
        }
        
        return false;
        
      });
      

    /*
    $("#fcrearLiga").submit(function() {

        if (($("input#nombreLiga").val() != "") &&  ($("input#contraLiga").val() != "")) {
          $("span#error").text("Validando...").show();
          return true;
        }
        
        if ($("input#nombreLiga").val() == ""){
        	$("span#error").text("Rellene el login.").show().fadeOut(3000);
        	return false;
        }
        
        if ($("input#contraLiga").val() == ""){ 
        	$("span#error").text("Rellene la password.").show().fadeOut(3000);
        	return false;
        }
        
        return false;
        
      });
    */

    

	
/* Este viene de arriba*/
});


/*
	function Registrarse(){
		alert("HACERLA");
	}
	
	function detalleJugador(codJug){
		alert("CodJug: " + codJug);
	}	
*/	
	
	function CerrarSesion(){
		document.formCerrarSesion.submit();	
	}	


	function evaluarOpcion(opc){
		//alert("opcion: " + opc);
		if (opc == 'Jugadores'){
			document.formMostrarJugadores.submit();		
		} else if (opc == 'Equipo'){
			document.formMostrarEquipo.submit();
		}
	}
	
</script>


		<div id="header">

 				<a target="_self" href="inicio.html" title="....." class="miLogo">
  					<img src="img/logo_tcm423-208626.gif" alt="bbva" />
 				</a>
 			
				<h2>XXXXXXXXXXXX</h2>
		
				<% if (identificado.equals(Ctes.SI)) { %>
					<a class="miRegistro" onclick="CerrarSesion();" href="#">Cerrar Sesi�n</a>
				<%}else{%>	
					<a class="miRegistro" onclick="Registrarse();" href="#">Registrarse</a>
				<%}%>
				

 		</div>
 		
 		
 		<% if (identificado.equals(Ctes.SI)) { 
 			ArrayList<MenuDTO> menuPrincipal = (ArrayList<MenuDTO>)objSesion.getAttribute(ConstantesSesion.OPCMENU);
 		%>
			<div id="menu">
				<h3>Menu</h3>
				<ul>
					<c:forEach var="fila" items="<%=menuPrincipal%>" varStatus="status"> 
						<li><a onclick="evaluarOpcion('${fila.nombre}');" href="#">${fila.nombre}</a></li>
					</c:forEach>			
				</ul>	
	 		</div>
 		<%}else{%>
 			<form name="fLogin" id="fLogin" action="login" method="POST" >
	 			<div id="menu">
	 				<h3>Identificacion</h3>
					
						<p>Usuario
							<input type="text" name="nombre" id="nombre" value="" maxlength="15" size="15" class="input" />
						</p>
					
						<p>Contrase�a
							<input type="password" name="contra" id="contra" value="" maxlength="15" size="15" class="input" />
						</p>
					
						<p>
						<input type="submit" name="Submit" value="Identificarse" maxlength="10" size="10" class="input" />
						</p>
						
						<span id="error"></span>
				</div> 		
			</form>
 		<%}%>
 		
<!--

<h2 class="demoHeaders">Autocomplete</h2>
<div>
	<input id="autocomplete" title="type &quot;a&quot;">
</div>
	
 -->

<!-- 
*******************************
	Distintos formularios 
*******************************
-->

<form name="formMostrarJugadores" action="jugadores" method="POST">
	<input type="hidden" name="XXXX" />
</form>

<form name="formMostrarEquipo" action="equipo" method="POST">
	<input type="hidden" name="XXXX" />
</form>

 
<form name="formCerrarSesion" action="cerrarSesion" method="POST">
	<!-- <input type="hidden" name="cerrarSesion" id="cerrarSesion" value="S" /> -->
</form>


 		