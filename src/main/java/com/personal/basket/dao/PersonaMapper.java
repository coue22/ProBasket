package com.personal.basket.dao;

import java.util.List;

import com.personal.basket.model.Persona;



public interface PersonaMapper {

	List<Persona> getAll();

	Persona findByPK(Long idPersona);

	void update(Persona persona);

	void remove(Persona persona);

	void insert(Persona persona);

}