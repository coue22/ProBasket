package com.personal.basket.servicios;


import java.util.ArrayList;

import com.personal.basket.dtos.*;

public interface IServiciosJugadores {

	public ArrayList<JugadorDTO> mostrarJugadores()throws Exception;
	public boolean eliminarJugador(JugadorDTO jDTO)throws Exception;


	
}
