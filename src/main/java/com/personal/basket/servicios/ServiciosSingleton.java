package com.personal.basket.servicios;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.personal.basket.HomeController;
import com.personal.basket.ctes.Constantes;
import com.personal.basket.ctes.Ctes;
import com.personal.basket.dao.ConfiguracionMapper;
import com.personal.basket.dao.EquipoRealMapper;
import com.personal.basket.dao.JugadorRealMapper;
import com.personal.basket.dao.NacionalidadMapper;
import com.personal.basket.dao.OperacionMapper;
import com.personal.basket.dao.RoleMapper;
import com.personal.basket.dtos.ConfiguracionDTO;
import com.personal.basket.dtos.EquipoRealDTO;
import com.personal.basket.dtos.JugadorRealDTO;
import com.personal.basket.dtos.NacionalidadDTO;
import com.personal.basket.dtos.OperacionDTO;
import com.personal.basket.dtos.RoleDTO;
import com.personal.basket.model.ConfiguracionModelDTO;
import com.personal.basket.model.EquipoRealModelDTO;
import com.personal.basket.model.JugadorRealModelDTO;
import com.personal.basket.model.NacionalidadModelDTO;
import com.personal.basket.model.OperacionModelDTO;
import com.personal.basket.model.RoleModelDTO;

@Service("servicioSingleton")
public class ServiciosSingleton implements IServiciosSingleton{
	
	
	
	@Resource
	ConfiguracionMapper configuracionMapper;

	@Resource
	NacionalidadMapper nacionalidadMapper;
	
	@Resource
	OperacionMapper operacionMapper;

	@Resource
	RoleMapper roleMapper;
	
	@Resource
	EquipoRealMapper equipoRealMapper;
	
	@Resource
	JugadorRealMapper jugadorRealMapper;	
	
	
	private static final Logger logger = LoggerFactory.getLogger(ServiciosSingleton.class);
	
	private HashMap<String, ConfiguracionDTO> hmConfiguracion = null;
	private HashMap<String, NacionalidadDTO> hmNacionalidad = null;
	private HashMap<String, OperacionDTO> hmOperaciones = null;
	private HashMap<String, RoleDTO> hmRoles  = null;
	private HashMap<String, EquipoRealDTO> hmEquiposReales  = null;
	private HashMap<String, JugadorRealDTO> hmJugadoresReales  = null;
	
	@Transactional(readOnly=true)
	public HashMap<String, ConfiguracionDTO> getConfiguracion(String refrescar)throws Exception{
		
		if (hmConfiguracion == null){
			
			//System.out.println("Se recuperan las configuracion");
			logger.debug("Se recupera la configuracion");
			hmConfiguracion = new HashMap<String, ConfiguracionDTO>();
			
			
			// Se realiza una llamada a BBDD para recuperar 
			// todas las filas de la tabla "Configuracion"
			List<ConfiguracionModelDTO> lConfModel = configuracionMapper.getConfiguracion();
			
			for( ConfiguracionModelDTO s : lConfModel ){
				ConfiguracionDTO configuracion = new ConfiguracionDTO();
				
				configuracion.setParametro(s.getParametro());
				configuracion.setValor(s.getValor());
				
				//System.out.print("Parametro:" + configuracion.getParametro());
				//System.out.println("Valor:" + configuracion.getValor());
				
				hmConfiguracion.put(configuracion.getParametro(), configuracion);
			}
			
		}else{
			if (refrescar.equalsIgnoreCase(Ctes.SI)){
				
				//System.out.println("Se recuperan las configuraciones por refresco");
				logger.debug("Se recuperan las configuraciones por refresco");
				
				
				hmConfiguracion.clear();
				

				// Se realiza una llamada a BBDD para recuperar 
				// todas las filas de la tabla "Configuracion"
				List<ConfiguracionModelDTO> lConfModel = configuracionMapper.getConfiguracion();
				
				for( ConfiguracionModelDTO s : lConfModel ){
					ConfiguracionDTO configuracion = new ConfiguracionDTO();
					
					configuracion.setParametro(s.getParametro());
					configuracion.setValor(s.getValor());
					
					hmConfiguracion.put(configuracion.getParametro(), configuracion);
				}
				
			}else{
				//System.out.println("YA ESTA RELLENA LA CONFIGURACION");
				logger.debug("YA ESTA RELLENA LA CONFIGURACION");
				
			}
		}

		
		return hmConfiguracion;
	}
	
