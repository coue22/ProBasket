package com.personal.basket.servicios;

import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.personal.basket.HomeController;
import com.personal.basket.ctes.Constantes;
import com.personal.basket.ctes.Ctes;
import com.personal.basket.dtos.ConfiguracionDTO;
import com.personal.basket.dtos.EquipoRealDTO;
import com.personal.basket.dtos.JugadorRealDTO;
import com.personal.basket.dtos.NacionalidadDTO;
import com.personal.basket.dtos.OperacionDTO;
import com.personal.basket.dtos.RoleDTO;

@Service("servicioSingleton")
public class ServiciosSingleton implements IServiciosSingleton{
	
	private static final Logger logger = LoggerFactory.getLogger(ServiciosSingleton.class);
	
	private HashMap<String, ConfiguracionDTO> hmConfiguracion = null;
	private HashMap<String, NacionalidadDTO> hmNacionalidad = null;
	private HashMap<String, OperacionDTO> hmOperaciones = null;
	private HashMap<String, RoleDTO> hmRoles  = null;
	private HashMap<String, EquipoRealDTO> hmEquiposReales  = null;
	private HashMap<String, JugadorRealDTO> hmJugadoresReales  = null;
	
	
	public HashMap<String, ConfiguracionDTO> getConfiguracion(String refrescar)throws Exception{
		
		if (hmConfiguracion == null){
			
			//System.out.println("Se recuperan las configuracion");
			logger.debug("Se recupera la configuracion");
			hmConfiguracion = new HashMap<String, ConfiguracionDTO>();
			
			// Se realiza una llamada a BBDD para recuperar 
			// todas las filas de la tabla "Configuracion"
						
			
			ConfiguracionDTO configuracion1 = new ConfiguracionDTO();
			ConfiguracionDTO configuracion2 = new ConfiguracionDTO();
			ConfiguracionDTO configuracion3 = new ConfiguracionDTO();
					
			configuracion1.setParametro(Ctes.MAPA_CONFIGURACION_TEMPORADA);
			configuracion1.setValor("2012");
			
			configuracion2.setParametro(Ctes.MAPA_CONFIGURACION_JORNADA);
			configuracion2.setValor("1");
			
			configuracion3.setParametro(Ctes.MAPA_CONFIGURACION_DRAFT);
			configuracion3.setValor("S");			

			hmConfiguracion.put(configuracion1.getParametro(), configuracion1);
			hmConfiguracion.put(configuracion2.getParametro(), configuracion2);
			hmConfiguracion.put(configuracion3.getParametro(), configuracion3);

			
		}else{
			if (refrescar.equalsIgnoreCase(Ctes.SI)){
				
				//System.out.println("Se recuperan las configuraciones por refresco");
				logger.debug("Se recuperan las configuraciones por refresco");
				
				
				hmConfiguracion.clear();
				

				// Se realiza una llamada a BBDD para recuperar 
				// todas las filas de la tabla "Configuracion"
							
				
				ConfiguracionDTO configuracion1 = new ConfiguracionDTO();
				ConfiguracionDTO configuracion2 = new ConfiguracionDTO();
				ConfiguracionDTO configuracion3 = new ConfiguracionDTO();
						
				configuracion1.setParametro(Ctes.MAPA_CONFIGURACION_TEMPORADA);
				configuracion1.setValor("2012");
				
				configuracion2.setParametro(Ctes.MAPA_CONFIGURACION_JORNADA);
				configuracion2.setValor("1");
				
				configuracion3.setParametro(Ctes.MAPA_CONFIGURACION_DRAFT);
				configuracion3.setValor("S");			

				hmConfiguracion.put(configuracion1.getParametro(), configuracion1);
				hmConfiguracion.put(configuracion2.getParametro(), configuracion2);
				hmConfiguracion.put(configuracion3.getParametro(), configuracion3);

				
			}else{
				//System.out.println("YA ESTA RELLENA LA CONFIGURACION");
				logger.debug("YA ESTA RELLENA LA CONFIGURACION");
				
			}
		}

		
		return hmConfiguracion;
	}
	
