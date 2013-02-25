package com.personal.basket.dao;

import java.util.List;

import com.personal.basket.model.EconomiaModelDTO;
import com.personal.basket.model.MenuModelDTO;


public interface EconomiaMapper {

	// Se obtiene la secuencia para inicializar una economia.
	String getSecuenciaEconomiaHSQL();
	
	// Se inicializa la economia de un usuario.
	void InicializarEconomia(EconomiaModelDTO ecomDTO);
	
	// Devuelve una economia.
	EconomiaModelDTO getEconomia( String codEcono);
	
	// Realiza restas a la economia.
	void restarEconomia(EconomiaModelDTO ecomDTO);
	

	
} 