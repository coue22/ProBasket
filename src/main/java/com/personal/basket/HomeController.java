package com.personal.basket;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



import com.personal.basket.ctes.ConstantesSesion;
import com.personal.basket.ctes.Ctes;

import com.personal.basket.dtos.ConfiguracionDTO;
import com.personal.basket.dtos.EconomiaDTO;
import com.personal.basket.dtos.NacionalidadDTO;
import com.personal.basket.dtos.OperacionDTO;
import com.personal.basket.dtos.RoleDTO;
import com.personal.basket.dtos.SalidaInscripcionDTO;
import com.personal.basket.dtos.UsuarioDTO;
import com.personal.basket.dtos.EquipoRealDTO;
import com.personal.basket.dtos.JugadorRealDTO;
import com.personal.basket.dtos.LigaDTO;
import com.personal.basket.dtos.MenuDTO;

import com.personal.basket.servicios.CatalogoServicios;
import com.personal.basket.servicios.ServiciosAdministracion;
import com.personal.basket.servicios.ServiciosGestionLigas;
import com.personal.basket.servicios.ServiciosSingleton;

import com.personal.basket.util.util;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	
	private HashMap<String, ConfiguracionDTO> hmConfiguracion  = null;
	private HashMap<String, NacionalidadDTO> hmNacionalidad  = null;
	private HashMap<String, OperacionDTO> hmOperaciones = null;
	private HashMap<String, RoleDTO> hmRoles  = null;
	private HashMap<String, EquipoRealDTO> hmEquiposReales  = null;
	private HashMap<String, JugadorRealDTO> hmJugadoresReales  = null;
	
	
	
	@Autowired
	CatalogoServicios catalogoServicio;
	
	@Autowired
	ServiciosAdministracion servicioAdministracion;
	
	@Autowired
	ServiciosGestionLigas servicioGestionLigas;
	
	@Autowired
	ServiciosSingleton servicioSingleton;
	

	public CatalogoServicios getCatalogoServicio() {
		return catalogoServicio;
	}
	public void setCatalogoServicio(CatalogoServicios catalogoServicio) {
		this.catalogoServicio = catalogoServicio;
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
	
	
	
	public ServiciosSingleton getServicioSingleton() {
		return servicioSingleton;
	}
	public void setServicioSingleton(ServiciosSingleton servicioSingleton) {
		this.servicioSingleton = servicioSingleton;
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
		
		// INYECCION DE DEPENDENCIA PARA TRANSACCIONES
		// NO PROBADO...
		//DataSourceTransactionManager transactionManager;
		
		
		logger.info("Welcome home! the client locale is "+ locale.toString());

		
		// Se crea la sesion.
    	HttpSession session = request.getSession(true);
    	session.setMaxInactiveInterval(-1);
    	
    	//System.out.println("Se crea la sesion con tiempo infinito:" + session.getId());
		
		// Se inicializan las variables de sesion.
		InicializarSesion(sesion);
		
		// Se recuperan los valores que son iguales para todos los usuarios.
		try {

			hmConfiguracion = servicioSingleton.getConfiguracion(Ctes.NO);
			hmNacionalidad = servicioSingleton.getNacionalidades(Ctes.NO);
			hmOperaciones = servicioSingleton.getOperaciones(Ctes.NO);
			hmRoles = servicioSingleton.getRoles(Ctes.NO);
			hmEquiposReales = servicioSingleton.getEquiposReales(Ctes.NO);
			hmJugadoresReales = servicioSingleton.getJugadoresReales(Ctes.NO);
			
/*			
			System.out.println("VARIABLES DE CONFIGURACION");
			System.out.println("--------------------------");
			Iterator it = hmConfiguracion.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry e = (Map.Entry)it.next();
				System.out.println(e.getKey() + ": " + ((ConfiguracionDTO)e.getValue()).getValor());
			}
*/			
			
			
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
					sesion.setAttribute(ConstantesSesion.COD_ECONO, dPers.getCodigoEcono());
					

					if (!dPers.getCodigoLiga().equals(Ctes.NO_ASIGNADO_EQUIPO)){
						sesion.setAttribute(ConstantesSesion.TIENE_EQUIPO, Ctes.SI);
						sesion.setAttribute(ConstantesSesion.MI_EQUIPO, dPers.getCodigoEquipo());

					}
				}
			}
			
			
			
			// Se guarda el mapa con lo que hay en la tabla de configuracion.
			//sesion.setAttribute(Ctes.MAPA_CONFIGURACION, dPers.getMapConfiguracion());

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
		
		// Se comprueba que existen las variables de configuracion.
/*
		try {
			
			ConfiguracionDTO conf1 = util.getValorMapaConfiguracion(hmConfiguracion,Ctes.MAPA_CONFIGURACION_TEMPORADA);
			ConfiguracionDTO conf2 = util.getValorMapaConfiguracion(hmConfiguracion,Ctes.MAPA_CONFIGURACION_JORNADA);
			
			//System.out.println("jornada  : " + conf1.getValor().toString() );
			//System.out.println("temporada: " + conf2.getValor().toString() );
			
		} catch (Exception e1) {
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al logarse.");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Actualmento no es posible cargar correctamente la configuración de la aplicacion.");
			return "error";
		}
*/	

		// Se decide lo que se va a pintar en la vista.
		ArrayList<MenuDTO> lMenu = null;

		try {
			
			if (sesion.getAttribute(ConstantesSesion.ADMINISTRADOR).equals(Ctes.SI)){
				//lMenu = catalogoServicio.mostrarMenuAdministrador();
				lMenu = catalogoServicio.mostrarMenu(Ctes.MENU_ADMIN);
				sesion.setAttribute(ConstantesSesion.TIPOMENU, "1");
				sesion.setAttribute(ConstantesSesion.OPCMENUTEXTO, "Administrador");/*Administrador*/
			}
			else {
				if (sesion.getAttribute(ConstantesSesion.PERTENECE_LIGA).equals(Ctes.NO)){
					sesion.setAttribute(ConstantesSesion.TIPOMENU, "2");
					sesion.setAttribute(ConstantesSesion.OPCMENUTEXTO, "Menu");
					//lMenu = catalogoServicio.mostrarMenuNoLiga();/*Crear e inscribirte en liga*/
					lMenu = catalogoServicio.mostrarMenu(Ctes.MENU_NOMENU);
				}else{
					
					ConfiguracionDTO cDTO = null;
					try {
						cDTO = util.getValorMapaConfiguracion(hmConfiguracion, Ctes.MAPA_CONFIGURACION_DRAFT);
					} catch (Exception e1) {
						sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al inscribirse en una liga.");
						sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Sin la variable de entorno del Draft no se puede continuar.");
						return "error";
					}					
					
					System.out.println("El valor de " + cDTO.getParametro().toString() + " es: " + cDTO.getValor().toString());
					
					if (cDTO.getValor().toString().equalsIgnoreCase(Ctes.SI)){
						sesion.setAttribute(ConstantesSesion.TIPOMENU, "3");
						sesion.setAttribute(ConstantesSesion.OPCMENUTEXTO, "Menu");
						//lMenu = catalogoServicio.mostrarMenuDraft();/* Es tiempo de draft*/
						lMenu = catalogoServicio.mostrarMenu(Ctes.MENU_DRAFT);
					}else{
						sesion.setAttribute(ConstantesSesion.TIPOMENU, "4");
						sesion.setAttribute(ConstantesSesion.OPCMENUTEXTO, "Menu");
						//lMenu = catalogoServicio.mostrarMenu();	/* Es tiempo de jugar */
						lMenu = catalogoServicio.mostrarMenu(Ctes.MENU_NORMAL);
					}

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
	
// SE DEBE HACER BIEN.
	@RequestMapping(value = "/equipo", method = RequestMethod.POST)
    public String equipo(Locale locale,
			Map<String, Object> model, 
            HttpServletRequest request, 
            HttpServletResponse response,
            HttpSession sesion) {
		
/*		
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
				
				JugadorRealDTO jDTO = new JugadorRealDTO();
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
		
		
			
		
		ArrayList<JugadorRealDTO> lJugadores = null;

		try {
			lJugadores = servicioJugadores.mostrarJugadores();
		} catch (Exception e) {
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
			return "error";
		}

		model.put(Ctes.OPCMENU_JUGADORES, lJugadores);
		
		return "equipo";
*/
		return "home";
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
				
				return "home";
				
/*
					
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
*/

		
				
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
				String nbEquipo = request.getParameter("nbEquipo");
				
				
				String[]temp = selLiga.split("#");
				System.out.println("Codigo Liga    : " + temp[0]);
				System.out.println("Es liga publica: " + temp[1]);
				System.out.println("nombre Equipo  : " + nbEquipo);
				
				LigaDTO lDTO = new LigaDTO();
				lDTO.setCodigoLiga(temp[0]);
				lDTO.setPassword(passLiga);
				if (temp[1].equals("true"))
					lDTO.setLigaPublica(true);
				else
					lDTO.setLigaPublica(false);
				
				
				
				SalidaInscripcionDTO salDTO = null;
				try {
					salDTO = servicioGestionLigas.inscribirLiga(lDTO);
				} catch (Exception e) {
					sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al inscribirse en una liga");
					sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
					return "error";
				}
				
				sesion.setAttribute(ConstantesSesion.PERTENECE_LIGA, Ctes.SI);
				sesion.setAttribute(ConstantesSesion.MI_LIGA, salDTO.getCodigoLiga());

				sesion.setAttribute(ConstantesSesion.TIENE_EQUIPO, Ctes.SI);
				sesion.setAttribute(ConstantesSesion.MI_EQUIPO, salDTO.getCodigoEquipo());
				
				sesion.setAttribute(ConstantesSesion.COD_ECONO, salDTO.getCodigoEcono());


				ConfiguracionDTO cDTO = null;
				try {
					cDTO = util.getValorMapaConfiguracion(hmConfiguracion, Ctes.MAPA_CONFIGURACION_DRAFT);
				} catch (Exception e1) {
					sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al inscribirse en una liga.");
					sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Sin la variable de entorno del Draft no se puede continuar.");
					return "error";
				}
				
				System.out.println("El valor de " + cDTO.getParametro().toString() + " es: " + cDTO.getValor().toString());
				
				ArrayList<MenuDTO> lMenu = null;
				
				// Si el valor de draft es SI
				if (cDTO.getValor().toString().equalsIgnoreCase(Ctes.SI)){
					
					try {
						//lMenu = catalogoServicio.mostrarMenuDraft();
						lMenu = catalogoServicio.mostrarMenu(Ctes.MENU_DRAFT);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					sesion.setAttribute(ConstantesSesion.TIPOMENU, "3");
					sesion.setAttribute(ConstantesSesion.OPCMENUTEXTO, "Menu");
					sesion.setAttribute(ConstantesSesion.OPCMENU, lMenu);
					return "home"; //TODO : Poner bien esta redireccion en caso de estar mal
					
				}else{

					try {
						//lMenu = catalogoServicio.mostrarMenu();
						lMenu = catalogoServicio.mostrarMenu(Ctes.MENU_NORMAL);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}	
					sesion.setAttribute(ConstantesSesion.TIPOMENU, "4");
					sesion.setAttribute(ConstantesSesion.OPCMENUTEXTO, "Menu");
					sesion.setAttribute(ConstantesSesion.OPCMENU, lMenu);
					return "home"; //TODO : Poner bien esta redireccion en caso de estar mal
				}

				
			}else{
				System.out.println("NINGUNA OPERACION - POR AKI NUNCA");	
			}
			
		}else{
			System.out.println("NINGUNA OPERACION - Seguramente sea el click del inicio.");	
		}
		

		
		
		
		return "inscribirseLiga";
	}	
	

	@RequestMapping(value = "/adminConfiguracion", method = RequestMethod.POST)
    public String adminConfiguracion(Locale locale,
			Map<String, Object> model, 
            HttpServletRequest request, 
            HttpServletResponse response,
            HttpSession sesion) {
		
		if (comprobarSesionActiva(sesion) == false)
			return "inactividad";
		
		// Si no esta identificado es un error.
		if (sesion.getAttribute(ConstantesSesion.IDENTIFICADO).equals(Ctes.NO)){
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al administrar configuracion");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Debe identificarse para poder continuar" );
			return "error";
		}
		
		// Si no esta identificado como administrador es un error
		if (sesion.getAttribute(ConstantesSesion.ADMINISTRADOR).equals(Ctes.NO)){
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al administrar configuracion");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Debe identificarse como administrador para poder continuar" );
			return "error";
		}	
		
		String operacion = request.getParameter("operacion");
		
		// Se evaluan las distintas operaciones para los jugadores
		if (operacion != null){
			
			// Si se ha pulsado modificar una configuracion.
			if (operacion.equalsIgnoreCase(Ctes.CONFIG_ADMIN_CONFIRMAR_MODIFICAR)){
				
				String parametro = request.getParameter("parametro");
				String valor = request.getParameter("valor");
				
				System.out.println("param a modificar: " + parametro);
				System.out.println("valor a modificar: " + valor);
				
				ConfiguracionDTO cDTO = new ConfiguracionDTO();
				cDTO.setParametro(parametro);
				cDTO.setValor(valor);
				
				boolean bModificado = false;
				try {
					bModificado = servicioAdministracion.modificarConfiguracion(cDTO);
				} catch (Exception e) {
					sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al modificar un parametro de la configuracion");
					sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
					return "error";
				}
				
	
			}else if (operacion.equalsIgnoreCase(Ctes.CONFIG_ADMIN_CONFIRMAR_INSERTAR)){
				String parametro = request.getParameter("parametro");
				String valor = request.getParameter("valor");
				
				//System.out.println("param a insertar: " + parametro);
				//System.out.println("valor a insertar: " + valor);
				
				ConfiguracionDTO cDTO = new ConfiguracionDTO();
				cDTO.setParametro(parametro);
				cDTO.setValor(valor);
				
				boolean bModificado = false;
				try {
					bModificado = servicioAdministracion.insertarConfiguracion(cDTO);
				} catch (Exception e) {
					sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al insertar un parametro de la configuracion");
					sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
					return "error";
				}				
				
			}else{
				sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error en la configuracion");
				sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Operacion no admitida");
				return "error";
			}
			
			
		}
		
		try {
			
			String refrescar = Ctes.NO;
			
			if (operacion != null){
				
				if (operacion.equalsIgnoreCase(Ctes.CONFIG_ADMIN_CONFIRMAR_MODIFICAR)){
					refrescar = Ctes.SI;
				}	
				if (operacion.equalsIgnoreCase(Ctes.CONFIG_ADMIN_CONFIRMAR_INSERTAR)){
					refrescar = Ctes.SI;
				}	
			}
			
			
			hmConfiguracion = servicioSingleton.getConfiguracion(refrescar);
			
		} catch (Exception e) {
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al recuperar la configuracion");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
			return "error";
		}
		model.put(Ctes.OPCADMIN_CONFIGURACION, hmConfiguracion);
		
		
		
		return "adminConfiguracion";
	}

	
	@RequestMapping(value = "/adminNacionalidad", method = RequestMethod.POST)
    public String adminNacionalidad(Locale locale,
			Map<String, Object> model, 
            HttpServletRequest request, 
            HttpServletResponse response,
            HttpSession sesion) {
		
		if (comprobarSesionActiva(sesion) == false)
			return "inactividad";
		
		// Si no esta identificado es un error.
		if (sesion.getAttribute(ConstantesSesion.IDENTIFICADO).equals(Ctes.NO)){
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al administrar nacionalidades");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Debe identificarse para poder continuar" );
			return "error";
		}
		
		// Si no esta identificado como administrador es un error
		if (sesion.getAttribute(ConstantesSesion.ADMINISTRADOR).equals(Ctes.NO)){
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al administrar nacionalidades");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Debe identificarse como administrador para poder continuar" );
			return "error";
		}	
		
		String operacion = request.getParameter("operacion");
		
		// Se evaluan las distintas operaciones para las nacionalidades
		if (operacion != null){

			// Si se ha pulsado eliminar una nacionalidad.
			if (operacion.equalsIgnoreCase(Ctes.CONFIG_NACIO_CONFIRMAR_ELIMINAR)){
				
				String codigoNacion = request.getParameter("codigoNacion");
				
				NacionalidadDTO nDTO = new NacionalidadDTO();
				nDTO.setCodigoNacionalidad(codigoNacion);
				
				try {
					servicioAdministracion.eliminarNacionalidad(nDTO);
				} catch (Exception e) {
					sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al eliminar la nacionalidad");
					sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
					return "error";
				}
				
			}else if (operacion.equalsIgnoreCase(Ctes.CONFIG_NACIO_CONFIRMAR_INSERTAR)){
				
				
				String descripcion = request.getParameter("descripcion");
				
				NacionalidadDTO nDTO = new NacionalidadDTO();
				nDTO.setDescripcion(descripcion);

				try {
					servicioAdministracion.insertarNacionalidad(nDTO);
				} catch (Exception e) {
					sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al insertar la nacionalidad");
					sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
					return "error";
				}

			}else{
				sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error en la nacionalidad");
				sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Operacion no admitida");
				return "error";
			}
			
		}

		
		try {
			String refrescar = Ctes.NO;
			
			if (operacion != null){
				
				if (operacion.equalsIgnoreCase(Ctes.CONFIG_NACIO_CONFIRMAR_ELIMINAR)){
					refrescar = Ctes.SI;
				}	
				if (operacion.equalsIgnoreCase(Ctes.CONFIG_NACIO_CONFIRMAR_INSERTAR)){
					refrescar = Ctes.SI;
				}	
			}

			hmNacionalidad = servicioSingleton.getNacionalidades(refrescar);
			
		} catch (Exception e) {
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al recuperar las nacionalidades. ");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
			return "error";
		}
		

		
		model.put(Ctes.OPCADMIN_NACIONALIDAD, hmNacionalidad);
		
		return "adminNacionalidad";
	}


	
	@RequestMapping(value = "/adminOperacion", method = RequestMethod.POST)
    public String adminOperacion(Locale locale,
			Map<String, Object> model, 
            HttpServletRequest request, 
            HttpServletResponse response,
            HttpSession sesion) {
		
		if (comprobarSesionActiva(sesion) == false)
			return "inactividad";
		
		// Si no esta identificado es un error.
		if (sesion.getAttribute(ConstantesSesion.IDENTIFICADO).equals(Ctes.NO)){
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al administrar operaciones");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Debe identificarse para poder continuar" );
			return "error";
		}
		
		// Si no esta identificado como administrador es un error
		if (sesion.getAttribute(ConstantesSesion.ADMINISTRADOR).equals(Ctes.NO)){
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al administrar operaciones");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Debe identificarse como administrador para poder continuar" );
			return "error";
		}	
		
		String operacion = request.getParameter("operacion");
		
		// Se evaluan las distintas operaciones para las nacionalidades
		if (operacion != null){
			if (operacion.equalsIgnoreCase(Ctes.CONFIG_OPERA_CONFIRMAR_MODIFICAR)){
				

				
				String codOpera = request.getParameter("codOpera");
				String descOpera = request.getParameter("descOpera");
				
				System.out.println("codigo operacion para modificar su descripcion: " + codOpera);
				System.out.println("valor a modificar: " + descOpera);
				
				OperacionDTO oDTO = new OperacionDTO();
				oDTO.setCodigoOpera(codOpera);
				oDTO.setDescripcion(descOpera);
				
				try {
					servicioAdministracion.modificarOperacion(oDTO);
				} catch (Exception e) {
					sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al modificar la operacion");
					sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
					return "error";
				}
				
				
			}else if (operacion.equalsIgnoreCase(Ctes.CONFIG_OPERA_CONFIRMAR_INSERTAR)){
				
				String descOpera = request.getParameter("descOpera");
				
				System.out.println("descripcion a insertar: " + descOpera);
				
				OperacionDTO oDTO = new OperacionDTO();
				oDTO.setDescripcion(descOpera);			
				
				try {
					servicioAdministracion.insertarOperacion(oDTO);
				} catch (Exception e) {
					sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al insertar la operacion");
					sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
					return "error";

				}

				
			}else{
				sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error en la operacion");
				sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Operacion no admitida");
				return "error";
			}
		}
		
		
		try {
			String refrescar = Ctes.NO;
			
			if (operacion != null){
				
				if (operacion.equalsIgnoreCase(Ctes.CONFIG_OPERA_CONFIRMAR_MODIFICAR)){
					refrescar = Ctes.SI;
				}	
				if (operacion.equalsIgnoreCase(Ctes.CONFIG_OPERA_CONFIRMAR_INSERTAR)){
					refrescar = Ctes.SI;
				}	
			}

			hmOperaciones = servicioSingleton.getOperaciones(refrescar);
			
		} catch (Exception e) {
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al recuperar las operaciones. ");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
			return "error";
		}
		
		model.put(Ctes.OPCADMIN_OPERACION, hmOperaciones);
		
	
		return "adminOperacion";
	}
	
	
	@RequestMapping(value = "/adminRol", method = RequestMethod.POST)
    public String adminRol(Locale locale,
			Map<String, Object> model, 
            HttpServletRequest request, 
            HttpServletResponse response,
            HttpSession sesion) {
		
		if (comprobarSesionActiva(sesion) == false)
			return "inactividad";
		
		// Si no esta identificado es un error.
		if (sesion.getAttribute(ConstantesSesion.IDENTIFICADO).equals(Ctes.NO)){
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al administrar los roles");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Debe identificarse para poder continuar" );
			return "error";
		}
		
		// Si no esta identificado como administrador es un error
		if (sesion.getAttribute(ConstantesSesion.ADMINISTRADOR).equals(Ctes.NO)){
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al administrar los roles");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Debe identificarse como administrador para poder continuar" );
			return "error";
		}	
		
		String operacion = request.getParameter("operacion");
		
		// Se evaluan las distintas operaciones para las nacionalidades
		if (operacion != null){
			
			if (operacion.equalsIgnoreCase(Ctes.CONFIG_ROL_CONFIRMAR_MODIFICAR)){
				
				String codRol = request.getParameter("codRol");
				String descRol = request.getParameter("descRol");
				
				System.out.println("codigo rol a modificar: " + codRol);
				System.out.println("descripcion a modificar: " + descRol);
				
				RoleDTO rDTO = new RoleDTO();
				rDTO.setCodigoRole(codRol);
				rDTO.setDescripcion(descRol);			
				
				try {
					servicioAdministracion.modificarRole(rDTO);
				} catch (Exception e) {
					sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al modificar el role");
					sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
					return "error";

				}
				

				
			}else if (operacion.equalsIgnoreCase(Ctes.CONFIG_ROL_CONFIRMAR_INSERTAR)){

				String descRol = request.getParameter("descRol");
				
				System.out.println("descripcion a insertar: " + descRol);
				
				RoleDTO rDTO = new RoleDTO();
				rDTO.setDescripcion(descRol);			
				
				try {
					servicioAdministracion.insertarRole(rDTO);
				} catch (Exception e) {
					sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al insertar el role");
					sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
					return "error";

				}				
				
			}else{
				sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error en la operacion de roles");
				sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Operacion no admitida");
				return "error";
			}
		}
		
		try {
			String refrescar = Ctes.NO;
			
			if (operacion != null){
				
				
				if (operacion.equalsIgnoreCase(Ctes.CONFIG_ROL_CONFIRMAR_MODIFICAR)){
					refrescar = Ctes.SI;
				}	
				if (operacion.equalsIgnoreCase(Ctes.CONFIG_ROL_CONFIRMAR_INSERTAR)){
					refrescar = Ctes.SI;
				}
				
			}

			hmRoles = servicioSingleton.getRoles(refrescar);
			
		} catch (Exception e) {
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al recuperar los roles. ");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
			return "error";
		}
		
		model.put(Ctes.OPCADMIN_ROLES, hmRoles);		
		
	
		return "adminRol";
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
		
		
		String operacion = request.getParameter("operacion");
		
		// Se evaluan las distintas operaciones para los jugadores
		if (operacion != null){
			
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
				System.out.println("NINGUNA OPERACION CONOCIDA");	
			}
		}
		
		/*
		ArrayList<EquipoRealDTO> lEquipo = null;

		try {
			lEquipo = servicioAdministracion.mostrarCatalogoEquipos();
		} catch (Exception e) {
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al recuperar equipos reales");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
			return "error";
		}

		model.put(Ctes.OPCADMIN_EQUIPO, lEquipo);
		*/


		try {
			String refrescar = Ctes.NO;
			if (operacion != null){
				
				if (operacion.equalsIgnoreCase(Ctes.EQUIPO_ADMIN_CONFIRMAR_MODIFICAR) ||
						operacion.equalsIgnoreCase(Ctes.EQUIPO_ADMIN_CONFIRMAR_INSERTAR)
						){
					refrescar = Ctes.SI;
				}
				
			}
			
			hmEquiposReales = servicioSingleton.getEquiposReales(refrescar);
			
		} catch (Exception e) {
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al recuperar equipos reales");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
			return "error";
		}
		model.put(Ctes.OPCADMIN_EQUIPO, hmEquiposReales);
		
		
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
	
	// TODO: MIRAR COMO SE HA GESTIONADO /adminEquipos PARA HACER LO MISMO CON JUGADORES.
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
		
		
		ArrayList<JugadorRealDTO> lJugador = null;
		try {
			//ivan
			//lJugador = servicioAdministracion.mostrarCatalogoJugadores();
			lJugador=null;
		} catch (Exception e) {
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al recuperar jugadores reales");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
			return "error";
		}
		
		model.put(Ctes.OPCADMIN_JUGADOR, lJugador);
		
		
		return "adminJugadores";
	}
		
	@RequestMapping(value = "/draft", method = RequestMethod.POST)
    public String draft(Locale locale,
			Map<String, Object> model, 
            HttpServletRequest request, 
            HttpServletResponse response,
            HttpSession sesion) {
		
		if (comprobarSesionActiva(sesion) == false)
			return "inactividad";
		
		// Si no esta identificado es un error.
		if (sesion.getAttribute(ConstantesSesion.IDENTIFICADO).equals(Ctes.NO)){
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al administrar jugador real");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Debe identificarse para poder continuar" );
			return "error";
		}
		

		// Que se debe informar para draft????
		// El nombre de la liga - Esto se puede obtener a partir de ConstantesSesion.MI_LIGA si se va a la tabla de Liga
		// El nombre del equipo - Esto se puede obtener a partir - ConstantesSesion.MI_EQUIPO si se va a la tabla de Equipo
		// Dinero disponible - Esto se puede obtener a partir - ConstantesSesion.COD_ECONO si se va a la tabla de Economia.
		
		
		model.put(Ctes.DRAFT_NB_LIGA, "la revuelta");
		model.put(Ctes.DRAFT_NB_EQUIPO, "los ventolines");
System.out.println(Double.toString(Ctes.ECONOMIA_INICIAL));
System.out.println(Ctes.ECONOMIA_INICIAL);
		model.put(Ctes.DRAFT_ECONOMIA, Double.toString(Ctes.ECONOMIA_INICIAL));//La cifra se obtendrá de bbdd
		
		
		return "draft";
		
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

