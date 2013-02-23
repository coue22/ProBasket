package com.personal.basket.dao;

import java.util.List;

import com.personal.basket.model.EquipoRealModelDTO;



public interface EquipoRealMapper {

	List<EquipoRealModelDTO> getActivosEquipoReal();
	List<EquipoRealModelDTO> getAllEquipoReal();// NO PROBADA PORQUE NO SE USA POR EL MOMENTO

	// Se comprueba si por el codigo de equipo real existe o no.
	//int existeEquipoReal(EquipoRealModelDTO eqDTO);
	int existeEquipoReal(String codEqReal);

	
} 