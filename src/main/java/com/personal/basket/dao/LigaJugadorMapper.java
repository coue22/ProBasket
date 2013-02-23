package com.personal.basket.dao;

import java.util.List;

import com.personal.basket.model.LigaJugadorModelDTO;




public interface LigaJugadorMapper {


	// Se asignan jugador a una liga. 
	// Utilizada 
	//		crear un liga.
	//		insertar jugadores por fichero.
	//		insertar jugador por pantalla (no implementada todavia.).
	void asignarJugador(LigaJugadorModelDTO lig);
	
	// Se cambia el estado de un jugador en todas las ligas.
	void cambiarEstadoJugador(LigaJugadorModelDTO jrmDTO);

	// Recupera todos los jugadores de una liga que esten activos
	// y los ordena por apellido.
	//List<LigaJugadorModelDTO> getJugadoresActivos(String codLiga);
} 