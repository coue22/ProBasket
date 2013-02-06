package com.personal.basket.ctes;

//Constantes de Sesion
public class ConstantesSesion {
	
	public static final String ADMINISTRADOR 	= "Administrador";		//(NO|SI)
	public static final String IDENTIFICADO 	= "Identificado";		//(NO|SI)

	public static final String PERTENECE_LIGA 	= "PerteneceLiga";		//(NO|SI)
	public static final String TIENE_EQUIPO 	= "TieneEquipo";		//(NO|SI)
	
	public static final String MI_LIGA 			= "mi_liga";			//(0000000000|Codigo real)
	public static final String MI_EQUIPO 		= "mi_equipo";			//(0000000000|Codigo real)
	
	public static final String COD_ECONO 		= "cod_econo";			//(0000000000|Codigo real)

	
	
	//1-Administrador
	//2-Crear e inscribir en liga
	//3-Draft
	//4-menu normal
	public static final String TIPOMENU 				= "TIPOMENU";
	public static final String OPCMENU 					= "OpcionesMenu";
	public static final String OPCMENUTEXTO				= "OpcionesMenuTexto";
	
	// Para la pagina de Error
	public static final String OPERACION_ERROR = "OperacionError";
	public static final String DETALLE_ERROR = "DetError";
	public static final String DETALLE_ERROR_TEXTO = "Debe identificarse para poder continuar.";
	
	//public static final String IVAN = "IVAN";
	
	// Sesenta segundos es el tiempo de inactividad.
	//public static final int TIEMPO_SESION = 180;
	public static final int TIEMPO_SESION = -1;
	
	
}
