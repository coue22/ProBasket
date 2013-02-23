package com.personal.basket.servicios;


import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.transaction.annotation.Transactional;

import com.personal.basket.dtos.JugadorRealDTO;
import com.personal.basket.dtos.LigaDTO;
import com.personal.basket.dtos.UsuarioDTO;


public interface IServiciosGestionLigas {

	/**
	 * Crea una liga un usuario y se incluye en ella.
	 * Inserta en la tabla de ligas un registro y actualiza el usuario con el codigo de liga.
	 * BBDD implicadas
	 * 				*SECUENCUIAS
	 * 					SEQ_LIGA: Para obtener un consecutivo que nos valdrá como codigo de liga.
	 * 				*TABLAS
	 * 					LIGA: Se realiza un "insert" con la en la tabla de la liga.
	 * 					USUARIO : Se realiza un "update" para asignar a ese usuario a una liga. 
	 * @param lDTO - Datos de la liga para realizar el insert.
	 * @param loginUsuario - Usuario el cual crea la liga y se asigna a ella.
	 * @param HashMap<String, JugadorRealDTO> hmJugadoresReales - Los jugadores a insertar en la liga.
	 * @return devuelve false - si no puede crear una liga e inscribirse de forma correcta.
	 * 			true - Si se ha creado la liga
	 * @throws Exception
	 */
	@Transactional
	public boolean crearLiga (LigaDTO lDTO, String loginUsuario, HashMap<String, JugadorRealDTO> hmJugadoresReales)throws Exception;
	
	/**
	 * Devuelve todas las ligas con el filtro pasado por parametro.
	 * * BBDD implicadas
	 * 				*TABLAS
	 * 					LIGA: Se realiza un "select con like" en la tabla liga para obtener
	 * 						  los nombres de las ligas que coinciden con el filtro.
	 * @param nbLigaABuscar
	 * @return ArrayList<LigaDTO> - Lista de ligas
	 * @throws Exception
	 */
	@Transactional(readOnly=true)
	public ArrayList<LigaDTO> obtenerLigas (String nbLigaABuscar)throws Exception;
	
	/**
	 * Actualiza el usuario con el codigo de liga al que se ha asociado.
	 * @param lDTO
	 * @return 
	 * @throws Exception
	 */
	@Transactional
	public UsuarioDTO inscribirLiga (LigaDTO lDTO, String loginUsuario, String nbEquipo)throws Exception;


	
}