	public HashMap<String, NacionalidadDTO> getNacionalidades(String refrescar)throws Exception{
		
		if (hmNacionalidad == null){
			
			//System.out.println("Se recuperan las configuracion");
			//logger.debug("Se recupera la nacionalidad");
			hmNacionalidad = new HashMap<String, NacionalidadDTO>();
			
			// Se realiza una llamada a BBDD para recuperar 
			// todas las filas de la tabla "Nacionalidad"
			
			NacionalidadDTO nac1 = new NacionalidadDTO();
			NacionalidadDTO nac2 = new NacionalidadDTO();
			NacionalidadDTO nac3 = new NacionalidadDTO();

			nac1.setCodigoNacionalidad("0001");
			nac1.setDescripcion(Constantes.USA);

			nac2.setCodigoNacionalidad("0002");
			nac2.setDescripcion(Constantes.ESP);

			nac3.setCodigoNacionalidad("0003");
			nac3.setDescripcion(Constantes.AND);
			
			
			hmNacionalidad.put(nac1.getCodigoNacionalidad(), nac1);
			hmNacionalidad.put(nac2.getCodigoNacionalidad(), nac2);
			hmNacionalidad.put(nac3.getCodigoNacionalidad(), nac3);

			
		}else{
			if (refrescar.equalsIgnoreCase(Ctes.SI)){
				
				//System.out.println("Se recuperan las configuraciones por refresco");
				logger.debug("Se recuperan las nacionalidades por refresco");
				
				
				hmNacionalidad.clear();
				

				// Se realiza una llamada a BBDD para recuperar 
				// todas las filas de la tabla "Nacionalidad"
				
				NacionalidadDTO nac1 = new NacionalidadDTO();
				NacionalidadDTO nac2 = new NacionalidadDTO();
				NacionalidadDTO nac3 = new NacionalidadDTO();


				nac1.setCodigoNacionalidad("0001");
				nac1.setDescripcion(Constantes.USA);

				nac2.setCodigoNacionalidad("0002");
				nac2.setDescripcion(Constantes.ESP);

				nac3.setCodigoNacionalidad("0003");
				nac3.setDescripcion(Constantes.AND);
				
				
				hmNacionalidad.put(nac1.getCodigoNacionalidad(), nac1);
				hmNacionalidad.put(nac2.getCodigoNacionalidad(), nac2);
				hmNacionalidad.put(nac3.getCodigoNacionalidad(), nac3);
				


				
			}else{
				//System.out.println("YA ESTA RELLENA LA NACIONALIDAD");
				logger.debug("YA ESTA RELLENA LA NACIONALIDAD");
				
			}
		}
		
		return hmNacionalidad;
	}
	
	
	public HashMap<String, OperacionDTO> getOperaciones(String refrescar)throws Exception{
		
		if (hmOperaciones == null){
			
			//System.out.println("Se recuperan las operaciones");
			logger.debug("Se recuperan las operaciones");
			hmOperaciones = new HashMap<String, OperacionDTO>();
			
			// Se realiza una llamada a BBDD para recuperar 
			// todas las filas de la tabla "Operacion"
						
			
			OperacionDTO operacion1 = new OperacionDTO();
			OperacionDTO operacion2 = new OperacionDTO();
			OperacionDTO operacion3 = new OperacionDTO();
			
			operacion1.setCodigoOpera("001");
			operacion1.setDescripcion("Abono por venta de jugador");
			
			operacion2.setCodigoOpera("002");
			operacion2.setDescripcion("Abono por partido en casa");
			
			operacion3.setCodigoOpera("003");
			operacion3.setDescripcion("Cargo por compra de jugador.");
			
			hmOperaciones.put(operacion1.getCodigoOpera(), operacion1);
			hmOperaciones.put(operacion2.getCodigoOpera(), operacion2);
			hmOperaciones.put(operacion3.getCodigoOpera(), operacion3);
			
		}else{
			if (refrescar.equalsIgnoreCase(Ctes.SI)){
				
				//System.out.println("Se recuperan las operaciones por refresco");
				logger.debug("Se recuperan las operaciones por refresco");
				
				
				hmOperaciones.clear();
				
				// Se realiza una llamada a BBDD para recuperar 
				// todas las filas de la tabla "Operacion"
				OperacionDTO operacion1 = new OperacionDTO();
				OperacionDTO operacion2 = new OperacionDTO();
				OperacionDTO operacion3 = new OperacionDTO();
				
				operacion1.setCodigoOpera("001");
				operacion1.setDescripcion("Abono por venta de jugador");
				
				operacion2.setCodigoOpera("002");
				operacion2.setDescripcion("Abono por partido en casa");
				
				operacion3.setCodigoOpera("003");
				operacion3.setDescripcion("Cargo por compra de jugador.");
				
				hmOperaciones.put(operacion1.getCodigoOpera(), operacion1);
				hmOperaciones.put(operacion2.getCodigoOpera(), operacion2);
				hmOperaciones.put(operacion3.getCodigoOpera(), operacion3);
				
			}else{
				//System.out.println("YA ESTA RELLENA LA LISTA DE OPERACIONES");
				logger.debug("YA ESTA RELLENA LA LISTA DE OPERACIONES");
				
			}
		}

		
		return hmOperaciones;
	}


