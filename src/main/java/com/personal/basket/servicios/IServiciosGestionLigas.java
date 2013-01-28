package com.personal.basket.servicios;


import java.util.ArrayList;

import com.personal.basket.dtos.LigaDTO;


public interface IServiciosGestionLigas {

	/**
	 * Crea una liga un usuario y se incluye en ella.
	 * Inserta en la tabla de ligas un registro y actualiza el usuario con el codigo de liga.
	 * @param lDTO
	 * @return devuelve "0000000000" - si no puede crear e inscribirse.
	 * 			y el codigo de la liga si tiene exito
	 * @throws Exception
	 */
	public String crearLiga (LigaDTO lDTO)throws Exception;
	
	/**
	 * Devuelve todas las ligas con el filtro pasado por parametro.
	 * @param nbLigaABuscar
	 * @return
	 * @throws Exception
	 */
	public ArrayList<LigaDTO> obtenerLigas (String nbLigaABuscar)throws Exception;
	
	/**
	 * Actualiza el usuario con el codigo de liga al que se ha asociado.
	 * @param lDTO
	 * @return devuelve "0000000000" - si no puede inscribirse.
	 * 			y el codigo de la liga si tiene exito
	 * @throws Exception
	 */
	public String inscribirLiga (LigaDTO lDTO)throws Exception;


	
}
