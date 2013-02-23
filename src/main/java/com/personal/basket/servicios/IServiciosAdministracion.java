package com.personal.basket.servicios;


import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import com.personal.basket.dtos.ConfiguracionDTO;
import com.personal.basket.dtos.EquipoRealDTO;
import com.personal.basket.dtos.JugadorRealDTO;
import com.personal.basket.dtos.LigaDTO;
import com.personal.basket.dtos.NacionalidadDTO;
import com.personal.basket.dtos.OperacionDTO;
import com.personal.basket.dtos.RoleDTO;



public interface IServiciosAdministracion {
	

	// **************************************************************************
	// **************************************************************************
	// Configuracion
	// **************************************************************************
	// **************************************************************************
	@Transactional
	public boolean modificarConfiguracion(ConfiguracionDTO cDTO)throws Exception;
	@Transactional
	public boolean insertarConfiguracion(ConfiguracionDTO cDTO)throws Exception;
	
	// **************************************************************************
	// **************************************************************************
	// Nacionalidad
	// **************************************************************************
	// **************************************************************************	
	@Transactional
	public boolean eliminarNacionalidad(NacionalidadDTO nDTO)throws Exception;
	@Transactional
	public boolean insertarNacionalidad(NacionalidadDTO nDTO)throws Exception;
	
	// **************************************************************************
	// **************************************************************************
	// Operacion
	// **************************************************************************
	// **************************************************************************
	@Transactional
	public boolean modificarOperacion(OperacionDTO oDTO)throws Exception;
	@Transactional
	public boolean insertarOperacion(OperacionDTO oDTO)throws Exception;
	
	
	// **************************************************************************
	// **************************************************************************
	// Role
	// **************************************************************************
	// **************************************************************************
	@Transactional
	public void modificarRole(RoleDTO rDTO)throws Exception;
	@Transactional
	public void insertarRole(RoleDTO rDTO)throws Exception;
	
		
	
	// **************************************************************************
	// **************************************************************************
	// Equipo Real
	// **************************************************************************
	// **************************************************************************	
	// Eliminacion de un equipo no se contempla. Eso no debe ocurrir nunca porque puede joder 
	// el aplicativo
	@Transactional
	public boolean modificarEquipoCatalogo(EquipoRealDTO eDTO)throws Exception;
	@Transactional
	public boolean insertarEquipoCatalogo(EquipoRealDTO eDTO)throws Exception;
	@Transactional(readOnly=true)
	public boolean existeEquipoReal(String codigoEquipoReal)throws Exception;
	
	
	// **************************************************************************
	// **************************************************************************
	// Jugador
	// **************************************************************************
	// **************************************************************************
	@Transactional
	public ArrayList<JugadorRealDTO> insertarJugadoresPorFichero(String nbFichero, String codEquipoReal, boolean bInsertar)throws Exception;

	// BBDD Afectadas: JUGADOR y LIGAJUGADOR
	@Transactional
	public void cambiarEstadoJugador(String codigoJugador, String estado)throws Exception;

	// Inserta un jugador desde la administracion.
	@Transactional
	public void insertarJugador(JugadorRealDTO jDTO)throws Exception;
	

	
	
}
