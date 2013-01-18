package com.personal.basket;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.personal.basket.bbdd.DBConneccionException;
import com.personal.basket.bbdd.MySQLConnection;
import com.personal.basket.ctes.ConstantesSesion;
import com.personal.basket.ctes.Ctes;
import com.personal.basket.dtos.DatosPersonalesDTO;
import com.personal.basket.dtos.EquipoDTO;
import com.personal.basket.dtos.JugadorDTO;
import com.personal.basket.dtos.LigaDTO;
import com.personal.basket.dtos.MenuDTO;
import com.personal.basket.servicios.CatalogoServicios;
import com.personal.basket.servicios.ServiciosAdministracion;
import com.personal.basket.servicios.ServiciosJugadores;
import com.personal.basket.servicios.ServiciosGestionLigas;
import com.personal.basket.util.util;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	CatalogoServicios catalogoServicio;

	@Autowired
	ServiciosJugadores servicioJugadores;
	
	@Autowired
	ServiciosAdministracion servicioAdministracion;
	
	@Autowired
	ServiciosGestionLigas servicioGestionLigas;
	

	public CatalogoServicios getCatalogoServicio() {
		return catalogoServicio;
	}
	public void setCatalogoServicio(CatalogoServicios catalogoServicio) {
		this.catalogoServicio = catalogoServicio;
	}

	public ServiciosJugadores getServicioJugadores() {
		return servicioJugadores;
	}
	public void setServicioJugadores(ServiciosJugadores servicioJugadores) {
		this.servicioJugadores = servicioJugadores;
	}
	
	public ServiciosAdministracion getServicioAdministracion() {
		return servicioAdministracion;
	}
	public void setServicioAdministracion(
			ServiciosAdministracion servicioAdministracion) {
		this.servicioAdministracion = servicioAdministracion;
	}
	

	public ServiciosGestionLigas getServicioGestionLigas() {
		return servicioGestionLigas;
	}
	public void setServicioGestionLigas(ServiciosGestionLigas servicioGestionLigas) {
		this.servicioGestionLigas = servicioGestionLigas;
	}
	/**
	 * Inicializa la sesion con valores por defecto.
	 * @param sesion
	 */
	private void InicializarSesion(HttpSession sesion){
		
		sesion.setAttribute(ConstantesSesion.ADMINISTRADOR, Ctes.NO);
		sesion.setAttribute(ConstantesSesion.IDENTIFICADO, Ctes.NO);
		sesion.setAttribute(ConstantesSesion.PERTENECE_LIGA, Ctes.NO);
		sesion.setAttribute(ConstantesSesion.TIENE_EQUIPO, Ctes.NO);
	}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Locale locale,
			Map<String, Object> model, 
            HttpServletRequest request, 
            HttpServletResponse response,
            HttpSession sesion) {
		
		logger.info("Welcome home! the client locale is "+ locale.toString());
		
/*		
		// PLANIFICADOR...
		try{
			
			if (APBDSchedulerFactoryBean.getInstance() == null){
				
				// Se arranca en la primera peticion el planificador.
				Properties propConstantes = new Properties();
				propConstantes.put("org.quartz.scheduler.instanceName","DefaultQuartzScheduler");
				propConstantes.put("org.quartz.jobStore.tablePrefix","QRT2_");
				propConstantes.put("org.quartz.scheduler.rmi.proxy","false");
				propConstantes.put("org.quartz.jobStore.class","org.quartz.impl.jdbcjobstore.JobStoreTX");
				propConstantes.put("org.quartz.threadPool.class","org.quartz.simpl.SimpleThreadPool");
				propConstantes.put("org.quartz.threadPool.threadPriority","5");
				propConstantes.put("org.quartz.scheduler.wrapJobExecutionInUserTransaction","false");
				propConstantes.put("org.quartz.jobStore.misfireThreshold","60000");
				propConstantes.put("org.quartz.scheduler.rmi.export","false");
				propConstantes.put("org.quartz.threadPool.threadCount","10");
				propConstantes.put("org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread","true");
				propConstantes.put("org.quartz.jobStore.driverDelegateClass","org.quartz.impl.jdbcjobstore.oracle.weblogic.WebLogicOracleDelegate");
				propConstantes.put("org.quartz.jobStore.dataSource","MyDataSource");
				propConstantes.put("org.quartz.dataSource.MyDataSource.jndiURL","APBDDS");
				
// ESTO ES LO QUE SUSTITUYE AL jndiURL (Las 5 lineas de abajo.)
//org.quartz.dataSource.APPDS.driver=com.sybase.jdbc2.jdbc.SybDriver
//org.quartz.dataSource.APPDS.URL=jdbc:sybase:Tds:<internal-server-name>:1029/appdbname
//org.quartz.dataSource.APPDS.user=[hidden]
//org.quartz.dataSource.APPDS.password=[hidden]
//org.quartz.dataSource.APPDS.maxConnections=30 
				
				
				// Se arranca en la primera peticion el planificador.
				APBDSchedulerFactoryBean.getInstance(propConstantes);
				
				// ASI SE LLAMA A LA PUBLICACION 
				//return quartz.getInstance().planificaPublicacion(pase, entorno, usuario, dFecha);
			}
			
		} catch (SchedulerException e) {
			
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
			return "error";
		}
*/		
		
		/*
		MySQLConnection  ms = new MySQLConnection ("","","","");
		try {
			ms.open();
		} catch (DBConneccionException e) {
			logger.info("ERROR:" + e.getMensaje());
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		*/
		

		InicializarSesion(sesion);
		
		// Se inicializa la configuracion del aplicativo.
		try {
			servicioAdministracion.InicializarConfiguracion();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		model.put("serverTime", formattedDate);
		//model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Locale locale,
			Map<String, Object> model, 
            HttpServletRequest request, 
            HttpServletResponse response,
            HttpSession sesion) {
		
		// Se comprueba si el login y la password son correctas y se establecen 
		// atributos de sesion.
		System.out.println("nombreLogin: " + request.getParameter("nombre"));
		System.out.println("passwordLogin:" + request.getParameter("contra") );
		
		
		// Se llama al servicio para logarse y se establecen algunas variables de sesion
		// asociadas al usuario.
		try {
			
			sesion.setAttribute(ConstantesSesion.IDENTIFICADO, Ctes.NO);
			sesion.setAttribute(ConstantesSesion.PERTENECE_LIGA, Ctes.NO);
			sesion.setAttribute(ConstantesSesion.TIENE_EQUIPO, Ctes.NO);
			
			DatosPersonalesDTO dPers = catalogoServicio.loggearse(request.getParameter("nombreReg"), 
														 request.getParameter("contraReg"));
			

			
			// Si esta logado, entonces puede tener liga y si tiene liga entonces puede tener equipo.
			// Se establecen constantes de sesion.
			if (dPers.isAdministrador()){
				sesion.setAttribute(ConstantesSesion.IDENTIFICADO, Ctes.SI);
				sesion.setAttribute(ConstantesSesion.ADMINISTRADOR, Ctes.SI);
			}else if (dPers.isLogado()){
				sesion.setAttribute(ConstantesSesion.IDENTIFICADO, Ctes.SI);
				if (!dPers.getIdLiga().equals(Ctes.NO_ASIGNADO_LIGA)){
					sesion.setAttribute(ConstantesSesion.PERTENECE_LIGA, Ctes.SI);
					if (!dPers.getIdLiga().equals(Ctes.NO_ASIGNADO_EQUIPO)){
						sesion.setAttribute(ConstantesSesion.TIENE_EQUIPO, Ctes.SI);	
					}
				}
			}
			
			// Se guarda el mapa con lo que hay en la tabla de configuracion.
			sesion.setAttribute(Ctes.MAPA_CONFIGURACION, dPers.getMapConfiguracion());

			System.out.println("Persona logada?:" + dPers.isLogado()+ " y esta Logado: " + sesion.getAttribute(ConstantesSesion.IDENTIFICADO));
			System.out.println("Codigo Liga:" + dPers.getIdLiga()+ " y pertenece Liga: " + sesion.getAttribute(ConstantesSesion.PERTENECE_LIGA));
			System.out.println("Cod. Equipo:" + dPers.getIdEquipo()+ " y tiene Equipo: " + sesion.getAttribute(ConstantesSesion.TIENE_EQUIPO));
			
		} catch (Exception e1) {
			
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al logarse");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e1.getMessage() + "-->" + e1.getStackTrace());
			return "error";
		}

		
		// Si no esta identificado es un error.
		if (sesion.getAttribute(ConstantesSesion.IDENTIFICADO).equals(Ctes.NO)){
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al logarse");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, ConstantesSesion.DETALLE_ERROR_TEXTO );
			return "error";
		}
		
			
		// Muestra en la consola todo lo que contiene el mapa de configuración.
		Map<String, String> mConfig = (Map<String, String>) sesion.getAttribute(Ctes.MAPA_CONFIGURACION);
		try {
			System.out.println("jorn:" + util.getValorMapa(mConfig, Ctes.MAPA_CONFIGURACION_JORNADA));
			System.out.println("temp:" + util.getValorMapa(mConfig, Ctes.MAPA_CONFIGURACION_TEMPORADA));
		} catch (Exception e1) {
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al logarse. No se han podido cargar correctamente las variables de configuracion.");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e1.getMessage() + "-->" + e1.getStackTrace());
			return "error";
		}
		

		
		// Se decide lo que se va a pintar en la vista.
		ArrayList<MenuDTO> lMenu = null;

		try {
			
			if (sesion.getAttribute(ConstantesSesion.ADMINISTRADOR).equals(Ctes.SI)){
				lMenu = catalogoServicio.mostrarMenuAdministrador();
			}
			else {
				if (sesion.getAttribute(ConstantesSesion.PERTENECE_LIGA).equals(Ctes.NO)){
					lMenu = catalogoServicio.mostrarMenuNoLiga();
				}else{
					lMenu = catalogoServicio.mostrarMenu();	
				}
			}
			
		} catch (Exception e) {
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al logarse");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
			return "error";
		}

		//model.put(Ctes.OPCMENU, lMenu);
		sesion.setAttribute(ConstantesSesion.OPCMENU, lMenu);
		
		return "home";
	}


	
	@RequestMapping(value = "/cerrarSesion", method = RequestMethod.POST)
    public String cerrarSesion(Locale locale,
			Map<String, Object> model, 
            HttpServletRequest request, 
            HttpServletResponse response,
            HttpSession sesion) {
		
		/* 
			En la clase de la interrupcion se pone la sesión a NO y aqui se
			redirige a la pantalla de inicio por si se quiere logear otra vez.
		*/
		InicializarSesion(sesion);

		
		return "home";
	}
	
	
	@RequestMapping(value = "/registrarse", method = RequestMethod.POST)
    public String registrarse(Locale locale,
			Map<String, Object> model, 
            HttpServletRequest request, 
            HttpServletResponse response,
            HttpSession sesion) {
		return "home";
	}
	
	
	@RequestMapping(value = "/error")
    public String error(Locale locale,
			Map<String, Object> model, 
            HttpServletRequest request, 
            HttpServletResponse response,
            HttpSession sesion) {
		return "error";
	}
	

	@RequestMapping(value = "/jugadores", method = RequestMethod.POST)
    public String jugadores(Locale locale,
			Map<String, Object> model, 
            HttpServletRequest request, 
            HttpServletResponse response,
            HttpSession sesion) {
		
		// Si no esta identificado es un error.
		if (sesion.getAttribute(ConstantesSesion.IDENTIFICADO).equals(Ctes.NO)){
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al obtener los jugadores");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Debe identificarse para poder continuar" );
			return "error";
		}
		
		// Se evaluan las distintas operaciones para los jugadores
		if (request.getParameter("operacion") != null){
			String operacion = request.getParameter("operacion");
			System.out.println("operacion:" + operacion);
			
			if (operacion.equalsIgnoreCase(Ctes.JUGADORES_ELIMINAR)){
				
				String idJugEliminar = request.getParameter("idJugEliminar");
				
				JugadorDTO jDTO = new JugadorDTO();
				jDTO.setCodigo(idJugEliminar);
				
				try {
					servicioJugadores.eliminarJugador(jDTO);
				} catch (Exception e) {
					sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al eliminar un jugador");
					sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
					return "error";
				}				
				
			}else{
				System.out.println("NINGUNA OPERACION");	
			}
		}
		
		
			
		
		ArrayList<JugadorDTO> lJugadores = null;

		try {
			lJugadores = servicioJugadores.mostrarJugadores();
		} catch (Exception e) {
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
			return "error";
		}

		model.put(Ctes.OPCMENU_JUGADORES, lJugadores);
		
		return "jugadores";
	}

	@RequestMapping(value = "/equipo", method = RequestMethod.POST)
    public String equipo(Locale locale,
			Map<String, Object> model, 
            HttpServletRequest request, 
            HttpServletResponse response,
            HttpSession sesion) {
		
		// Si no esta identificado es un error.
		if (sesion.getAttribute(ConstantesSesion.IDENTIFICADO).equals(Ctes.NO)){
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al obtener el equipo");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Debe identificarse para poder continuar" );
			return "error";
		}
			
		
		EquipoDTO equipo = null;

		try {
			equipo = catalogoServicio.mostrarEquipo();
		} catch (Exception e) {
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al mostrar el equipo");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
			return "error";
		}

		model.put(Ctes.OPCMENU_EQUIPO, equipo);
		
		return "equipo";
	}
	
	
	
	
	
	
	

	
	@RequestMapping(value = "/crearLiga", method = RequestMethod.POST)
    public String crearLiga(Locale locale,
			Map<String, Object> model, 
            HttpServletRequest request, 
            HttpServletResponse response,
            HttpSession sesion) {

		// Si no esta identificado es un error.
		if (sesion.getAttribute(ConstantesSesion.IDENTIFICADO).equals(Ctes.NO)){
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al crear la liga");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Debe identificarse para poder continuar" );
			return "error";
		}
		
		// Lo pasado desde la pagina.
		String operacion = request.getParameter("operacion");
		String nbLiga = request.getParameter("nbLiga");
		String pwdLiga = request.getParameter("pwdLiga");
		
		// Se evaluan las distintas operaciones para los jugadores
		if (operacion != null){
			
			//System.out.println("operacion:" + operacion);
			
			if (operacion.equalsIgnoreCase(Ctes.CREAR_LIGA)){
				
				LigaDTO lDTO = new LigaDTO();
				lDTO.setNombre(nbLiga);
				lDTO.setPassword(pwdLiga);
				
				boolean bCrearLiga = false;
				try {
					bCrearLiga = servicioGestionLigas.crearLiga(lDTO);
				} catch (Exception e) {
					sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al crear una liga");
					sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
					return "error";
				}
				
				// Si ha ido bien el alta.
				if (bCrearLiga){
					
					// Ya pertenece a una liga.
					sesion.setAttribute(ConstantesSesion.PERTENECE_LIGA, Ctes.SI);
					ArrayList<MenuDTO> lMenu = null;
					try {
						lMenu = catalogoServicio.mostrarMenu();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					sesion.setAttribute(ConstantesSesion.OPCMENU, lMenu);
					return "home";
				}else{
					
					//System.out.print("HA IDO MAL" + lDTO.getError());
					model.put("errorCrearLiga", lDTO.getError());
				}
		
				
			}else{
				System.out.println("NINGUNA OPERACION - Seguramente sea el click del inicio.");	
			}
		}		
		
		
		
		//System.out.println("CREANDO LIGA...");	
		return "crearLiga";
	}
	
	@RequestMapping(value = "/inscribirseLiga", method = RequestMethod.POST)
    public String inscribirseLiga(Locale locale,
			Map<String, Object> model, 
            HttpServletRequest request, 
            HttpServletResponse response,
            HttpSession sesion) {

		//System.out.println("INSCRIBIRSE EN LIGA...");
		
		// Si no esta identificado es un error.
		if (sesion.getAttribute(ConstantesSesion.IDENTIFICADO).equals(Ctes.NO)){
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al intentar inscribirse en una la liga");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Debe identificarse para poder continuar" );
			return "error";
		}
		
		
		ArrayList<LigaDTO> lLigas = null;
		try {
			lLigas = servicioGestionLigas.obtenerLigas();
		} catch (Exception e) {
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al recuperar las ligas");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
			return "error";
		}
		
		model.put(Ctes.OPCMENU_INSCRIBIR_LIGA_RECUPERAR_LIGAS, lLigas);
		
		return "inscribirseLiga";
	}	
	

	
	
	@RequestMapping(value = "/adminEquipos", method = RequestMethod.POST)
    public String adminEquipos(Locale locale,
			Map<String, Object> model, 
            HttpServletRequest request, 
            HttpServletResponse response,
            HttpSession sesion) {

		
		// Si no esta identificado es un error.
		if (sesion.getAttribute(ConstantesSesion.IDENTIFICADO).equals(Ctes.NO)){
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al administrar equipo real");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Debe identificarse para poder continuar" );
			return "error";
		}
		
		// Si no esta identificado como administrador es un error
		if (sesion.getAttribute(ConstantesSesion.ADMINISTRADOR).equals(Ctes.NO)){
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al administrar equipo real");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Debe identificarse como administrador para poder continuar" );
			return "error";
		}		
		
		String confirmarInserccion = Ctes.NO;
		String confirmarModificacion = Ctes.NO;
		String idEquipoModificar="";
		String equipoNombre="";
		String equipoSiglas="";		
		
		// Se evaluan las distintas operaciones para los jugadores
		if (request.getParameter("operacion") != null){
			String operacion = request.getParameter("operacion");
			System.out.println("operacion:" + operacion);
			
			if (operacion.equalsIgnoreCase(Ctes.EQUIPO_ADMIN_MODIFICAR)){
				
				idEquipoModificar = request.getParameter("idEquipoModificar");
				equipoNombre = request.getParameter("equipoNombre");
				equipoSiglas = request.getParameter("equipoSiglas");
				
				//System.out.println("idEquipoModificar: " + idEquipoModificar);
				//System.out.println("equipoNombre: " + equipoNombre);
				//System.out.println("equipoSiglas: " + equipoSiglas);
				
				confirmarModificacion = Ctes.SI;
					
				
			}else if (operacion.equalsIgnoreCase(Ctes.EQUIPO_ADMIN_CONFIRMAR_MODIFICAR)){
				
				idEquipoModificar = request.getParameter("idEquipoModificar");
				equipoNombre = request.getParameter("equipoNombre");
				equipoSiglas = request.getParameter("equipoSiglas");
				
				//System.out.println("idEquipoModificar: " + idEquipoModificar);
				//System.out.println("equipoNombre: " + equipoNombre);
				//System.out.println("equipoSiglas: " + equipoSiglas);
				
				// Se lleva a cabo la llamada al servicio para modificar un equipo.
				EquipoDTO eDTO = new EquipoDTO();
				eDTO.setCodigo(idEquipoModificar);
				eDTO.setNombre(equipoNombre);
				eDTO.setSiglas(equipoSiglas);
				
				try {
					servicioAdministracion.modificarEquipoCatalogo(eDTO);
				} catch (Exception e) {
					sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al modificar equipo real");
					sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
					return "error";
				}
				
			}else if (operacion.equalsIgnoreCase(Ctes.EQUIPO_ADMIN_INSERTAR)){
				confirmarInserccion = Ctes.SI;
			}else if (operacion.equalsIgnoreCase(Ctes.EQUIPO_ADMIN_CONFIRMAR_INSERTAR)){
				
				equipoNombre = request.getParameter("equipoNombre");
				equipoSiglas = request.getParameter("equipoSiglas");
				
				//System.out.println("equipoNombre Insertar: " + equipoNombre);
				//System.out.println("equipoSiglas Insertar: " + equipoSiglas);
				
				EquipoDTO eDTO = new EquipoDTO();
				eDTO.setNombre(equipoNombre);
				eDTO.setSiglas(equipoSiglas);
				
				try {
					servicioAdministracion.insertarEquipoCatalogo(eDTO);
				} catch (Exception e) {
					sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al insertar equipo real");
					sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
					return "error";
				}				
				
			}
			else{
				System.out.println("NINGUNA OPERACION");	
			}
		}
		
		
		ArrayList<EquipoDTO> lEquipo = null;

		try {
			lEquipo = servicioAdministracion.mostrarCatalogoEquipos();
		} catch (Exception e) {
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al recuperar equipos reales");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
			return "error";
		}

		model.put(Ctes.OPCADMIN_EQUIPO, lEquipo);
		
		// Para indicar que muestre en la pantalla "adminEquipos" para modificar.
		model.put(Ctes.EQUIPO_ADMIN_MOSTRAR_MODIFICAR, confirmarModificacion);
		if (confirmarModificacion.equals(Ctes.SI)){
			model.put(Ctes.EQUIPO_ADMIN_MOSTRAR_MODIFICAR_ID, idEquipoModificar);
			model.put(Ctes.EQUIPO_ADMIN_MOSTRAR_MODIFICAR_NB, equipoNombre);
			model.put(Ctes.EQUIPO_ADMIN_MOSTRAR_MODIFICAR_SG, equipoSiglas);
		}
		
		// Para indicar que muestre en la pantalla "adminEquipos" para insertar.
		model.put(Ctes.EQUIPO_ADMIN_MOSTRAR_INSERTAR, confirmarInserccion);		

		return "adminEquipos";
	}
	
	
	@RequestMapping(value = "/adminJugadores", method = RequestMethod.POST)
    public String adminJugadores(Locale locale,
			Map<String, Object> model, 
            HttpServletRequest request, 
            HttpServletResponse response,
            HttpSession sesion) {

		//System.out.println("adminJugadores...");
		
		// Si no esta identificado es un error.
		if (sesion.getAttribute(ConstantesSesion.IDENTIFICADO).equals(Ctes.NO)){
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al administrar jugador real");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Debe identificarse para poder continuar" );
			return "error";
		}
		
		// Si no esta identificado como administrador es un error
		if (sesion.getAttribute(ConstantesSesion.ADMINISTRADOR).equals(Ctes.NO)){
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al administrar jugador real");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Debe identificarse como administrador para poder continuar" );
			return "error";
		}
		
		
		ArrayList<JugadorDTO> lJugador = null;
		try {
			lJugador = servicioAdministracion.mostrarCatalogoJugadores();
		} catch (Exception e) {
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al recuperar jugadores reales");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
			return "error";
		}
		
		model.put(Ctes.OPCADMIN_JUGADOR, lJugador);
		
		
		return "adminJugadores";
	}
		
	
		
	
	
	
/*
	
	@RequestMapping(value = "/opcionElegida", method = RequestMethod.GET)
	public @ResponseBody String getOpcionElegida(@RequestParam String opcion,
			  									 HttpSession sesion) {
		  
		  logger.info("Llamada AJAX...Opcion Elegida:" + opcion);

		  
		  logger.info("Llamada AJAX...pantallaAPintar:" + pantallaAPintar);
		  
		  return pantallaAPintar;
		  
	  }
*/
	
	
}

