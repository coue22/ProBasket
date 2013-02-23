package com.personal.basket.ctes;

// Constantes normales
public class Ctes {

	public static final int FILAS_JUGADOR_FICHERO = 10;
	public static final int NUM_RONDAS_DRAFT = 10;
	
	
	public static final String SI = "S";
	public static final String NO = "N";
	
	
	public static final String OPCMENU_JUGADORES 			= "OpcionJugadores";
		public static final String JUGADORES_ELIMINAR 		= "ELIMINAR";
	
	public static final String REGISTRO 					= "REGISTRO";
	public static final String CONFIRMAR_REGISTRO 			= "CONFIRMAR_REGISTRO";
	
	public static final String OPCMENU_EQUIPO	 			= "OpcionEquipo";
	
	// Todas las pantallas deberian tenerla rellena para dar una salida por pantalla y saber que el proceso ha terminado.
	public static final String ADMIN_SALIDA					= "ADMIN_SALIDA";
	
	// Navegacion del la opcion de administracion de la configuracion.
	public static final String OPCADMIN_CONFIGURACION		= "OpcionAdminConfiguracion";
		public static final String CONFIG_ADMIN_CONFIRMAR_MODIFICAR		= "CONFIG_ADMIN_CONFIRMAR_MODIFICAR";
		public static final String CONFIG_ADMIN_CONFIRMAR_INSERTAR		= "CONFIG_ADMIN_CONFIRMAR_INSERTAR";

	// Navegacion del la opcion de administracion de la nacionalidad.
	public static final String OPCADMIN_NACIONALIDAD		= "OpcionAdminNacionalidad";
		public static final String CONFIG_NACIO_CONFIRMAR_ELIMINAR		= "CONFIG_NACIO_CONFIRMAR_ELIMINAR";
		public static final String CONFIG_NACIO_CONFIRMAR_INSERTAR		= "CONFIG_NACIO_CONFIRMAR_INSERTAR";
		
	// Navegacion del la opcion de administracion de la Operacion.
	public static final String OPCADMIN_OPERACION		= "OpcionAdminOperacion";
		public static final String CONFIG_OPERA_CONFIRMAR_MODIFICAR		= "CONFIG_OPERA_CONFIRMAR_MODIFICAR";
		public static final String CONFIG_OPERA_CONFIRMAR_INSERTAR		= "CONFIG_OPERA_CONFIRMAR_INSERTAR";

	// Navegacion del la opcion de administracion de los roles.
	public static final String OPCADMIN_ROLES		= "OpcionAdminRoles";
		public static final String CONFIG_ROL_CONFIRMAR_MODIFICAR		= "CONFIG_ROL_CONFIRMAR_MODIFICAR";
		public static final String CONFIG_ROL_CONFIRMAR_INSERTAR		= "CONFIG_ROL_CONFIRMAR_INSERTAR";
		
	// Navegacion del la opcion de administracion de un equipo.
	public static final String OPCADMIN_EQUIPO	 			= "OpcionAdminEquipo";
		public static final String EQUIPO_ADMIN_MODIFICAR 					= "EQUIPO_ADMIN_MODIFICAR";
		public static final String EQUIPO_ADMIN_MOSTRAR_MODIFICAR 			= "OpcionAdminEquipo_Modificar";
		public static final String EQUIPO_ADMIN_MOSTRAR_MODIFICAR_ID 		= "OpcionAdminEquipo_Modificar_ID";
		public static final String EQUIPO_ADMIN_MOSTRAR_MODIFICAR_NB 		= "OpcionAdminEquipo_Modificar_NB";
		public static final String EQUIPO_ADMIN_MOSTRAR_MODIFICAR_SG 		= "OpcionAdminEquipo_Modificar_SG";
		public static final String EQUIPO_ADMIN_MOSTRAR_MODIFICAR_PA 		= "OpcionAdminEquipo_Modificar_PA";
		public static final String EQUIPO_ADMIN_CONFIRMAR_MODIFICAR			= "EQUIPO_ADMIN_CONFIRMAR_MODIFICAR";
		public static final String EQUIPO_ADMIN_INSERTAR					= "EQUIPO_ADMIN_INSERTAR";
		public static final String EQUIPO_ADMIN_MOSTRAR_INSERTAR 			= "OpcionAdminEquipo_Insertar";
		public static final String EQUIPO_ADMIN_CONFIRMAR_INSERTAR			= "EQUIPO_ADMIN_CONFIRMAR_INSERTAR";
		
