package com.personal.basket.dao;

import java.util.List;

import com.personal.basket.model.LigaModelDTO;




public interface LigaMapper {

	// Comprueba si existe una liga.
	int existeLiga(String nombre);
	
	// Se obtiene la secuencia para inicializar una liga.
	String getSecuenciaLigaHSQL();
	
	// Se crea una liga
	void crearLiga(LigaModelDTO lig);
	
	// Se buscan las ligas por un nombre patron
	List<LigaModelDTO> buscarLigas(LigaModelDTO lDTO);
	
	// Si la liga es privada, entonces se debe acreditar que tiene permiso para inscribirse.
	int acreditacionEnLiga(LigaModelDTO lig);
	
	// Se devuelven todas las ligas.
	List<LigaModelDTO> getAllLigas();
	
	// Se devuelven la liga asociada a su codigo
	LigaModelDTO getLiga(String codLiga);
	
} 