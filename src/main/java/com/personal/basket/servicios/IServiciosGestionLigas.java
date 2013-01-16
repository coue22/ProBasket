package com.personal.basket.servicios;


import java.util.ArrayList;

import com.personal.basket.dtos.LigaDTO;


public interface IServiciosGestionLigas {

	public boolean crearLiga (LigaDTO lDTO)throws Exception;
	
	public ArrayList<LigaDTO> obtenerLigas ()throws Exception;
	
	//public boolean inscribirLiga (LigaDTO lDTO)throws Exception;


	
}
