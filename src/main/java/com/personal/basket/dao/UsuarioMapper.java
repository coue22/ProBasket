package com.personal.basket.dao;

import java.util.List;
import com.personal.basket.model.MenuModelDTO;
import com.personal.basket.model.UsuarioModelDTO;


public interface UsuarioMapper {

	int existeUsuario(String login);
	
	void registrar(UsuarioModelDTO usr);
	
	UsuarioModelDTO logarse(UsuarioModelDTO usr);
	
	// Actualiza el codigo de liga de un usuario(crear liga,...)
	void actualizarUsuarioLiga(UsuarioModelDTO usr);
	
	// Devuelve los datos del usuario (crear liga,...)
	UsuarioModelDTO getDatosUsuario(String login);

} 