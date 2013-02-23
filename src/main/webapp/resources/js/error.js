function redireccionarAError(operacion, detalle){
	
	//alert(operacion + "--" + detalle);
	
	document.formError.operacion.value=operacion;
	document.formError.detalle.value=detalle;
	
	//alert("llamar1");
	document.formError.submit();	
		
	//alert("llamar2");

}