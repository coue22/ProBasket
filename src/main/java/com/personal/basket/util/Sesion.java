package com.personal.basket.util;

import javax.servlet.http.HttpServletRequest;

import com.personal.basket.usuario.Usuario;

public class Sesion {

	public static final String SESION_USR 	= "SesionUsuario";	
	
	// Recupera un usuario.
	public static Usuario getUsuario (final HttpServletRequest request)
	{
		Usuario usuario = null;
		try
		{
			//usuario = (Usuario)request.getSession().getAttribute(Constantes.claveSesionUsuario);
			usuario = (Usuario)request.getSession().getAttribute(SESION_USR);
		}
		catch (Exception e)
		{
			// Problemas con casteo de clases iguales. Se devuelve el usuario nulo.
		}
		return usuario;
	}
	
	// Establece datos del usuario.
	public static void setUsuario (final HttpServletRequest request, final Usuario datosUsuario)
	{
		setDatos(request, SESION_USR, datosUsuario);
	}	
	
	private static void setDatos (final HttpServletRequest request, String claveSesion, final Object datos)
	{
		request.getSession().setAttribute(claveSesion, datos);
	}
	
}
