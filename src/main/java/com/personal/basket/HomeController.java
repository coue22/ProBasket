package com.personal.basket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



import com.personal.basket.ctes.ConstantesSesion;
import com.personal.basket.ctes.Ctes;

import com.personal.basket.dao.join.LigaJugadorJoinJugador;
import com.personal.basket.dtos.ConfiguracionDTO;
import com.personal.basket.dtos.DraftDTO;
import com.personal.basket.dtos.EconomiaDTO;
import com.personal.basket.dtos.EquipoDTO;
import com.personal.basket.dtos.LigaJugadorDTO;
import com.personal.basket.dtos.LigaJugadorJoinJugadorDTO;
import com.personal.basket.dtos.NacionalidadDTO;
import com.personal.basket.dtos.OperacionDTO;
import com.personal.basket.dtos.RoleDTO;
import com.personal.basket.dtos.UsuarioDTO;
import com.personal.basket.dtos.EquipoRealDTO;
import com.personal.basket.dtos.JugadorRealDTO;
import com.personal.basket.dtos.LigaDTO;
import com.personal.basket.dtos.MenuDTO;

import com.personal.basket.model.LigaJugadorJoinJugadorModelDTO;
import com.personal.basket.model.UsuarioModelDTO;
import com.personal.basket.servicios.CatalogoServicios;
import com.personal.basket.servicios.ServiciosAdministracion;
import com.personal.basket.servicios.ServiciosDraft;
import com.personal.basket.servicios.ServiciosGestionLigas;
import com.personal.basket.servicios.ServiciosSingleton;

import com.personal.basket.util.Util;

