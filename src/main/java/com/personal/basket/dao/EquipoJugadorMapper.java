package com.personal.basket.dao;

import java.util.List;

import com.personal.basket.model.EquipoJugadorModelDTO;




public interface EquipoJugadorMapper {

	void asignarJugador(EquipoJugadorModelDTO ejmDTO);
 
/*
	// Se asignan jugador a una liga. 
	// Utilizada 
	//		crear un liga.
	//		insertar jugadores por fichero.
	//		insertar jugador por pantalla (no implementada todavia.).
	void asignarJugador(LigaJugadorModelDTO lig);
	
	// Se cambia el estado de un jugador en todas las ligas.
	void cambiarEstadoJugador(LigaJugadorModelDTO jrmDTO);

	// Recupera todos los jugadores de una liga que esten activos y no tengan equipo asignado.
	// Esto se usa en el draft cuando se asigna el jugador a un equipo para una liga.
	List<LigaJugadorModelDTO> getJugadoresActivosSinEquipo(String codLiga);
	
	// Se recuperan los campos de un jugador.
	LigaJugadorModelDTO getLigaJugador (LigaJugadorModelDTO jrmDTO);
	
	// Se asigna a un equipo para un jugador de una liga.
	void asignarEquipoAJugador(LigaJugadorModelDTO jrmDTO);	
*/	
	
	
} 