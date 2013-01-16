package com.personal.basket.servicios;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;
import com.personal.basket.dtos.LigaDTO;


@Service("servicioGestionLigas")
public class ServiciosGestionLigas implements IServiciosGestionLigas{


	public boolean crearLiga (LigaDTO lDTO)throws Exception{

		// Aqui debe crear una liga y vincular el usuario a la liga.
		// 1 Usuario en 1 Liga.
		
		// En la tabla se debe guardar: nombre, password, DatosPersonalesDTO.login
		// y con casi toda seguridad mas datos.
		
		//return true;
		
		
		// En el caso de devolver false. Debería tener una propiedad en "LigaDTO" para ver el error
		lDTO.setError("No se ha podido crear la liga." + " Lo que devuelva de BBDD");
		return false;
	}

	public  ArrayList<LigaDTO> obtenerLigas ()throws Exception{
		
		ArrayList<LigaDTO> lLiga = new ArrayList<LigaDTO> ();
		
		
		for (int i = 0; i< 100 ; i++){
			LigaDTO lDTO = new LigaDTO();
			
			lDTO.setCodigo(""+i);
			lDTO.setNombre("Liga " + i);
			
			// Sera pulbica si no tiene contraseña, es decir, esta vacia.
			if (i % 4 == 0)
				lDTO.setLigaPublica(true);
			else
				lDTO.setLigaPublica(false);
			
			lLiga.add(lDTO);
			
			// Aunque la contraseña
			
		}
		
		
		
		// Devolver de BBDD todas las ligas.
		return lLiga;
	}
	
	
}
