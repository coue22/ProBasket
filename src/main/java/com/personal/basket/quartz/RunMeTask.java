package com.personal.basket.quartz;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.personal.basket.ctes.ConstantesSesion;
import com.personal.basket.ctes.Ctes;
import com.personal.basket.dtos.ConfiguracionDTO;
import com.personal.basket.servicios.ServiciosDraft;
import com.personal.basket.servicios.ServiciosSingleton;
import com.personal.basket.util.Util;

public class RunMeTask {
	

	
	@Autowired
	ServiciosDraft serviciosDraft;
	


	public ServiciosDraft getServiciosDraft() {
		return serviciosDraft;
	}
	public void setServiciosDraft(ServiciosDraft serviciosDraft) {
		this.serviciosDraft = serviciosDraft;
	}
	
	
	

	public void printMe() {
		
		
		Calendar calendario = new GregorianCalendar();
		
		int hora =calendario.get(Calendar.HOUR_OF_DAY);
		int minutos = calendario.get(Calendar.MINUTE);
		int segundos = calendario.get(Calendar.SECOND);
		
		System.out.println("Spring 3 + Quartz 1.8.6 --> " + hora + ":" + minutos + ":" + segundos);
		
		//System.out.println("Spring 3 + Quartz 1.8.6 " );
		
		
		try {
			serviciosDraft.setDraftJugadorAEquipo();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
			System.out.println("Aqui se deberia hacer algo." );
			// REGISTRAR EN UNA BBDD O ALGO SIMILAR
		}
		
		

		
		
		
		
	}
	
	
	
}