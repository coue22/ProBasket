package com.personal.basket.dao;

import java.util.List;

import com.personal.basket.model.RoleModelDTO;



public interface RoleMapper {

	// Se obtienen todos los roles
	List<RoleModelDTO> getRole();
	
	// Inserta un nuevo Role
	void insertarRole(RoleModelDTO rmDTO);
	
	// Modifica un Role existente
	void modificarRole(RoleModelDTO rmDTO);
} 