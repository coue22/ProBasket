package com.personal.basket.dao;

import java.util.List;
import com.personal.basket.model.NacionalidadModelDTO;

public interface NacionalidadMapper {

	List<NacionalidadModelDTO> getNacionalidad();

	
	// Inserta una nacionalidad
	void insertarNacionalidad(NacionalidadModelDTO nmDTO);
	
	// elimina una 
	void eliminarNacionalidad(NacionalidadModelDTO nmDTO);	
	
} 