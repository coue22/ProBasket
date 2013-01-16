package com.personal.basket.intercepciones;

import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.quartz.SchedulerException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.personal.basket.ctes.ConstantesSesion;
import com.personal.basket.ctes.Ctes;
import com.personal.basket.usuario.Usuario;

 

 
public class Intercepciones extends HandlerInterceptorAdapter {
 
    @Override
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {
    	
    	
        //my code here
    	System.out.println("preHandle");
    	
    	// Récupera la sesion y si no existe la crea.
    	HttpSession session = request.getSession(true);

    	// Session inexistante
    	if(session == null) {
    		System.out.println("AQUI DEBERIA DAR UN ERROR PORQUE NUNCA PUEDE IR POR ESTE SITIO.");
    	}else{
    		
    		// Se comprueba la procedencia de la peticion.
    		System.out.println("getRequestURI: " + request.getRequestURI());
    		
    		if (request.getRequestURI().equals("/ProBasket")){
    			
    			System.out.println("Punto de inicio ");
    			//Usuario userSesion = null;
    			//userSesion = new Usuario();
    			
    		}else if (request.getRequestURI().equals("/ProBasket/login")){
    			
    			System.out.println("nombre: " + request.getParameter("nombre"));
    			System.out.println("password:" + request.getParameter("contra") );
    			
    			//session.setAttribute(ConstantesSesion.IDENTIFICADO, Ctes.SI);
    			//session.setAttribute(ConstantesSesion.PERTENECE_LIGA, Ctes.SI);

    		}else if (request.getRequestURI().equals("/ProBasket/registrarse")){

    			System.out.println("nombre: " + request.getParameter("nombreReg"));
    			System.out.println("password:" + request.getParameter("contraReg") );
    			
    			//session.setAttribute(ConstantesSesion.IDENTIFICADO, Ctes.NO);
    			//session.setAttribute(ConstantesSesion.PERTENECE_LIGA, Ctes.NO);
    			
    		}else if (request.getRequestURI().equals("/ProBasket/cerrarSesion")){
    			//session.setAttribute(ConstantesSesion.IDENTIFICADO, Ctes.NO);
    			//session.setAttribute(ConstantesSesion.PERTENECE_LIGA, Ctes.NO);
    		}/*else if (request.getRequestURI().equals("/ProBasket/jugadores")){
    			System.out.println("PINCHA EN JUGADORES");
    			//session.setAttribute(ConstantesSesion.IDENTIFICADO, Ctes.NO);
    			session.setAttribute(ConstantesSesion.PERTENECE_LIGA, Ctes.NO);
    		}*//*else {
    			response.sendRedirect("error");
    			return false;
    		}*/
    		

    		
    		if (session.getAttribute(ConstantesSesion.IDENTIFICADO) == null){
    			System.out.println(" Con Null el identificado. Sospechoso... ");
    			session.setAttribute(ConstantesSesion.IDENTIFICADO, Ctes.NO);
    			session.setAttribute(ConstantesSesion.PERTENECE_LIGA, Ctes.NO);	    			
    		}
    		System.out.println("IDENTIFICADO: " + session.getAttribute(ConstantesSesion.IDENTIFICADO));
    			
    			

    	}

    	
        return super.preHandle(request, response, handler);
    }
    
    public void postHandle(HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            ModelAndView modelAndView) throws Exception{

    	//request.setAttribute("muestra", "ivan y dani");
    	//modelAndView.addObject("muestra1", "ivan1 y dani1");
    	
    	System.out.println("postHandle");
    }
	    
}
	