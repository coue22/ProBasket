package com.personal.basket.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.basket.ctes.Constantes;
import com.personal.basket.dao.CalendarioMapper;
import com.personal.basket.dao.PersonaMapper;
import com.personal.basket.dtos.JugadorDTO;
import com.personal.basket.model.Calendario;

@Service("servicioJugadores")
public class ServiciosJugadores implements IServiciosJugadores{


    //@Resource  
    //@Autowired
    //PersonaMapper personaMapper;
	
/*
	@Autowired
    CalendarioMapper calendarioMapper;


	public CalendarioMapper getCalendarioMapper() {
		return calendarioMapper;
	}

	public void setCalendarioMapper(CalendarioMapper calendarioMapper) {
		this.calendarioMapper = calendarioMapper;
	}
*/


	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //
	// 								OPERACIONES DE JUGADOR								   //
	// ----------------------------------------------------------------------------------- //
	// ----------------------------------------------------------------------------------- //
	public ArrayList<JugadorDTO> mostrarJugadores()throws Exception{
		
		ArrayList<JugadorDTO> lJugadores = new ArrayList<JugadorDTO>();
		
		JugadorDTO j1 = new JugadorDTO();
		j1.setCodigo("00001");
		j1.setNombre("Luis");
		j1.setApellido("Avila");
		j1.setPuesto(Constantes.BASE);
		j1.setAltura("192");
		j1.setPeso("90");
		j1.setNacionalidad(Constantes.ESP);

		JugadorDTO j2 = new JugadorDTO();
		j2.setCodigo("00002");
		j2.setNombre("Charles");
		j2.setApellido("Fish");
		j2.setPuesto(Constantes.BASE);
		j2.setAltura("184");
		j2.setPeso("82");
		j2.setNacionalidad(Constantes.USA);
		
		JugadorDTO j3 = new JugadorDTO();
		j3.setCodigo("00003");
		j3.setNombre("Javier");
		j3.setApellido("Diaz de Miguel");
		j3.setPuesto(Constantes.ALERO);
		j3.setAltura("201");
		j3.setPeso("99");
		j3.setNacionalidad(Constantes.ESP);
		
		
/*
		//personaMapper.getAll();
		List<Calendario> lCal = calendarioMapper.getAll();
		for (int i = 0 ; i<lCal.size(); i++){
			Calendario cal = lCal.get(i);
			
			System.out.println(cal.getNombre());
		}
*/
		
		
		lJugadores.add(j1);
		lJugadores.add(j2);
		lJugadores.add(j3);
		return lJugadores;
	}
	


	public boolean eliminarJugador(JugadorDTO jDTO)throws Exception{
		//jDTO.getCodigo()
		return true;
		
	}

	

	
	
}