	@Transactional(readOnly=true)
	public HashMap<String, NacionalidadDTO> getNacionalidades(String refrescar)throws Exception{
		
		if (hmNacionalidad == null){
			
			//System.out.println("Se recuperan las configuracion");
			//logger.debug("Se recupera la nacionalidad");
			hmNacionalidad = new HashMap<String, NacionalidadDTO>();
		
			// Se realiza una llamada a BBDD para recuperar 
			// todas las filas de la tabla "Nacionalidad"
			List<NacionalidadModelDTO> lNacionalidadModel = nacionalidadMapper.getNacionalidad();
			for( NacionalidadModelDTO s : lNacionalidadModel ){
				NacionalidadDTO nacionalidad = new NacionalidadDTO();
				
				nacionalidad.setCodigoNacionalidad(s.getCodNacionalidad());
				nacionalidad.setDescripcion(s.getDescripcion());
				
				hmNacionalidad.put(nacionalidad.getCodigoNacionalidad(), nacionalidad);
			}
								
		}else{
			if (refrescar.equalsIgnoreCase(Ctes.SI)){
				
				//System.out.println("Se recuperan las configuraciones por refresco");
				logger.debug("Se recuperan las nacionalidades por refresco");
				
				
				hmNacionalidad.clear();
				
				// Se realiza una llamada a BBDD para recuperar 
				// todas las filas de la tabla "Nacionalidad"
				List<NacionalidadModelDTO> lNacionalidadModel = nacionalidadMapper.getNacionalidad();
				for( NacionalidadModelDTO s : lNacionalidadModel ){
					NacionalidadDTO nacionalidad = new NacionalidadDTO();
					
					nacionalidad.setCodigoNacionalidad(s.getCodNacionalidad());
					nacionalidad.setDescripcion(s.getDescripcion());
					
					hmNacionalidad.put(nacionalidad.getCodigoNacionalidad(), nacionalidad);
				}					

				
			}else{
				//System.out.println("YA ESTA RELLENA LA NACIONALIDAD");
				logger.debug("YA ESTA RELLENA LA NACIONALIDAD");
				
			}
		}
		
		return hmNacionalidad;
	}
	
	@Transactional(readOnly=true)
	public HashMap<String, OperacionDTO> getOperaciones(String refrescar)throws Exception{
		
		if (hmOperaciones == null){
			
			//System.out.println("Se recuperan las operaciones");
			logger.debug("Se recuperan las operaciones");
			hmOperaciones = new HashMap<String, OperacionDTO>();
			
			// Se realiza una llamada a BBDD para recuperar 
			// todas las filas de la tabla "Operacion"
			List<OperacionModelDTO> lOperacion = operacionMapper.getOperacion();
			for( OperacionModelDTO s : lOperacion ){
				
				OperacionDTO operacion = new OperacionDTO();
				
				operacion.setCodigoOpera(s.getCodOpera());
				operacion.setDescripcion(s.getDescripcion());
				
				hmOperaciones.put(operacion.getCodigoOpera(), operacion);
			}	
			
			
		}else{
			if (refrescar.equalsIgnoreCase(Ctes.SI)){
				
				//System.out.println("Se recuperan las operaciones por refresco");
				logger.debug("Se recuperan las operaciones por refresco");
				
				
				hmOperaciones.clear();

				// Se realiza una llamada a BBDD para recuperar 
				// todas las filas de la tabla "Operacion"
				List<OperacionModelDTO> lOperacion = operacionMapper.getOperacion();
				for( OperacionModelDTO s : lOperacion ){
					
					OperacionDTO operacion = new OperacionDTO();
					
					operacion.setCodigoOpera(s.getCodOpera());
					operacion.setDescripcion(s.getDescripcion());
					
					hmOperaciones.put(operacion.getCodigoOpera(), operacion);
				}	
				
			}else{
				//System.out.println("YA ESTA RELLENA LA LISTA DE OPERACIONES");
				logger.debug("YA ESTA RELLENA LA LISTA DE OPERACIONES");
				
			}
		}

		
		return hmOperaciones;
	}

