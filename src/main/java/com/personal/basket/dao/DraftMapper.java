package com.personal.basket.dao;

import java.util.List;

import com.personal.basket.model.DraftModelDTO;


public interface DraftMapper {

	// Se guarda la elección de un draft
	void guardarEleccionDraft(DraftModelDTO dmDTO);
	
	// Se elimina la eleccion de un draft para un equipo y una liga.
	void eliminarEleccionDraft(DraftModelDTO dmDTO);
	
	// Devuelve todos los jugadores de un equipo asociado a una liga y un equipo.
	List<DraftModelDTO> getDraftEquipo(DraftModelDTO dmDTO);
	
    
    	
} 