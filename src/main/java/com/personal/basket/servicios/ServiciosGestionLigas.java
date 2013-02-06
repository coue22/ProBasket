package com.personal.basket.servicios;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;

import com.personal.basket.ctes.Ctes;
import com.personal.basket.dtos.LigaDTO;
import com.personal.basket.dtos.SalidaInscripcionDTO;


@Service("servicioGestionLigas")
public class ServiciosGestionLigas implements IServiciosGestionLigas{


	public String crearLiga (LigaDTO lDTO)throws Exception{

		// Aqui debe crear una liga y vincular el usuario a la liga.
		// 1 Usuario en 1 Liga.
		
		// En la tabla se debe guardar: nombre, password, DatosPersonalesDTO.login
		// y con casi toda seguridad mas datos.
		
		//return true;
		
		
		// En el caso de devolver false. Debería tener una propiedad en "LigaDTO" para ver el error
		//lDTO.setError("No se ha podido crear la liga." + " Lo que devuelva de BBDD");
		//return Ctes.NO_ASIGNADO_LIGA;
		return "0000000001";
	}

	public  ArrayList<LigaDTO> obtenerLigas (String nbLigaABuscar)throws Exception{
		
		ArrayList<LigaDTO> lLiga = new ArrayList<LigaDTO> ();
		
		// AKI DEBE RECUPERAR LAS LIGAS QUE CUMPLAN CON EL FILTRO DE ENTRADA.
		// RECUPERA LAS LIGAS QUE COMIENCEN POR "nbLigaABuscar"

		for (int i = 0; i< 100 ; i++){
			LigaDTO lDTO = new LigaDTO();
			
			lDTO.setCodigoLiga(""+i);
			lDTO.setNombre("Liga " + i);

			// Sera pulbica si no tiene contraseña, es decir, esta vacia.
			if (i % 4 == 0)
				lDTO.setLigaPublica(true);
			else
				lDTO.setLigaPublica(false);
			
			lLiga.add(lDTO);
			
		}

		// Devolver de BBDD todas las ligas.
		return lLiga;
	}
	
	public SalidaInscripcionDTO inscribirLiga (LigaDTO lDTO)throws Exception{
		
		// Se debe actualizar la tabla del usuario para indicar la liga a la que pertence.
		
		// 1.- Se comprueba que se puede añadir a la liga
		//		Si es publica --> no hay mayor problema
		//		Si es privada --> debe comprobar la contraseña.
		
		// 2.- Se crea un equipo con todo lo que lleve.
		
		// 3.- Si se puede añadir debe actualizar el usuario con el codigo de la liga.
		//		y con el codigo de equipo
		
		// 3.- Si todo ha ido bien devuelve true y false e.o.c.
		//return Ctes.NO_ASIGNADO_LIGA;
		//return "0000000001";
		
		
		SalidaInscripcionDTO salDTO = new SalidaInscripcionDTO();
		
		salDTO.setCodigoLiga("0000000001");
		salDTO.setCodigoEquipo("0000000006");
		salDTO.setNombreLiga("la liguilla");
		salDTO.setNombreEquipo("los ventolines");
		
		return salDTO;
	}
	
}
