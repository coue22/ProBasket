package com.personal.basket.dao;

import java.util.List;

import com.personal.basket.model.EquipoModelDTO;
import com.personal.basket.model.LigaModelDTO;




public interface EquipoMapper {

	
	// Comprueba si existe un equipo con ese nombre
	int existeEquipo(String nombre);

	// Se obtiene la secuencia para inicializar un equipo.
	String getSecuenciaEquipoHSQL();

	// Se crea un equipo
	void crearEquipo(EquipoModelDTO eqmDTO);

	// Devuelve un equipo.
	EquipoModelDTO getEquipo(String codEquipo);
	
	// Numero de equipos de un liga.
	List<EquipoModelDTO> getEquiposLiga(String codLiga);

/*	
	// Se buscan las ligas por un nombre patron
	List<LigaModelDTO> buscarLigas(LigaModelDTO lDTO);
	
	// Si la liga es privada, entonces se debe acreditar que tiene permiso para inscribirse.
	int acreditacionEnLiga(LigaModelDTO lig);
*/	
	
	
} 