	public HashMap<String, RoleDTO> getRoles(String refrescar)throws Exception{
		
		
		if (hmRoles == null){
			
			//System.out.println("Se recuperan los roles");
			logger.debug("Se recuperan los roles");
			
			hmRoles = new HashMap<String, RoleDTO>();
			
			// Se realiza una llamada a BBDD para recuperar 
			// todas las filas de la tabla "Role"
			
			RoleDTO role1 = new RoleDTO();
			RoleDTO role2 = new RoleDTO();
			RoleDTO role3 = new RoleDTO();
			
			role1.setCodigoRole("001");
			role1.setDescripcion("Capitan");

			role2.setCodigoRole("002");
			role2.setDescripcion("Anotador");

			role3.setCodigoRole("003");
			role3.setDescripcion("Defensor");
			
			hmRoles.put(role1.getCodigoRole(), role1);
			hmRoles.put(role2.getCodigoRole(), role2);
			hmRoles.put(role3.getCodigoRole(), role3);			
			
		}else{
			//System.out.println("YA ESTA RELLENA LA LISTA DE ROLES");
			if (refrescar.equalsIgnoreCase(Ctes.SI)){
				
				hmRoles.clear();
				
				logger.debug("Se recuperan los roles por refresco");
				
				// Se realiza una llamada a BBDD para recuperar 
				// todas las filas de la tabla "Role"
				RoleDTO role1 = new RoleDTO();
				RoleDTO role2 = new RoleDTO();
				RoleDTO role3 = new RoleDTO();
				
				role1.setCodigoRole("001");
				role1.setDescripcion("Capitan");

				role2.setCodigoRole("002");
				role2.setDescripcion("Anotador");

				role3.setCodigoRole("003");
				role3.setDescripcion("Defensor");
				
				hmRoles.put(role1.getCodigoRole(), role1);
				hmRoles.put(role2.getCodigoRole(), role2);
				hmRoles.put(role3.getCodigoRole(), role3);		
				
			}else{
				//System.out.println("YA ESTA RELLENA LA LISTA DE OPERACIONES");
				logger.debug("YA ESTA RELLENA LA LISTA DE ROLES");
				
			}
		}

		
		return hmRoles;
	}
	
	
	public HashMap<String, EquipoRealDTO> getEquiposReales(String refrescar)throws Exception{
		

		if (hmEquiposReales == null){
			
			//System.out.println("Se recuperan los equipos reales");
			logger.debug("Se recuperan los equipos reales");
			
			hmEquiposReales = new HashMap<String, EquipoRealDTO>();
			
			// Se realiza una llamada a BBDD para recuperar 
			// todas las filas de la tabla "EquipoReal"
			
			EquipoRealDTO er1 = new EquipoRealDTO();
			EquipoRealDTO er2 = new EquipoRealDTO();
			EquipoRealDTO er3 = new EquipoRealDTO();
			EquipoRealDTO er4 = new EquipoRealDTO();
			
			er1.setCodigoEquipoReal("00001");
			er1.setNombre("Baloncesto Fuenlabrada");
			er1.setSiglas("MCF");
						
			er2.setCodigoEquipoReal("00002");
			er2.setNombre("Real Madrid");
			er2.setSiglas("RM");	
			
			er3.setCodigoEquipoReal("00003");
			er3.setNombre("Barcelona");
			er3.setSiglas("FCB");

			er4.setCodigoEquipoReal("00004");
			er4.setNombre("Cajasol");
			er4.setSiglas("CAJ");
			
			hmEquiposReales.put(er1.getCodigoEquipoReal(), er1);
			hmEquiposReales.put(er2.getCodigoEquipoReal(), er2);
			hmEquiposReales.put(er3.getCodigoEquipoReal(), er3);
			hmEquiposReales.put(er4.getCodigoEquipoReal(), er4);		
			
		}else{
			if (refrescar.equalsIgnoreCase(Ctes.SI)){
				
				hmEquiposReales.clear();
				
				//System.out.println("Se recuperan los equipos reales por refresco");
				logger.debug("Se recuperan los equipos reales por refresco");
				
				// Se realiza una llamada a BBDD para recuperar 
				// todas las filas de la tabla "EquipoReal"
				
				EquipoRealDTO er1 = new EquipoRealDTO();
				EquipoRealDTO er2 = new EquipoRealDTO();
				EquipoRealDTO er3 = new EquipoRealDTO();
				EquipoRealDTO er4 = new EquipoRealDTO();
				
				er1.setCodigoEquipoReal("00001");
				er1.setNombre("Baloncesto Fuenlabrada");
				er1.setSiglas("MCF");
							
				er2.setCodigoEquipoReal("00002");
				er2.setNombre("Real Madrid");
				er2.setSiglas("RM");	
				
				er3.setCodigoEquipoReal("00003");
				er3.setNombre("Barcelona");
				er3.setSiglas("FCB");

				er4.setCodigoEquipoReal("00004");
				er4.setNombre("Cajasol");
				er4.setSiglas("CAJ");
				
				hmEquiposReales.put(er1.getCodigoEquipoReal(), er1);
				hmEquiposReales.put(er2.getCodigoEquipoReal(), er2);
				hmEquiposReales.put(er3.getCodigoEquipoReal(), er3);
				hmEquiposReales.put(er4.getCodigoEquipoReal(), er4);					

				
			}else{
				//System.out.println("YA ESTA RELLENA LA LISTA DE EQUIPOS REALES");
				logger.debug("YA ESTA RELLENA LA LISTA DE EQUIPOS REALES");
				
			}
		}

		
		return hmEquiposReales;		
	}
	
	
	public HashMap<String, JugadorRealDTO> getJugadoresReales(String refrescar)throws Exception{
		
		if (hmJugadoresReales == null){
			
			
			// Se realiza una llamada a BBDD para recuperar 
			// todas las filas de la tabla "JugadorRealDTO"
			
			//System.out.println("Se recuperan los jugadores reales");
			logger.debug("Se recuperan los jugadores reales");
			
			hmJugadoresReales = new HashMap<String, JugadorRealDTO>();
			
			JugadorRealDTO j1 = new JugadorRealDTO();
			j1.setCodigoJugador("0000000001");
			j1.setActivo(true);
			j1.setNombre("Robert");
			j1.setApellido("Kurz");
			j1.setPuesto(Constantes.PIVOT);
			j1.setAltura("206");
			j1.setPeso("105");
			j1.setNacionalidad(Constantes.USA);
			j1.setCodigoEquipoReal("00001");
			
			JugadorRealDTO j2 = new JugadorRealDTO();
			j2.setCodigoJugador("0000000002");
			j2.setActivo(true);
			j2.setNombre("Quino");
			j2.setApellido("Colom");
			j2.setPuesto(Constantes.BASE);
			j2.setAltura("188");
			j2.setPeso("80");
			j2.setNacionalidad(Constantes.AND);
			j2.setCodigoEquipoReal("00001");
			
			JugadorRealDTO j3 = new JugadorRealDTO();
			j3.setCodigoJugador("0000000003");
			j3.setActivo(true);
			j3.setNombre("Sergio");
			j3.setApellido("Sanchez");
			j3.setPuesto(Constantes.BASE);
			j3.setAltura("189");
			j3.setPeso("85");
			j3.setNacionalidad(Constantes.ESP);
			j3.setCodigoEquipoReal("00001");	
			
			JugadorRealDTO j4 = new JugadorRealDTO();
			j4.setCodigoJugador("0000000004");
			j4.setActivo(true);
			j4.setNombre("Kristaps");
			j4.setApellido("Valters");
			j4.setPuesto(Constantes.BASE);
			j4.setAltura("191");
			j4.setPeso("84");
			j4.setNacionalidad(Constantes.ESP);
			j4.setCodigoEquipoReal("00001");
			
			JugadorRealDTO j5 = new JugadorRealDTO();
			j5.setCodigoJugador("0000000005");
			j5.setActivo(true);
			j5.setNombre("James");
			j5.setApellido("Feldeine");
			j5.setPuesto(Constantes.ALERO);
			j5.setAltura("193");
			j5.setPeso("92");
			j5.setNacionalidad(Constantes.USA);
			j5.setCodigoEquipoReal("00001");
			
			
			JugadorRealDTO j6 = new JugadorRealDTO();
			j6.setCodigoJugador("0000000006");
			j6.setActivo(true);
			j6.setNombre("Jon");
			j6.setApellido("Cortaberria");
			j6.setPuesto(Constantes.ALERO);
			j6.setAltura("200");
			j6.setPeso("92");
			j6.setNacionalidad(Constantes.ESP);
			j6.setCodigoEquipoReal("00001");
			
			
			hmJugadoresReales.put(j1.getCodigoJugador(), j1);
			hmJugadoresReales.put(j2.getCodigoJugador(), j2);
			hmJugadoresReales.put(j3.getCodigoJugador(), j3);
			hmJugadoresReales.put(j4.getCodigoJugador(), j4);
			hmJugadoresReales.put(j5.getCodigoJugador(), j5);
			hmJugadoresReales.put(j6.getCodigoJugador(), j6);
			
			
		}else{
			if (refrescar.equalsIgnoreCase(Ctes.SI)){
				
				hmJugadoresReales.clear();
				
				//System.out.println("Se recuperan los jugadores reales por refresco");
				logger.debug("Se recuperan los jugadores reales por refresco");
				
				
				// Se realiza una llamada a BBDD para recuperar 
				// todas las filas de la tabla "JugadorRealDTO"
				
				JugadorRealDTO j1 = new JugadorRealDTO();
				j1.setCodigoJugador("0000000001");
				j1.setActivo(true);
				j1.setNombre("Robert");
				j1.setApellido("Kurz");
				j1.setPuesto(Constantes.PIVOT);
				j1.setAltura("206");
				j1.setPeso("105");
				j1.setNacionalidad(Constantes.USA);
				j1.setCodigoEquipoReal("00001");
				
				JugadorRealDTO j2 = new JugadorRealDTO();
				j2.setCodigoJugador("0000000002");
				j2.setActivo(true);
				j2.setNombre("Quino");
				j2.setApellido("Colom");
				j2.setPuesto(Constantes.BASE);
				j2.setAltura("188");
				j2.setPeso("80");
				j2.setNacionalidad(Constantes.AND);
				j2.setCodigoEquipoReal("00001");
				
				JugadorRealDTO j3 = new JugadorRealDTO();
				j3.setCodigoJugador("0000000003");
				j3.setActivo(true);
				j3.setNombre("Sergio");
				j3.setApellido("Sanchez");
				j3.setPuesto(Constantes.BASE);
				j3.setAltura("189");
				j3.setPeso("85");
				j3.setNacionalidad(Constantes.ESP);
				j3.setCodigoEquipoReal("00001");	
				
				JugadorRealDTO j4 = new JugadorRealDTO();
				j4.setCodigoJugador("0000000004");
				j4.setActivo(true);
				j4.setNombre("Kristaps");
				j4.setApellido("Valters");
				j4.setPuesto(Constantes.BASE);
				j4.setAltura("191");
				j4.setPeso("84");
				j4.setNacionalidad(Constantes.ESP);
				j4.setCodigoEquipoReal("00001");
				
				JugadorRealDTO j5 = new JugadorRealDTO();
				j5.setCodigoJugador("0000000005");
				j5.setActivo(true);
				j5.setNombre("James");
				j5.setApellido("Feldeine");
				j5.setPuesto(Constantes.ALERO);
				j5.setAltura("193");
				j5.setPeso("92");
				j5.setNacionalidad(Constantes.USA);
				j5.setCodigoEquipoReal("00001");
				
				
				JugadorRealDTO j6 = new JugadorRealDTO();
				j6.setCodigoJugador("0000000006");
				j6.setActivo(true);
				j6.setNombre("Jon");
				j6.setApellido("Cortaberria");
				j6.setPuesto(Constantes.ALERO);
				j6.setAltura("200");
				j6.setPeso("92");
				j6.setNacionalidad(Constantes.ESP);
				j6.setCodigoEquipoReal("00001");
				
				
				hmJugadoresReales.put(j1.getCodigoJugador(), j1);
				hmJugadoresReales.put(j2.getCodigoJugador(), j2);
				hmJugadoresReales.put(j3.getCodigoJugador(), j3);
				hmJugadoresReales.put(j4.getCodigoJugador(), j4);
				hmJugadoresReales.put(j5.getCodigoJugador(), j5);
				hmJugadoresReales.put(j6.getCodigoJugador(), j6);				
				
				
				
			}else{
				//System.out.println("YA ESTA RELLENA LA LISTA DE JUGADORES REALES");
				logger.debug("YA ESTA RELLENA LA LISTA DE JUGADORES REALES");
			}
		}
		
		return hmJugadoresReales;
	}
	
	
}