	@Transactional(readOnly=true)
	public HashMap<String, RoleDTO> getRoles(String refrescar)throws Exception{
		

		
		if (hmRoles == null){
			
			//System.out.println("Se recuperan los roles");
			logger.debug("Se recuperan los roles");
			
			hmRoles = new HashMap<String, RoleDTO>();
			
			
			// Se realiza una llamada a BBDD para recuperar 
			// todas las filas de la tabla "Role"
			List<RoleModelDTO> lRole = roleMapper.getRole();
			for( RoleModelDTO s : lRole ){
				
				RoleDTO role = new RoleDTO();
				
				role.setCodigoRole(s.getCodRole());
				role.setDescripcion(s.getDescripcion());
				
				hmRoles.put(role.getCodigoRole(), role);
				
			}			
			
	
			
		}else{
			//System.out.println("YA ESTA RELLENA LA LISTA DE ROLES");
			if (refrescar.equalsIgnoreCase(Ctes.SI)){
				
				hmRoles.clear();
				
				logger.debug("Se recuperan los roles por refresco");
				
				// Se realiza una llamada a BBDD para recuperar 
				// todas las filas de la tabla "Role"				
				List<RoleModelDTO> lRole = roleMapper.getRole();
				for( RoleModelDTO s : lRole ){
					
					RoleDTO role = new RoleDTO();
					
					role.setCodigoRole(s.getCodRole());
					role.setDescripcion(s.getDescripcion());
					
					hmRoles.put(role.getCodigoRole(), role);

				}		
				
			}else{
				//System.out.println("YA ESTA RELLENA LA LISTA DE OPERACIONES");
				logger.debug("YA ESTA RELLENA LA LISTA DE ROLES");
				
			}
		}

		
		return hmRoles;
	}
	
	@Transactional(readOnly=true)
	public HashMap<String, EquipoRealDTO> getEquiposReales(String refrescar)throws Exception{
		

		if (hmEquiposReales == null){
			
			//System.out.println("Se recuperan los equipos reales");
			logger.debug("Se recuperan los equipos reales");
			
			hmEquiposReales = new HashMap<String, EquipoRealDTO>();
			
			// Se realiza una llamada a BBDD para recuperar 
			// todas las filas de la tabla "EquipoReal"
			List<EquipoRealModelDTO> lEquipoReal = equipoRealMapper.getActivosEquipoReal();
			for( EquipoRealModelDTO s : lEquipoReal ){
				
				EquipoRealDTO equipoRealDTO = new EquipoRealDTO();
				
				equipoRealDTO.setCodigoEquipoReal(s.getCodEquipoReal());
				equipoRealDTO.setNombre(s.getNombre());
				equipoRealDTO.setSiglas(s.getSiglas());
				equipoRealDTO.setPatrocinio(s.getPatrocinio());
				equipoRealDTO.setActivo("S");
				
				hmEquiposReales.put(equipoRealDTO.getCodigoEquipoReal(), equipoRealDTO);

			}
			
			
		}else{
			if (refrescar.equalsIgnoreCase(Ctes.SI)){
				
				hmEquiposReales.clear();
				
				//System.out.println("Se recuperan los equipos reales por refresco");
				logger.debug("Se recuperan los equipos reales por refresco");
				
				// Se realiza una llamada a BBDD para recuperar 
				// todas las filas de la tabla "EquipoReal"
				List<EquipoRealModelDTO> lEquipoReal = equipoRealMapper.getActivosEquipoReal();
				for( EquipoRealModelDTO s : lEquipoReal ){
					
					EquipoRealDTO equipoRealDTO = new EquipoRealDTO();
					
					equipoRealDTO.setCodigoEquipoReal(s.getCodEquipoReal());
					equipoRealDTO.setNombre(s.getNombre());
					equipoRealDTO.setSiglas(s.getSiglas());
					equipoRealDTO.setPatrocinio(s.getPatrocinio());
					equipoRealDTO.setActivo("S");
					
					hmEquiposReales.put(equipoRealDTO.getCodigoEquipoReal(), equipoRealDTO);

				}
				
			}else{
				//System.out.println("YA ESTA RELLENA LA LISTA DE EQUIPOS REALES");
				logger.debug("YA ESTA RELLENA LA LISTA DE EQUIPOS REALES");
				
			}
		}

		
		return hmEquiposReales;		
	}
	
