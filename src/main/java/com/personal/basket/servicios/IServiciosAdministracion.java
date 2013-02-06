package com.personal.basket.servicios;


import java.util.ArrayList;

import com.personal.basket.dtos.ConfiguracionDTO;
import com.personal.basket.dtos.EquipoRealDTO;
import com.personal.basket.dtos.JugadorRealDTO;
import com.personal.basket.dtos.NacionalidadDTO;
import com.personal.basket.dtos.OperacionDTO;
import com.personal.basket.dtos.RoleDTO;


public interface IServiciosAdministracion {
	

	// **************************************************************************
	// **************************************************************************
	// Configuracion
	// **************************************************************************
	// **************************************************************************
	public boolean modificarConfiguracion(ConfiguracionDTO cDTO)throws Exception;
	public boolean insertarConfiguracion(ConfiguracionDTO cDTO)throws Exception;
	
	// **************************************************************************
	// **************************************************************************
	// Nacionalidad
	// **************************************************************************
	// **************************************************************************	
	public boolean eliminarNacionalidad(NacionalidadDTO nDTO)throws Exception;
	public boolean insertarNacionalidad(NacionalidadDTO nDTO)throws Exception;
	
	// **************************************************************************
	// **************************************************************************
	// Operacion
	// **************************************************************************
	// **************************************************************************
	public boolean modificarOperacion(OperacionDTO oDTO)throws Exception;
	public boolean insertarOperacion(OperacionDTO oDTO)throws Exception;
	
	
	// **************************************************************************
	// **************************************************************************
	// Role
	// **************************************************************************
	// **************************************************************************
	public boolean modificarRole(RoleDTO rDTO)throws Exception;
	public boolean insertarRole(RoleDTO rDTO)throws Exception;
	
		
	
	// Eliminacion de un equipo no se contempla. Eso no debe ocurrir nunca porque puede joder 
	// el aplicativo
	public boolean modificarEquipoCatalogo(EquipoRealDTO eDTO)throws Exception;
	public boolean insertarEquipoCatalogo(EquipoRealDTO eDTO)throws Exception;
	
	
	// Jugadores Catalogo
	
	/*
	public boolean eliminarJugadorCatalogo(JugadorRealDTO jDTO)throws Exception;
	public boolean insertarJugadorCatalogo(JugadorRealDTO jDTO)throws Exception;
	*/

	
}