	// Navegacion del la opcion de administracion de un jugador.
	public static final String OPCADMIN_JUGADOR	 			= "OpcionAdminJugador";
		public static final String JUGADOR_ADMIN_INSERTAR	 			= "JUGADOR_ADMIN_INSERTAR";
		public static final String JUGADOR_ADMIN_JUGADORES_PARA_INSERTAR = "OpcionAdminJugador_para_insertar";
		public static final String JUGADOR_ADMIN_JUGADORES_FICHERO		 = "OpcionAdminJugador_fichero";
		public static final String JUGADOR_ADMIN_JUGADORES_INSERTADOS	 = "OpcionAdminJugador_jugadores_insertados";
		public static final String CUSTOMJUGADOR_ADMIN_CAMBIO_ESTADO	 = "CUSTOMJUGADOR_ADMIN_CAMBIO_ESTADO";
		
		public static final String JUGADOR_ADMIN_INSERTAR_JUGADOR	 = "JUGADOR_ADMIN_INSERTAR_JUGADOR";
		
		
	// Crear Liga
	public static final String CREAR_LIGA	 				= "CREAR_LIGA";
	// Inscrirse en liga (Buscar liga y Inscribirse en liga)
	public static final String BUSCAR_LIGA	 				= "BUSCAR_LIGA";
	public static final String INSCRIBIR_LIGA	 			= "INSCRIBIR_LIGA";
	
	// Inscribirse en la liga
	public static final String OPCMENU_LIGAS_RECUPERADAS	 			= "ligas_recuperadas";
	public static final String OPCMENU_HAY_LIGAS_RECUPERADAS	 		= "hay_ligas_recuperadas";
	
	// Constantes de la configuracion.
	public static final String MAPA_CONFIGURACION_JORNADA	 		= "jornada";
	public static final String MAPA_CONFIGURACION_TEMPORADA	 		= "temporada";
	public static final String MAPA_CONFIGURACION_DRAFT	 			= "draft";
	public static final String MAPA_CONFIGURACION_DINEROINI			= "dineroInicial";
	
	
	
	public static final String NO_ASIGNADO_LOGIN 			= "0000000000";
	public static final String NO_ASIGNADO_LIGA	 			= "0000000000";
	public static final String NO_ASIGNADO_EQUIPO	 		= "0000000000";
	public static final String NO_ASIGNADO_ROLE	 			= "000";
		public static final String ROLE_CAPITAN	 			= "001";
		public static final String ROLE_ANOTADOR 			= "002";
		public static final String ROLE_DEFENSOR 			= "003";
	public static final String NO_ASIGNADO_ECONOMIA			= "0000000000";	
		//public static final double ECONOMIA_INICIAL			= 4000000;
		

	//public static final String OPCMENU_COMUN			= "OpcionesMenuComun";
	
	
	//public static final String CERRAR_SESION	 		= "cerrarSesion";
	
	
	public static final String DRAFT_NB_LIGA			= "DRAFT_NB_LIGA";
	public static final String DRAFT_NB_EQUIPO			= "DRAFT_NB_EQUIPO";
	public static final String DRAFT_ECONOMIA			= "DRAFT_ECONOMIA";
	public static final String DRAFT_JUGS_RECUPERADOS_SIN_EQUIPO	= "draft_jugadores_recuperados_sin_equipo";
	public static final String DRAFT_JUGS_RECUPERADOS_CON_EQUIPO    = "draft_jugadores_recuperados_con_equipo";
	public static final String DRAFT_JUGS_DRAFT			= "draft_jugadores_draft";
	
	
	
	// Menu
	public static final String MENU_NORMAL	 						= "00";
	public static final String MENU_NOMENU	 						= "01";
	public static final String MENU_ADMIN	 						= "02";
	public static final String MENU_DRAFT	 						= "03";
		
	// OPCIONES DE MENUS
	/*
	public static final String MENU_EQUIPO	 						= "001";
	public static final String MENU_LIGA	 						= "002";
	
	public static final String MENU_NOLIGA_CREAR_LIGA	 			= "101";
	public static final String MENU_NOLIGA_INSCRIBIRSE_LIGA			= "102";
	
	public static final String MENU_ADMIN_CONFIG	 				= "201";
	public static final String MENU_ADMIN_NACION	 				= "202";
	public static final String MENU_ADMIN_OPERAC	 				= "203";
	public static final String MENU_ADMIN_ROLES	 					= "204";
	public static final String MENU_ADMIN_EQUIPOS	 				= "205";
	public static final String MENU_ADMIN_JUGADORES	 				= "206";
	
	public static final String MENU_DRAFTEO			 				= "301";
	*/
	
	public static final int OK	 										= 0;
	public static final int ERROR_INSCRIPCION_ACREDITACION	 			= 1;
	public static final int ERROR_INSCRIPCION_NB_EQUIPO					= 2;
	
	public static final String ERROR_INSCRIBIR_LIGA						= "errorInscribirLiga";
	
	public static final String ERROR_TXT_INSCRIBIR_LIGA_CREDENCIADLES	= "Los datos para inscribirse a la liga no son correctos.";
	public static final String ERROR_TXT_INSCRIBIR_LIGA_EQUIPO			= "Ya existe un equipo con ese nombre.";
	

	
}