	@Transactional(readOnly=true)
	public HashMap<String, JugadorRealDTO> getJugadoresReales(String refrescar)throws Exception{
		
		if (hmJugadoresReales == null){
			
			
			// Se realiza una llamada a BBDD para recuperar 
			// todas las filas de la tabla "JugadorRealDTO"
			
			//System.out.println("Se recuperan los jugadores reales");
			logger.debug("Se recuperan los jugadores reales");
			
			hmJugadoresReales = new HashMap<String, JugadorRealDTO>();
			

			List<JugadorRealModelDTO> lJugadorReal = jugadorRealMapper.getJugadores();
			for( JugadorRealModelDTO s : lJugadorReal ){
				
				
				JugadorRealDTO jugadorRealDTO = new JugadorRealDTO();
				
				jugadorRealDTO.setCodigoJugador(s.getCodJugador());
				jugadorRealDTO.setActivo(s.getActivo());
				jugadorRealDTO.setNombre(s.getNombre());
				jugadorRealDTO.setApellidos(s.getApellidos());
				jugadorRealDTO.setPuesto(s.getPuesto());
				jugadorRealDTO.setAltura(s.getAltura());
				jugadorRealDTO.setPeso(s.getPeso());
				//jugadorRealDTO.setFecalta(s.getFecalta());
				
				DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
				jugadorRealDTO.setFecaltaMostrar(df.format(s.getFecalta()));
				
				//System.out.println("iiiivan1:" + jugadorRealDTO.getFecalta());
				//DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
				//System.out.println("iiiivan2:" + df.format(s.getFecalta()));
				jugadorRealDTO.setPrecio(s.getPrecio());
				
				
				jugadorRealDTO.setCodigoNacionalidad(s.getCodNacionalidad());
				jugadorRealDTO.setCodigoEquipoReal(s.getCodEquipoReal());
				
				hmJugadoresReales.put(jugadorRealDTO.getCodigoJugador(), jugadorRealDTO);
				

			}			
			
		}else{
			if (refrescar.equalsIgnoreCase(Ctes.SI)){
				
				hmJugadoresReales.clear();
				
				//System.out.println("Se recuperan los jugadores reales por refresco");
				logger.debug("Se recuperan los jugadores reales por refresco");
				
				
				// Se realiza una llamada a BBDD para recuperar 
				// todas las filas de la tabla "JugadorRealDTO"
				List<JugadorRealModelDTO> lJugadorReal = jugadorRealMapper.getJugadores();
				for( JugadorRealModelDTO s : lJugadorReal ){
					
					
					JugadorRealDTO jugadorRealDTO = new JugadorRealDTO();
					
					jugadorRealDTO.setCodigoJugador(s.getCodJugador());
					jugadorRealDTO.setActivo(s.getActivo());
					jugadorRealDTO.setNombre(s.getNombre());
					jugadorRealDTO.setApellidos(s.getApellidos());
					jugadorRealDTO.setPuesto(s.getPuesto());
					jugadorRealDTO.setAltura(s.getAltura());
					jugadorRealDTO.setPeso(s.getPeso());
					//jugadorRealDTO.setFecalta(s.getFecalta());
					
					//System.out.println("iiiivan3:" + jugadorRealDTO.getFecalta());
					//DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
					//System.out.println("iiiivan4:" + df.format(s.getFecalta()));
					DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
					jugadorRealDTO.setFecaltaMostrar(df.format(s.getFecalta()));
					
					jugadorRealDTO.setPrecio(s.getPrecio());
					
					
					jugadorRealDTO.setCodigoNacionalidad(s.getCodNacionalidad());
					jugadorRealDTO.setCodigoEquipoReal(s.getCodEquipoReal());
					
					hmJugadoresReales.put(jugadorRealDTO.getCodigoJugador(), jugadorRealDTO);

				}	

				
			}else{
				//System.out.println("YA ESTA RELLENA LA LISTA DE JUGADORES REALES");
				logger.debug("YA ESTA RELLENA LA LISTA DE JUGADORES REALES");
			}
		}
		
		return hmJugadoresReales;
	}
	



	
	
	
}