import java.util.*;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;


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
	
	@Autowired
	ServiciosDraft serviciosDraft;
	
	
	
	
	

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
	
	
	
	
	public ServiciosDraft getServiciosDraft() {
		return serviciosDraft;
	}
	public void setServiciosDraft(ServiciosDraft serviciosDraft) {
		this.serviciosDraft = serviciosDraft;
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
		
		sesion.setAttribute(ConstantesSesion.LOGIN_USUARIO, Ctes.NO_ASIGNADO_LOGIN);

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
		
		
/*	
		try{

            // Configuramos las propiedades
            Properties props = new Properties();
            props.put("mail.transport.protocol","smtp");
            props.put("mail.smtp.host","basongaiz.ProBasket.com");
            props.setProperty("mail.user", "info@ProBasket.com");
            props.setProperty("mail.password", "xxxxx");

            // Creo la sesión y un nuevo mensaje de correo
            //Autentificacion pwd = new Autentificacion();
            Session mailSession = Session.getInstance(props,null);
            Message msg = new MimeMessage(mailSession);
           
            // Configuramos los campos del mensaje
            msg.setFrom(new InternetAddress("info@ProBasket.com"));              
            msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse("coue22@gmail.com"));
            msg.setSubject("Tema del mensaje");
            String msgBody = "Cuerpo del mensaje";
            DataHandler dh = new DataHandler(msgBody,"text/plain");
            msg.setDataHandler(dh);
           
            // Pedimos a la clase Transport que envie el mensaje de correo
            javax.mail.Transport.send(msg);

		} catch (Exception e) {
            e.printStackTrace();
		}
*/		
		
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
			// Va a mirar si es tiempo de draft.
			// Si es tiempo de draft no dejara que la gente se registre.
			// El motivo es que a esa altura los equipos en las ligas esten cerrados para
			// realizar la planificación del draft.
			ConfiguracionDTO cDTO = null;
			try {
				cDTO = Util.getValorMapaConfiguracion(hmConfiguracion, Ctes.MAPA_CONFIGURACION_DRAFT);
			} catch (Exception e1) {
				sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error grave al entrar en la aplicacion ProBasket.");
				sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Sin la variable de entorno del Draft no se puede continuar.");
				return "error";
			}					
			
			sesion.setAttribute(ConstantesSesion.PERMITIR_REGISTRO, Ctes.SI);
			if (cDTO.getValor().toString().equalsIgnoreCase(Ctes.SI)){
				sesion.setAttribute(ConstantesSesion.PERMITIR_REGISTRO, Ctes.NO);
			}
			*/
			
			
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
		
		//BorrarEjemploLeerFichero();
		

		// Se llama al servicio para logarse y se establecen algunas variables de sesion
		// asociadas al usuario.
		try {
			
			
			InicializarSesion(sesion);

			// Se llama al servicio para logarse.
			UsuarioDTO dPers = catalogoServicio.loggearse(request.getParameter("nombre"), 
														 request.getParameter("contra"));
			
			// No ha logrado logarse por credenciales.
			if (dPers == null){
				sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al logarse");
				sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Sus credenciales no son correctas." );
				return "error";
			}
			
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
						cDTO = Util.getValorMapaConfiguracion(hmConfiguracion, Ctes.MAPA_CONFIGURACION_DRAFT);
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
		
		// Se establecen como variables de sesion el nombre de usuario
		sesion.setAttribute(ConstantesSesion.LOGIN_USUARIO, request.getParameter("nombre"));
		
		
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
		
		model.put("tiempoDraft", Ctes.NO);
		
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
						
						ConfiguracionDTO cDTO = null;
						try {
							cDTO = Util.getValorMapaConfiguracion(hmConfiguracion, Ctes.MAPA_CONFIGURACION_DINEROINI);
						} catch (Exception e1) {
							sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al registrarse.");
							sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Sin la variable de entorno del dinero inicial no se puede continuar.");
							return "error";
						}
						
						double dineroInicial=Double.valueOf(cDTO.getValor()).doubleValue();
						
						// Se inserta por fin el usuario con su economia.
						boolean bExisteUsuario = catalogoServicio.registrarse(
												loginRegistro, 
												pwdRegistro,
												emailRegistro,
												selAnoNacRegistro,
												selSexRegistro,
												dineroInicial);
						
						if (bExisteUsuario == false){
							model.put("errorRegistro", "Ya existe un usuario con ese mismo login. Elija otro login");
							return "registrarse";							
						}
						
						model.put("okRegistro", "Usuario " + loginRegistro + " se ha registrado de forma correcta.");
	
						// Se inicializa la sesion con todos los valores por defecto.
						InicializarSesion(sesion);

						
						
					} catch (Exception e) {
						sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al realizar el registro");
						sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
						return "error";
					}
					

					
				}
				
				
			}else if (operacion.equalsIgnoreCase(Ctes.REGISTRO)){
				
				ConfiguracionDTO cConfigDraftDTO = null;
				try {
					 cConfigDraftDTO = Util.getValorMapaConfiguracion(hmConfiguracion, Ctes.MAPA_CONFIGURACION_DRAFT);
				} catch (Exception e1) {
					sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error grave al intentar registrarse en la aplicacion ProBasket.");
					sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Sin la variable de entorno del Draft no se puede continuar.");
					return "error";
				}					
				
				// Si es tiempo de draft no se deja que se registre.
				if (cConfigDraftDTO.getValor().toString().equalsIgnoreCase(Ctes.SI)){
					model.put("errorRegistro", "Estamos en tiempo de draft. A partir del dd/MM/2013 podra registrarse.");
					model.put("tiempoDraft", Ctes.SI);
				}					
				
			}else {
				sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Operacion no permitida");
				sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Operacion erronea en el registro.");
				return "error";
			}

				
				
		}else{
			System.out.println("NINGUNA OPERACION - Seguramente sea el click del inicio.");	
		}		


		
		return "registrarse";
	}
	
	
	/**
	 * Esta funcion es para cuando se llama desde la pantalla porque ha habido un error.
	 * @param locale
	 * @param model
	 * @param request
	 * @param response
	 * @param sesion
	 * @return
	 */
	@RequestMapping(value = "/error")
    public String error(Locale locale,
			Map<String, Object> model, 
            HttpServletRequest request, 
            HttpServletResponse response,
            HttpSession sesion) {
		System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
		String operacion = request.getParameter("operacion");
		String detalle = request.getParameter("detalle");
		
		sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, operacion);
		sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, detalle);

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
		
		//logger.info("Se ha pulsado el boton de Crear Liga");

		// Si no esta identificado es un error.
		if (sesion.getAttribute(ConstantesSesion.IDENTIFICADO).equals(Ctes.NO)){
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al crear la liga");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Debe identificarse para poder continuar" );
			return "error";
		}
		
		String operacion = request.getParameter("operacion");

		
		// No se pueden crear ligas si estoy en tiempo de draft.
		ConfiguracionDTO cConfigDraftDTO = null;
		try {
			 cConfigDraftDTO = Util.getValorMapaConfiguracion(hmConfiguracion, Ctes.MAPA_CONFIGURACION_DRAFT);
		} catch (Exception e1) {
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error grave al intentar crear liga en la aplicacion ProBasket.");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Sin la variable de entorno del Draft no se puede continuar.");
			return "error";
		}					
		
		// Si es tiempo de draft no se deja que se cree la liga.
		if (cConfigDraftDTO.getValor().toString().equalsIgnoreCase(Ctes.SI)){
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "No se pueden crear ligas.");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Al estar en tiempo de draft esta operativa no esta disponible.");
			return "error";
		}	
		
		

		// Se evaluan las distintas operaciones para los jugadores
		if (operacion != null){
			
			//System.out.println("operacion:" + operacion);
			
			if (operacion.equalsIgnoreCase(Ctes.CREAR_LIGA)){
				
				// Lo pasado desde la pagina.
				String nbLiga = request.getParameter("nbLiga");
				String pwdLiga = request.getParameter("pwdLiga");
				String pwd2Liga = request.getParameter("pwd2Liga");
				String chPublica = request.getParameter("chPublica"); //(S|N)				
				
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
				
				// Se debe asegurar que existe el login de usuario porque para crear una liga se
				// debe tener ya este dato establecido al logarse.
				if (sesion.getAttribute(ConstantesSesion.LOGIN_USUARIO).equals(Ctes.NO_ASIGNADO_LOGIN)){
					sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al crear la liga");
					sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Usuario no establecido." );
					return "error";
				}
				
				// Se refrescan los jugadores porque voy a crear una liga y puede que
				// no esten todos los deseados.
				try {
					hmJugadoresReales 	= servicioSingleton.getJugadoresReales(Ctes.SI);
				} catch (Exception e) {
					sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al recuperar jugadores reales al crear la liga.");
					sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
					return "error";
				}
				
				// Se crear la liga.
				boolean bCrearLiga = false;
				try {
					bCrearLiga = servicioGestionLigas.crearLiga(lDTO, sesion.getAttribute(ConstantesSesion.LOGIN_USUARIO).toString(), hmJugadoresReales);
					if (bCrearLiga == false){
						sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al crear la liga");
						sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Ya existe una liga con ese nombre." );
						return "error";
					}
					
				} catch (Exception e) {
					sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al crear una liga");
					sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
					return "error";
				}
				
				// Se pinta el menu que corresponda.
				
				sesion.setAttribute(ConstantesSesion.PERTENECE_LIGA, Ctes.NO);
				sesion.setAttribute(ConstantesSesion.MI_LIGA, Ctes.NO_ASIGNADO_LIGA);
				sesion.setAttribute(ConstantesSesion.COD_ECONO, Ctes.NO_ASIGNADO_ECONOMIA);
				sesion.setAttribute(ConstantesSesion.TIENE_EQUIPO, Ctes.NO);
				sesion.setAttribute(ConstantesSesion.MI_EQUIPO, Ctes.NO_ASIGNADO_EQUIPO);

				
				// Se decide lo que se va a pintar en la vista.
				ArrayList<MenuDTO> lMenu = null;

				try {
		
					sesion.setAttribute(ConstantesSesion.TIPOMENU, "2");
					sesion.setAttribute(ConstantesSesion.OPCMENUTEXTO, "Menu");
					lMenu = catalogoServicio.mostrarMenu(Ctes.MENU_NOMENU);

					
				} catch (Exception e) {
					sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al crear la liga.");
					sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
					return "error";
				}

				//model.put(Ctes.OPCMENU, lMenu);
				sesion.setAttribute(ConstantesSesion.OPCMENU, lMenu);
				
				
				return "home";				


				
			}else{
				System.out.println("NINGUNA OPERACION - POR AKI NUNCA");	
			}
		}else{
			System.out.println("NINGUNA OPERACION - Seguramente sea el click del inicio.");	
		}
		
		return "crearLiga";	
		//System.out.println("CREANDO LIGA...");	
		//return "crearLiga";
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
		
		// No se pueden crear ligas si estoy en tiempo de draft.
		ConfiguracionDTO cConfigDraftDTO = null;
		try {
			 cConfigDraftDTO = Util.getValorMapaConfiguracion(hmConfiguracion, Ctes.MAPA_CONFIGURACION_DRAFT);
		} catch (Exception e1) {
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error grave al intentar inscribirse un una liga.");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Sin la variable de entorno del Draft no se puede continuar.");
			return "error";
		}					
		
		// Si es tiempo de draft no se deja que se cree la liga.
		if (cConfigDraftDTO.getValor().toString().equalsIgnoreCase(Ctes.SI)){
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "No se pueden inscribir en una liga.");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Al estar en tiempo de draft esta operativa no esta disponible.");
			return "error";
		}			
		
		// Se inicializa a vacio y si tiene que coger un valor lo hara donde corresponda
		// que es en la inscripcion cuando las credenciales no son correctas 
		// o el nombre del equipo es repetido
		model.put(Ctes.ERROR_INSCRIBIR_LIGA,"");
		
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
				System.out.println("Codigo Liga      : " + temp[0]);
				System.out.println("Es liga publica  : " + temp[1]);
				System.out.println("Nombre de la liga: " + temp[2]);
				System.out.println("nombre Equipo    : " + nbEquipo);
				
				LigaDTO lDTO = new LigaDTO();
				lDTO.setCodigoLiga(temp[0]);
				
				
				if (temp[1].equals("true"))
					lDTO.setLigaPublica(true);
				else
					lDTO.setLigaPublica(false);
				
				
				lDTO.setNombre(temp[2]);
				lDTO.setPassword(passLiga);
				
				
				// Comprobaciones a nivel de servidor para el registro
				// Nunca debería llegar y entrar aqui porque lo debe para javascript pero si no lo
				// tiene activado se lo puede saltar y habría lio.
				if (nbEquipo == null || nbEquipo.length()<1){
					sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al inscribirse en una liga");
					sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "El nombre del equipo debe ir relleno");
					return "error"; 
				}
				
				if (lDTO.isLigaPublica()==false){
					if (passLiga == null || passLiga.length()<1){
						sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al inscribirse en una liga");
						sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Al no ser una liga publica la password es obligatoria.");
						return "error"; 
					}
				}
				
				
				UsuarioDTO usrDTO = null;
				try {
					usrDTO = servicioGestionLigas.inscribirLiga(lDTO, sesion.getAttribute(ConstantesSesion.LOGIN_USUARIO).toString(), nbEquipo);
				} catch (Exception e) {
					sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al inscribirse en una liga");
					sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
					return "error";
				}
				
				if (usrDTO.getError() == Ctes.ERROR_INSCRIPCION_ACREDITACION){// No se ha acreditado bien
					model.put(Ctes.ERROR_INSCRIBIR_LIGA, Ctes.ERROR_TXT_INSCRIBIR_LIGA_CREDENCIADLES);
					return "inscribirseLiga";
				}else if (usrDTO.getError() == Ctes.ERROR_INSCRIPCION_NB_EQUIPO){// Ya existe un equipo con este nombre.
					model.put(Ctes.ERROR_INSCRIBIR_LIGA, Ctes.ERROR_TXT_INSCRIBIR_LIGA_EQUIPO);
					return "inscribirseLiga";
				}

				sesion.setAttribute(ConstantesSesion.PERTENECE_LIGA, Ctes.SI);
				sesion.setAttribute(ConstantesSesion.MI_LIGA, usrDTO.getCodigoLiga());

				sesion.setAttribute(ConstantesSesion.TIENE_EQUIPO, Ctes.SI);
				sesion.setAttribute(ConstantesSesion.MI_EQUIPO, usrDTO.getCodigoEquipo());
				
				sesion.setAttribute(ConstantesSesion.COD_ECONO, usrDTO.getCodigoEcono());


				ConfiguracionDTO cDTO = null;
				try {
					cDTO = Util.getValorMapaConfiguracion(hmConfiguracion, Ctes.MAPA_CONFIGURACION_DRAFT);
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
						sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al inscribirse en la liga");
						sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Imposible presentar el menu");
						return "error";
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
						sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al inscribirse en la liga");
						sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Imposible presentar el menu");
						return "error";
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
				
				model.put(Ctes.ADMIN_SALIDA, "Propiedad " + parametro + " modificada correctamente con el valor " + valor);
				
				// Ahora bien, esto es bastante complejo porque si la variable de entorno
				// modificada es draft y cambia a S. 
				// Esto desencadena una serie de procesos
				// 1.- Para todas las ligas, se averigua el numero de usuarios que tiene
				// 2.- Se establece un orden justo de eleccion en el draft.
				// Aqui termina nuestro trabajo. Ahora serán los usuarios quien manualmente realizarán
				// sus elecciones.				
				if (parametro.equalsIgnoreCase(Ctes.MAPA_CONFIGURACION_DRAFT) 
						&& valor.equalsIgnoreCase(Ctes.SI)){
					
					System.out.println("ESTABLECER eleccion de draft para cada liga.");
					try {
						serviciosDraft.setDraft();
						model.put(Ctes.ADMIN_SALIDA, "Propiedad " + parametro + " modificada correctamente con el valor " + valor + ". Sorteo de eleccion de Dratf en la tabla DRAFTTURNO");
					} catch (Exception e) {
						sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al establecer los turnos del draft en las ligas.");
						sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
						return "error";
					}
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
					model.put(Ctes.ADMIN_SALIDA, "Propiedad " + parametro + " insertada correctamente con el valor " + valor);
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
			/*
			ConfiguracionDTO cDTO = null;
			try {
				cDTO = Util.getValorMapaConfiguracion(hmConfiguracion, Ctes.MAPA_CONFIGURACION_DRAFT);
			} catch (Exception e1) {
				sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error grave al entrar en la aplicacion ProBasket.");
				sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Sin la variable de entorno del Draft no se puede continuar.");
				return "error";
			}					
			
			sesion.setAttribute(ConstantesSesion.PERMITIR_REGISTRO, Ctes.SI);
			if (cDTO.getValor().toString().equalsIgnoreCase(Ctes.SI)){
				sesion.setAttribute(ConstantesSesion.PERMITIR_REGISTRO, Ctes.NO);
			}
			*/			
			
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
				
				
				String codigoNacion = request.getParameter("codigoNacion");
				String descripcion = request.getParameter("descripcion");
				
				NacionalidadDTO nDTO = new NacionalidadDTO();
				nDTO.setCodigoNacionalidad(codigoNacion);
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

				String codRol = request.getParameter("codRol");
				String descRol = request.getParameter("descRol");
				
				System.out.println("codigo rol a modificar: " + codRol);
				System.out.println("descripcion a modificar: " + descRol);
				
				RoleDTO rDTO = new RoleDTO();
				rDTO.setCodigoRole(codRol);
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
		String equipoPatrocinio="";		
		
		
		String operacion = request.getParameter("operacion");
		
		// Se evaluan las distintas operaciones para los jugadores
		if (operacion != null){
			
			System.out.println("operacion:" + operacion);
			
			if (operacion.equalsIgnoreCase(Ctes.EQUIPO_ADMIN_MODIFICAR)){
				
				idEquipoModificar = request.getParameter("idEquipoModificar");
				equipoNombre = request.getParameter("equipoNombre");
				equipoSiglas = request.getParameter("equipoSiglas");
				equipoPatrocinio = request.getParameter("equipoPatrocinio");
				
				//System.out.println("idEquipoModificar: " + idEquipoModificar);
				//System.out.println("equipoNombre: " + equipoNombre);
				//System.out.println("equipoSiglas: " + equipoSiglas);
				
				confirmarModificacion = Ctes.SI;
					
				
			}else if (operacion.equalsIgnoreCase(Ctes.EQUIPO_ADMIN_CONFIRMAR_MODIFICAR)){
				
				idEquipoModificar = request.getParameter("idEquipoModificar");
				equipoNombre = request.getParameter("equipoNombre");
				equipoSiglas = request.getParameter("equipoSiglas");
				equipoPatrocinio = request.getParameter("equipoPatrocinio");
				
				//System.out.println("idEquipoModificar: " + idEquipoModificar);
				//System.out.println("equipoNombre: " + equipoNombre);
				//System.out.println("equipoSiglas: " + equipoSiglas);
				
				// Se lleva a cabo la llamada al servicio para modificar un equipo.
				EquipoRealDTO eDTO = new EquipoRealDTO();
				eDTO.setCodigoEquipoReal(idEquipoModificar);
				eDTO.setNombre(equipoNombre);
				eDTO.setSiglas(equipoSiglas);
				eDTO.setPatrocinio(equipoPatrocinio);
				
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
				equipoPatrocinio = request.getParameter("equipoPatrocinio");
				
				//System.out.println("equipoNombre Insertar: " + equipoNombre);
				//System.out.println("equipoSiglas Insertar: " + equipoSiglas);
				
				EquipoRealDTO eDTO = new EquipoRealDTO();
				eDTO.setNombre(equipoNombre);
				eDTO.setSiglas(equipoSiglas);
				eDTO.setPatrocinio(equipoPatrocinio);
				
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
			model.put(Ctes.EQUIPO_ADMIN_MOSTRAR_MODIFICAR_PA, equipoPatrocinio);
			 
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
		
		String operacion = request.getParameter("operacion");
		
		// Se evaluan las distintas operaciones para los jugadores
		if (operacion != null){
			
			System.out.println("operacion:" + operacion);
			
			if (operacion.equalsIgnoreCase(Ctes.JUGADOR_ADMIN_INSERTAR)){
				
				
				String nbFichero = request.getParameter("nbFicheroJugadores");
				
				
				if (nbFichero.length()<3){
					sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al insertar jugadores");
					sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "El nombre del fichero debe tener al menos 3 caracteres.");
					return "error";
				}
				
				// Se obtiene el codigo de equipo real.
				String codEquipoReal = nbFichero.substring(0, 3);
				
				// Comprueba si existe el equipo real.
				boolean bExisteEquipoReal = false;
				try {
					bExisteEquipoReal = servicioAdministracion.existeEquipoReal(codEquipoReal);
				} catch (Exception e) {
					sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al insertar jugadores");
					sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
					return "error";

				}
				
				// Comprueba que el nombre del fichero es correcto.
				if (bExisteEquipoReal == false){
					sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al insertar jugadores");
					sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "El nombre del fichero no es correcto. Deben coincidir los 3 primeros caracteres con un codigo de equipo REAL.");
					return "error";
				}

				String bInsertar = request.getParameter("bInsertar");
				model.put(Ctes.JUGADOR_ADMIN_JUGADORES_INSERTADOS, "N");

				// Inserta los jugadores de un fichero.
				ArrayList<JugadorRealDTO> lJugadoresAInsertar = null;
				try {
					if (bInsertar.equalsIgnoreCase("S")){
						lJugadoresAInsertar = servicioAdministracion.insertarJugadoresPorFichero(nbFichero, codEquipoReal, true);
						model.put(Ctes.JUGADOR_ADMIN_JUGADORES_INSERTADOS, "S");
					}
					else{
						lJugadoresAInsertar = servicioAdministracion.insertarJugadoresPorFichero(nbFichero, codEquipoReal, false);
						model.put(Ctes.JUGADOR_ADMIN_JUGADORES_INSERTADOS, "N");
					}
					
				} catch (Exception e) {
					sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al insertar jugadores");
					if (bInsertar.equalsIgnoreCase("S")){
						sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
						//sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Asegurese que no existe insertado ninguno de los jugadores existentes en el fichero..");
					}else{
						sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Error mostrando los jugadores a insertar.");	
					}
					
					return "error";
				}
				
				
				// Si he insertado...
				if (bInsertar.equalsIgnoreCase("S")){
					
					try {
						
						// ... Se refrescan los jugadores porque he insertado por fichero
						hmJugadoresReales 	= servicioSingleton.getJugadoresReales(Ctes.SI);			
						
					} catch (Exception e) {
						sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al recuperar jugadores reales");
						sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
						return "error";
					}
					
				}

				
				
				model.put(Ctes.JUGADOR_ADMIN_JUGADORES_FICHERO, nbFichero);
				model.put(Ctes.JUGADOR_ADMIN_JUGADORES_PARA_INSERTAR, lJugadoresAInsertar);
				
			}else{
				sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al administrar jugadores");
				sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Opcion no contemplada.");
				return "error";
			}
				
		}

		return "adminJugadores";
	}
	
	@RequestMapping(value = "/adminCustomJugador", method = RequestMethod.POST)
    public String adminCustomJugador(Locale locale,
			Map<String, Object> model, 
            HttpServletRequest request, 
            HttpServletResponse response,
            HttpSession sesion) {
		
		if (comprobarSesionActiva(sesion) == false)
			return "inactividad";
		
		//System.out.println("adminCustomJugador...");
		
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
		
		String operacion = request.getParameter("operacion");
		
		// Se evaluan las distintas operaciones para los jugadores
		if (operacion != null){
			
			System.out.println("operacion:" + operacion);
			
			if (operacion.equalsIgnoreCase(Ctes.CUSTOMJUGADOR_ADMIN_CAMBIO_ESTADO)){

				String codigoJugador = request.getParameter("codigoJugador");
				String estado = request.getParameter("estado");

				try {
					servicioAdministracion.cambiarEstadoJugador(codigoJugador, estado);
				} catch (Exception e) {
					sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al administrar jugadores");
					sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Error al intentar cambiar el estado de un jugador." + codigoJugador);;
					return "error";
				}

			
				// Se refrescan los jugadores porque he cambiador el estado a un jugador
				// que pasa de activo a no activo o viceversa.
				try {
					hmJugadoresReales 	= servicioSingleton.getJugadoresReales(Ctes.SI);
				} catch (Exception e) {
					sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al cambiar estado de un jugador.");
					sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
					return "error";
				}
				
				
				
			}else{
				sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al recuperar jugadores");
				sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Opcion no contemplada.");
				return "error";
			}
		}
		
		/*
		try {
			
			// Se refrescan los jugadores porque he cambiador el estado a un jugador
			// que pasa de activo a no activo o viceversa.
			hmJugadoresReales 	= servicioSingleton.getJugadoresReales(Ctes.SI);			
			
		} catch (Exception e) {
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al recuperar jugadores reales");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
			return "error";
		}
		*/
		
		model.put(Ctes.OPCADMIN_JUGADOR, hmJugadoresReales);
		
		
		return "adminCustomJugador";
		
	}
	
	
	@RequestMapping(value = "/adminInsertarJugador", method = RequestMethod.POST)
    public String adminInsertarJugador(Locale locale,
			Map<String, Object> model, 
            HttpServletRequest request, 
            HttpServletResponse response,
            HttpSession sesion) {
		
		if (comprobarSesionActiva(sesion) == false)
			return "inactividad";
		
		//System.out.println("adminCustomJugador...");
		
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
		

		String operacion = request.getParameter("operacion");
		
		// Se evaluan las distintas operaciones para los jugadores
		if (operacion != null){
			
			System.out.println("operacion:" + operacion);
			
			if (operacion.equalsIgnoreCase(Ctes.JUGADOR_ADMIN_INSERTAR_JUGADOR)){
				
				String CodigoJugador = request.getParameter("CodigoJugador");
				String NombreJugador = request.getParameter("NombreJugador");
				String ApellidoJugador = request.getParameter("ApellidoJugador");
				String PosicionJugador = request.getParameter("PosicionJugador");
				String AlturaJugador = request.getParameter("AlturaJugador");
				String NacionalidadJugador = request.getParameter("NacionalidadJugador");
				String PrecioJugadorEntero = request.getParameter("PrecioJugadorEntero");
				String PrecioJugadorDecimal = request.getParameter("PrecioJugadorDecimal");
				String CodigoEquipoRealJugador = request.getParameter("CodigoEquipoRealJugador");

/*
				System.out.println(CodigoJugador);
				System.out.println(NombreJugador);
				System.out.println(ApellidoJugador);
				System.out.println(PosicionJugador);
				System.out.println(AlturaJugador);
				System.out.println(NacionalidadJugador);
				System.out.println(PrecioJugadorEntero);
				System.out.println(PrecioJugadorDecimal);
				System.out.println(CodigoEquipoRealJugador);
*/
				
				
				JugadorRealDTO jDTO = new JugadorRealDTO();
				
				jDTO.setCodigoJugador(CodigoJugador);
				jDTO.setActivo("N");
				jDTO.setNombre(NombreJugador);
				jDTO.setApellidos(ApellidoJugador);
				jDTO.setAltura(AlturaJugador);
				jDTO.setPeso("");
				if (Double.parseDouble(PrecioJugadorDecimal) == 0){
					jDTO.setPrecio( Double.parseDouble(PrecioJugadorEntero));
				}
				else{
					jDTO.setPrecio( Double.parseDouble(PrecioJugadorEntero)+
							Double.parseDouble(PrecioJugadorDecimal)/100);					
				}
				jDTO.setPuesto(PosicionJugador);				
				jDTO.setCodigoEquipoReal(CodigoEquipoRealJugador);
				jDTO.setCodigoNacionalidad(NacionalidadJugador);
				
				// Se inserta un jugador.
				try {
					servicioAdministracion.insertarJugador(jDTO);
				} catch (Exception e) {
					sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al insertar jugador");
					sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
					return "error";
				}
				
				// Se refresca el mapa de jugadores puesto que he insertado uno nuevo.
				try {
					
					// ... Se refrescan los jugadores porque he insertado
					hmJugadoresReales 	= servicioSingleton.getJugadoresReales(Ctes.SI);			
					
				} catch (Exception e) {
					sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al recuperar jugadores al dar de alta un jugador.");
					sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
					return "error";
				}				
		
			}else{
				sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al administrar jugador");
				sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Opcion no contemplada.");
				return "error";
			}
		
		}

		
		// Se obtienen las nacionalidades.
		try {
			hmNacionalidad = servicioSingleton.getNacionalidades(Ctes.NO);
			model.put(Ctes.OPCADMIN_NACIONALIDAD, hmNacionalidad);
		} catch (Exception e) {
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al recuperar las nacionalidades al dar de alta un jugador. ");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
			return "error";
		}
		
		// Se obtienen equipos reales.
		try {
			hmEquiposReales = servicioSingleton.getEquiposReales(Ctes.NO);
			model.put(Ctes.OPCADMIN_EQUIPO, hmEquiposReales);
		} catch (Exception e) {
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error al recuperar los equipos reales al dar de alta un jugador. ");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
			return "error";
		}

		
		return "adminInsertarJugador";
		
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
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error en el draft");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Debe identificarse para poder continuar" );
			return "error";
		}
		
		if (sesion.getAttribute(ConstantesSesion.PERTENECE_LIGA).equals(Ctes.NO)){
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error en el draft");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Debe pertenecer a una liga para poder continuar" );
			return "error";
		}
		
		if (sesion.getAttribute(ConstantesSesion.TIENE_EQUIPO).equals(Ctes.NO)){
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error en el draft");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Debe tener equipo para poder continuar" );
			return "error";
		}
		
		
		System.out.println("Cod. Liga: " + sesion.getAttribute(ConstantesSesion.MI_LIGA));
		System.out.println("Cod. Equipo: " + sesion.getAttribute(ConstantesSesion.MI_EQUIPO));
		System.out.println("Cod. Economia: " + sesion.getAttribute(ConstantesSesion.COD_ECONO));
		System.out.println("Login: " + sesion.getAttribute(ConstantesSesion.LOGIN_USUARIO));
		
		
		// Se obtiene la liga para su nombre
		LigaDTO lDTO = null;
		try {
			lDTO = serviciosDraft.getLiga(sesion.getAttribute(ConstantesSesion.MI_LIGA).toString());
			//System.out.println("Nombre Liga: " + lDTO.getNombre());
		} catch (Exception e) {
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error en el draft - Imposible recuperar el nombre de la liga.");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
			return "error";
		}
		
		// Se obtiene el equipo para su nombre y el codigo de liga para una comprobacion
		// posterior.
		EquipoDTO eqDTO = null;
		try {
			eqDTO = serviciosDraft.getEquipo(sesion.getAttribute(ConstantesSesion.MI_EQUIPO).toString());
			//System.out.println("Nombre Equipo: " + eqDTO.getNombre());
			//System.out.println("Cod.Liga: " + eqDTO.getCodigoLiga());
		} catch (Exception e) {
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error en el draft - Imposible recuperar el nombre del equipo.");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
			return "error";
		}
		
		// Se realiza una comprobación de rutina.
		// Se comprueban que los codigos de las ligas son iguales en la tabla de la liga y en la del equipo.
		if (!sesion.getAttribute(ConstantesSesion.MI_LIGA).toString().equalsIgnoreCase(eqDTO.getCodigoLiga())){
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error en el draft - Tu equipo no pertenece a la liga.");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "El codigo de liga no coincide con el codigo de la liga asociado a tu usuario.");
			return "error";	
		}
		

		// Se obtiene el dinero disponible el usuario.
		EconomiaDTO ecDTO = null;
		try {
			ecDTO = serviciosDraft.getEconomia(sesion.getAttribute(ConstantesSesion.MI_EQUIPO).toString());
			//System.out.println("Dinero Disponible: " + ecDTO.getDinero());

		} catch (Exception e) {
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error en el draft - Imposible recuperar el dinero del usuario.");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
			return "error";
		}
		
		// Se necesitan recuperar los jugadores de una liga. De la tabla LigaJugadores.
		// Esta lista es de jugadores activos y a mi me interesa diferenciar entre
		// los que tienen equipo y no tienen.
		List<LigaJugadorJoinJugadorDTO> lLigaJugadoresActivos = null;
		try {
			lLigaJugadoresActivos = serviciosDraft.getLigaJugadoresActivos(sesion.getAttribute(ConstantesSesion.MI_LIGA).toString());
		} catch (Exception e) {
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error en el draft - Imposible recuperar los jugadores de la liga.");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
			return "error";
		}
		
		// Se hacen dos listas:
		//	lLigaJugadoresActivosSINEquipo
		//	lLigaJugadoresActivosCONEquipo
		List<LigaJugadorJoinJugadorDTO> lLigaJugadoresActivosSINEquipo = new ArrayList<LigaJugadorJoinJugadorDTO>();
		List<LigaJugadorJoinJugadorDTO> lLigaJugadoresActivosCONEquipo = new ArrayList<LigaJugadorJoinJugadorDTO>();
		for( LigaJugadorJoinJugadorDTO s : lLigaJugadoresActivos ){
			
			if (s.getCodigoEquipo().equalsIgnoreCase(Ctes.NO_ASIGNADO_EQUIPO))
				lLigaJugadoresActivosSINEquipo.add(s);
			else
				lLigaJugadoresActivosCONEquipo.add(s);
		}
		
		// Obtiene los jugadores que tiene seleccionados para el draft un usuario.
		// Esos jugadores no son suyos, es su eleccion de draft.
		List<DraftDTO> lJugadoresRecuperadosDraft = null;
		try {
			lJugadoresRecuperadosDraft= serviciosDraft.getDraftEquipo(
											sesion.getAttribute(ConstantesSesion.MI_LIGA).toString(), 
											sesion.getAttribute(ConstantesSesion.MI_EQUIPO).toString() );
		} catch (Exception e) {
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error en el draft - Imposible recuperar los jugadores en la eleccion de draft.");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
			return "error";
		}
		
		
		model.put(Ctes.DRAFT_NB_LIGA, lDTO.getNombre());
		model.put(Ctes.DRAFT_NB_EQUIPO, eqDTO.getNombre());
		model.put(Ctes.DRAFT_ECONOMIA, ecDTO.getDinero());
		
		// Son los jugadores recuperados que estan activos y no pertenecen a ningun equipo.
		// Es por ese motivo que son seleccionables para el equipo que lo quiera.
		model.put(Ctes.DRAFT_JUGS_RECUPERADOS_SIN_EQUIPO, lLigaJugadoresActivosSINEquipo);
		
		// Estos son los jugadores que pertenecen a algun equipo y que por lo tanto 
		// no estan disponibles en el draft.
		model.put(Ctes.DRAFT_JUGS_RECUPERADOS_CON_EQUIPO, lLigaJugadoresActivosCONEquipo);
		
		// Son los jugadores que un usuario tiene seleccionados en el draft.
		// Los que se pintan en los combos nada más entrar a la pestaña de draft.
		//for(int i = 0 ; i< lJugadoresRecuperadosDraft.size(); i++)
		//	System.out.println("Antes del model:" + lJugadoresRecuperadosDraft.get(i).getCodigoJugador());
		model.put(Ctes.DRAFT_JUGS_DRAFT, lJugadoresRecuperadosDraft);

		
		return "draft";
		
	}
	
	
	/*
	 * 			AJAX		AJAX		AJAX
	 * 			AJAX		AJAX		AJAX
	 * 			AJAX		AJAX		AJAX
	 * 			AJAX		AJAX		AJAX
	 */
	
	/**
	 * METODO AJAX
	 * Cuando se guarda la elección del draft, entonces se llama a este método.
	 * @param codliga
	 * @param codequipo
	 * @param draft1
	 * @param draft2
	 * @param draft3
	 * @param draft4
	 * @param draft5
	 * @param draft6
	 * @param draft7
	 * @param draft8
	 * @param draft9
	 * @param draft10
	 * @param sesion
	 * @return
	 */
	@RequestMapping(value = "/confirmarDraft", method = RequestMethod.GET)
	public @ResponseBody String getconfirmarDraft(@RequestParam String codliga,
													@RequestParam String codequipo,
													@RequestParam String draft1,
				  									@RequestParam String draft2,
				  									@RequestParam String draft3,
				  									@RequestParam String draft4,
				  									@RequestParam String draft5,
				  									@RequestParam String draft6,
				  									@RequestParam String draft7,
				  									@RequestParam String draft8,
				  									@RequestParam String draft9,
				  									@RequestParam String draft10,
				  									HttpSession sesion) {
		
/*		
		// En cada draftn viene en codigo de jugador.
		System.out.println("liga      :" + codliga);
		System.out.println("equipo    :" + codequipo);
		
		System.out.println("eleccion1 :" + draft1);
		System.out.println("eleccion2 :" + draft2);
		System.out.println("eleccion3 :" + draft3);
		System.out.println("eleccion4 :" + draft4);
		System.out.println("eleccion5 :" + draft5);
		System.out.println("eleccion6 :" + draft6);
		System.out.println("eleccion7 :" + draft7);
		System.out.println("eleccion8 :" + draft8);
		System.out.println("eleccion9 :" + draft9);
		System.out.println("eleccion10:" + draft10);
*/		
		DraftDTO dDTO1 = new DraftDTO();
		dDTO1.setCodigoLiga(codliga);
		dDTO1.setCodigoEquipo(codequipo);
		dDTO1.setCodigoJugador(draft1);
		
		DraftDTO dDTO2 = new DraftDTO();
		dDTO2.setCodigoLiga(codliga);
		dDTO2.setCodigoEquipo(codequipo);
		dDTO2.setCodigoJugador(draft2);		
		
		DraftDTO dDTO3 = new DraftDTO();
		dDTO3.setCodigoLiga(codliga);
		dDTO3.setCodigoEquipo(codequipo);
		dDTO3.setCodigoJugador(draft3);
		
		DraftDTO dDTO4 = new DraftDTO();
		dDTO4.setCodigoLiga(codliga);
		dDTO4.setCodigoEquipo(codequipo);
		dDTO4.setCodigoJugador(draft4);
		
		DraftDTO dDTO5 = new DraftDTO();
		dDTO5.setCodigoLiga(codliga);
		dDTO5.setCodigoEquipo(codequipo);
		dDTO5.setCodigoJugador(draft5);		
		
		DraftDTO dDTO6 = new DraftDTO();
		dDTO6.setCodigoLiga(codliga);
		dDTO6.setCodigoEquipo(codequipo);
		dDTO6.setCodigoJugador(draft6);		
		
		DraftDTO dDTO7 = new DraftDTO();
		dDTO7.setCodigoLiga(codliga);
		dDTO7.setCodigoEquipo(codequipo);
		dDTO7.setCodigoJugador(draft7);		
		
		DraftDTO dDTO8 = new DraftDTO();
		dDTO8.setCodigoLiga(codliga);
		dDTO8.setCodigoEquipo(codequipo);
		dDTO8.setCodigoJugador(draft8);
		
		DraftDTO dDTO9 = new DraftDTO();
		dDTO9.setCodigoLiga(codliga);
		dDTO9.setCodigoEquipo(codequipo);
		dDTO9.setCodigoJugador(draft9);
		
		DraftDTO dDTO10 = new DraftDTO();
		dDTO10.setCodigoLiga(codliga);
		dDTO10.setCodigoEquipo(codequipo);
		dDTO10.setCodigoJugador(draft10);		
		
		
		ArrayList<DraftDTO> lDraftJugadores = new ArrayList<DraftDTO>();
		
		lDraftJugadores.add(dDTO1);
		lDraftJugadores.add(dDTO2);
		lDraftJugadores.add(dDTO3);
		lDraftJugadores.add(dDTO4);
		lDraftJugadores.add(dDTO5);
		lDraftJugadores.add(dDTO6);
		lDraftJugadores.add(dDTO7);
		lDraftJugadores.add(dDTO8);
		lDraftJugadores.add(dDTO9);
		lDraftJugadores.add(dDTO10);
		
		try {
			serviciosDraft.guardarEleccionDraft(codliga, codequipo, lDraftJugadores);
		} catch (Exception e) {
			return "No se ha podido guardar la eleccion del draft";
		}
		
		
		return "La eleccion del draft ha quedado establecida correctamente.";
		

	}
	
	
