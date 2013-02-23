package com.personal.basket.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.personal.basket.model.ConfiguracionModelDTO;


public interface ConfiguracionMapper {

	List<ConfiguracionModelDTO> getConfiguracion();
	void insertarConfiguracion(ConfiguracionModelDTO configuracionDTO);
	void modificarConfiguracion(ConfiguracionModelDTO configuracionDTO);
	
} 