package com.personal.basket.servicios;


import java.util.ArrayList;

import com.personal.basket.dtos.EquipoRealDTO;
import com.personal.basket.dtos.JugadorDTO;


public interface IServiciosAdministracion {
	
	// Inicializacion
	public void InicializarConfiguracion()throws Exception;
	
	
	// Equipo catalogo
	public ArrayList<EquipoRealDTO> mostrarCatalogoEquipos()throws Exception;
	
	// Eliminacion de un equipo no se contempla. Eso no debe ocurrir nunca porque puede joder 
	// el aplicativo
	
	public boolean modificarEquipoCatalogo(EquipoRealDTO eDTO)throws Exception;
	public boolean insertarEquipoCatalogo(EquipoRealDTO eDTO)throws Exception;
	
	
	// Jugadores Catalogo
	public ArrayList<JugadorDTO> mostrarCatalogoJugadores()throws Exception;
	// Consiste en poner la propiedad "activo" con un 0. --> significa que no tiene equipo.
	public boolean eliminarJugadorCatalogo(JugadorDTO jDTO)throws Exception;
	public boolean insertarJugadorCatalogo(JugadorDTO jDTO)throws Exception;

	
}
