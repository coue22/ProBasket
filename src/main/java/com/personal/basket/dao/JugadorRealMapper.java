package com.personal.basket.dao;

import java.util.List;

import com.personal.basket.model.ConfiguracionModelDTO;
import com.personal.basket.model.JugadorRealModelDTO;




public interface JugadorRealMapper {

	List<JugadorRealModelDTO> getJugadores();
	void insertJugador(JugadorRealModelDTO jrmDTO);
	void cambiarEstadoJugador(JugadorRealModelDTO jrmDTO);
	
} 