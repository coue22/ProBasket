package com.personal.basket.dao;

import com.personal.basket.model.DraftTurnoModelDTO;


public interface DraftTurnoMapper {

	// Se guarda el turno del draft de un equipo para una liga.
	void guardarTurnoDraft(DraftTurnoModelDTO dtmDTO);
	
	// Se eliminan de todas las ligas, todos los turnos de eleccion. 
	// Esto se hace para que cuando se cambie la variable de administracion de configuracion draft de N a S
	// no falle.
	void eliminarTurnosDraft();
	
	// A partir de una liga y un turno, se obtiene el equipo al cual le toca seleccionar.
	DraftTurnoModelDTO getEquipoTurnoDraft(DraftTurnoModelDTO dtmDTO);
	
} 

