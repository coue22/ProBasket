package com.personal.basket.dao;

import java.util.List;

import com.personal.basket.model.DetEconomiaModelDTO;




public interface DetEconomiaMapper {

	// Se obtiene la secuencia para inicializar una economia.
	String getSecuenciaDetEconomiaHSQL();
	
	// Se inicializa la economia de un usuario.
	void setDetEconomia(DetEconomiaModelDTO detEco);

	
} 