package com.personal.basket;

import java.text.DateFormat; 
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.personal.basket.dtos.EquipoDTO;
import com.personal.basket.dtos.JugadorDTO;
import com.personal.basket.dtos.MenuDTO;
import com.personal.basket.servicios.CatalogoServicios;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	CatalogoServicios catalogoServicio;
	
	public CatalogoServicios getCatalogoServicio() {
		return catalogoServicio;
	}
	public void setCatalogoServicio(CatalogoServicios catalogoServicio) {
		this.catalogoServicio = catalogoServicio;
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
		MySQLConnection  ms = new MySQLConnection ("","","","");
		try {
			ms.open();
		} catch (DBConneccionException e) {
			logger.info("ERROR:" + e.getMensaje());
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		*/
		
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
		
		// Si no esta identificado es un error.
		if (sesion.getAttribute(ConstantesSesion.IDENTIFICADO).equals(Ctes.NO)){
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, ConstantesSesion.DETALLE_ERROR_TEXTO );
			return "error";
		}
			
		
		ArrayList<MenuDTO> lMenu = null;

		try {
			if (sesion.getAttribute(ConstantesSesion.PERTENECE_LIGA).equals(Ctes.NO)){
				lMenu = catalogoServicio.mostrarMenuNoLiga();
			}else{
				lMenu = catalogoServicio.mostrarMenu();	
			}
			
		} catch (Exception e) {
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
			return "error";
		}

		//model.put(Ctes.OPCMENU, lMenu);
		sesion.setAttribute(ConstantesSesion.OPCMENU, lMenu);
		
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
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Debe identificarse para poder continuar" );
			return "error";
		}
			
		
		ArrayList<JugadorDTO> lJugadores = null;

		try {
			lJugadores = catalogoServicio.mostrarJugadores();
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
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, "Debe identificarse para poder continuar" );
			return "error";
		}
			
		
		EquipoDTO equipo = null;

		try {
			equipo = catalogoServicio.mostrarEquipo();
		} catch (Exception e) {
			sesion.setAttribute(ConstantesSesion.DETALLE_ERROR, e.getMessage() + "-->" + e.getStackTrace());
			return "error";
		}

		model.put(Ctes.OPCMENU_EQUIPO, equipo);
		
		return "equipo";
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
		return "home";
	}	

	
	@RequestMapping(value = "/crearLiga", method = RequestMethod.POST)
    public String crearLiga(Locale locale,
			Map<String, Object> model, 
            HttpServletRequest request, 
            HttpServletResponse response,
            HttpSession sesion) {

		return "home";
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

