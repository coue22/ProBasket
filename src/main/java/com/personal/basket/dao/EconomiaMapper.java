package com.personal.basket.dao;

import java.util.List;

import com.personal.basket.model.EconomiaModelDTO;
import com.personal.basket.model.MenuModelDTO;


public interface EconomiaMapper {

	// Se obtiene la secuencia para inicializar una economia.
	String getSecuenciaEconomiaHSQL();
	
	// Se inicializa la economia de un usuario.
	void InicializarEconomia(EconomiaModelDTO eco);
	
	EconomiaModelDTO getEconomia( String codEcono);

	
} 