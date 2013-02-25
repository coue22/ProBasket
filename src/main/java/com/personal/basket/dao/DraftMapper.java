package com.personal.basket.dao;

import java.util.List;

import com.personal.basket.model.DraftModelDTO;


public interface DraftMapper {

	
	//**************************************************************************************************
	//**************************************************************************************************
	//**************************************************************************************************
	// 				LOS SIGUIENTES 3 METODOS ESTAN FUERTEMENTE RELACIONADOS							  //
	
	// Se guarda la elección de un draft
	// Estos son los jugadores guardados y que posteriormente se pintan en los combos llamando
	// al metodo de abajo "getDraftEquipo"
	void guardarEleccionDraft(DraftModelDTO dmDTO);
	
	// Se elimina la eleccion de un draft para un equipo y una liga.
	// Cada vez que se llama a "guardarEleccionDraft" se borran todas las selecciones de los combos.
	void eliminarEleccionDraft(DraftModelDTO dmDTO);
	
	// Devuelve todos los jugadores de un equipo asociado a una liga y un equipo.
	// Esto se utiliza en los combos que se pintan para elegir
	List<DraftModelDTO> getDraftEquipo(DraftModelDTO dmDTO);
	
	//**************************************************************************************************
	//**************************************************************************************************
	//**************************************************************************************************
	// Se elimina de una liga a todas las elecciones de un jugador (Usada al elegir ya un jugador en draft)
	// para todos los equipos
	void eliminarJugadorDelDraft(DraftModelDTO dmDTO);
	
	// Se elimina de una liga a todas las elecciones de un jugador para un equipo(Usada al elegir ya un jugador en draft)
	void eliminarJugadorEquipoDelDraft(DraftModelDTO dmDTO);
	

} 