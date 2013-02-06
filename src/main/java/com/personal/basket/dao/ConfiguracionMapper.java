package com.personal.basket.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.personal.basket.model.ConfiguracionDTO;


public interface ConfiguracionMapper {

	List<ConfiguracionDTO> getAll();
	void insert(ConfiguracionDTO configuracionDTO);
	
} 