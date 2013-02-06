package com.personal.basket.servicios;


import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

import com.personal.basket.dtos.*;

public interface ICatalogoServicios {
 
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //
	// 								TABS INICIALES										   //
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //
	
	
	
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //
	// 								SERVICIOS DE MENU									   //
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //	
	public ArrayList<MenuDTO> mostrarMenu(String idTipoMenu)throws Exception;
	/*
	public ArrayList<MenuDTO> mostrarMenu()throws Exception;
	public ArrayList<MenuDTO> mostrarMenuDraft()throws Exception;
	public ArrayList<MenuDTO> mostrarMenuNoLiga()throws Exception;
	public ArrayList<MenuDTO> mostrarMenuAdministrador()throws Exception;
	*/
	


	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //
	// 								OPERACIONES DE EQUIPO								   //
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //
	//public EquipoRealDTO mostrarEquipo()throws Exception;

	
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //
	// 								SERVICIOS DE LOGIN 									   //
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //	
	public boolean existeUsuario(String log)throws Exception;
	
	public UsuarioDTO registrarse(String log, String pass, 
							   String email, String anoNac, 
							   String sex, String codigoEcono)throws Exception;
	public UsuarioDTO loggearse(String log, String pass)throws Exception;
	//public DetalleServicioDTO recuperarDetalleServicio(String identificador) throws Exception;


	
	
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //
	// 								SERVICIOS DE ECONOMIA								   //
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //
	public EconomiaDTO setEconomia()throws Exception;
	
}
