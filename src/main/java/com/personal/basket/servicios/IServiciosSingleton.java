package com.personal.basket.servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.personal.basket.dtos.ConfiguracionDTO;
import com.personal.basket.dtos.EquipoRealDTO;
import com.personal.basket.dtos.JugadorRealDTO;
import com.personal.basket.dtos.MenuDTO;
import com.personal.basket.dtos.NacionalidadDTO;
import com.personal.basket.dtos.OperacionDTO;
import com.personal.basket.dtos.RoleDTO;

/**
 * Servicios que se utilizan como singleton.
 * 		Esto es debido a que hay tablas que son estáticas para todos los usuarios
 * 		y por lo tanto se cargan una vez y las demás peticiones utilizan ya los
 * 		valores que contienen.
 *  
 * @author iballesteros
 *
 */


public interface IServiciosSingleton {
	
	/**
	 * El método devuelve todos los valores de configuracion disponibles para la aplicación.
	 * Si es la primera vez que entra un usuario:
	 * 		Va a BBDD y carga los valores
	 * Si ya hubo cargo cualquier usuario la lista
	 * 		Si refrescar == N --> Utiliza la lista ya rellena.
	 * 		Si refrescar == S --> Vacia la lista y vuelve a BBDD a por los valores.
	 * 
	 * @param refrescar - 	S: Vuelve a cargar los datos de la BBDD y los carga sobre un array
	 * 						N: No carga los valores otra vez de la BBDD y usa el array existente con los valores que hay.
	 * @return - Array con todas las operaciones.
	 * @throws Exception
	 */
	@Transactional(readOnly=true)
	public HashMap<String, ConfiguracionDTO> getConfiguracion(String refrescar)throws Exception;
	
	/**
	 * El método devuelve todos los valores de las nacionalidades disponibles para la aplicación.
	 * Si es la primera vez que entra un usuario:
	 * 		Va a BBDD y carga los valores
	 * Si ya hubo cargo cualquier usuario la lista
	 * 		Si refrescar == N --> Utiliza la lista ya rellena.
	 * 		Si refrescar == S --> Vacia la lista y vuelve a BBDD a por los valores.
	 * 
	 * @param refrescar - 	S: Vuelve a cargar los datos de la BBDD y los carga sobre un array
	 * 						N: No carga los valores otra vez de la BBDD y usa el array existente con los valores que hay.
	 * @return - Array con todas las operaciones.
	 * @throws Exception
	 */	
	@Transactional(readOnly=true)
	public HashMap<String, NacionalidadDTO> getNacionalidades(String refrescar)throws Exception;
	
	
	
	/**
	 * El método devuelve todas las operaciones disponibles para la aplicación.
	 * Si es la primera vez que entra un usuario:
	 * 		Va a BBDD y carga los valores
	 * Si ya hubo cargo cualquier usuario la lista
	 * 		Si refrescar == N --> Utiliza la lista ya rellena.
	 * 		Si refrescar == S --> Vacia la lista y vuelve a BBDD a por los valores.
	 * 
	 * @param refrescar - 	S: Vuelve a cargar los datos de la BBDD y los carga sobre un array
	 * 						N: No carga los valores otra vez de la BBDD y usa el array existente con los valores que hay.
	 * @return - Array con todas las operaciones.
	 * @throws Exception
	 */
	@Transactional(readOnly=true)
	public HashMap<String, OperacionDTO> getOperaciones(String refrescar)throws Exception;
	
	/**
	 * El método devuelve todos los roles disponibles para la aplicación.
	 * Si es la primera vez que entra un usuario:
	 * 		Va a BBDD y carga los valores
	 * Si ya hubo cargo cualquier usuario la lista
	 * 		Si refrescar == N --> Utiliza la lista ya rellena.
	 * 		Si refrescar == S --> Vacia la lista y vuelve a BBDD a por los valores.
	 * 
	 * @param refrescar - 	S: Vuelve a cargar los datos de la BBDD y los carga sobre un array
	 * 						N: No carga los valores otra vez de la BBDD y usa el array existente con los valores que hay.
	 * @return - Array con todas las operaciones.
	 * @throws Exception
	 */
	@Transactional(readOnly=true)
	public HashMap<String, RoleDTO> getRoles(String refrescar)throws Exception;
	
	
	
	/**
	 * El método devuelve todos los equipos reales disponibles para la aplicación.
	 * Si es la primera vez que entra un usuario:
	 * 		Va a BBDD y carga los valores
	 * Si ya hubo cargo cualquier usuario la lista
	 * 		Si refrescar == N --> Utiliza la lista ya rellena.
	 * 		Si refrescar == S --> Vacia la lista y vuelve a BBDD a por los valores.
	 * 
	 * @param refrescar - 	S: Vuelve a cargar los datos de la BBDD y los carga sobre un array
	 * 						N: No carga los valores otra vez de la BBDD y usa el array existente con los valores que hay.
	 * @return - Array con todas las operaciones.
	 * @throws Exception
	 */
	@Transactional(readOnly=true)
	public HashMap<String, EquipoRealDTO> getEquiposReales(String refrescar)throws Exception;

	
	/**
	 * El método devuelve todos los jugadores reales disponibles para la aplicación.
	 * Si es la primera vez que entra un usuario:
	 * 		Va a BBDD y carga los valores
	 * Si ya hubo cargo cualquier usuario la lista
	 * 		Si refrescar == N --> Utiliza la lista ya rellena.
	 * 		Si refrescar == S --> Vacia la lista y vuelve a BBDD a por los valores.
	 * 
	 * @param refrescar - 	S: Vuelve a cargar los datos de la BBDD y los carga sobre un array
	 * 						N: No carga los valores otra vez de la BBDD y usa el array existente con los valores que hay.
	 * @return - Array con todas las operaciones.
	 * @throws Exception
	 */
	@Transactional(readOnly=true)
	public HashMap<String, JugadorRealDTO> getJugadoresReales(String refrescar)throws Exception;

	
	
	
	
	
	
}

