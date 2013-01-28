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
import com.personal.basket.dtos.EconomiaDTO;
import com.personal.basket.dtos.UsuarioDTO;
import com.personal.basket.dtos.EquipoRealDTO;
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
	 * Se comprueba si la sesion esta activa.
	 * @param sesion
	 * @return true: esta activa
	 * 		   false: NO activa
	 */
	private boolean comprobarSesionActiva(HttpSession sesion){
		
		// Compureba si una propiedad es null o no.
		if (sesion.getAttribute(ConstantesSesion.IDENTIFICADO) == null)
			return false;
		else
			return true;

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
		
		sesion.setAttribute(ConstantesSesion.MI_LIGA, Ctes.NO_ASIGNADO_LIGA);
		sesion.setAttribute(ConstantesSesion.MI_EQUIPO, Ctes.NO_ASIGNADO_EQUIPO);
		
		
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

		
		// Se crea la sesion.
    	HttpSession session = request.getSession(true);
    	session.setMaxInactiveInterval(-1);
    	
    	//System.out.println("Se crea la sesion con tiempo infinito:" + session.getId());
		
		// Se inicializan las variables de sesion.
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
			
			
			InicializarSesion(sesion);
			/*
			sesion.setAttribute(ConstantesSesion.ADMINISTRADOR, Ctes.NO);
			sesion.setAttribute(ConstantesSesion.IDENTIFICADO, Ctes.NO);
			sesion.setAttribute(ConstantesSesion.PERTENECE_LIGA, Ctes.NO);
			sesion.setAttribute(ConstantesSesion.TIENE_EQUIPO, Ctes.NO);
			sesion.setAttribute(ConstantesSesion.MI_LIGA, Ctes.NO_ASIGNADO_LIGA);
			sesion.setAttribute(ConstantesSesion.MI_EQUIPO, Ctes.NO_ASIGNADO_EQUIPO);			
			*/
			
			UsuarioDTO dPers = catalogoServicio.loggearse(request.getParameter("nombreReg"), 
														 request.getParameter("contraReg"));
			

			
			// Si esta logado, entonces puede tener liga y si tiene liga entonces puede tener equipo.
			// Se establecen constantes de sesion.
			if (dPers.isAdministrador()){
				sesion.setAttribute(ConstantesSesion.IDENTIFICADO, Ctes.SI);
				sesion.setAttribute(ConstantesSesion.ADMINISTRADOR, Ctes.SI);
			}else if (dPers.isLogado()){
				sesion.setAttribute(ConstantesSesion.IDENTIFICADO, Ctes.SI);
				if (!dPers.getCodigoLiga().equals(Ctes.NO_ASIGNADO_LIGA)){
					sesion.setAttribute(ConstantesSesion.PERTENECE_LIGA, Ctes.SI);
					sesion.setAttribute(ConstantesSesion.MI_LIGA, dPers.getCodigoLiga());
					if (!dPers.getCodigoLiga().equals(Ctes.NO_ASIGNADO_EQUIPO)){
						sesion.setAttribute(ConstantesSesion.TIENE_EQUIPO, Ctes.SI);
						sesion.setAttribute(ConstantesSesion.MI_EQUIPO, dPers.getCodigoEquipo());
					}
				}
			}
			
			// Se guarda el mapa con lo que hay en la tabla de configuracion.
			sesion.setAttribute(Ctes.MAPA_CONFIGURACION, dPers.getMapConfiguracion());

			System.out.println("Persona logada?:" + dPers.isLogado()+ " y esta Logado: " + sesion.getAttribute(ConstantesSesion.IDENTIFICADO));
			System.out.println("Codigo Liga:" + dPers.getCodigoLiga()+ " y pertenece Liga: " + sesion.getAttribute(ConstantesSesion.PERTENECE_LIGA));
			System.out.println("Cod. Equipo:" + dPers.getCodigoEquipo()+ " y tiene Equipo: " + sesion.getAttribute(ConstantesSesion.TIENE_EQUIPO));
			
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
				sesion.setAttribute(ConstantesSesion.OPCMENUTEXTO, "Administrador");
			}
			else {
				if (sesion.getAttribute(ConstantesSesion.PERTENECE_LIGA).equals(Ctes.NO)){
					sesion.setAttribute(ConstantesSesion.OPCMENUTEXTO, "Menu");
					lMenu = catalogoServicio.mostrarMenuNoLiga();
				}else{
					sesion.setAttribute(ConstantesSesion.OPCMENUTEXTO, "Menu");
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
		
		sesion.setMaxInactiveInterval(ConstantesSesion.TIEMPO_SESION);
		//System.out.println("Se crea la sesion con tiempo " + ConstantesSesion.TIEMPO_SESION + " y sesion " + sesion.getId());
		
		return "home";
	}


	
	@RequestMapping(value = "/cerrarSesion", method = RequestMethod.POST)
    public String cerrarSesion(Locale locale,
			Map<String, Object> model, 
            HttpServletRequest request, 
            HttpServletResponse response,
            HttpSession sesion) {
		
		// destruye la sesion
		sesion.invalidate();
		
		return "home";
	}
	
	
	@RequestMapping(value = "/registrarse", method = RequestMethod.POST)
    public String registrarse(Locale locale,
			Map<String, Object> model, 
            HttpServletRequest request, 
            HttpServletResponse response,
            HttpSession sesion) {
		
		
		if (comprobarSesionActiva(sesion) == false)
			return "inactividad";
		
		
		
		if (sesion.getAttribute(ConstantesSesion.IDENTIFICADO).equals(Ctes.SI)){
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Imposible registrarse");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Ya se encuentra identificado y por lo tanto no puede registrarse." );
			return "error";
		}
		
		String operacion = request.getParameter("operacion");
		
		if (operacion != null){
			
			if (operacion.equalsIgnoreCase(Ctes.CONFIRMAR_REGISTRO)){
				
				String loginRegistro = request.getParameter("loginRegistro");
				String pwdRegistro = request.getParameter("pwdRegistro");
				String pwd2Registro = request.getParameter("pwd2Registro");
				String emailRegistro = request.getParameter("emailRegistro");
				String email2Registro = request.getParameter("email2Registro");
				String selAnoNacRegistro = request.getParameter("selAnoNacRegistro");
				String selSexRegistro = request.getParameter("selSexRegistro");
				String chPolitica = request.getParameter("chPolitica");
				
				boolean bCumpleRestricciones = true;

				
				/*
				System.out.println("loginRegistro: " + loginRegistro);
				System.out.println("pwdRegistro: " + pwdRegistro);
				System.out.println("pwd2Registro: " + pwd2Registro);
				System.out.println("emailRegistro: " + emailRegistro);
				System.out.println("email2Registro: " + email2Registro);
				System.out.println("selAnoNacRegistro: " + selAnoNacRegistro);
				System.out.println("selSexRegistro: " + selSexRegistro);// H-Hombre ; M-Mujer
				System.out.println("chPolitica: " + chPolitica); // N-No acepta; S-Si Acepta.
				*/
				
				// Comprobaciones a nivel de servidor para el registro
				if (loginRegistro == null || loginRegistro.length()<1){
					model.put("errorRegistro", "El login no debe ir relleno.");
					bCumpleRestricciones = false;
				}
				
				if (pwdRegistro == null || pwdRegistro.length()<1){
					model.put("errorRegistro", "La password debe ir rellena.");
					bCumpleRestricciones = false;
				}
				if (pwd2Registro == null || pwd2Registro.length()<1){
					model.put("errorRegistro", "La re-password debe ir rellena.");
					bCumpleRestricciones = false;
				}
				if (!pwdRegistro.equals(pwd2Registro)){
					model.put("errorRegistro", "Las password deben coincidir.");
					bCumpleRestricciones = false;
				}
				
				if (emailRegistro == null || emailRegistro.length()<1){
					model.put("errorRegistro", "El email debe ir relleno.");
					bCumpleRestricciones = false;
				}
				if (email2Registro == null || email2Registro.length()<1){
					model.put("errorRegistro", "El re-email debe ir relleno.");
					bCumpleRestricciones = false;
				}
				if (!emailRegistro.equals(email2Registro)){
					model.put("errorRegistro", "Los e-mail deben coincidir.");
					bCumpleRestricciones = false;
				}				
	
				
				if (selAnoNacRegistro.equals("---")){
					model.put("errorRegistro", "Debe seleccionar un año de nacimiento");
					bCumpleRestricciones = false;
				}

				if (selSexRegistro.equals("---")){
					model.put("errorRegistro", "Debe seleccionar un sexo");
					bCumpleRestricciones = false;
				}

				if (chPolitica.equalsIgnoreCase(Ctes.NO)){
					model.put("errorRegistro", "Debe confirmar la politca de privacidad de datos.");
					bCumpleRestricciones = false;
				}
				
				// Si todo ha ido bien.
				if (bCumpleRestricciones == true){
					
					try {
						
						
						// Se comprueba si existe ya para otro usuario ese login.
						boolean bExisteUsuario = catalogoServicio.existeUsuario(loginRegistro);
						if (bExisteUsuario){
							model.put("errorRegistro", "Ya existe un usuario con ese mismo login. Elija otro login");
							return "registrarse";							
						}
						
						// Se genera la economía.
						EconomiaDTO economia = catalogoServicio.setEconomia();
						if (economia.getCodigoEcono() == Ctes.NO_ASIGNADO_ECONOMIA){
							sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al realizar el registro");
							sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Implosible realizar el registro porque no se puede establecer una economía.");
							return "error";
						}
						
						
						// Se inserta por fin el usuario con su economia.
						UsuarioDTO dPers = catalogoServicio.registrarse(
													loginRegistro, 
													pwdRegistro,
													emailRegistro,
													selAnoNacRegistro,
													selSexRegistro,
													economia.getCodigoEcono());
						

						model.put("okRegistro", "Usuario " + loginRegistro + " se ha registrado de forma correcta.");
	
						// Se inicializa la sesion con todos los valores por defecto.
						InicializarSesion(sesion);

						
						
					} catch (Exception e) {
						sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al realizar el registro");
						sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
						return "error";
					}
					

					
				}
				
				
			}// Ctes.CONFIRMAR_REGISTRO

				
				
		}else{
			System.out.println("NINGUNA OPERACION - Seguramente sea el click del inicio.");	
		}		


		
		return "registrarse";
	}
	
	
	@RequestMapping(value = "/error")
    public String error(Locale locale,
			Map<String, Object> model, 
            HttpServletRequest request, 
            HttpServletResponse response,
            HttpSession sesion) {
		return "error";
	}
	

	@RequestMapping(value = "/equipo", method = RequestMethod.POST)
    public String equipo(Locale locale,
			Map<String, Object> model, 
            HttpServletRequest request, 
            HttpServletResponse response,
            HttpSession sesion) {
		
		if (comprobarSesionActiva(sesion) == false)
			return "inactividad";
		
		
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
				jDTO.setCodigoJugador(idJugEliminar);
				
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
		
		return "equipo";
	}

	/*
	@RequestMapping(value = "/equipoReal", method = RequestMethod.POST)
    public String equipoReal(Locale locale,
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
	*/
	
	@RequestMapping(value = "/liga", method = RequestMethod.POST)
    public String liga(Locale locale,
			Map<String, Object> model, 
            HttpServletRequest request, 
            HttpServletResponse response,
            HttpSession sesion) {
		
		if (comprobarSesionActiva(sesion) == false)
			return "inactividad";

		
		System.out.println("LIGA");
		
		/**
		 * Aqui se pueden las variables de sesion  
		 * sesion.getAttribute(ConstantesSesion.MI_LIGA)
		 * sesion.getAttribute(ConstantesSesion.MI_EQUIPO)
		 * tendrían los valores correctos y en la vista se puede pintar diferente dependiendo
		 * de lo que contengan esas dos variables de sesion.
		 */
		

		return "liga";
		
	}
	
	
	
	
	
	
	
	

	
	@RequestMapping(value = "/crearLiga", method = RequestMethod.POST)
    public String crearLiga(Locale locale,
			Map<String, Object> model, 
            HttpServletRequest request, 
            HttpServletResponse response,
            HttpSession sesion) {
		
		if (comprobarSesionActiva(sesion) == false)
			return "inactividad";
		
		logger.info("Se ha pulsado el boton de Crear Liga");

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
		String pwd2Liga = request.getParameter("pwd2Liga");
		String chPublica = request.getParameter("chPublica"); //(S|N)
		
		
		

		
		// Se evaluan las distintas operaciones para los jugadores
		if (operacion != null){
			
			//System.out.println("operacion:" + operacion);
			
			if (operacion.equalsIgnoreCase(Ctes.CREAR_LIGA)){
				
				// Solo si la liga no es publica entonces se deben comprobar contraseñas.
				if (chPublica.equals(Ctes.NO)){
					/* Se comprueba que coincidan las contraseñas.*/
					if (!pwdLiga.equals(pwd2Liga)){
						sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al crear la liga");
						sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "La contraseñas no coinciden." );
						return "error";
					}
				}
					

				
				LigaDTO lDTO = new LigaDTO();
				lDTO.setNombre(nbLiga);
				lDTO.setPassword(pwdLiga);
				
				String sCrearLiga = "";
				try {
					sCrearLiga = servicioGestionLigas.crearLiga(lDTO);
				} catch (Exception e) {
					sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al crear una liga");
					sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
					return "error";
				}
				
				// Si ha ido bien el alta.
				if (!sCrearLiga.equals(Ctes.NO_ASIGNADO_LIGA)){
					
					// TODO: SE DEBERIA REALIZAR UPDATE AL USUARIO CON EL CODIGO DE LA LIGA.
					
					// Ya pertenece a una liga.
					sesion.setAttribute(ConstantesSesion.PERTENECE_LIGA, Ctes.SI);
					sesion.setAttribute(ConstantesSesion.MI_LIGA, sCrearLiga);
							
					
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
				System.out.println("NINGUNA OPERACION - POR AKI NUNCA");	
			}
		}else{
			System.out.println("NINGUNA OPERACION - Seguramente sea el click del inicio.");	
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

		if (comprobarSesionActiva(sesion) == false)
			return "inactividad";
		
		// Si no esta identificado es un error.
		if (sesion.getAttribute(ConstantesSesion.IDENTIFICADO).equals(Ctes.NO)){
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al intentar inscribirse en una la liga");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Debe identificarse para poder continuar" );
			return "error";
		}
		
		// Si pertence a una liga es un error porque no se puede inscribir.
		if (sesion.getAttribute(ConstantesSesion.PERTENECE_LIGA).equals(Ctes.SI)){
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al intentar inscribirse en una la liga");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Ya pertenece a una liga y no puede estar inscrito en mas de una." );
			return "error";
		}
		
		
		String operacion = request.getParameter("operacion");
				
		if (operacion != null){

			if (operacion.equalsIgnoreCase(Ctes.BUSCAR_LIGA)){
				
				String nbLigaBuscar = request.getParameter("nbLigaBuscar");
				
				ArrayList<LigaDTO> lLigas = null;
				try {
					lLigas = servicioGestionLigas.obtenerLigas(nbLigaBuscar);
				} catch (Exception e) {
					sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al recuperar las ligas");
					sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
					return "error";
				}
				
				model.put(Ctes.OPCMENU_LIGAS_RECUPERADAS, lLigas);
				
				if (lLigas.size()<=0){
					model.put(Ctes.OPCMENU_HAY_LIGAS_RECUPERADAS,Ctes.NO);
				}
				else{
					model.put(Ctes.OPCMENU_HAY_LIGAS_RECUPERADAS,Ctes.SI);
				}
					
				
				
			}else if (operacion.equalsIgnoreCase(Ctes.INSCRIBIR_LIGA)){
				
				// Formato de "selLiga" codLiga#publica
				//	Ejemplos:
				//		5#true  - codigo liga=5 y es publica
				//	   12#false - codigo liga=12 y es privada				
				String selLiga = request.getParameter("selLiga");
				String passLiga = request.getParameter("passLiga");
				
				String[]temp = selLiga.split("#");
				System.out.println("Codigo Liga    : " + temp[0]);
				System.out.println("Es liga publica: " + temp[1]);
				
				LigaDTO lDTO = new LigaDTO();
				lDTO.setCodigoLiga(temp[0]);
				lDTO.setPassword(passLiga);
				if (temp[1].equals("true"))
					lDTO.setLigaPublica(true);
				else
					lDTO.setLigaPublica(false);
				
				String sInscrito = "";
				try {
					sInscrito = servicioGestionLigas.inscribirLiga(lDTO);
				} catch (Exception e) {
					sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al inscribirse en una liga");
					sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
					return "error";
				}
					
				if (!sInscrito.equals(Ctes.NO_ASIGNADO_LIGA)){
					
					// TODO: SE DEBERIA REALIZAR UPDATE AL USUARIO CON EL CODIGO DE LA LIGA.
					
					sesion.setAttribute(ConstantesSesion.PERTENECE_LIGA, Ctes.SI);
					sesion.setAttribute(ConstantesSesion.MI_LIGA, sInscrito);
					
					ArrayList<MenuDTO> lMenu = null;
					try {
						lMenu = catalogoServicio.mostrarMenu();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					
					
					sesion.setAttribute(ConstantesSesion.OPCMENU, lMenu);
					return "liga";
					
				}else{
					
					// Si falla se debe gestionar de otra manera mejor.
					//FALTAN HACER COSAS
					//FALTAN HACER COSAS
					//FALTAN HACER COSAS
					//FALTAN HACER COSAS
					//FALTAN HACER COSAS						
				}

				
			}else{
				System.out.println("NINGUNA OPERACION - POR AKI NUNCA");	
			}
			
		}else{
			System.out.println("NINGUNA OPERACION - Seguramente sea el click del inicio.");	
		}
		

		
		
		return "inscribirseLiga";
	}	
	

	
	
	@RequestMapping(value = "/adminEquipos", method = RequestMethod.POST)
    public String adminEquipos(Locale locale,
			Map<String, Object> model, 
            HttpServletRequest request, 
            HttpServletResponse response,
            HttpSession sesion) {

		if (comprobarSesionActiva(sesion) == false)
			return "inactividad";

		
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
				EquipoRealDTO eDTO = new EquipoRealDTO();
				eDTO.setCodigoEquipoReal(idEquipoModificar);
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
				
				EquipoRealDTO eDTO = new EquipoRealDTO();
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
		
		
		ArrayList<EquipoRealDTO> lEquipo = null;

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

		if (comprobarSesionActiva(sesion) == false)
			return "inactividad";
		
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