/*
	@RequestMapping(value = "/refrescarDraft", method = RequestMethod.GET)
	public @ResponseBody String getconfirmarDraft(@RequestParam String sinValor,
												  HttpSession sesion, 
												  Map<String, Object> model) {	
		
		
		List<DraftDTO> lJugadoresRecuperadosDraft = null;
		try {
			lJugadoresRecuperadosDraft= serviciosDraft.getDraftEquipo(
											sesion.getAttribute(ConstantesSesion.MI_LIGA).toString(), 
											sesion.getAttribute(ConstantesSesion.MI_EQUIPO).toString() );
		} catch (Exception e) {
			sesion.setAttribute(ConstantesSesion.OPERACION_ERROR, "Error en el draft - Imposible recuperar los jugadores en la eleccion de draft.");
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
			return "error";
		}
		

		
		// Son los jugadores que un usuario tiene seleccionados en el draft.
		// Los que se pintan en los combos nada más entrar a la pestaña de draft.
		//for(int i = 0 ; i< lJugadoresRecuperadosDraft.size(); i++)
		//	System.out.println("Antes del model:" + lJugadoresRecuperadosDraft.get(i).getCodigoJugador());
		model.put(Ctes.DRAFT_JUGS_DRAFT, lJugadoresRecuperadosDraft);
		
		return "hola";
		
	}
*/
	
	
}

